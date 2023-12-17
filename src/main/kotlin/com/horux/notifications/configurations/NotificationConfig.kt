package com.horux.notifications.configurations

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.messaging.FirebaseMessaging
import com.horux.notifications.utils.NotificationUtils
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ClassPathResource
import java.lang.RuntimeException

@Configuration
class NotificationConfig(val notificationProperties: NotificationProperties) {

    @Bean
    fun notificationUtil(firebaseMessaging: FirebaseMessaging): NotificationUtils = NotificationUtils(firebaseMessaging)

    @Bean
    fun firebaseMessaging(firebaseApp: FirebaseApp): FirebaseMessaging = FirebaseMessaging.getInstance(firebaseApp)

    @Bean
    fun firebaseApp(googleCredentials: GoogleCredentials): FirebaseApp = FirebaseApp.initializeApp(
        FirebaseOptions.builder()
            .setCredentials(googleCredentials)
            .build()
    )

    @Bean
    fun googleCredentials(): GoogleCredentials {
        val fileName = with(notificationProperties.configFileName) {
            if (!this.endsWith(".json")) throw RuntimeException("Please provide name with extension of json file downloaded from firebase")
            return@with this
        }
        return GoogleCredentials.fromStream(ClassPathResource(fileName).inputStream)
    }


}
