package com.check.ui;

import com.check.data.DataHandler;
import com.check.login.Login;
import com.check.login.User;
import com.check.characters.Character;
import com.check.characters.CharacterCreator;
import com.check.characters.Controls;
import com.check.characters.CharacterCreator.InvalidCharacterException;
import com.check.game.Game;
import com.check.game.GameState;

import java.util.Map;
import java.util.Scanner;
import com.check.game.CPU;

public class UI {

    Scanner playerInput = new Scanner(System.in);
    User currentUser;

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public void displayWelcomeScreen() {
        System.out.println("Welcome to factions! this is a turn based fighting game!\nPress enter to begin");
        playerInput.nextLine();
        displayOptionScreen();
    }

    public void displayOptionScreen() {
        System.out.println("1. Sign-up \n2. Login \n3. How to play \n4. Play\n5. Character options\n6. Stats\n7. LeaderBoard");

        int option = -1;
        if (playerInput.hasNextInt()) {
            option = playerInput.nextInt();
            playerInput.nextLine();
        } else {
            playerInput.nextLine();
            System.out.println("Enter a number");
        }

       returnUserOption(option);
    }

    public void displaySignUp() {
        System.out.println("Player sign up \nusername: ");
        String userName = playerInput.nextLine();
        System.out.println("password: ");
        String password = playerInput.nextLine();
        Login.signUp(userName, password);
        currentUser = Login.logIn(userName, password);
        setCurrentUser(currentUser);
        if (currentUser != null) {
            System.out.println("Signup and login successful! welcome " + userName);
        }
        displayOptionScreen();
    }

    public void displayLogIn() {
        System.out.println("Player Login \nusername: ");
        String userName = playerInput.nextLine();
        System.out.println("password: ");
        String password = playerInput.nextLine();
        currentUser = Login.logIn(userName, password);
        if (currentUser != null) {
            System.out.println("login successful! welcome " + userName);
            setCurrentUser(currentUser);
        }
        displayOptionScreen();
    }
    public void playGame(Character player1, Character player2) {
        System.out.println("Game is starting");
        try {
            Game game = new Game(player1, player2);
            System.out.println(game.display());
            String any_input = playerInput.nextLine();

            while (game.getState().getType() == GameState.Type.IN_PROGRESS) {
                int player1Move = displayChooseMove(game.getPlayer1());
                int player2Move = displayChooseMove(game.getPlayer2());
                
                game.newRound(player1Move, player2Move);
                System.out.println(game.display());
                displayHealth(player1, player2);  // Display health after each round
            }
            System.out.println(game.display());

        } catch (InvalidCharacterException e) {
            System.out.println("Bad Character choiuce soz: " + e.getMessage());
        }

    }

    public void displayHealth(Character player1, Character player2) {
        System.out.println(player1.getName() + " Health: " + player1.getHealthBar().getHealth());
        System.out.println(player2.getName() + " Health: " + player2.getHealthBar().getHealth());
    }

    public int displayChooseMove(Character player) {
        if (!player.isCPU()){
            System.out.println("Player "+ player.getName() +" choose ur move\n");
            System.out.println("===========================================");
            System.out.println("Press "+ Controls.getAttack() +" to attack");
            System.out.println("Press "+ Controls.getDodge() +" to dodge");
            System.out.println("Press "+ Controls.getUseHealPotion() +" to use heal potion");
            System.out.println("Press "+ Controls.getUseDamagePotion() +" to use damage potion");
            System.out.println("===========================================\n");
            int playerMove = playerInput.nextInt();
            return playerMove;
        }
        else{
            int CPUMove = CPU.generateMove(player);
            return CPUMove;
        }
    }

    public void chooseCharacter() {
        System.out.println("Choose Your character - Player 1");
        Character chosenCharacter = displayCharacterChoices(0);
        playerInput.nextLine();

        System.out.println("Choose Your character - Player 2");
        System.out.println("Player (0) or CPU (1)");
        int cpu_int = playerInput.nextInt();
        if (cpu_int != 0 && cpu_int != 1) {
            System.out.println("====== ! Invalid choice ! ======");
            chooseCharacter();
        }
        playerInput.nextLine();
        Character enemyCharacter = displayCharacterChoices(cpu_int);

        playGame(chosenCharacter,enemyCharacter);

    }

