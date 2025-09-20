package com.guacamolie.auth.utils

import org.junit.Test
import org.junit.Assert.*

class PasswordUtilsTest {
    
    @Test
    fun `hashPassword should return hashed password that differs from original`() {
        val plainPassword = "test123"
        val hashedPassword = PasswordUtils.hashPassword(plainPassword)
        
        // Hashed password should not be the same as plain text
        assertNotEquals(plainPassword, hashedPassword)
        
        // Hashed password should not be empty
        assertTrue(hashedPassword.isNotBlank())
        
        // BCrypt hashes start with $2a, $2b, or $2y 
        assertTrue(hashedPassword.startsWith("$2"))
    }
    
    @Test
    fun `verifyPassword should return true for correct password`() {
        val plainPassword = "test123"
        val hashedPassword = PasswordUtils.hashPassword(plainPassword)
        
        val isValid = PasswordUtils.verifyPassword(plainPassword, hashedPassword)
        
        assertTrue(isValid)
    }
    
    @Test
    fun `verifyPassword should return false for incorrect password`() {
        val plainPassword = "test123"
        val wrongPassword = "wrong456"
        val hashedPassword = PasswordUtils.hashPassword(plainPassword)
        
        val isValid = PasswordUtils.verifyPassword(wrongPassword, hashedPassword)
        
        assertFalse(isValid)
    }
    
    @Test
    fun `hashPassword should produce different hashes for same password due to salt`() {
        val plainPassword = "test123"
        val hash1 = PasswordUtils.hashPassword(plainPassword)
        val hash2 = PasswordUtils.hashPassword(plainPassword)
        
        // Due to salt, same password should produce different hashes
        assertNotEquals(hash1, hash2)
        
        // But both should verify correctly
        assertTrue(PasswordUtils.verifyPassword(plainPassword, hash1))
        assertTrue(PasswordUtils.verifyPassword(plainPassword, hash2))
    }
}