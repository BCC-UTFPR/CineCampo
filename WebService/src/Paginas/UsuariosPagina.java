package Paginas;

import java.io.File; 
import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

import org.apache.commons.io.FileUtils;

import Database.UsuariosDatabase;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

@Path("/usuarios")
public class UsuariosPagina {
	private UsuariosDatabase database = new UsuariosDatabase();
	private Gson builder = new GsonBuilder().setPrettyPrinting().create();
	private JsonParser parser = new JsonParser();
	private File arquivo;
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String paginaUsuarios() throws IOException{
    	URL HTML = getClass().getResource("Usuarios.html");
    	arquivo = new File(HTML.getPath());
    	String resultado = FileUtils.readFileToString(arquivo, Charset.forName("UTF-8"));
    	return resultado;
	}

	@GET
	@Path("/listar")
	@Produces(MediaType.APPLICATION_JSON)
	public String listarUsuario(){
		JsonElement element = parser.parse(database.getUsuarios());
		return builder.toJson(element);
	}
	
	@GET
	@Path("/cadastrar/{usuario}/{senha}")
	@Produces(MediaType.APPLICATION_JSON)
	public String cadastrarUsuario(@PathParam("usuario") String usuario, 
							   @PathParam("senha") String senha){
		JsonElement element = parser.parse(database.realizarCadastro(usuario, senha));
		return builder.toJson(element);
	}

	@GET
	@Path("/logar/{usuario}/{senha}")
	@Produces(MediaType.APPLICATION_JSON)
	public String logarUsuario(@PathParam("usuario") String usuario, 
							   @PathParam("senha") String senha){
		JsonElement element = parser.parse(database.realizarLogin(usuario, senha));
		return builder.toJson(element);	}
}
