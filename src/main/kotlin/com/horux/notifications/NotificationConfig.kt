package com.horux.notifications

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.messaging.FirebaseMessaging
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ClassPathResource
import java.io.FileInputStream


@Configuration
class NotificationConfig {

    @Bean
    fun firebaseMessaging(firebaseApp: FirebaseApp): FirebaseMessaging = FirebaseMessaging.getInstance(firebaseApp)

    @Bean
    fun firebaseApp(googleCredentials: GoogleCredentials): FirebaseApp = FirebaseApp.initializeApp(FirebaseOptions.builder()
            .setCredentials(googleCredentials)
            .build())

    @Bean
    fun googleCredentials(): GoogleCredentials {
//        if (firebaseProperties.getServiceAccount() != null) {
//            firebaseProperties.getServiceAccount().getInputStream().use { `is` -> return GoogleCredentials.fromStream(`is`) }
//        } else {
            // Use standard credentials chain. Useful when running inside GKE
            return GoogleCredentials.fromStream(ClassPathResource("korux-firebase.json").inputStream)
//        }
    }


}