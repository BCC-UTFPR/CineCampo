package Interfaces;

import org.jvnet.hk2.annotations.Contract;

@Contract
public interface UsuariosInterface {
	String getUsuarios();
	String realizarCadastro(String usuario, String senha);
	String realizarLogin(String usuario, String senha);
}
