package com.example.njxfuture.ViewPager;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.example.njxfuture.API.APIRequests;
import com.example.njxfuture.API.DataModels.PushNotificationDataModel;
import com.example.njxfuture.MainActivity;
import com.example.njxfuture.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.UUID;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificationService extends Service {

    private int NOTIFICATION_ID = 1;
    private static final String API_URL = "http://njx.revacg.in/pushnot.php?imeival=79489f81-16dd-45d8-ab13-39d8635b0857";

    private final Handler handler = new Handler(Looper.getMainLooper());

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        startForeground(NOTIFICATION_ID, createNotification());
        startPolling();
        return START_STICKY;
    }

    private void startPolling() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                fetchNotification();
                startPolling(); // Schedule next poll
            }
        }, 6000); // Poll every minute (adjust as needed)
    }

    private Notification createNotification() {
        // Create the notification channel if necessary (for Android Oreo and above)
        createNotificationChannel();

        // Create the notification with minimal content
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "channel_id").setContentTitle("Foreground Service").setContentText("Running...").setSmallIcon(R.drawable.ic_notifications_black_24dp).setPriority(NotificationCompat.PRIORITY_DEFAULT);

        return builder.build();
    }

    // Helper method to create the notification channel
    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("channel_id", "Channel Name", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            if (manager != null) {
                manager.createNotificationChannel(channel);
            }
        }
    }

    private void fetchNotification() {
        Call<List<PushNotificationDataModel>> call = APIRequests.getPushNotify(getDeviceIds(getApplicationContext()));
        call.enqueue(new Callback<List<PushNotificationDataModel>>() {
            @Override
            public void onResponse(@NonNull Call<List<PushNotificationDataModel>> call, @NonNull Response<List<PushNotificationDataModel>> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        List<PushNotificationDataModel> pushNotify = response.body();
                        for (int i = 0; i < pushNotify.size(); i++) {
                            showNotification(pushNotify.get(i).gettitle(), pushNotify.get(i).getdtxt(),
                                    pushNotify.get(i).getpckimg(), pushNotify.get(i).getnid());
                        }
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<PushNotificationDataModel>> call, @NonNull Throwable t) {
                Log.e("API_CALL", "API call failed: " + t.getMessage());
            }
        });
    }

    private void showNotification(String title, String message, String imageUrl, String nid) {
        new Thread(() -> {
            try {
                if (!imageUrl.startsWith("http://") || !imageUrl.startsWith("https://")) {
                    URL url = new URL(imageUrl);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setDoInput(true);
                    connection.connect();
                    InputStream input = connection.getInputStream();
                    Bitmap imageBitmap = BitmapFactory.decodeStream(input);
                    Intent notificationIntent = new Intent(this, MainActivity.class);
                    // Add any extra data you want to pass to the activity
                    notificationIntent.putExtra("notification_id", nid);
                    notificationIntent.putExtra("notification_tab", "3");
                    notificationIntent.setAction(Intent.ACTION_VIEW);
                    PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, PendingIntent.FLAG_MUTABLE);

                    // Create the notification with the downloaded image
                    NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "channel_id")
                            .setSmallIcon(R.drawable.ic_notifications_black_24dp)
                            .setContentTitle(title)
                            .setContentText(message)
                            .setLargeIcon(imageBitmap)
                            .setStyle(new NotificationCompat.BigPictureStyle()
                                    .bigPicture(imageBitmap)
                                    .bigLargeIcon(null))
                            .setAutoCancel(true)
                            .setPriority(NotificationCompat.PRIORITY_DEFAULT);

                    NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                    if (notificationManager != null) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            NotificationChannel notificationChannel = notificationManager.getNotificationChannel("channel_id");
                            if (notificationChannel == null) {
                                int importance = NotificationManager.IMPORTANCE_HIGH;
                                notificationChannel = new NotificationChannel("channel_id", "Channel Name", importance);
                                notificationChannel.setDescription("Channel Description");
                                notificationChannel.setLightColor(Color.GREEN);
                                notificationChannel.enableVibration(true);
                                notificationManager.createNotificationChannel(notificationChannel);
                            }
                        }
                        builder.setContentIntent(pendingIntent);
                        notificationManager.notify(NOTIFICATION_ID, builder.build());
                        NOTIFICATION_ID++;
                    }
                } else {
                    Log.e("URL_ERROR", "Invalid URL: " + imageUrl);
                }
            } catch (MalformedURLException e) {
                Intent notificationIntent = new Intent(this, MainActivity.class);
                // Add any extra data you want to pass to the activity
                notificationIntent.putExtra("notification_id", nid);
                notificationIntent.putExtra("notification_tab", "3");
                notificationIntent.setAction(Intent.ACTION_VIEW);
                PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, PendingIntent.FLAG_MUTABLE);
                // Create the notification with the downloaded image
                NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "channel_id")
                        .setSmallIcon(R.drawable.ic_notifications_black_24dp)
                        .setContentTitle(title)
                        .setContentText(message)
                        .setAutoCancel(true)
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT);

                NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                if (notificationManager != null) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        NotificationChannel notificationChannel = notificationManager.getNotificationChannel("channel_id");
                        if (notificationChannel == null) {
                            int importance = NotificationManager.IMPORTANCE_HIGH;
                            notificationChannel = new NotificationChannel("channel_id", "Channel Name", importance);
                            notificationChannel.setDescription("Channel Description");
                            notificationChannel.setLightColor(Color.GREEN);
                            notificationChannel.enableVibration(true);
                            notificationManager.createNotificationChannel(notificationChannel);
                        }
                    }
                    builder.setContentIntent(pendingIntent);
                    notificationManager.notify(NOTIFICATION_ID, builder.build());
                    NOTIFICATION_ID++;
                }
            } catch (IOException e) {
                Log.e("URL_ERROR", "Error fetching image from URL: " + imageUrl);
            }
        }).start();
    }

    public String getDeviceIds(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        String deviceId = sharedPreferences.getString("device_id", null);
        if (deviceId == null) {
            deviceId = UUID.randomUUID().toString();
            sharedPreferences.edit().putString("device_id", deviceId).apply();
        }
        return deviceId;
    }

    public Bitmap drawableToBitmap(Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }

        int width = drawable.getIntrinsicWidth();
        int height = drawable.getIntrinsicHeight();
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}