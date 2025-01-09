import me.pushy.sdk.Pushy;
import me.pushy.sdk.model.PushyNotificationListener;
import me.pushy.sdk.util.exceptions.PushyException;

import javax.swing.*;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        // Set Pushy App ID (Pushy Demo)
        Pushy.setAppId("550ee57c5b5d72117f51e801");

        // Listen for notifications
        Pushy.listen();

        // Not registered yet?
        if (!Pushy.isRegistered()) {
            try {
                // Register for push notifications & obtain device token
                String deviceToken = Pushy.register();

                // Display device token in alert dialog
                JOptionPane.showMessageDialog(null, "Pushy registration success: " + deviceToken);
            } catch (PushyException e) {
                // Display registration errors
                JOptionPane.showMessageDialog(null, "Pushy registration failed: " + e.getMessage());
            }
        }

        // Handle incoming push notifications
        Pushy.setNotificationListener(new PushyNotificationListener() {
            @Override
            public void onNotificationReceived(Map<String, Object> data) {
                // Display an alert dialog with notification payload "message" value
                JOptionPane.showMessageDialog(null, "Notification received: " + data.get("message"));
            }
        });

        // Make sure the user is registered for notifications
        if (Pushy.isRegistered()) {
            try {
                // Subscribe the user to a topic
                Pushy.subscribe("news");
            } catch (PushyException e) {
                // Display error dialog
                JOptionPane.showMessageDialog(null, "Pushy subscribe failed: " + e.getMessage());
            }
        }
    }
}