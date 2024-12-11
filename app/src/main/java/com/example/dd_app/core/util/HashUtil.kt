package com.example.dd_app.core.util

import java.security.SecureRandom
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.PBEKeySpec

class HashUtil {
    private val saltLength = 16
    private val iterations = 10000
    private val keyLength = 256

    fun generateSalt(): String {
        val salt = ByteArray(saltLength)
        SecureRandom().nextBytes(salt)
        return salt.joinToString("") { "%02x".format(it) }
    }

    fun hashValue(value: String, salt: String): String {
        val spec = PBEKeySpec(value.toCharArray(), salt.toByteArray(), iterations, keyLength)
        val factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256")
        val hashedValue = factory.generateSecret(spec).encoded
        return hashedValue.joinToString("") { "%02x".format(it) }
    }

    fun verifyHashValue(value: String, salt: String, storedHash: String): Boolean {
        val hash = hashValue(value, salt)
        return hash == storedHash
    }
}