package com.example.essecproject;

import android.util.Log;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = "MyFirebaseMessaging";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.d(TAG, "From: " + remoteMessage.getFrom());
        Log.d(TAG, "Notification Message Body: " + remoteMessage.getNotification().getBody());

        // Handle the message based on the topic
        if (remoteMessage.getFrom().equals("/topics/students")) {
            // Handle student-specific message
            handleStudentMessage(remoteMessage);
        } else if (remoteMessage.getFrom().equals("/topics/teachers")) {
            // Handle teacher-specific message
            handleTeacherMessage(remoteMessage);
        } else {
            // Handle general message
            handleGeneralMessage(remoteMessage);
        }
    }

    private void handleStudentMessage(RemoteMessage remoteMessage) {
        // Implement student-specific message handling
    }

    private void handleTeacherMessage(RemoteMessage remoteMessage) {
        // Implement teacher-specific message handling
    }

    private void handleGeneralMessage(RemoteMessage remoteMessage) {
        // Implement general message handling
    }
}
