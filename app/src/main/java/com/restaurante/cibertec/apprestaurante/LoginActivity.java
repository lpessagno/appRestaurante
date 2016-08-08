package com.restaurante.cibertec.apprestaurante;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.restaurante.cibertec.apprestaurante.R;
import com.restaurante.cibertec.apprestaurante.SignInActivity;

public class LoginActivity extends AppCompatActivity {

    SharedPreferences appPreferences;
    SharedPreferences.Editor editor;

    EditText userlogin;
    EditText pwdlogin;

    String loginUserText;
    String loginPasswordText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userlogin = (EditText)findViewById(R.id.userlogin);
        pwdlogin = (EditText)findViewById(R.id.pwdlogin);
        appPreferences = getSharedPreferences(getString(R.string.preferences),MODE_PRIVATE);

    }

    public void loginInApp(View view) {
        loginUserText = userlogin.getText().toString();
        loginPasswordText = pwdlogin.getText().toString();
        if(validateLogin()){
            //setear valores en preferencias
            editor = appPreferences.edit();
            editor.putString(getString(R.string.user),loginUserText);
            editor.putString(getString(R.string.password),loginPasswordText);
            editor.commit();
            //Cambiar de activity
            //ActivityPrincipal de restaurante.
        }
    }

    private boolean validateLogin() {
        //ir a Base de Datos
        return  true;
    }

    public void registerInApp(View view) {
        Intent intent = new Intent(this,SignInActivity.class);
        startActivity(intent);
    }
}
