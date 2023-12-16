package com.horux.notifications

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.messaging.FirebaseMessaging
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import org.springframework.core.io.ClassPathResource
import java.lang.RuntimeException
import kotlin.reflect.full.findAnnotation

@Configuration
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
annotation class EnablePushNotifications(val configJsonFilename: String) {

    @Import(EnablePushNotifications::class)
    class NotificationConfig {

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
            val fileName = with(this::class.findAnnotation<EnablePushNotifications>()?.configJsonFilename) {
                this ?: throw RuntimeException("${EnablePushNotifications::class.java.name} was not found")
                if (!this.endsWith(".json")) throw RuntimeException("Please provide name with extension of json file downloaded from firebase")
                return@with this
            }
            return GoogleCredentials.fromStream(ClassPathResource(fileName).inputStream)
        }


    }

}
