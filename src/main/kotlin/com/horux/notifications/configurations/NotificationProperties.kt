package com.horux.notifications.configurations

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@ConfigurationProperties(prefix = "notification")
@Component
class NotificationProperties {

    lateinit var configFileName: String
}