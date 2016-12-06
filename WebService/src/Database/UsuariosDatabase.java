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

import Interfaces.DatabaseInterface;
import Interfaces.JSONInterface;
import Interfaces.UsuariosInterface;

@Service
public class UsuariosDatabase implements UsuariosInterface, DatabaseInterface, JSONInterface {
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
	public String getUsuarios() {
		try {
			database = conectar();
			String query = "SELECT * FROM Usuarios";
			Statement state = database.createStatement();
			ResultSet resultado = state.executeQuery(query);
			resposta = getJSON(resultado, "Usuários");
			database.close();	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resposta;
	}
	
	@Override
	public String realizarCadastro(String usuario, String senha) {
		try {
			database = conectar();
			String query = "INSERT INTO Usuarios (nome, senha) VALUES (\"" + usuario + "\", \"" + senha + "\")";
			Statement state = database.createStatement();
			int resultado = state.executeUpdate(query);
		
			JSONObject json = new JSONObject();
			if (resultado != 0){
				json.put("STATUS", "SUCESSO_AO_CADASTRAR");
				resposta = json.toString();
			} else {
				json.put("STATUS", "ERRO_AO_CADASTRAR");	
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
	public String realizarLogin(String usuario, String senha) {
		try {
			database = conectar();
			String query = "SELECT * FROM Usuarios WHERE BINARY nome = \"" + usuario + "\" AND BINARY senha = \"" + senha + "\";";
			Statement state = database.createStatement();
			ResultSet resultado = state.executeQuery(query);
			boolean existeUsuario = resultado.next();

			JSONObject json = new JSONObject();
			if (existeUsuario == true){
				json.put("STATUS", "SUCESSO_AO_LOGAR");
				resposta = json.toString();				
			} else {
				json.put("STATUS", "ERRO_AO_LOGAR");
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
				json.put("nome", r.getString("nome"));
				json.put("senha", r.getString("senha"));
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
