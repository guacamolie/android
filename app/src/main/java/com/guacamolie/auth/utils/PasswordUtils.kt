package com.guacamolie.auth.utils

import at.favre.lib.crypto.bcrypt.BCrypt

/**
 * Utility class for password hashing using bcrypt.
 * 
 * This ensures consistency with the Next.js frontend which also uses bcrypt
 * for password hashing before sending to the backend API.
 */
object PasswordUtils {
    
    private const val DEFAULT_COST = 12
    
    /**
     * Hash a plain text password using bcrypt.
     * 
     * @param password The plain text password to hash
     * @param cost The bcrypt cost factor (default: 12)
     * @return The hashed password as a string
     */
    fun hashPassword(password: String, cost: Int = DEFAULT_COST): String {
        return BCrypt.withDefaults().hashToString(cost, password.toCharArray())
    }
    
    /**
     * Verify a plain text password against a hashed password.
     * 
     * @param password The plain text password to verify
     * @param hashedPassword The hashed password to verify against
     * @return true if the password matches, false otherwise
     */
    fun verifyPassword(password: String, hashedPassword: String): Boolean {
        return BCrypt.verifyer().verify(password.toCharArray(), hashedPassword).verified
    }
}