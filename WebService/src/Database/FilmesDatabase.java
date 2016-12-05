package Database;
import java.sql.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jvnet.hk2.annotations.Service;

import Interfaces.DatabaseInterface;
import Interfaces.FilmesInterface;
import Interfaces.JSONInterface;
 
@Service
public class FilmesDatabase implements FilmesInterface, DatabaseInterface, JSONInterface {
	private Connection database;
	private String resposta;
	private String tabela;
	
	@Override
	public Connection conectar(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			database = DriverManager.getConnection(
					"jdbc:mysql://localhost/cinecampo?"
					+ "user=root&"
					+ "password=asafaster");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return database;
	}
	
	public String getFilmes(String tipo) {
		try {
			if(tipo.equals("Atuais")){
				tabela = "Filmes";
			} else {
				tabela = "Breves";	
			}
			
			database = conectar();
			String query = "SELECT * FROM " + tabela;
			Statement state = database.createStatement();
			ResultSet resultado = state.executeQuery(query);
			resposta = getJSON(resultado, tabela);
			database.close();
			
		} catch (SQLException e){
			System.out.println("SQLException: " + e.getMessage());
		} 
		return resposta;
	}

	public String getJSON(ResultSet r, String tabela) {
		JSONObject raiz = new JSONObject();
		JSONArray array = new JSONArray();
		try {
			while(r.next()){
				JSONObject json = new JSONObject();
				json.put("id", r.getString("identificador"));
				json.put("nome", r.getString("nome"));
				json.put("sinopse",r.getString("sinopse"));
				json.put("duracao", r.getString("duracao"));
				json.put("genero", r.getString("genero"));
				json.put("imagemurl", r.getString("imagemurl"));
				json.put("videourl", r.getString("videourl"));;
				
				if(tabela.equals("Filmes")){
					json.put("avaliacao", r.getInt("avaliacao"));
					json.put("sessao", r.getString("sessao"));
					json.put("tresdimensoes", r.getString("tresdimensoes"));
				}
				array.put(json);
			}
			raiz.put("Filmes",array);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		return raiz.toString();
	}
	
	public String realizarAvaliacao(String identificador, String nota){
		try {
			database = conectar();
			tabela = "Filmes";
			
			String query_avaliar = "UPDATE " + tabela 
					+ " SET avaliacao = avaliacao + " + nota
					+ " WHERE identificador = " + identificador;
			
			Statement state = database.createStatement();
			int resultado = state.executeUpdate(query_avaliar);
			
			String query_get_avaliacao = "SELECT avaliacao FROM " + tabela + " WHERE identificador = " + identificador;
			ResultSet avaliacao = state.executeQuery(query_get_avaliacao);
			
			JSONObject json = new JSONObject();
			if (resultado != 0){
				json.put("STATUS", "SUCESSO_AO_AVALIAR");
				while(avaliacao.next()){
					json.put("NOTA_ATUAL", avaliacao.getString("avaliacao"));
				}
				resposta = json.toString();
			} else {
				json.put("STATUS", "ERRO_AO_AVALIAR");	
				resposta = json.toString();
			}
			
			database.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return resposta;
	}
}
