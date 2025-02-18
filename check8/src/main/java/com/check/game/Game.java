package com.check.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.check.characters.Character;
import com.check.data.GameCache;
import com.check.characters.CharacterCreator;
import com.check.characters.Controls;
import com.check.data.GameHistory;
import com.check.login.User;

public class Game {
    private static Logger logger = LoggerFactory.getLogger(Game.class.getName());
    private static Game instance;
    private List<Character> characters;
    private GameState state;
    private int rounds;
    private Character player;
    private Character cpu;
    private Controls playerControls;
    private GameHistory gameHistory;
    private Scanner scanner;
    private User currentUser;

    public Game() {
        this.characters = new ArrayList<>();
        this.state = new Ready();
        this.rounds = 0;
        this.gameHistory = new GameHistory();
        this.scanner = new Scanner(System.in);
        logger.info("New game initialized");
    }

    public static Game getInstance() {
        if (instance == null) {
            instance = new Game();
        }
        return instance;
    }

    public void start() {
        while (true) {
            state.handleRequest(this);
        }
    }

    public Scanner getScanner() {
        return scanner;
    }

    public void displayGameState() {
        System.out.println("\n=== Round " + rounds + " ===");
        System.out.println(player.getName() + " HP: " + player.getHealthBar().getHealth() + 
                          " (Dodges: " + player.getRemainingDodges() + ")");
        System.out.println(cpu.getName() + " HP: " + cpu.getHealthBar().getHealth() + 
                          " (Dodges: " + cpu.getRemainingDodges() + ")");
        System.out.println("Available potions: " + player.getInventory().getItems());
    }

    public void handlePlayerTurn() {
        System.out.println("\nYour turn! Choose action:");
        System.out.println("1. Attack");
        System.out.println("2. Dodge");
        System.out.println("3. Use Heal Potion");
        System.out.println("4. Use Damage Potion");
        System.out.println("5. Undo last move");

        try {
            // Reset attackable status at start of player turn
            player.setAttackable(true);
            
            String input = scanner.nextLine().trim();
            int choice;
            
            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                choice = switch(input.toLowerCase()) {
                    case "attack" -> 1;
                    case "dodge" -> 2;
                    case "heal" -> 3;
                    case "damage" -> 4;
                    case "undo" -> 5;
                    default -> {
                        System.out.println("Invalid input. Please enter 1-5 or the action name.");
                        yield -1;
                    }
                };
            }

            if (choice < 1 || choice > 5) {
                System.out.println("Please choose a number between 1 and 5");
                return;
            }

            if (choice == 5) {
                gameHistory.undo(this);
                return;
            }

            playerControls.pressButton(choice - 1, cpu);
            
        } catch (Exception e) {
            System.out.println("Invalid input. Please try again.");
            scanner.nextLine();
        }
    }

    public void handleCPUTurn() {
        System.out.println("\nCPU's turn!");
        // Only reset CPU's attackable status, not the player's
        cpu.setAttackable(true);
        
        CPU.generateMove(cpu, player);
    }

    public void displayGameOver() {
        System.out.println("\n=== Game Over ===");
        String winner = player.getHealthBar().getHealth() <= 0 ? cpu.getName() : player.getName();
        System.out.println("Winner: " + winner);
        System.out.println("Total rounds: " + rounds);
    }

    public void setState(GameState state) {
        this.state = state;
        logger.debug("Game state changed to: {}", state.getClass().getSimpleName());
    }

    public void request() {
        state.handleRequest(this);
    }

    public GameState getCurrentState() {
        return state;
    }

    public void initializeGame(String playerCharType, String cpuCharType) throws CharacterCreator.InvalidCharacterException {
        player = CharacterCreator.createCharacter(playerCharType, false);
        cpu = CharacterCreator.createCharacter(cpuCharType, true);
        playerControls = new Controls(player);
        characters.add(player);
        characters.add(cpu);
        logger.info("Game initialized with {} vs {}", playerCharType, cpuCharType);
    }

    public Character getPlayer() {
        return player;
    }

    public Character getCPU() {
        return cpu;
    }

    public GameCache saveToCache() {
        return new GameCache(characters, state, rounds);
    }

    public void restoreFromCache(GameCache cache) {
        this.characters = new ArrayList<>(cache.getCharacters());
        this.state = cache.getState();
        this.rounds = cache.getRounds();
        logger.info("Game restored from cache");
    }

    public void incrementRounds() {
        rounds++;
        // Reset attackable status only at the end of a complete round
        if (!player.canDodge() && !cpu.canDodge()) {
            player.setAttackable(true);
            cpu.setAttackable(true);
        }
    }

    public int getRounds() {
        return rounds;
    }

    public GameState getState() {
        return this.state;
    }

    public List<Character> getCharacters() {
        return this.characters;
    }

    public void setCacheState(List<Character> characters, GameState state, int rounds) {
        this.characters = new ArrayList<>(characters);
        this.state = state;
        this.rounds = rounds;
        logger.debug("Game state manually set with {} characters, state: {}, rounds: {}", 
            characters.size(), state.getClass().getSimpleName(), rounds);
    }

    public void setCurrentUser(User user) {
        this.currentUser = user;
        if (user != null) {
            logger.info("User {} logged in", user.getName());
        } else {
            logger.info("User logged out");
        }
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public GameHistory getGameHistory() {
        return gameHistory;
    }

    public Controls getPlayerControls() {
        return playerControls;
    }
}