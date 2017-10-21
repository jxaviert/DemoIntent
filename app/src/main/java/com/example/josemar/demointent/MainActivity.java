package com.example.josemar.demointent;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.josemar.demointent.broadcastreceiver.AlarmReceiver;
import com.example.josemar.demointent.utils.Constantes;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private TextView tvLogin;
    private TextView tvPlacarVisitante;
    private TextView tvPlacarHome;

    private int placarHome = 0;
    private int placarVisitante = 0;

    private Button golCasa;
    private Button golVisitante;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvLogin = (TextView) findViewById(R.id.tvLogin);
        tvPlacarVisitante = (TextView) findViewById(R.id.tvPlacarVisitante);
        tvPlacarHome = (TextView) findViewById(R.id.tvPlacarHome);

        golCasa = (Button)findViewById(R.id.golCasa);
        golVisitante = (Button)findViewById(R.id.golVisitante);

        golCasa.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                placarHome++;
                tvPlacarHome.setText(String.valueOf(placarHome));
            }
        });

        golVisitante.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                placarVisitante++;
                tvPlacarVisitante.setText(String.valueOf(placarVisitante));
            }
        });

        if(savedInstanceState != null){
            placarHome = savedInstanceState.getInt(Constantes.KEY_PLACAR_CASA);
            placarVisitante = savedInstanceState.getInt(Constantes.KEY_PLACAR_VISITANTE);

        }

        tvPlacarHome.setText(String.valueOf(placarHome));
        tvPlacarVisitante.setText(String.valueOf(placarVisitante));

        if (getIntent() != null) {
            tvLogin.setText(getIntent().getStringExtra(Constantes.KEY_LOGIN));

        }
    }



    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putInt(Constantes.KEY_PLACAR_CASA, placarHome);
        outState.putInt(Constantes.KEY_PLACAR_VISITANTE, placarVisitante);
    }

    public void programarAlarme(View v){
        EditText text = (EditText) findViewById(R.id.tvTempoJogo);
        int i = Integer.parseInt(text.getText().toString());
        Intent intent = new Intent(this, AlarmReceiver. class);

        PendingIntent pendingIntent = PendingIntent. getBroadcast(
                this.getApplicationContext(), 0, intent, 0);
        AlarmManager alarmManager = (AlarmManager) getSystemService( ALARM_SERVICE);

        alarmManager.set(AlarmManager. RTC_WAKEUP,
                System. currentTimeMillis() + (i * 1000),
                pendingIntent);
        Toast.makeText(this, "Alarm set in " +i+ "seconds",Toast.LENGTH_LONG).show();
    }



}
