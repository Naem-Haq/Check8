package com.check.game;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.check.characters.CharacterCreator;
import com.check.characters.CharacterCommand;
import com.check.characters.Controls;
import com.check.characters.DodgeCommand;

public class InProgress implements GameState {
    private static Logger logger = LoggerFactory.getLogger(InProgress.class.getName());
    private boolean inCombat = false;

    @Override
    public void handleRequest(Game game) {
        if (!inCombat) {
            showGameMenu(game);
            return;
        }

        game.displayGameState();

        // Collect actions first
        CharacterCommand playerAction = null;
        CharacterCommand cpuAction = null;

        // Get player's action
        try {
            System.out.println("\nYour turn! Choose action:");
            System.out.println("1. Attack");
            System.out.println("2. Dodge");
            System.out.println("3. Use Heal Potion");
            System.out.println("4. Use Damage Potion");
            System.out.println("5. Undo last move");

            String input = game.getScanner().nextLine().trim();
            int choice = parseChoice(input);
            if (choice == 5) {
                game.getGameHistory().undo(game);
                return;
            }
            playerAction = game.getPlayerControls().getCommand(choice - 1);
        } catch (Exception e) {
            System.out.println("Invalid input. Please try again.");
            return;
        }

        // Get CPU's action
        System.out.println("\nCPU's turn!");
        cpuAction = CPU.generateMove(game.getCPU(), game.getPlayer());

        // Execute both actions
        if (playerAction != null && cpuAction != null) {
            // First determine and set both actions' effects
            boolean playerDodging = playerAction.getClass().getSimpleName().equals("DodgeCommand");
            boolean cpuDodging = cpuAction.getClass().getSimpleName().equals("DodgeCommand");

            // Set initial dodge states
            if (playerDodging) {
                game.getPlayer().setAttackable(false);
            } else {
                game.getPlayer().setAttackable(true);
            }
            
            if (cpuDodging) {
                game.getCPU().setAttackable(false);
            } else {
                game.getCPU().setAttackable(true);
            }

            // Execute actions and show results
            System.out.println("\nPlayer's action:");
            playerAction.execute(game.getCPU());
            System.out.println(playerAction.executionText());

            System.out.println("\nCPU's action:");
            cpuAction.execute(game.getPlayer());
            System.out.println(cpuAction.executionText());

            // Reset attackable status at end of round if no dodges are active
            if (!game.getPlayer().canDodge() && !game.getCPU().canDodge()) {
                game.getPlayer().setAttackable(true);
                game.getCPU().setAttackable(true);
            }
        }

        game.incrementRounds();
        game.getGameHistory().save(game);

        // Check if game is over after both actions
        if (isGameOver(game)) {
            endCombat(game);
        }
    }

    private void showGameMenu(Game game) {
        System.out.println("\n=== Game Menu ===");
        System.out.println("1. Start Game");
        System.out.println("2. View Stats");
        System.out.println("3. View Leaderboard");
        System.out.println("4. Logout");
        
        String choice = game.getScanner().nextLine().trim();
        switch (choice) {
            case "1":
                startCombat(game);
                return;
            case "2":
                showStats(game);
                break;
            case "3":
                showLeaderboard(game);
                break;
            case "4":
                game.setCurrentUser(null);
                game.setState(new Ready());
                inCombat = false;
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    private void startCombat(Game game) {
        try {
            System.out.println("Choose your character (archer/mage/brute/knight):");
            String playerChoice = game.getScanner().nextLine().toLowerCase().trim();
            game.initializeGame(playerChoice, "knight");
            inCombat = true;
        } catch (CharacterCreator.InvalidCharacterException e) {
            logger.error("Error in combat: {}", e.getMessage());
            inCombat = false;
        }
    }

    private void endCombat(Game game) {
        inCombat = false;
        updateGameStats(game);
        game.displayGameOver();
    }

    private boolean isGameOver(Game game) {
        return game.getPlayer().getHealthBar().getHealth() <= 0 || 
               game.getCPU().getHealthBar().getHealth() <= 0;
    }

    private void showStats(Game game) {
        System.out.println("\n=== Player Stats ===");
        System.out.println("Player: " + game.getCurrentUser().getName());
        // Add more stats display
    }

    private void showLeaderboard(Game game) {
        System.out.println("\n=== Leaderboard ===");
        // Add leaderboard display
    }

    private void updateGameStats(Game game) {
        if (game.getPlayer().getHealthBar().getHealth() <= 0) {
            game.getCurrentUser().addLoss();
        } else {
            game.getCurrentUser().addWin();
        }
    }

    private int parseChoice(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return switch(input.toLowerCase()) {
                case "attack" -> 1;
                case "dodge" -> 2;
                case "heal" -> 3;
                case "damage" -> 4;
                case "undo" -> 5;
                default -> -1;
            };
        }
    }
}

