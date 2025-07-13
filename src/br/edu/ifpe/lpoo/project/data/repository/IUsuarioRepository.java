package br.edu.ifpe.lpoo.project.data.repository;

import java.util.List;

import br.edu.ifpe.lpoo.project.entities.user.Usuario;

public interface IUsuarioRepository {
	
	int inserir(Usuario usuario);
	
	boolean existe(Usuario usuario);

	Usuario buscarPorId(int idUsuario);

	void deletar(int Usuario);

	List<Usuario> buscarTodos();

	List<Usuario> buscarPorTermo(String termo);

	void atualizar(Usuario usuario);
}
