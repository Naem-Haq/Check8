package com.check.ui;

import com.check.login.Login;
import com.check.login.User;

import java.util.Scanner;

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
                System.out.println("Play feature not implemented yet.");
                displayOptionScreen();
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
