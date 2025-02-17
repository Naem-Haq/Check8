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

public class Game {
    private static Logger logger = LoggerFactory.getLogger(Game.class.getName());
    private List<Character> characters;
    private GameState state;
    private int rounds;
    private Character player;
    private Character cpu;
    private Controls playerControls;
    private GameHistory gameHistory;
    private Scanner scanner;

    public Game() {
        this.characters = new ArrayList<>();
        this.state = new Ready();
        this.rounds = 0;
        this.gameHistory = new GameHistory();
        this.scanner = new Scanner(System.in);
        logger.info("New game initialized");
    }

    public void start() {
        try {
            System.out.println("Choose your character (archer/mage/brute/knight):");
            String playerChoice = scanner.nextLine().toLowerCase();
            initializeGame(playerChoice, "knight"); // CPU always uses knight for now
            
            while (state instanceof InProgress) {
                displayGameState();
                handlePlayerTurn();
                if (!(state instanceof InProgress)) break;
                
                handleCPUTurn();
                if (!(state instanceof InProgress)) break;
                
                rounds++;
                gameHistory.save(this);
            }
            
            displayGameOver();
            
        } catch (CharacterCreator.InvalidCharacterException e) {
            logger.error("Error starting game: {}", e.getMessage());
        }
    }

    private void displayGameState() {
        System.out.println("\n=== Round " + rounds + " ===");
        System.out.println(player.getName() + " HP: " + player.getHealthBar().getHealth());
        System.out.println(cpu.getName() + " HP: " + cpu.getHealthBar().getHealth());
        System.out.println("Available potions: " + player.getInventory().getItems());
    }

    private void handlePlayerTurn() {
        System.out.println("\nYour turn! Choose action:");
        System.out.println("1. Attack");
        System.out.println("2. Dodge");
        System.out.println("3. Use Heal Potion");
        System.out.println("4. Use Damage Potion");
        System.out.println("5. Undo last move");

        try {
            String input = scanner.next();  // Changed from nextInt()
            int choice;
            
            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                // Handle text input like "attack"
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
            request();
            
        } catch (Exception e) {
            System.out.println("Invalid input. Please try again.");
            scanner.nextLine(); // Clear the scanner buffer
        }
    }

    private void handleCPUTurn() {
        System.out.println("\nCPU's turn!");
        CPU.generateMove(cpu);
        request();
    }

    private void displayGameOver() {
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
        setState(new InProgress());
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
}
