package br.edu.ifpe.lpoo.project.data.repository;

import br.edu.ifpe.lpoo.project.entities.funcionario.Funcionario;

public interface ILoginRepository {
	
	Funcionario buscarFuncionario(String funcionario);
}
