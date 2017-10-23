package michalbaran.testservice;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;


public class MyService extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        // use System.currentTimeMillis() to have a unique ID for the pending intent
        PendingIntent pIntent = PendingIntent.getActivity(this, (int) System.currentTimeMillis(), intent, 0);
        Notification n  = new Notification.Builder(this)
                .setContentTitle("New mail from " + "test@gmail.com")
                .setContentText("testService")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentIntent(pIntent)
                .setAutoCancel(true).getNotification();;

        int index = 0;
        long millis_mem =  System.currentTimeMillis() / 1000;
        do{
            if((millis_mem+15) < (System.currentTimeMillis() / 1000)){
                millis_mem =  System.currentTimeMillis() / 1000;
                index++;

                NotificationManager notificationManager = (NotificationManager) this.getSystemService(NOTIFICATION_SERVICE);
                notificationManager.notify(0, n);
            }
        }while(index < 10);

        Toast.makeText(this, "Service done ...", Toast.LENGTH_LONG).show();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "Service Destroyed and timer...", Toast.LENGTH_LONG).show();
    }

}
