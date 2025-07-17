package br.edu.ifpe.lpoo.project.business.gerenciamento;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import br.edu.ifpe.lpoo.project.data.implement.AtualizacaoSistemaRepsitory;
import br.edu.ifpe.lpoo.project.data.implement.EmprestimoRepository;
import br.edu.ifpe.lpoo.project.data.implement.UsuarioRepository;
import br.edu.ifpe.lpoo.project.entities.gerenciamento.Emprestimo;
import br.edu.ifpe.lpoo.project.entities.gerenciamento.TaxaService;
import br.edu.ifpe.lpoo.project.entities.user.Usuario;
import br.edu.ifpe.lpoo.project.enums.StatusEmprestimo;
import br.edu.ifpe.lpoo.project.enums.StatusUsuario;
import br.edu.ifpe.lpoo.project.exception.BusinessException;
import br.edu.ifpe.lpoo.project.exception.ExceptionDb;

public final class AtualizaMultasAtrasoService {
	
	public static void atualizarAtrazoEMultar() {	
		
		AtualizacaoSistemaRepsitory atualizacaoSistemaRepsitory = new AtualizacaoSistemaRepsitory();
		
		LocalDate dataUltimaAtualizacao = atualizacaoSistemaRepsitory.buscarUltimaAtualizacao();
		LocalDate hojeAtualizar = LocalDate.now();
		
		if(dataUltimaAtualizacao.equals(hojeAtualizar)) {
			return;
		}
		
		atualizacaoSistemaRepsitory.atualizarDataUltimaAtualizacao(hojeAtualizar);
		
		try {
			EmprestimoRepository emprestimoRepository = new EmprestimoRepository();
			UsuarioRepository  usuarioRepository = new UsuarioRepository();
			
			emprestimoRepository.atualizarStatusAtrasados();
			
			List<Emprestimo> emprestimos = emprestimoRepository.listarTodosAtrazados();
			
			if(emprestimos.isEmpty()) {
				return;
			}
			
			LocalDate hoje = LocalDate.now();
			
			for (Emprestimo emprestimo : emprestimos) {
				
				LocalDate prazo = emprestimo.getDataDevolucao();
				
				int atraso = (int) ChronoUnit.DAYS.between(prazo, hoje);
				
				Usuario usuario = usuarioRepository.buscarPorId(emprestimo.getIdUsuario());
				double debitoExistente = usuario.getDebito();
				usuario.setDebito(atraso * TaxaService.TAXA_ATRASO + debitoExistente);
				usuario.setStatusUsuario(StatusUsuario.SUSPENSO);
				usuarioRepository.atualizar(usuario);
				emprestimo.setStatusEmprestimo(StatusEmprestimo.ATRASADO);
				emprestimoRepository.atualizar(emprestimo);
				
			}
		} catch (ExceptionDb e) {
			throw new BusinessException("Erro ao atualizar débitos e atrasos. Causado por: " + e.getMessage());
		}
		
	}
	
}
