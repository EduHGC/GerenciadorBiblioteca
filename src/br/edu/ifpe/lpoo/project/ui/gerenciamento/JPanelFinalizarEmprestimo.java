package br.edu.ifpe.lpoo.project.ui.gerenciamento;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;

import br.edu.ifpe.lpoo.project.business.gerenciamento.EmprestimoController;
import br.edu.ifpe.lpoo.project.entities.gerenciamento.CardEmprestimo;
import br.edu.ifpe.lpoo.project.entities.gerenciamento.Emprestimo;
import br.edu.ifpe.lpoo.project.enums.StatusEmprestimo;
import br.edu.ifpe.lpoo.project.exception.BusinessException;
import br.edu.ifpe.lpoo.project.ui.BibliotecaApp;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.time.LocalDate;

public class JPanelFinalizarEmprestimo extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JPanel panel;
	private JButton btnBuscar;
	private JPanel panelCard;
	private JButton btnFinalizar;

	public JPanelFinalizarEmprestimo(BibliotecaApp app) {
		setLayout(null);
		
		JLabel lblIdEmprestimo = new JLabel("Identificador do empéstimo");
		lblIdEmprestimo.setFont(new Font("Arial Black", Font.PLAIN, 13));
		lblIdEmprestimo.setBounds(40, 40, 200, 20);
		add(lblIdEmprestimo);
		
		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				
				if(e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
					String novoId = textField.getText();
					
					limparPanel();
					
					textField.setText(novoId);
				}
			}
		});
		textField.setFont(new Font("Arial Black", Font.BOLD, 15));
		textField.setBounds(40, 60, 200, 40);
		add(textField);
		textField.setColumns(10);
		
		panel = new JPanel();
		panel.setBounds(35, 104, 604, 532);
		add(panel);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				limparPanel();
				
				if(textField.getText().trim().isEmpty()) {
					
					JOptionPane.showMessageDialog(JPanelFinalizarEmprestimo.this, "Insira um id válido", "Id Inválido",
							JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				String idEmprestimo = textField.getText().trim();
				
				int id;
				try {
					id = Integer.parseInt(idEmprestimo);
				}catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(JPanelFinalizarEmprestimo.this, "O id precisa ser numérico", "Id Inválido",
							JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				try {
					
					EmprestimoController emprestimoController = new EmprestimoController();
					Emprestimo emprestimo = emprestimoController.buscarEmprestimoPorId(id);
					
					if(emprestimo.getStatusEmprestimo().getStatus().equals("Finalizado")) {
						JOptionPane.showMessageDialog(JPanelFinalizarEmprestimo.this, "O empréstimo com o id fornecido já foi finalizado", "Atenção",
								JOptionPane.WARNING_MESSAGE);
						return;
					}
					
					CardEmprestimo cardEmprestimo = emprestimoController.buscarCardEmprestimo(id);
					panelCard = new JPanelCardEmprestimo(cardEmprestimo);
					panel.add(panelCard);
					
					
				}catch (BusinessException e2) {
					JOptionPane.showMessageDialog(JPanelFinalizarEmprestimo.this, "Não existe empréstimo com esse id", "Id Inválido",
							JOptionPane.WARNING_MESSAGE);
				}
				
				panel.revalidate();
				panel.repaint();
				
				btnFinalizar.setEnabled(true);
			}
		});
		btnBuscar.setFont(new Font("Arial Black", Font.BOLD, 13));
		btnBuscar.setBounds(250, 60, 120, 40);
		add(btnBuscar);
		
		
		
		btnFinalizar = new JButton("Finalizar");
		btnFinalizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(textField.getText().trim().isEmpty()) {
					
					JOptionPane.showMessageDialog(JPanelFinalizarEmprestimo.this, "Insira um id válido", "Id Inválido",
							JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				String idEmprestimo = textField.getText().trim();
				
				int id;
				try {
					id = Integer.parseInt(idEmprestimo);
				}catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(JPanelFinalizarEmprestimo.this, "O id precisa ser numérico", "Id Inválido",
							JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				EmprestimoController emprestimoController = new EmprestimoController();
				Emprestimo emprestimo = emprestimoController.buscarEmprestimoPorId(id);
				emprestimo.setStatusEmprestimo(StatusEmprestimo.FINALIZADO);
				LocalDate dataFinalização = LocalDate.now();
				emprestimo.setDataRealDevolucao(dataFinalização);
				emprestimoController.atualizarEmprestimoFinalizar(emprestimo);
				
				JOptionPane.showMessageDialog(JPanelFinalizarEmprestimo.this,
						"O empréstimo finalizado com sucesso em nosso sistema.", "Empréstimo finalizado",
						JOptionPane.INFORMATION_MESSAGE);
				
				limparPanel();
				app.atualizarAbaListagemEmprestimos();
			}
		});
		btnFinalizar.setEnabled(false);
		btnFinalizar.setFont(new Font("Arial Black", Font.BOLD, 13));
		btnFinalizar.setBounds(380, 60, 133, 40);
		add(btnFinalizar);
	}
	
	private void limparPanel() {
		if (panelCard != null) {
			panel.remove(panelCard);
			panelCard = null;
			panel.revalidate();
			panel.repaint();
			btnFinalizar.setEnabled(false);
			textField.setText("");
		}
	}
	
}
