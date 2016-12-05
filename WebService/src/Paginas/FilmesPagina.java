package Paginas;

import java.io.File; 
import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import Database.FilmesDatabase;
import org.apache.commons.io.FileUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

@Path("/filmes")
public class FilmesPagina {
	FilmesDatabase database = new FilmesDatabase();
	Gson builder = new GsonBuilder().setPrettyPrinting().create();
	JsonParser parser = new JsonParser();


	@GET
	@Produces(MediaType.TEXT_HTML)
	public String paginaFilmes() throws IOException{
    	URL HTML = getClass().getResource("Filmes.html");
    	File arquivo = new File(HTML.getPath());
    	String resultado = FileUtils.readFileToString(arquivo, Charset.forName("UTF-8"));
    	return resultado;	
    }	

	@GET
	@Path("/atuais")
	@Produces(MediaType.APPLICATION_JSON)
	public String getAtuais(){
		JsonElement element = parser.parse(database.getFilmes("Atuais"));
		return builder.toJson(element);
	}
	
	@GET
	@Path("/breves")
	@Produces(MediaType.APPLICATION_JSON)
	public String getBreves(){
		JsonElement element = parser.parse(database.getFilmes("Breves"));
		return builder.toJson(element);
	}
	
	@GET
	@Path("/avaliar/{id}/{nota}")
	@Produces(MediaType.APPLICATION_JSON)
	public String setAvaliacao(@PathParam("id") String identificador, 
							   @PathParam("nota") String nota){
		JsonElement element = parser.parse(database.realizarAvaliacao(identificador, nota));
		return builder.toJson(element);
	}
}
