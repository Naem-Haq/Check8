package com.check.ui;

import com.check.data.DataHandler;
import com.check.login.User;
import com.check.characters.Character;
import com.check.characters.CharacterCreator;
import com.check.characters.Controls;

import java.util.Map;
import java.util.function.Function;

public class UI {

    private static final String YELLOW = "\u001B[33m";
    private static final String RESET = "\u001B[0m";
    private static final String GREEN = "\u001B[32m";
    private static final String BLUE = "\u001B[34m";
    private static final String RED = "\u001B[31m";
    private static final String CYAN = "\u001B[36m";
    private static final String ORANGE = "\u001B[33m";

    public void displayMessage(String message) {
        System.out.println(message);
    }

    public void displayOptionScreen() {
        System.out.println("========================================");
        System.out.println("              Main Menu                 ");
        System.out.println("========================================");
        System.out.println("1. Sign-up");
        System.out.println("2. Login");
        System.out.println("3. How to play");
        System.out.println("4. Play");
        System.out.println("5. Character options");
        System.out.println("6. Stats");
        System.out.println("7. LeaderBoard");
        System.out.println("8. Resume Game");
        System.out.println("9. Exit");
        System.out.println("========================================");
        System.out.println("Enter your choice:");
    }

    public void displayCharacterDetails(){
        for (Function<Boolean, Character> charMaker : CharacterCreator.getHashMap().values()) {
            Character character = charMaker.apply(true);
            System.out.println("===========================================");
            System.out.println(YELLOW + character.getName() + RESET + ":\n" + character.getDescription());
            System.out.println(YELLOW + "Weapon:\n" + RESET + character.getWeapon().getDescription());
            System.out.println();
            System.out.println(YELLOW + "Inventory:\n" + RESET);
            character.getInventory().getItems().forEach((item, quantity) -> 
                System.out.println(item + ": " + quantity.size())
            );
            System.out.println("===========================================");
        }
    }

    public void displayPlayerSignUp(){
        System.out.println(YELLOW + "Player sign up \nusername: " + RESET);
    }

    public void displaySignUpSuccess(String userName){
        System.out.println("Signup and login successful! welcome " + userName);
    }

    public void displayPassword(){
        System.out.println(YELLOW + "password: " + RESET);
    }

    public void displayLoginRequest() {
        System.out.println(YELLOW + "Player Login \nusername: " + RESET);
    }

    public void loginSuccess(String userName){
        System.out.println("Login successful! Welcome " + userName);
    }

    public void displayCompleteRound(int roundNum){
        System.out.println("Round " + roundNum + " complete");
    }

    public void displayHealth(Character player1, Character player2) {    
        System.out.println(BLUE + player1.getName() + RESET + " Health: " + GREEN + player1.getHealthBar().getHealth() + RESET);
        System.out.println(RED + player2.getName() + RESET + " Health: " + GREEN + player2.getHealthBar().getHealth() + RESET);
    }

    public void displayMoveOptions(Character player) {
        System.out.println("Player "+ player.getName() +" choose ur move\n");
        System.out.println("===========================================");
        System.out.println("Press "+ Controls.getAttack() +" to attack");
        System.out.println("Press "+ Controls.getDodge() +" to dodge");
        System.out.println("Press "+ Controls.getUseHealPotion() +" to use heal potion");
        System.out.println("Press "+ Controls.getUseDamagePotion() +" to use damage potion");
        System.out.println("Press 9 to Save and exit game");
        System.out.println("===========================================\n");
    }

    public void chooseCharacterText(int playerNumber){
        System.out.println(CYAN + "Choose Your character - Player " + playerNumber + RESET);
    }

    public void choosePlayerOrCPU(){
        System.out.println(GREEN + "Player (0)" + RESET + " or " + ORANGE + "CPU (1)" + RESET);
    }

    public void invalidChoice(){
        System.out.println(RED + "====== ! Invalid choice ! ======" + RESET);
    }

    public void showCharacterOptions(){
        System.out.println(CYAN + "Choose character" + RESET);
        System.out.println("1. Knight");
        System.out.println("2. Mage");
        System.out.println("3. Brute");
        System.out.println("4. Archer");
        System.out.println("==========================================="); 
    }

    public void dislayChosenCharacter(Character character) {
        System.out.println("Character chosen: " + character.getName() + "\n\n");
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
    }

    public void displayUserStats(User currentUser) {
        System.out.println(currentUser.getName() + "'s stats:");
        Map<String, Integer> userStats = currentUser.loadStats();
        System.out.println("Games Played: " + userStats.get("gamesPlayed"));
        System.out.println(GREEN + "Games Won: " + userStats.get("wins") + RESET);
        System.out.println(RED + "Games Lost: " + userStats.get("losses") + RESET);
        System.out.println(ORANGE + "Games Tie: " + userStats.get("ties") + RESET);
        System.out.println();
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
    }

}
