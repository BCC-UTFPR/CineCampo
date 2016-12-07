package com.example.vinicius.cinecampoapp.FragEAtiv;

import android.os.Build;
import android.os.Bundle;
import android.app.Fragment;
import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.vinicius.cinecampoapp.Adapter.EmBreve;
import com.example.vinicius.cinecampoapp.Adapter.EmBreveAdapter;
import com.example.vinicius.cinecampoapp.Adapter.EmExibicao;
import com.example.vinicius.cinecampoapp.Adapter.EmExibicaoAdapter;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Exibicao extends Fragment {

    View view;
    String result;

    public Exibicao() {
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_exibicao, container, false);

        if (Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        try {
            URL url = new URL("http://138.68.15.190:8080/Servidor/filmes/atuais");
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
            //System.out.println("result"+result);
        } catch (Exception e){
            System.out.println(" error"+e);
        }

        final List<EmExibicao> emExibicao = new ArrayList<EmExibicao>();

        try {
            JSONObject obj = new JSONObject(String.valueOf(createObjectFromString(result)));
            JSONArray m_jArry = obj.getJSONArray("Filmes");
            ArrayList<HashMap<String, String>> formList = new ArrayList<HashMap<String, String>>();
            HashMap<String, String> m_li;

            for (int i = 0; i < m_jArry.length(); i++) {

                JSONObject jo_inside = m_jArry.getJSONObject(i);
                int id = jo_inside.getInt("id");
                String nome = jo_inside.getString("nome");
                String sessao = jo_inside.getString("sessao");
                String sinopse = jo_inside.getString("sinopse");
                String duracao = jo_inside.getString("duracao");
                String genero = jo_inside.getString("genero");
                String imagemUrl = jo_inside.getString("imagemurl");
                String videoUrl = jo_inside.getString("videourl");
                String d3 = jo_inside.getString("tresdimensoes");
                int avaliacao = jo_inside.getInt("avaliacao");

                emExibicao.add(new EmExibicao(id, nome, duracao, genero, sinopse, d3, sessao, avaliacao, videoUrl, imagemUrl));

                m_li = new HashMap<String, String>();
                m_li.put("id", String.valueOf(id));
                m_li.put("nome", nome);
                m_li.put("sessao", sessao);
                m_li.put("sinopse", sinopse);
                m_li.put("duracao", duracao);
                m_li.put("genero", genero);
                m_li.put("imagemurl", imagemUrl);
                m_li.put("videourl", videoUrl);
                m_li.put("tresdimensoes", d3);
                m_li.put("avaliacao", String.valueOf(avaliacao));
                formList.add(m_li);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        EmExibicaoAdapter adapter = new EmExibicaoAdapter(getActivity(), R.layout.fragment_exibicao_item, emExibicao);
        final ListView Lista = (ListView) view.findViewById(R.id.listView_emExibicao);
        Lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                System.out.println(emExibicao.get(position));
            }
        });
        System.out.println(Lista);
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
