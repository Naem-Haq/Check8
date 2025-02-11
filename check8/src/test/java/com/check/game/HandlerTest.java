// package com.check.game;

// import static org.junit.Assert.*;
// import org.junit.Before;
// import org.junit.Test;
// import com.check.characters.Character;
// import com.check.characters.HealthBar;
// import com.check.characters.Archer;

// public class HandlerTest {
//     private CriticalHPHandler criticalHandler;
//     private LowHPHandler lowHandler;
//     private MidHPHandler midHandler;
//     private FullHPHandler fullHandler;
//     private Character testCharacter;
//     private HealthBar healthBar;

//     @Before
//     public void setUp() {
//         criticalHandler = new CriticalHPHandler();
//         lowHandler = new LowHPHandler();
//         midHandler = new MidHPHandler();
//         fullHandler = new FullHPHandler();
        
//         // Set up chain
//         criticalHandler.setNextHandler(lowHandler);
//         lowHandler.setNextHandler(midHandler);
//         midHandler.setNextHandler(fullHandler);
        
//         healthBar = new HealthBar();
//         testCharacter = new Archer("Test", healthBar);
//     }

//     @Test
//     public void testCriticalHealthHandling() {
//         healthBar.setHealth(20);
//         criticalHandler.handleCharacterDecision(testCharacter);
//         // Verify health is in critical range
//         assertTrue(testCharacter.getHealthBar().getHealth() < 25);
//     }

//     @Test
//     public void testLowHealthHandling() {
//         healthBar.setHealth(35);
//         criticalHandler.handleCharacterDecision(testCharacter);
//         // Verify health is in low range
//         assertTrue(testCharacter.getHealthBar().getHealth() >= 25 
//             && testCharacter.getHealthBar().getHealth() < 50);
//     }

//     @Test
//     public void testMidHealthHandling() {
//         healthBar.setHealth(60);
//         criticalHandler.handleCharacterDecision(testCharacter);
//         // Verify health is in mid range
//         assertTrue(testCharacter.getHealthBar().getHealth() >= 50 
//             && testCharacter.getHealthBar().getHealth() < 75);
//     }

//     @Test
//     public void testFullHealthHandling() {
//         healthBar.setHealth(85);
//         criticalHandler.handleCharacterDecision(testCharacter);
//         // Verify health is in full range
//         assertTrue(testCharacter.getHealthBar().getHealth() >= 75);
//     }
// } 