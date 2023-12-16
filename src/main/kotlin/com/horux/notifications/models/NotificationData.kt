package com.horux.notifications.models

import com.google.gson.JsonObject

data class NotificationData(
        val registrationToken: String,
        val body: JsonObject
)