    public Character displayCharacterChoices(int cpu_int) {
        Character character = null;
        boolean cpu;
        if (cpu_int == 0){
            cpu = false;
        }
        else{
            cpu = true;
        }
        
        System.out.println("Choose character");
        System.out.println("1. Knight");
        System.out.println("2. Mage");
        System.out.println("3. Brute");
        System.out.println("4. Archer");

        try{
            int characterChoice = playerInput.nextInt();
            switch (characterChoice){
                case 1:
                    character = CharacterCreator.createCharacter("Knight", cpu);
                    break;
                case 2:
                    character = CharacterCreator.createCharacter("Mage", cpu);
                    break;
                case 3:
                    character = CharacterCreator.createCharacter("Brute", cpu);
                    break;
                case 4:
                    character = CharacterCreator.createCharacter("Archer", cpu);
                    break;
                default:
                    throw new InvalidCharacterException("Invalid character choice");
            }
        }
        catch(InvalidCharacterException e){
            System.out.println("Invalid character choice");
            displayCharacterChoices(cpu_int);
        }
        System.out.println("Character chosen: " + character.getName() + "\n\n");
        return character;
    }



    public void displayHowToPlay() {
        System.out.println("=== How to Play Factions ===\n");
        System.out.println("Factions is a turn-based fighter game where strategy and luck determine the outcome of battles.");
        System.out.println("Your goal is to defeat your opponent by carefully choosing attacks, dodging, and using potions.");

        System.out.println("\n--- Game Flow ---");
        System.out.println("1. **Sign Up or Log In** – Create an account or log in to track your progress.");
        System.out.println("2. **Main Menu** – Choose from options like starting a new game, viewing stats, or selecting a character.");
        System.out.println("3. **Character Selection** – Pick a unique fighter with specialized weapons and abilities.");
        System.out.println("4. **Battle Begins** – Each turn, both you and the AI choose an action simultaneously.");
        System.out.println("5. **Actions Available:**");
        System.out.println("   - **Attack** – Deal damage to your opponent.");
        System.out.println("   - **Dodge** – Attempt to evade the enemy’s attack.");
        System.out.println("   - **Use a Potion** – Heal yourself or enhance your attack power.");
        System.out.println("6. **Victory or Defeat** – The battle continues until one fighter’s health reaches zero.");

        System.out.println("\n--- Additional Features ---");
        System.out.println("- **Track Stats:** View your past performance and battle history.");
        System.out.println("- **Change Characters:** Try different fighters and strategies.");
        System.out.println("- **Leaderboards:** Compare your skills with other players.");

        System.out.println("\nMaster your strategies, experiment with different characters, and become the strongest fighter in Factions!");
        System.out.println("Press enter to continue...");
        playerInput.nextLine();
        displayOptionScreen();
    }

    public void displayUserStats(User currentUser) {
        System.out.println(currentUser.getName() + "'s stats:");
        Map<String, Integer> userStats = currentUser.loadStats();
        System.out.println("Games Played: " + userStats.get("gamesPlayed"));
        System.out.println("Games Won: " + userStats.get("wins"));
        System.out.println("Games Lost: " + userStats.get("losses"));
        System.out.println("Games Tie: " + userStats.get("ties"));
        System.out.println();
        System.out.println("Press enter to continue...");
        playerInput.nextLine();
        displayOptionScreen();
    }

    public void displayLeaderboard() {
        Map<String, Map<String, Integer>> playerStats = DataHandler.loadUserStats();
        int leaderboardLimit = 3;

        System.out.println("🏆 Leaderboard (Top 3) 🏆");

        playerStats.entrySet().stream()
                .sorted((a, b) -> Integer.compare(b.getValue().get("wins"), a.getValue().get("wins"))) // Sort by wins (descending)
                .limit(leaderboardLimit)
                .forEachOrdered(entry -> {
                    String username = entry.getKey();
                    int wins = entry.getValue().get("wins");
                    System.out.println(username + " - Wins: " + wins);
                });
        System.out.println();
        System.out.println("Press enter to continue...");
        playerInput.nextLine();
        displayOptionScreen();
    }


    public void returnUserOption(int option) {
        switch (option) {
            case 1:
                displaySignUp();
                break;
            case 2:
                displayLogIn();
                break;
            case 3:
                displayHowToPlay();
                break;
            case 4:
                chooseCharacter();
                break;
            case 5:
                System.out.println("Character options feature not implemented yet.");
                displayOptionScreen();
                break;
            case 6:
                if (currentUser != null) {displayUserStats(currentUser);}
                else {System.out.println("Please login first.");
                displayOptionScreen();}
                break;
            case 7:
                displayLeaderboard();
                break;
            default:
                System.out.println("Invalid option. Please enter a valid number.");
                displayOptionScreen();
        }
    }

    public static void main(String[] args) {
        UI ui = new UI();
        ui.displayWelcomeScreen();

    }


}
