package com.check.game;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.check.characters.CharacterCreator;

public class InProgress implements GameState {
    private static Logger logger = LoggerFactory.getLogger(InProgress.class.getName());
    private boolean inCombat = false;

    @Override
    public void handleRequest(Game game) {
        if (!inCombat) {
            showGameMenu(game);
            return;
        }

        // Combat loop
        game.displayGameState();
        game.handlePlayerTurn();
        game.handleCPUTurn();
        game.incrementRounds();
        game.getGameHistory().save(game);

        // Check if game is over after each round
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
}

