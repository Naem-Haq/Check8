package com.check.game;

import com.check.login.Login;
import com.check.login.User;

public class Ready implements GameState {
    @Override
    public void handleRequest(Game game) {
        System.out.println("\n=== Welcome to the Game ===");
        System.out.println("1. Login");
        System.out.println("2. Sign up");
        System.out.println("3. Exit");
        
        String choice = game.getScanner().nextLine();
        switch (choice) {
            case "1":
                handleLogin(game);
                break;
            case "2":
                handleSignup(game);
                break;
            case "3":
                System.out.println("Thanks for playing!");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    private void handleLogin(Game game) {
        System.out.println("Enter username:");
        String username = game.getScanner().nextLine();
        System.out.println("Enter password:");
        String password = game.getScanner().nextLine();
        
        User user = Login.logIn(username, password);
        if (user != null) {
            game.setCurrentUser(user);
            game.setState(new InProgress());
        }
    }

    private void handleSignup(Game game) {
        System.out.println("Enter new username:");
        String username = game.getScanner().nextLine();
        System.out.println("Enter new password:");
        String password = game.getScanner().nextLine();
        
        Login.signUp(username, password);
        User user = Login.logIn(username, password);
        if (user != null) {
            game.setCurrentUser(user);
            game.setState(new InProgress());
        }
    }
}