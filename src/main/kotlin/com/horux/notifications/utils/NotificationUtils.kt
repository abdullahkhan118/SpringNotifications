package com.horux.notifications.utils

import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.Message
import com.google.firebase.messaging.MulticastMessage
import com.google.firebase.messaging.Notification
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.horux.notifications.models.NotificationBody
import org.springframework.stereotype.Component

@Component
class NotificationUtils(private val firebaseMessaging: FirebaseMessaging) {


    fun sendMessageByToken(jsonObject: JsonObject, vararg registrationTokens: String) {
        if (registrationTokens.size == 1) {
            sendMessageToSingleUser(jsonObject, registrationTokens[0])
        } else {
            sendMessageToMultipleUser(jsonObject, *registrationTokens)
        }
    }

    fun sendNotificationByToken(notification: NotificationBody, vararg registrationTokens: String) {
        if (registrationTokens.size == 1) {
            sendNotificationToSingleUser(createNotification(notification), registrationTokens.first())
        } else {
            sendNotificationToMultipleUser(createNotification(notification), *registrationTokens)
        }
    }

    private fun sendNotificationToSingleUser(notification: Notification, registrationToken: String) {
        firebaseMessaging.send(
            Message.builder()
                .setNotification(notification)
                .setToken(registrationToken)
                .build()
        )
    }

    private fun sendNotificationToMultipleUser(notification: Notification, vararg registrationTokens: String) {
        firebaseMessaging.sendEachForMulticast(
            MulticastMessage.builder()
                .setNotification(notification)
                .addAllTokens(registrationTokens.toList())
                .build()
        )
    }


    private fun sendMessageToSingleUser(jsonObject: JsonObject, registrationToken: String) = runCatching {
        firebaseMessaging.send(
            Message.builder()
                .setToken(registrationToken)
                .putData("body", Gson().toJson(jsonObject))
                .build()
        )
    }.onFailure {

    }

    fun sendMessageToTopic(jsonObject: JsonObject, topic: String) {
        firebaseMessaging.send(
            Message.builder()
                .putData("body", Gson().toJson(jsonObject))
                .setTopic(topic)
                .build()
        )
    }

    fun sendNotificationToTopic(notification: NotificationBody, topic: String) {
        firebaseMessaging.send(
            Message.builder()
                .setNotification(createNotification(notification))
                .setTopic(topic)
                .build()
        )
    }

    private fun sendMessageToMultipleUser(jsonObject: JsonObject, vararg registrationTokens: String) {
        firebaseMessaging.sendEachForMulticast(
            MulticastMessage.builder()
                .addAllTokens(registrationTokens.toList())
                .putData("body", Gson().toJson(jsonObject))
                .build()
        )
    }

    private fun createNotification(notification: NotificationBody) = with(notification) {
        Notification.builder().setTitle(title).setBody(body).setImage(image).build()
    }

}