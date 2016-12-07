package Paginas;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.commons.io.FileUtils;

import Database.ComentariosDatabase;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;


@Path("/comentarios")
public class ComentariosPagina {
	private ComentariosDatabase database = new ComentariosDatabase();
	private Gson builder = new GsonBuilder().setPrettyPrinting().create();
	private JsonParser parser = new JsonParser();
	private File arquivo;
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String paginaFilmes() throws IOException{
    	URL HTML = getClass().getResource("Comentarios.html");
    	arquivo = new File(HTML.getPath());
    	String resultado = FileUtils.readFileToString(arquivo, Charset.forName("UTF-8"));
    	return resultado;	
    }	

	@GET
	@Path("/listar/{f_identificador}")
	@Produces(MediaType.APPLICATION_JSON)
	public String listarComentarios(@PathParam("f_identificador") String f_identificador){
		JsonElement element = parser.parse(database.getComentarios(f_identificador));
		return builder.toJson(element);
	}

	@GET
	@Path("/comentar/{f_identificador}/{usuario}/{comentario}")
	@Produces(MediaType.APPLICATION_JSON)
	public String cadastrarComentario(@PathParam("f_identificador") String f_identificador,
			@PathParam("usuario") String usuario,
			@PathParam("comentario") String comentario){
		JsonElement element = parser.parse(database.realizarComentario(f_identificador, usuario, comentario));
		return builder.toJson(element);
	}
	
}
