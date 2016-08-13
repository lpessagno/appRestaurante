package com.restaurante.cibertec.apprestaurante;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.restaurantmodel.dao.UserDao;
import com.example.restaurantmodel.impl.UserDaoImpl;
import com.example.restaurantmodel.model.User;

public class SignInActivity extends AppCompatActivity {

    EditText usernameText;
    EditText emailText;
    EditText passwordText;
    EditText confirmPasswordText;
    EditText phoneText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        usernameText=(EditText)findViewById(R.id.username);
        emailText=(EditText)findViewById(R.id.email);
        passwordText=(EditText)findViewById(R.id.pwd);
        confirmPasswordText=(EditText)findViewById(R.id.pwd2);
        phoneText=(EditText)findViewById(R.id.phone);
    }

    public void onSignIn(View view) {
        String pwd1 = passwordText.getText().toString();
        String pwd2 = confirmPasswordText.getText().toString();
        //validar que el password es el mismo
        if (!pwd1.equals(pwd2)){
            Toast.makeText(this, R.string.clave_invalida,Toast.LENGTH_SHORT).show();
        } else {
            signInUser();
        }
    }

    private void signInUser() {
        UserDao dao = new UserDaoImpl(this);

        User user = new User();
        user.setName(usernameText.getText().toString());
        user.setEmail(emailText.getText().toString());
        user.setPassword(passwordText.getText().toString());
        user.setPhone(phoneText.getText().toString());
        int userid= (int)dao.insert(user);
        if(userid == 0){
            String msg = getString(R.string.signin_error);
            Toast.makeText(this,msg,Toast.LENGTH_LONG).show();
        } else {
            //setear preferencias
            SharedPreferences appPreferences = getSharedPreferences(getString(R.string.preferences),MODE_PRIVATE);;
            SharedPreferences.Editor editor = appPreferences.edit();
            editor.putString(getString(R.string.user),user.getName());
            editor.putString(getString(R.string.password),user.getPassword());
            editor.putInt(getString(R.string.userid),user.getId());
            editor.commit();

            Intent intent = new Intent();
            intent.putExtra(getString(R.string.USEROK_VALUE),userid);
            setResult(RESULT_OK,intent);
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("ONDESTROY","HOLA");
    }
}
