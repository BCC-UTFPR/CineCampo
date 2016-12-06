package Paginas;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.commons.io.FileUtils;

@Path("/")
public class InicialPagina {
	private File arquivo;
	
    @GET
    @Produces(MediaType.TEXT_HTML)
    public String sayHtmlHello() throws IOException  {
    	URL HTML = getClass().getResource("Principal.html");
    	arquivo = new File(HTML.getPath());
    	String resultado = FileUtils.readFileToString(arquivo, Charset.forName("UTF-8"));
    	return resultado;
    }  
}
