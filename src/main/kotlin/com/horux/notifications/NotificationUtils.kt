package com.horux.notifications

import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.Message
import com.google.firebase.messaging.MulticastMessage
import com.google.firebase.messaging.Notification
import com.google.gson.Gson
import com.google.gson.JsonObject
import org.springframework.stereotype.Component

@Component
class NotificationUtils(val firebaseMessaging: FirebaseMessaging) {


    fun sendMessageByToken(jsonObject: JsonObject, vararg registrationTokens: String) {
        if(registrationTokens.size == 1) {
            sendMessageToSingleUser(jsonObject,registrationTokens[0])
        } else {
            sendMessageToMultipleUser(jsonObject, *registrationTokens)
        }
    }

    fun sendNotificationByToken(notification: Notification, vararg registrationTokens: String) {
        if(registrationTokens.size == 1){
            sendNotificationToSingleUser(notification,registrationTokens.first())
        } else {
            sendNotificationToMultipleUser(notification, *registrationTokens)
        }
    }
    private fun sendNotificationToSingleUser(notification: Notification, registrationToken: String) {
        firebaseMessaging.send(Message.builder()
            .setNotification(notification)
            .setToken(registrationToken)
            .build())
    }
    private fun sendNotificationToMultipleUser(notification: Notification, vararg registrationTokens: String) {
        firebaseMessaging.sendEachForMulticast(
            MulticastMessage.builder()
                .setNotification(notification)
                .addAllTokens(registrationTokens.toList())
                .build()
        )
    }


    private fun sendMessageToSingleUser(jsonObject: JsonObject, registrationToken: String) {
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
    private fun sendMessageToMultipleUser(jsonObject: JsonObject, vararg registrationTokens: String) {
        firebaseMessaging.sendEachForMulticast(
                MulticastMessage.builder()
                        .addAllTokens(registrationTokens.toList())
                        .putData("body", Gson().toJson(jsonObject))
                        .build()
        )
    }

}