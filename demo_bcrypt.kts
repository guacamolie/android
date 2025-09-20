#!/usr/bin/env kotlin

@file:DependsOn("at.favre.lib:bcrypt:0.10.2")

import at.favre.lib.crypto.bcrypt.BCrypt

/**
 * Simple demonstration script to show bcrypt password hashing functionality
 * that will be used in the Android app for consistency with Next.js frontend.
 */
fun main() {
    val password = "test123"
    
    println("=== Android bcrypt Implementation Demo ===")
    println("Original password: $password")
    
    // Hash the password
    val hashedPassword = BCrypt.withDefaults().hashToString(12, password.toCharArray())
    println("Hashed password: $hashedPassword")
    
    // Verify the password
    val isValid = BCrypt.verifyer().verify(password.toCharArray(), hashedPassword).verified
    println("Password verification: $isValid")
    
    // Test with wrong password
    val wrongPassword = "wrong123"
    val isWrongValid = BCrypt.verifyer().verify(wrongPassword.toCharArray(), hashedPassword).verified
    println("Wrong password verification: $isWrongValid")
    
    println("\n✅ bcrypt implementation ready for Android app!")
    println("This ensures consistency with the Next.js frontend bcrypt usage.")
}