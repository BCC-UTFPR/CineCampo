package Interfaces;
import org.jvnet.hk2.annotations.Contract;

@Contract
public interface FilmesInterface {
	String getFilmes(String tipo);
	String realizarAvaliacao(String identificador, String nota);
}
