package com.example.josemar.demointent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.josemar.demointent.utils.Constantes;

import static android.app.Activity.RESULT_OK;

public class LoginActivity extends AppCompatActivity {

    private EditText etLogin;
    private EditText etSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etLogin = (EditText)findViewById(R.id.etLogin);
        etSenha = (EditText)findViewById(R.id.etSenha);
    }
    public void conectar(View v){
        Intent validaLogin = new Intent(this, ValidaLoginActivity.class);

        validaLogin.putExtra(Constantes.KEY_LOGIN,
                etLogin.getText().toString());

        validaLogin.putExtra(Constantes.KEY_SENHA,
                etSenha.getText().toString());

        startActivityForResult(validaLogin,
                Constantes.REQUEST_CODE_VALIDA_LOGIN);
    }

    public void testeBroadcast(View v){
        Intent i = new Intent();

        i.setAction("android.app.action.LOGIN_SUCESSO");
        sendBroadcast(i);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case Constantes.REQUEST_CODE_VALIDA_LOGIN:
                switch (resultCode){
                    case RESULT_OK:
                        boolean isLoginValido =
                                data.getBooleanExtra(
                                    Constantes.KEY_RESULT_LOGIN,false);
                        if(isLoginValido) {

                            Intent mainActivity = new Intent(this, MainActivity.class);

                            mainActivity.putExtra(Constantes.KEY_LOGIN,
                                    etLogin.getText().toString());
                            startActivity(mainActivity);
                            finish();


                        }else{
                            Toast.makeText(this, "Login inválido", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case RESULT_CANCELED:
                        break;
                }

                break;
        }
    }
}
