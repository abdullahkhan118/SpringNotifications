package com.horux.notifications.models
data class SendNotification(
    val notification: NotificationBody,
    val registrationTokens: Array<String>
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as SendNotification

        if (notification != other.notification) return false
        if (!registrationTokens.contentEquals(other.registrationTokens)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = notification.hashCode()
        result = 31 * result + registrationTokens.contentHashCode()
        return result
    }
}
