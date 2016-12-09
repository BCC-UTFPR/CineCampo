package com.example.vinicius.cinecampoapp.FragEAtiv;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.app.Fragment;
import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.vinicius.cinecampoapp.Adapter.EmBreve;
import com.example.vinicius.cinecampoapp.Adapter.EmBreveAdapter;
import com.example.vinicius.cinecampoapp.R;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EmBreves extends Fragment {

    View view;
    String result;

    public EmBreves() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_em_breve, container, false);

        if (Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        try {
            URL url = new URL("http://138.68.15.190:8080/distribuidos/filmes/breves");
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
        } catch (Exception e){
            System.out.println(" error"+e);
        }

        final List<EmBreve> EmBreve = new ArrayList<EmBreve>();

        try {
            JSONObject obj = new JSONObject(String.valueOf(createObjectFromString(result)));
            JSONArray m_jArry = obj.getJSONArray("Filmes");
            ArrayList<HashMap<String, String>> formList = new ArrayList<HashMap<String, String>>();
            HashMap<String, String> m_li;

            for (int i = 0; i < m_jArry.length(); i++) {

                JSONObject jo_inside = m_jArry.getJSONObject(i);
                String nome = jo_inside.getString("nome");
                String sinopse = jo_inside.getString("sinopse");
                String duracao = jo_inside.getString("duracao");
                String genero = jo_inside.getString("genero");
                String imagemUrl = jo_inside.getString("imagemurl");
                String videoUrl = jo_inside.getString("videourl");

                Log.i(nome, genero);

                EmBreve.add(new EmBreve(nome ,sinopse ,duracao ,genero ,imagemUrl, videoUrl));

                m_li = new HashMap<String, String>();
                m_li.put("nome", nome);
                m_li.put("sinopse", sinopse);
                m_li.put("duracao", duracao);
                m_li.put("genero", genero);
                m_li.put("imagemurl", imagemUrl);
                m_li.put("videourl", videoUrl);
                formList.add(m_li);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        EmBreveAdapter adapter = new EmBreveAdapter(getActivity(), R.layout.fragment_em_breve_item, EmBreve);
        final ListView Lista = (ListView) view.findViewById(R.id.listview_emBreve);
        Lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final EmBreve emBreve = EmBreve.get(position);
                final Dialog dialog = new Dialog(getActivity());
                dialog.setContentView(R.layout.fragment_em_breve_dialog);

                TextView nome_filme = (TextView) dialog.findViewById(R.id.textView_nome_filme);
                nome_filme.setText(emBreve.getNome());
                TextView genero = (TextView) dialog.findViewById(R.id.textView_genero);
                genero.setText(emBreve.getGenero());
                TextView duracao = (TextView) dialog.findViewById(R.id.textView_duracao);
                duracao.setText(emBreve.getDuracao());
                TextView sinopse  = (TextView) dialog.findViewById(R.id.textView_usuario_comentario);
                sinopse.setText(emBreve.getSinopse());
                ImageView capa = (ImageView) dialog.findViewById(R.id.imageView_capa);
                Picasso.with(getActivity()).load(emBreve.getImagem_capa()).into(capa);

                final String trailer_link = emBreve.getVideoUrl();

                Button trailer = (Button) dialog.findViewById(R.id.button_trailer);
                trailer.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent trailerIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(trailer_link));
                        startActivity(trailerIntent);
                    }
                });
                dialog.show();
            }
        });
        Lista.setAdapter(adapter);

        return view;
    }

    public static JSONObject createObjectFromString(String objectString) {
        try {
            return new JSONObject(objectString);
        } catch (JSONException e) {
            Log.e("MYAPP", e.toString());
        }
        return null;
    }
}
