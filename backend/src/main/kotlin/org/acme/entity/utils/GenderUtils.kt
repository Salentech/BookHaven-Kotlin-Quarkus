package org.acme.entity.utils

enum class Gender {
    MALE,
    FEMALE,
    OTHER;

    companion object {
        fun fromString(value: String): Gender? {
            return entries.find { it.name.equals(value, ignoreCase = true) }
        }
    }
}