package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.inject.Inject;
import javax.inject.Named;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jvnet.hk2.annotations.Service;

import Interfaces.ComentariosInterface;
import Interfaces.DatabaseInterface;
import Interfaces.JSONInterface;

@Service
public class ComentariosDatabase implements ComentariosInterface, DatabaseInterface, JSONInterface {
	private Connection database;
	private String resposta;
	
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
	
	@Override
	public String getComentarios(String f_identificador) {
		try {
			database = conectar();
			String query = "SELECT * FROM Comentarios WHERE BINARY f_identificador = \"" + f_identificador + "\"";
			Statement state = database.createStatement();
			ResultSet resultado = state.executeQuery(query);
			resposta = getJSON(resultado,"Comentários");
			database.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return resposta;
	}

	@Override
	public String realizarComentario(String f_identificador, String usuario,
			String comentario) {
		try {
			database = conectar();
			Statement state = database.createStatement();
			String query_usuario = "SELECT * FROM Usuarios WHERE BINARY nome = \"" + usuario + "\"";
			ResultSet resultado_usuario = state.executeQuery(query_usuario);
			boolean existeUsuario = resultado_usuario.next();

			JSONObject json = new JSONObject();
			if (existeUsuario == true){
				String query_filme = "SELECT * FROM Filmes WHERE BINARY identificador = \"" + f_identificador + "\"";
				ResultSet resultado_filme = state.executeQuery(query_filme);
				boolean existeFilme = resultado_filme.next();

				if (existeFilme == true){
					String query = "INSERT INTO Comentarios (usuario, comentario, f_identificador) VALUES (\"" + usuario + "\",\"" + comentario + "\",\"" + f_identificador + "\")"; 
					int resultado = state.executeUpdate(query);	
					if (resultado != 0){
						json.put("STATUS", "SUCESSO_AO_COMENTAR");
						resposta = json.toString();
					} else {
						json.put("STATUS", "ERRO_AO_COMENTAR");
						json.put("MOTIVO", "ERRO_AO_INSERIR");	
						resposta = json.toString();
					}		
				} else {
					json.put("STATUS", "ERRO_AO_COMENTAR");
					json.put("MOTIVO", "FILME_INVALIDO");	
					resposta = json.toString();					
				}
			} else {
				json.put("STATUS", "ERRO_AO_COMENTAR");
				json.put("MOTIVO", "USUARIO_INVALIDO");	
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

	@Override
	public String getJSON(ResultSet r, String tipo) {
		JSONObject raiz = new JSONObject();
		JSONArray array = new JSONArray();
		
		try {
			while(r.next()){
				JSONObject json = new JSONObject();
				json.put("usuario", r.getString("usuario"));
				json.put("comentario", r.getString("comentario"));
				array.put(json);
			}
			raiz.put(tipo, array);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return raiz.toString();
	}
}
