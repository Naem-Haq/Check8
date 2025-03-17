package com.check.controllers;

import java.util.Scanner;

import com.check.game.CPU;
import com.check.game.Game;
import com.check.game.GameOver;
import com.check.login.Login;
import com.check.login.User;
import com.check.ui.UI;
import com.check.characters.Character;
import com.check.characters.CharacterCreator;
import com.check.characters.CharacterCreator.InvalidCharacterException;
import com.check.characters.Controls;
import com.check.data.GameHistory;
import com.check.game.GameState;

public class Controller {
    Scanner playerInput = new Scanner(System.in);
    private GameHistory history = new GameHistory();
    private Game game;
    private User currentUser;
    private final UI ui;

    public Controller() {
        this.ui = new UI();
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public Game getGame() {
        if (game != null) {
            return game;
        }
        throw new NullPointerException("Game is null");
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public void playGame(Character player1, Character player2) {
        ui.displayMessage("Game is starting");
        Game game = new Game(player1, player2);
        setGame(game);
        ui.displayMessage(game.display());
        playerInput.nextLine();
        playLoop();
    }

    public void playLoop(){
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
        while (true){
            if (!player.isCPU()){
                ui.displayMoveOptions(player);
                int playerMove = playerInput.nextInt();
                if (playerMove == 9) {
                    ui.displayMessage("You have forfeited the game. Progress saved.");
                    history.save(getGame());
                    game.setState(new GameOver());
                    loadMainMenu();
                }

                // Guard for item availability and dodge count
                if (playerMove == Controls.getUseHealPotion() && !player.getInventory().hasHealPotion()) {
                    ui.displayMessage("You have run out of heal potions. Please choose a different move.");
                    continue;
                } else if (playerMove == Controls.getUseDamagePotion() && !player.getInventory().hasDamagePotion()) {
                    ui.displayMessage("You have run out of damage potions. Please choose a different move.");
                    continue;
                } else if (playerMove == Controls.getDodge() && !player.canDodge()) {
                    ui.displayMessage("You cannot dodge anymore. Please choose a different move.");
                    continue;
                }
                return playerMove;
            }else{
                return CPU.generateMove(player);
            }
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
            case 8:
                try {
                    history.undo(getGame());
                    ui.displayMessage("Game has been restored to previous state.");
                }catch (NullPointerException e) {
                    ui.displayMessage("No game to restore.");
                    loadMainMenu();
                }
                playLoop();
                loadMainMenu();
                break;
            case 9:
                ui.displayMessage("Goodbye!");
                System.exit(0);
                return;
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