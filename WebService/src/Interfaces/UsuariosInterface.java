package Interfaces;

public interface UsuariosInterface {
	String getUsuarios();
	String realizarCadastro(String usuario, String senha);
	String realizarLogin(String usuario, String senha);
}
