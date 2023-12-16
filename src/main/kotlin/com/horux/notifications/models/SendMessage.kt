package com.horux.notifications.models

import com.google.gson.JsonObject

data class SendMessage(
    val body: JsonObject,
    val registrationTokens: Array<String>
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as SendMessage

        if (body != other.body) return false
        if (!registrationTokens.contentEquals(other.registrationTokens)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = body.hashCode()
        result = 31 * result + registrationTokens.contentHashCode()
        return result
    }
}
