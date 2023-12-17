package com.horux.notifications.models

import com.google.gson.JsonObject

data class TopicMessage(
    val body: JsonObject,
    val topic: String
)
