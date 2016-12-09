package com.example.vinicius.cinecampoapp.FragEAtiv;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.app.Fragment;
import android.os.StrictMode;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vinicius.cinecampoapp.Adapter.Comentario;
import com.example.vinicius.cinecampoapp.Adapter.ComentarioAdapter;
import com.example.vinicius.cinecampoapp.Adapter.EmExibicao;
import com.example.vinicius.cinecampoapp.Adapter.EmExibicaoAdapter;
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

import static com.example.vinicius.cinecampoapp.R.id.ratingBar;


public class Exibicao extends Fragment {

    View view;
    String result;
    String result_comentarios;
    int nota_usuario;
    String usuario_main;

    public Exibicao() {
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        usuario_main = this.getArguments().getString("usuario");
        view = inflater.inflate(R.layout.fragment_exibicao, container, false);

        if (Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        try {
            URL url = new URL("http://138.68.15.190:8080/distribuidos/filmes/atuais");
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
                final EmExibicao emExibicao_ = emExibicao.get(position);
                final Dialog dialog = new Dialog(getActivity());
                dialog.setContentView(R.layout.fragment_exibicao_dialog);

                final TextView nome_filme = (TextView) dialog.findViewById(R.id.textView_nome_filme);
                nome_filme.setText(emExibicao_.getNome());
                final TextView genero = (TextView) dialog.findViewById(R.id.textView_genero);
                genero.setText((emExibicao_.getGenero()));
                TextView duracao = (TextView) dialog.findViewById(R.id.textView_duracao);
                duracao.setText(emExibicao_.getDuracao());
                TextView sessao = (TextView) dialog.findViewById(R.id.textView_sessao);
                sessao.setText(emExibicao_.getSessao());
                TextView sinopse = (TextView) dialog.findViewById(R.id.textView_usuario_comentario);
                sinopse.setText(emExibicao_.getSinopse());
                ImageView capa = (ImageView) dialog.findViewById(R.id.imageView_capa);
                Picasso.with(getActivity()).load(emExibicao_.getImagemurl()).into(capa);

                Button trailer = (Button) dialog.findViewById(R.id.button_trailer);
                Button votar = (Button) dialog.findViewById(R.id.button_votar);
                Button comentarios = (Button) dialog.findViewById(R.id.button_comentarios);

                final EditText comentario = (EditText) dialog.findViewById(R.id.editText_comentario);

                final RatingBar nota = (RatingBar) dialog.findViewById(ratingBar);

                nota.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View view, MotionEvent motionEvent) {
                        if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                            float touchPositionX = motionEvent.getX();
                            float width = nota.getWidth();
                            float starsf = (touchPositionX / width) * 5.0f;
                            int stars = (int) starsf + 1;
                            nota.setRating(stars);

                            nota_usuario = (int) nota.getRating();
                            view.setPressed(false);
                        }
                        if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                            view.setPressed(true);
                        }
                        if (motionEvent.getAction() == MotionEvent.ACTION_CANCEL) {
                            view.setPressed(false);
                        }
                        return true;
                    }
                });

                final String trailer_link = emExibicao_.getVideourl();
                trailer.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent trailerIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(trailer_link));
                        startActivity(trailerIntent);
                    }
                });

                votar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                            //TODO: Passar a nota quando o felipe terminar de arrumar a votação
                            //TODO: Chamar a URL de votação quando o botão de votar foi clicado
                        try {
                            String aux = comentario.getText().toString();
                            if (TextUtils.isEmpty(aux)) {
                                Toast.makeText(getActivity(), "O comentário está vazio.", Toast.LENGTH_SHORT).show();
                            }
                            if (nota_usuario == 0){
                                Toast.makeText(getActivity(), "Não foi atribuído nenhuma nota.", Toast.LENGTH_SHORT).show();
                            }

                            URL url1 = new URL("http://138.68.15.190:8080/distribuidos/filmes/avaliar/" +
                            String.valueOf(emExibicao_.getId()) + "/" + String.valueOf(nota_usuario));
                            HttpURLConnection urlConnection_avaliar = (HttpURLConnection) url1.openConnection();

                            URL url2 = new URL("http://138.68.15.190:8080/distribuidos/comentarios/comentar/" +
                            String.valueOf(emExibicao_.getId()) + "/" + usuario_main + "/" + comentario.getText());
                            HttpURLConnection urlConnection_comentar = (HttpURLConnection) url2.openConnection();

                            InputStream in1 = new BufferedInputStream(urlConnection_avaliar.getInputStream());
                            BufferedReader reader1 = new BufferedReader(new InputStreamReader(in1, "UTF-8"), 8);
                            StringBuilder sb1 = new StringBuilder();

                            InputStream in2 = new BufferedInputStream(urlConnection_comentar.getInputStream());
                            BufferedReader reader2 = new BufferedReader(new InputStreamReader(in2, "UTF-8"), 8);
                            StringBuilder sb2 = new StringBuilder();

                            String line = null;
                            while ((line = reader2.readLine()) != null)
                            {
                                sb2.append(line + "\n");
                            }
                            result = sb2.toString();
                            if (result.contains("SUCESSO_AO_COMENTAR")) {
                                Toast.makeText(getActivity(), "Avaliação enviada com sucesso. Obrigado por avaliar!", Toast.LENGTH_LONG).show();
                                dialog.dismiss();
                            } else {
                                Toast.makeText(getActivity(), "Não foi possível registrar seu comentário! :(", Toast.LENGTH_LONG).show();
                                dialog.dismiss();
                            }
                        } catch (Exception e){
                            System.out.println(" error"+e);
                        }
                    }
                });

                comentarios.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        final Dialog dialog_comentario = new Dialog(getActivity());
                        dialog_comentario.setContentView(R.layout.fragment_comentario);
                        try {
                            URL url = new URL("http://138.68.15.190:8080/distribuidos/comentarios/listar/" + emExibicao_.getId());
                            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

                            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                            BufferedReader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"), 8);
                            StringBuilder sb = new StringBuilder();

                            String line = null;
                            while ((line = reader.readLine()) != null)
                            {
                                sb.append(line + "\n");
                            }
                            result_comentarios = sb.toString();
                        } catch (Exception e){
                            System.out.println(" error"+e);
                        }

                        final List<Comentario> comentarios1 = new ArrayList<Comentario>();

                        try {
                            JSONObject obj = new JSONObject(String.valueOf(createObjectFromString(result_comentarios)));
                            JSONArray m_jArry = obj.getJSONArray("Comentários");
                            ArrayList<HashMap<String, String>> formList = new ArrayList<HashMap<String, String>>();
                            HashMap<String, String> m_li;

                            for (int i = 0; i < m_jArry.length(); i++) {

                                JSONObject jo_inside = m_jArry.getJSONObject(i);
                                String nome = jo_inside.getString("usuario");
                                String comentario = jo_inside.getString("comentario");

                                comentarios1.add(new Comentario(nome, comentario));

                                m_li = new HashMap<String, String>();
                                m_li.put("nome", nome);
                                m_li.put("comentario", comentario);
                                formList.add(m_li);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        ComentarioAdapter adapter = new ComentarioAdapter(getActivity(), R.layout.fragment_comentario_item, comentarios1);
                        final ListView Lista_comentarios = (ListView) dialog_comentario.findViewById(R.id.listView_comentarios);
                        Lista_comentarios.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                System.out.println(comentarios1.get(i));
                            }
                        });
                        dialog_comentario.show();
                        Lista_comentarios.setAdapter(adapter);
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