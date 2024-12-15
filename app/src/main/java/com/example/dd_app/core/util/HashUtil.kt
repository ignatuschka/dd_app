package com.example.dd_app.core.util

import java.security.MessageDigest
import java.security.SecureRandom
import java.util.Base64
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.PBEKeySpec

object HashUtil {
    private const val keyLength = 256
    private const val saltLength = 16
    private const val iterations = 10000
    private val secureRandom = SecureRandom()

    fun generateSalt(): String {
        val salt = ByteArray(saltLength)
        secureRandom.nextBytes(salt)
        return Base64.getEncoder().encodeToString(salt)
    }

    fun hashPassword(password: String, salt: String): String {
        val spec = PBEKeySpec(password.toCharArray(), Base64.getDecoder().decode(salt), iterations, keyLength)
        try {
            val factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256")
            val hashedValue = factory.generateSecret(spec).encoded
            return Base64.getEncoder().encodeToString(hashedValue)
        } finally {
            spec.clearPassword()
        }
    }

    fun verifyHashPassword(password: String, salt: String, storedHash: String): Boolean {
        val hashedPassword = hashPassword(password, salt)
        return MessageDigest.isEqual(Base64.getDecoder().decode(hashedPassword), Base64.getDecoder().decode(storedHash))
    }
}