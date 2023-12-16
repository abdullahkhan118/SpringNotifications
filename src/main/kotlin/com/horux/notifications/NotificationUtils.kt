package com.horux.notifications

import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.Message
import com.google.firebase.messaging.MulticastMessage
import com.google.gson.Gson
import com.google.gson.JsonObject
import org.springframework.stereotype.Component

@Component
class NotificationUtils(val firebaseMessaging: FirebaseMessaging) {

    fun sendMessageToSingleUser(jsonObject: JsonObject, registrationToken: String) {
        firebaseMessaging.send(Message.builder()
                .setToken(registrationToken)
                .putData("body", Gson().toJson(jsonObject))
                .build())
    }
    fun sendNotificationToSingleUser(jsonObject: JsonObject, registrationToken: String) {
        firebaseMessaging.send(Message.builder()
                .setToken(registrationToken)
                .putData("body", Gson().toJson(jsonObject))
                .build())
    }

    fun sendMessageToTopic(jsonObject: JsonObject, topic: String) {
        firebaseMessaging.send(Message.builder()
                .putData("body",Gson().toJson(jsonObject))
                .setTopic(topic)
                .build())
    }
    fun sendMessageToMultipleUser(jsonObject: JsonObject, vararg registrationTokens: String) {
        firebaseMessaging.sendEachForMulticast(
                MulticastMessage.builder()
                        .addAllTokens(registrationTokens.toList())
                        .putData("body", "some data")
                        .build()
        )
    }

}