package com.check.controllers;

import java.util.Scanner;

import com.check.game.CPU;
import com.check.game.Game;
import com.check.login.Login;
import com.check.login.User;
import com.check.ui.UI;
import com.check.characters.Character;
import com.check.characters.CharacterCreator;
import com.check.characters.CharacterCreator.InvalidCharacterException;
import com.check.game.GameState;

public class Controller {
    Scanner playerInput = new Scanner(System.in);
    User currentUser;
    private final UI ui;

    public Controller() {
        this.ui = new UI();
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public void playGame(Character player1, Character player2) {
        ui.displayMessage("Game is starting");
        Game game = new Game(player1, player2);
        ui.displayMessage(game.display());
        playerInput.nextLine();

        
        while (game.getState().getType() == GameState.Type.IN_PROGRESS) {
            int player1Move = move(game.getPlayer1());
            int player2Move = move(game.getPlayer2());

            game.newRound(player1Move, player2Move);
            ui.displayCompleteRound(game.getNumRounds());
            ui.displayHealth(game.getPlayer1(), game.getPlayer2());
        }
        ui.displayMessage(game.display());
    }

    public int move(Character player) {
        if (!player.isCPU()){
            ui.displayMoveOptions(player);
            return playerInput.nextInt();
        }else{
            return CPU.generateMove(player);
        }
    }

    public void chooseCharacter() {
        ui.chooseCharacterText(1);
        Character chosenCharacter = displayCharacterChoices(0);
        playerInput.nextLine();
        
        ui.chooseCharacterText(2);
        ui.choosePlayerOrCPU();
        int cpuInt = playerInput.nextInt();
        if (cpuInt != 0 && cpuInt != 1) {
            ui.invalidChoice();
            chooseCharacter();
        }
        playerInput.nextLine();
        Character enemyCharacter = displayCharacterChoices(cpuInt);
    
        playGame(chosenCharacter, enemyCharacter);
    }

        public Character displayCharacterChoices(int cpuInt) {
        Character character = null;
        boolean cpu;
        if (cpuInt == 0) {
            cpu = false;
        } else {
            cpu = true;
        }
    
        ui.showCharacterOptions();
    
        try {
            int characterChoice = playerInput.nextInt();
            switch (characterChoice) {
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
        } catch (InvalidCharacterException e) {
            ui.invalidChoice();
            displayCharacterChoices(cpuInt);
        }
        ui.dislayChosenCharacter(character);
        return character;
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
                ui.displayHowToPlay();
                pressEnter();
                loadMainMenu();
                break;
            case 4:
                chooseCharacter();
                break;
            case 5:
                throw new UnsupportedOperationException("Character options feature not implemented yet.");
            case 6:
                if (currentUser != null) {
                    ui.displayUserStats(currentUser);
                    pressEnter();
                    loadMainMenu();}
                else {ui.displayMessage("Please login first.");;
                    loadMainMenu();}
                break;
            case 7:
                ui.displayLeaderboard();
                pressEnter();
                loadMainMenu();
                break;
            default:
                ui.invalidChoice();
                loadMainMenu();
        }
    }

    public void loadMainMenu(){
        ui.displayOptionScreen();
        takeMenuInput();
    }

    public void takeMenuInput(){
        int option = -1;
        if (playerInput.hasNextInt()) {
            option = playerInput.nextInt();
            playerInput.nextLine();
        } else {
            playerInput.nextLine();
            ui.displayMessage("Enter a number");
        }
        returnUserOption(option);
    }

    public void displaySignUp() {
        ui.displayPlayerSignUp();
        String userName = playerInput.nextLine();
        ui.displayPassword();
        String password = playerInput.nextLine();
        Login.signUp(userName, password);
        currentUser = Login.logIn(userName, password);
        setCurrentUser(currentUser);
        if (currentUser != null) {
            ui.displaySignUpSuccess(userName);
        }
        loadMainMenu();
    }

    public void displayLogIn() {
        ui.displayLoginRequest();
        String userName = playerInput.nextLine();
        ui.displayPassword();
        String password = playerInput.nextLine();
        currentUser = Login.logIn(userName, password);
        if (currentUser != null) {
            ui.loginSuccess(userName);
            setCurrentUser(currentUser);
        }
        loadMainMenu();
    }

    public void displayWelcomeScreen() {
        ui.displayMessage("Welcome to factions! this is a turn based fighting game!\n");
        pressEnter();
        loadMainMenu();
    }

    public void pressEnter() {
        ui.displayMessage("Press enter to continue");
        playerInput.nextLine();
    }
}