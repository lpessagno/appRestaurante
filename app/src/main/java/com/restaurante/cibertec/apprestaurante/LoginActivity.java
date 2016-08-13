package com.restaurante.cibertec.apprestaurante;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.restaurantmodel.dao.UserDao;
import com.example.restaurantmodel.impl.UserDaoImpl;
import com.example.restaurantmodel.model.User;
import com.restaurante.cibertec.apprestaurante.R;
import com.restaurante.cibertec.apprestaurante.SignInActivity;

public class LoginActivity extends AppCompatActivity {

    public static final int SIGNIN_REQUEST = 444;
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
            this.finish();
        } else {
            Toast.makeText(this,"Usuario/Password No Valido",Toast.LENGTH_SHORT).show();
        }
    }

    private boolean validateLogin() {
        Boolean validate = false;
        UserDao dao = new UserDaoImpl(this);
        User user = dao.getByName(loginUserText);
        if (user.getPassword().equals(loginPasswordText)){
            validate = true;
        }
        return  validate;
    }

    public void registerInApp(View view) {
        Intent intent = new Intent(this,SignInActivity.class);
        startActivityForResult(intent, SIGNIN_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==SIGNIN_REQUEST){
            if(resultCode==RESULT_OK){
                int value = data.getIntExtra(getString(R.string.USEROK_VALUE),0);
                if (value!=0){
                    finish();
                }
            }
        }
    }
}
