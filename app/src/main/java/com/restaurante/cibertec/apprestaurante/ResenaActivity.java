package com.restaurante.cibertec.apprestaurante;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.restaurantmodel.dao.ReseniaDAO;
import com.example.restaurantmodel.impl.ReseniaDaoImpl;
import com.example.restaurantmodel.model.Commentary;
import com.example.restaurantmodel.model.Restaurant;
import com.example.restaurantmodel.model.User;

import java.util.Date;

public class ResenaActivity extends AppCompatActivity {

    EditText resenaText;
    Spinner rankSpinner;
    EditText priceText;

    SharedPreferences appPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resena);
        appPreferences = getSharedPreferences(getString(R.string.preferences),MODE_PRIVATE);

        resenaText = (EditText)findViewById(R.id.resena);
        rankSpinner =(Spinner)findViewById(R.id.ranking_spinner);
        priceText= (EditText)findViewById(R.id.price);

    }

    public void agregarResena(View view) {
        ReseniaDAO dao = new ReseniaDaoImpl(this);
        Commentary comment = new Commentary();

        User user = new User();
        user.setId(appPreferences.getInt(getString(R.string.userid),0));
        comment.setUser(user);
        Restaurant rest = new Restaurant();
        rest.setId(appPreferences.getInt(getString(R.string.restaurantid),0));
        rest.setName(appPreferences.getString(getString(R.string.restaurant_name),getString(R.string.default_string)));
        comment.setRestaurant(rest);
        double price = Double.parseDouble(priceText.getText().toString());
        comment.setPrice(price);
        comment.setDate(new Date());
        comment.setComment(resenaText.getText().toString());
        String value = rankSpinner.getSelectedItem().toString();
        comment.setRanking(Integer.parseInt(value));

        int id = (int)dao.insertarResenia(comment);
        Intent intent = new Intent();
        setResult(RESULT_OK,intent);
        finish();
    }
}
