package com.example.vinicius.cinecampoapp.FragEAtiv;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Build;
import android.os.StrictMode;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vinicius.cinecampoapp.Adapter.EmBreve;
import com.example.vinicius.cinecampoapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LoginActivity extends AppCompatActivity {

    String result;
    final Context context = this;
    TextView textCadastro;
    EditText usuario_dialog;
    EditText senha_dialog;
    EditText usuario_login;
    EditText senha_login;
    Button login;
    Button cadastrar;
    ImageButton close_dialog;

    boolean dismiss = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if (Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        login = (Button) findViewById(R.id.button_login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                usuario_login = (EditText) findViewById(R.id.editText_usuario);
                senha_login = (EditText) findViewById(R.id.editText_senha);

                String campo_usuario = String.valueOf(usuario_login.getText());
                String campo_senha = String.valueOf(senha_login.getText());
                String md5_senha = md5(campo_senha);

                try {
                    if(campo_usuario.matches("") || campo_senha.matches("")){
                        Toast.makeText(LoginActivity.this, "Uns dos campos acima não está preenchido!", Toast.LENGTH_SHORT).show();
                    }
                    URL url = new URL("http://138.68.15.190:8080/distribuidos/usuarios/logar/"
                            + campo_usuario + "/" + md5_senha);
                    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

                    InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"), 8);
                    StringBuilder sb = new StringBuilder();

                    String line = null;
                    while ((line = reader.readLine()) != null)
                    {
                        sb.append(line + "\n");
                    }
                    result = sb.toString();
                    if(result.contains("SUCESSO")){
                        Toast.makeText(LoginActivity.this, "Logado com sucesso!", Toast.LENGTH_LONG).show();
                        Intent i = new Intent(LoginActivity.this, MainActivity.class);
                        i.putExtra("usuario", campo_usuario);
                        startActivity(i);
                    } else {
                        Toast.makeText(LoginActivity.this, "Usuário ou senha incorretos. Tente novamente!", Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e){
                    System.out.println(" error"+e);
                }
            }
        });

        textCadastro = (TextView) findViewById(R.id.textView_cadastro);
        textCadastro.setPaintFlags(textCadastro.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        textCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater li = LayoutInflater.from(context);
                View cadastroView = li.inflate(R.layout.dialog_cadastro, null);

                usuario_dialog = (EditText) cadastroView.findViewById(R.id.editText_usuario);
                senha_dialog = (EditText) cadastroView.findViewById(R.id.editText_senha);
                cadastrar = (Button) cadastroView.findViewById(R.id.button_cadastrar);
                close_dialog = (ImageButton) cadastroView.findViewById(R.id.imageButton_close);

                final AlertDialog.Builder cadastroDialog = new AlertDialog.Builder(context);
                cadastroDialog.setView(cadastroView);
                final AlertDialog ad = cadastroDialog.create();

                cadastrar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String campo_usuario = String.valueOf(usuario_dialog.getText());
                        String campo_senha = String.valueOf(senha_dialog.getText());
                        String md5_senha = md5(campo_senha);

                        try {
                            if(campo_usuario.matches("") || campo_senha.matches("")){
                                Toast.makeText(LoginActivity.this, "Uns dos campos acima não está preenchido!", Toast.LENGTH_SHORT).show();
                                dismiss = false;
                            }
                            URL url = new URL("http://138.68.15.190:8080/distribuidos/usuarios/cadastrar/"
                            + campo_usuario + "/" + md5_senha);
                            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

                            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                            BufferedReader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"), 8);
                            StringBuilder sb = new StringBuilder();

                            String line = null;
                            while ((line = reader.readLine()) != null)
                            {
                                sb.append(line + "\n");
                            }
                            result = sb.toString();
                            if(result.contains("SUCESSO")){
                                Toast.makeText(LoginActivity.this, "Cadastro efetuado com sucesso!", Toast.LENGTH_LONG).show();
                                dismiss = true;
                            } else if(result.contains("USUARIO_JA_EXISTE")) {
                                Toast.makeText(LoginActivity.this, "Este nome de usuário já existe!", Toast.LENGTH_LONG).show();
                                dismiss = false;
                            } else {
                                Toast.makeText(LoginActivity.this, "Erro ao cadastrar. Tente novamente!", Toast.LENGTH_SHORT).show();
                                dismiss = false;
                            }
                        } catch (Exception e){
                            System.out.println(" error"+e);
                        }
                        if(dismiss == true) {
                            ad.dismiss();
                        }
                    }
                });

                close_dialog.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ad.dismiss();
                    }
                });

                ad.show();
            }
        });

        //        try {
//            URL url = new URL("http://138.68.15.190:8080/Servidor/filmes/breves ");
//            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
//
//            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
//            BufferedReader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"), 8);
//            StringBuilder sb = new StringBuilder();
//
//            String line = null;
//            while ((line = reader.readLine()) != null)
//            {
//                sb.append(line + "\n");
//            }
//            result = sb.toString();
//        } catch (Exception e){
//            System.out.println(" error"+e);
//        }
//
//        final List<EmBreve> EmBreve = new ArrayList<EmBreve>();
//
//        try {
//            JSONObject obj = new JSONObject(String.valueOf(createObjectFromString(result)));
//            JSONArray m_jArry = obj.getJSONArray("Filmes");
//            ArrayList<HashMap<String, String>> formList = new ArrayList<HashMap<String, String>>();
//            HashMap<String, String> m_li;
//
//            for (int i = 0; i < m_jArry.length(); i++) {
//
//                JSONObject jo_inside = m_jArry.getJSONObject(i);
//                String nome = jo_inside.getString("nome");
//                String sinopse = jo_inside.getString("sinopse");
//                String duracao = jo_inside.getString("duracao");
//                String genero = jo_inside.getString("genero");
//                String imagemUrl = jo_inside.getString("imagemurl");
//                String videoUrl = jo_inside.getString("videourl");
//
//                Log.i(nome, genero);
//
//                EmBreve.add(new EmBreve(nome ,sinopse ,duracao ,genero ,imagemUrl, videoUrl));
//
//                m_li = new HashMap<String, String>();
//                m_li.put("nome", nome);
//                m_li.put("sinopse", sinopse);
//                m_li.put("duracao", duracao);
//                m_li.put("genero", genero);
//                m_li.put("imagemurl", imagemUrl);
//                m_li.put("videourl", videoUrl);
//                formList.add(m_li);
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
    }

    public static JSONObject createObjectFromString(String objectString) {
        try {
            return new JSONObject(objectString);
        } catch (JSONException e) {
            Log.e("MYAPP", e.toString());
        }
        return null;
    }

    public static final String md5(final String s) {
        final String MD5 = "MD5";
        try {
            // Create MD5 Hash
            MessageDigest digest = java.security.MessageDigest
                    .getInstance(MD5);
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuilder hexString = new StringBuilder();
            for (byte aMessageDigest : messageDigest) {
                String h = Integer.toHexString(0xFF & aMessageDigest);
                while (h.length() < 2)
                    h = "0" + h;
                hexString.append(h);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
}
