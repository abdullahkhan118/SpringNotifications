package com.horux.notifications.controller

import com.horux.notifications.NotificationUtils
import com.horux.notifications.models.SendMessage
import com.horux.notifications.models.SendNotification
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/v1/send")
class NotificationController(val notificationUtils: NotificationUtils) {

    @PostMapping("/message")
    fun sendMessage(@RequestBody data: SendMessage) {
        notificationUtils.sendMessageByToken(data.body,*data.registrationTokens)
    }
    @PostMapping("/notification")
    fun sendNotification(@RequestBody data: SendNotification) {
        notificationUtils.sendNotificationByToken(data.notification,*data.registrationTokens)
    }

}