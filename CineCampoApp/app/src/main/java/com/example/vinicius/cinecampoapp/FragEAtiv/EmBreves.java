package com.example.vinicius.cinecampoapp.FragEAtiv;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.media.session.MediaController;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.app.Fragment;
import android.os.StrictMode;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.vinicius.cinecampoapp.Adapter.EmBreve;
import com.example.vinicius.cinecampoapp.Adapter.EmBreveAdapter;
import com.example.vinicius.cinecampoapp.Adapter.EmBreveAdapter2;
import com.example.vinicius.cinecampoapp.R;
import com.example.vinicius.cinecampoapp.Utils.JSONParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static android.R.id.list;

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
            URL url = new URL("http://138.68.15.190:8080/Servidor/filmes/breves ");
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
                // custom dialog
                final Dialog dialog = new Dialog(getActivity());
                dialog.setContentView(R.layout.fragment_em_breve_dialog);

                VideoView videoView =(VideoView) view.findViewById(R.id.videoView);
                MediaController mediaController= new MediaController(this);
                mediaController.setAnchorView(videoView);
                Uri uri=Uri.parse("rtsp://r7---sn-4g57kue6.googlevideo.com/Ck0LENy73wIaRAmk3cJBg-iaXhMYDSANFC3u0pRWMOCoAUIJbXYtZ29vZ2xlSARSBXdhdGNoYIaluaTkzciOVooBCzVxRjNraG5XcXdnDA==/D693A8E7577C3A29E60C292B42C9C87D7C25A565.762A63DC4CA0A028DA83256C6A79E5F160CBEDA3/yt6/1/video.3gp");
                videoView.setMediaController(mediaController);
                videoView.setVideoURI(uri);
                videoView.requestFocus();
                videoView.start();

//                TextView nome_minicurso = (TextView) dialog.findViewById(R.id.textViewNomeMinicurso);
//                nome_minicurso.setText(minicursos.getTitulo());
//                TextView local_minicurso = (TextView) dialog.findViewById(R.id.textViewLocal);
//                local_minicurso.setText(minicursos.getLocal());
//                TextView data_minicurso = (TextView) dialog.findViewById(R.id.textViewData);
//                data_minicurso.setText(minicursos.getData());
//                TextView palestrante_minicurso = (TextView) dialog.findViewById(R.id.textViewMinistrante);
//                palestrante_minicurso.setText(minicursos.getInstrutor());
//                TextView descricao_minicurso = (TextView) dialog.findViewById(R.id.textViewDescricao);
//                descricao_minicurso.setText(minicursos.getDescricao());
//
//                Button button_agendar = (Button) dialog.findViewById(R.id.f_agendar_button);
//                button_agendar.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        String titulo = minicursos.getTitulo();
//                        String local = minicursos.getLocal();
//                        String descricao = minicursos.getDescricao();
//
//                        String date = minicursos.getData();
//                        String[] separated_date = date.split("-");
//                        int ano = Integer.parseInt(separated_date[0]);
//                        int mes = Integer.parseInt(separated_date[1]);
//                        int dia = Integer.parseInt(separated_date[2]);
//
//                        String hour = minicursos.getHorario();
//                        String [] separated_hour = hour.split(":");
//                        int hours = Integer.parseInt(separated_hour[0]);
//                        int minutes = Integer.parseInt(separated_hour[1]);
//
//                        calendarMinicursos(titulo, local, descricao, ano, mes, dia, hours, minutes);
//                    }
//                });
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
