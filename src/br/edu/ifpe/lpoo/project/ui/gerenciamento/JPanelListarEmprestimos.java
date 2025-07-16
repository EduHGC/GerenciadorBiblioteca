package br.edu.ifpe.lpoo.project.ui.gerenciamento;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import br.edu.ifpe.lpoo.project.business.gerenciamento.EmprestimoController;
import br.edu.ifpe.lpoo.project.entities.gerenciamento.CardEmprestimo;
import br.edu.ifpe.lpoo.project.entities.gerenciamento.Emprestimo;
import br.edu.ifpe.lpoo.project.exception.BusinessException;

public class JPanelListarEmprestimos extends JPanel {

	private static final long serialVersionUID = 1L;
	private EmprestimoController emprestimoController;
	private JPanel painelCards;

	public JPanelListarEmprestimos() {
		setLayout(new BorderLayout());
		emprestimoController = new EmprestimoController();

		JLabel titulo = new JLabel("Empréstimos");
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		titulo.setFont(new Font("Arial Black", Font.BOLD, 20));
		add(titulo, BorderLayout.NORTH);

		painelCards = new JPanel(new GridLayout(0, 2, 10, 10));
		painelCards.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		carregarEmprestimos();

		JScrollPane scrollPane = new JScrollPane(painelCards);
		scrollPane.getVerticalScrollBar().setUnitIncrement(16);
		add(scrollPane, BorderLayout.CENTER);
	}

	private void carregarEmprestimos() {

		try {
			List<Emprestimo> emprestimos = emprestimoController.listarEmprestimos();

			if (emprestimos.isEmpty()) {
				JLabel mensagemVazia = new JLabel("Não existe registro de empréstimo.", SwingConstants.CENTER);
				mensagemVazia.setFont(new Font("Arial", Font.PLAIN, 16));
				painelCards.add(mensagemVazia);
			} else {
				for (Emprestimo emprestimo : emprestimos) {

					EmprestimoController emprestimoController = new EmprestimoController();
					CardEmprestimo cardEmprestimo = emprestimoController
							.buscarCardEmprestimo(emprestimo.getIdEmprestimo());
					painelCards.add(new JPanelCardEmprestimo(cardEmprestimo));
				}
			}
		} catch (BusinessException e1) {
			JOptionPane.showMessageDialog(JPanelListarEmprestimos.this, e1.getMessage(), "Erro",
					JOptionPane.ERROR_MESSAGE);
		}
	}

}
