package com.example.vinicius.cinecampoapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    final Context context = this;
    TextView textCadastro;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login = (Button) findViewById(R.id.button_login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(i);
            }
        });

        textCadastro = (TextView) findViewById(R.id.textView_cadastro);
        textCadastro.setPaintFlags(textCadastro.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        textCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater li = LayoutInflater.from(context);
                View cadastroView = li.inflate(R.layout.dialog_cadastro, null);

                AlertDialog.Builder cadastroDialog = new AlertDialog.Builder(context);

                cadastroDialog.setView(cadastroView);

                AlertDialog ad = cadastroDialog.create();
                ad.show();
            }
        });
    }
}
