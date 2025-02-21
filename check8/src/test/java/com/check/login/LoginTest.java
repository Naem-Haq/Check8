package com.check.login;

import com.check.data.DataHandler;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;

public class LoginTest {

    private MockedStatic<DataHandler> mockedDataHandler;

    @BeforeEach
    void setUp() {
        // Create a static mock for DataHandler that lasts for the entire test
        mockedDataHandler = Mockito.mockStatic(DataHandler.class);

        // Define behavior for loadUserLogin
        mockedDataHandler.when(() -> DataHandler.loadUserLogin("testUser", Login.hashPassword("testPass")))
                .thenReturn(true);
        mockedDataHandler.when(() -> DataHandler.loadUserLogin("testUser", Login.hashPassword("wrongPass")))
                .thenReturn(false);
        mockedDataHandler.when(() -> DataHandler.loadUserLogin("nonExistentUser", Login.hashPassword("somePass")))
                .thenReturn(false);

        // Prevent file I/O errors by mocking file-related methods:
        // Return an empty stats map for loadUserStats
        mockedDataHandler.when(DataHandler::loadUserStats).thenReturn(new HashMap<>());
        // For saveUserStats, do nothing (or return null)
        mockedDataHandler.when(() -> DataHandler.saveUserStats(anyString(), anyInt(), anyInt(), anyInt()))
                .thenAnswer(invocation -> null);
    }

    @AfterEach
    void tearDown() {
        // Close the static mock to deregister it
        mockedDataHandler.close();
    }

    @Test
    void testLogInSuccess() {
        User user = Login.logIn("testUser", "testPass");

        assertNotNull(user, "User should be created for valid credentials.");
        assertEquals("testUser", user.getName(), "Username should match.");
    }

    @Test
    void testLogInFailureWrongPassword() {
        User user = Login.logIn("testUser", "wrongPass");

        assertNull(user, "User should be null for incorrect password.");
    }

    @Test
    void testLogInFailureNonExistentUser() {
        User user = Login.logIn("nonExistentUser", "somePass");

        assertNull(user, "User should be null for a non-existent username.");
    }

    @Test
    void testHashPasswordConsistency() {
        String hash1 = Login.hashPassword("samePassword");
        String hash2 = Login.hashPassword("samePassword");

        assertEquals(hash1, hash2, "Hashed passwords should be consistent.");
    }

    @Test
    void testHashPasswordDifferentPasswords() {
        String hash1 = Login.hashPassword("password1");
        String hash2 = Login.hashPassword("password2");

        assertNotEquals(hash1, hash2, "Different passwords should have different hashes.");
    }
}
