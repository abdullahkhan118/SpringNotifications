package com.horux.notifications

import com.horux.notifications.configurations.NotificationProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan
@SpringBootApplication()
@EnableConfigurationProperties(value = arrayOf(NotificationProperties::class))
@ComponentScan(basePackages = arrayOf("com.horux.notifications","com.horux.notifications.*"))
class NotificationsApplication

fun main(args: Array<String>) {
	runApplication<NotificationsApplication>(*args)
}
