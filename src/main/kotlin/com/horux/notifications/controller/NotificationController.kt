package com.horux.notifications.controller

import com.horux.notifications.NotificationUtils
import com.horux.notifications.models.NotificationData
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/v1/notification")
class NotificationController(val notificationUtils: NotificationUtils) {

    @PostMapping("send")
    fun send(@RequestBody data: NotificationData) {
        notificationUtils.sendMessageToSingleUser(data.body,data.registrationToken)
    }

}