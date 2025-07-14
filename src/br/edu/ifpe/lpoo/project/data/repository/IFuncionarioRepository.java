package br.edu.ifpe.lpoo.project.data.repository;

import java.util.List;

import br.edu.ifpe.lpoo.project.entities.funcionario.Funcionario;

public interface IFuncionarioRepository {

	int inserir(Funcionario funcionario);

	boolean existe(Funcionario funcionario);

	void deletar(int Idfuncionario);

	void atualizar(Funcionario funcionario);

	Funcionario buscarPorId(int idFuncionario);

	List<Funcionario> buscarTodos();

}
