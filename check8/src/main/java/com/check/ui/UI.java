package com.check.ui;

import com.check.login.Login;
import com.check.login.User;
import com.check.characters.Character;
import com.check.characters.CharacterCreator;
import com.check.characters.Controls;
import com.check.characters.CharacterCreator.InvalidCharacterException;
import com.check.game.Game;
import com.check.game.GameState;
import java.util.Scanner;
import java.util.ResourceBundle.Control;
import com.check.game.CPU;

public class UI {

    Scanner playerInput = new Scanner(System.in);

    public void displayWelcomeScreen() {
        System.out.println("Welcome to factions! this is a turn based fighting game!\nPress any key to begin");
        playerInput.nextLine();
        displayOptionScreen();
    }

    public void displayOptionScreen() {
        System.out.println("1. Sign-up \n2. Login \n3. How to play \n4. Play\n5. Stats\n6. Character options\n7. LeaderBoard");

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
        User player = Login.logIn(userName, password);
        if (player != null) {
            System.out.println("Signup and login successful! welcome " + userName);
        }
        displayOptionScreen();
    }

    public void displayLogIn() {
        System.out.println("Player Login \nusername: ");
        String userName = playerInput.nextLine();
        System.out.println("password: ");
        String password = playerInput.nextLine();
        User player = Login.logIn(userName, password);
        if (player != null) {
            System.out.println("login successful! welcome " + userName);
        }
        displayOptionScreen();
    }
    public void playGame(Character player1, Character player2) {
        System.out.println("game starting");
        System.out.println(player1.getName()+ player2.getName());
        try {
            Game game = new Game(player1, player2);
            System.out.println(game.request());
            while (game.getState().getType() == GameState.Type.IN_PROGRESS) {
                int player1Move = displayChooseMove(game.getPlayer1());
                int player2Move = displayChooseMove(game.getPlayer2());
                
                game.newRound(player1Move, player2Move);
                System.out.println(game.request());
            }
            System.out.println(game.request());

        } catch (InvalidCharacterException e) {
            System.out.println("Bad Character choiuce soz: " + e.getMessage());
        }

    }

    public int displayChooseMove(Character player) {
        if (!player.isCPU()){
            System.out.println("Player choose ur move");
            System.out.println("Press "+ Controls.getAttack() +" to attack");
            System.out.println("Press "+ Controls.getDodge() +" to dodge");
            System.out.println("Press "+ Controls.getUseHealPotion() +" to use heal potion");
            System.out.println("Press "+ Controls.getUseDamagePotion() +" to use damage potion");
            int playerMove = playerInput.nextInt();
            return playerMove;
        }
        else{
            int CPUMove = CPU.generateMove(player);
            return CPUMove;
        }
    }

    public void chooseCharacter() {
        System.out.println("choos ur character");
        Character chosenCharacter = displayCharacterChoices();
        System.out.println("choose enemy");
        Character enemyCharacter = displayCharacterChoices();
        playGame(chosenCharacter,enemyCharacter);

    }

    public Character displayCharacterChoices() {
        Character c = null;
        System.out.println("CPU (1) or Player (0)");
        int cpu_int = playerInput.nextInt();
        if (cpu_int != 0 && cpu_int != 1) {
            System.out.println("Invalid choice");
            displayCharacterChoices();
        }
        boolean player;
        if (cpu_int == 0){
            player = false;
        }
        else{
            player = true;
        }
        System.out.println("Choose character");
        System.out.println("1. Knight");
        System.out.println("2. Mage");
        System.out.println("3. Brute");
        System.out.println("4. Archer");
        playerInput.nextLine();

        try{
            int characterChoice = playerInput.nextInt();
            switch (characterChoice){
                case 1:
                System.out.println("Knight chosen");
                    c = CharacterCreator.createCharacter("Knight", player);
                    break;
                case 2:
                    c = CharacterCreator.createCharacter("Mage", player);
                    break;
                case 3:
                    c = CharacterCreator.createCharacter("Brute", player);
                    break;
                case 4:
                    c = CharacterCreator.createCharacter("Archer", player);
                    break;
                default:
                    throw new InvalidCharacterException("Invalid character choice");
            }
        }
        catch(InvalidCharacterException e){
            System.out.println("Invalid character choice");
            displayCharacterChoices();
        }
        System.out.println("Character chosen: " + c.getName());
        return c;
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
        System.out.println("\nPress any key to return to the main menu...");
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
                System.out.println("Stats feature not implemented yet.");
                displayOptionScreen();
                break;
            case 6:
                System.out.println("Character options feature not implemented yet.");
                displayOptionScreen();
                break;
            case 7:

                System.out.println("Leaderboard feature not implemented yet.");
                displayOptionScreen();
                break;
            default:
                System.out.println("Invalid option. Please enter a valid number.");
                displayOptionScreen();
        }
    }

    public static void main(String[] args) {
        UI ui = new UI();
        ui.displayWelcomeScreen();
        ui.displayOptionScreen();
    }


}
