package com.example.josemar.demointent.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.widget.Toast;

import com.example.josemar.demointent.R;

/**
 * Created by logonrm on 21/10/2017.
 */

public class AlarmReceiver extends BroadcastReceiver {
    private MediaPlayer mp = null;
    public AlarmReceiver() {
    }
    @Override
    public void onReceive(Context context, Intent intent) {
        mp = MediaPlayer.create(context, R.raw.despertar);
        mp.start();
        Toast.makeText(context, "Alarm....", Toast.LENGTH_LONG).show();
    }
}
