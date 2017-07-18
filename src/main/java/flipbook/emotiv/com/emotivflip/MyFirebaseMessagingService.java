package flipbook.emotiv.com.emotivflip;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import static com.google.android.gms.internal.zzs.TAG;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

//
    public MyFirebaseMessagingService() {
        super();
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);


        Log.e("SDK", "From: " + remoteMessage.getFrom());
        //Log.e("SDK", "Notification Message Body: " + remoteMessage.getNotification().getBody());
        sendNotification(remoteMessage.getData().get("title"),remoteMessage.getData().get("body"));

        System.out.println("intent Received");
        Intent RTReturn = new Intent(CurlActivity.RECEIVE_JSON);
        RTReturn.putExtra("title", remoteMessage.getData().get("title"));
        LocalBroadcastManager.getInstance(this).sendBroadcast(RTReturn);
    }

    private void sendNotification(String messageTitle,String messageBody) {
        Intent intent = new Intent(this, CurlActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0 /* request code */, intent,PendingIntent.FLAG_UPDATE_CURRENT);

        long[] pattern = {500,500,500,500,500};

        Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Builder notificationBuilder = (NotificationCompat.Builder) new NotificationCompat.Builder(this)
                .setSmallIcon(android.R.drawable.ic_dialog_alert)
                .setContentTitle(messageTitle)
                .setContentText(messageBody)
                .setAutoCancel(true)
                .setVibrate(pattern)
                .setLights(Color.BLUE,1,1)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
    }
}
