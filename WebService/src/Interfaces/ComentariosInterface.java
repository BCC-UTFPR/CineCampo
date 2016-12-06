package Interfaces;

import org.jvnet.hk2.annotations.Contract;

@Contract
public interface ComentariosInterface {
	String getComentarios(String f_identificador);
	String realizarComentario(String f_identificador, String usuario, String comentario);
}
