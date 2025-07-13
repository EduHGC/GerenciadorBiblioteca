package br.edu.ifpe.lpoo.project.ui.acervo;

import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import br.edu.ifpe.lpoo.project.business.acervo.ExemplarController;
import br.edu.ifpe.lpoo.project.entities.acervo.Exemplar;
import br.edu.ifpe.lpoo.project.enums.StatusExemplar;
import br.edu.ifpe.lpoo.project.exception.BusinessException;

import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class JPanelAtualizarStatus extends JPanel {

	private static final long serialVersionUID = 1L;

	private JTextField textFieldIdItem;
	private JButton btnAtualizarExemplar;
	private JComboBox<String> comboBox;
	private JButton btnBuscarExemplar;
	private Exemplar exemplar;
	private JPanel painelExemplar;

	public JPanelAtualizarStatus() {
		setLayout(null);

		JLabel lblIdExemplar = new JLabel("Digite o id do Exemplar");
		lblIdExemplar.setFont(new Font("Arial Black", Font.BOLD, 13));
		lblIdExemplar.setBounds(40, 19, 214, 20);
		add(lblIdExemplar);

		textFieldIdItem = new JTextField();
		textFieldIdItem.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
					
					String novoId = textFieldIdItem.getText();
					limparCampos();
					textFieldIdItem.setText(novoId);
				}
			}
		});
		textFieldIdItem.setFont(new Font("Arial Black", Font.BOLD, 15));
		textFieldIdItem.setColumns(10);
		textFieldIdItem.setBounds(40, 40, 214, 40);
		add(textFieldIdItem);

		btnBuscarExemplar = new JButton("Buscar");
		btnBuscarExemplar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				painelExemplar.removeAll();

				if (textFieldIdItem.getText().trim().isEmpty()) {
					JOptionPane.showMessageDialog(JPanelAtualizarStatus.this, "Insira um id válido", "Id Inválido",
							JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				String idExemplar = textFieldIdItem.getText();
				try {

					ExemplarController exemplarController = new ExemplarController();
					exemplar = exemplarController.buscarExemplarId(idExemplar);
					painelExemplar.add(new JPanelCardExemplar(exemplar));
					painelExemplar.revalidate();
					painelExemplar.repaint();
					
					comboBox.setEnabled(true);

				} catch (BusinessException e1) {
					JOptionPane.showMessageDialog(JPanelAtualizarStatus.this, e1.getMessage(), "Erro",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnBuscarExemplar.setFont(new Font("Arial Black", Font.BOLD, 15));
		btnBuscarExemplar.setBounds(273, 40, 120, 40);
		add(btnBuscarExemplar);

		btnAtualizarExemplar = new JButton("Atualizar");
		btnAtualizarExemplar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int confirmacao = JOptionPane.showConfirmDialog(JPanelAtualizarStatus.this,
		                "Tem certeza que deseja atualizar o status do exemplar?", "Confirmar Atualização",
		                JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
				
				if(confirmacao == JOptionPane.OK_CANCEL_OPTION) {
					
					JOptionPane.showMessageDialog(JPanelAtualizarStatus.this,
		                    "Operação de atualização cancelada.", "Cancelado",
		                    JOptionPane.INFORMATION_MESSAGE);
		            return;
				}
				
				String opcao = (String) comboBox.getSelectedItem();
				exemplar.setStatus(StatusExemplar.fromDescricao(opcao));
				try {
					ExemplarController exemplarController = new ExemplarController();
					
					exemplarController.atualizarStatus(exemplar);
					
				} catch (BusinessException e2) {
					JOptionPane.showMessageDialog(JPanelAtualizarStatus.this, e2.getMessage(), "Erro",
							JOptionPane.ERROR_MESSAGE);
				}
				limparCampos();
			}
		});
		btnAtualizarExemplar.setFont(new Font("Arial Black", Font.BOLD, 15));
		btnAtualizarExemplar.setBounds(40, 140, 214, 40);
		add(btnAtualizarExemplar);

		comboBox = new JComboBox<>();
		comboBox.addItem("Selecione o novo status");
		for (StatusExemplar status : StatusExemplar.values()) {
		    comboBox.addItem(status.getDescricao());
		}
		comboBox.setEnabled(false);
		comboBox.setFont(new Font("Arial Black", Font.BOLD, 13));
		comboBox.setBounds(40, 90, 214, 40);
		comboBox.addActionListener(e -> {
			String opcaoSelecionada = (String) comboBox.getSelectedItem();

			if ("Selecione o novo status".equals(opcaoSelecionada)) {
				btnAtualizarExemplar.setEnabled(false);
			} else {
				btnAtualizarExemplar.setEnabled(true);

			}
		});
		add(comboBox);

		painelExemplar = new JPanel();
		painelExemplar.setSize(430, 60);
		painelExemplar.setLocation(40, 200);
		painelExemplar.setLayout(new BoxLayout(painelExemplar, BoxLayout.Y_AXIS));
		add(painelExemplar);

	}
	
	private void limparCampos() {
	    textFieldIdItem.setText("");
	    comboBox.setSelectedIndex(0);
	    comboBox.setEnabled(false);
	    btnAtualizarExemplar.setEnabled(false);
	    painelExemplar.removeAll();
	    painelExemplar.revalidate();
	    painelExemplar.repaint();
	    exemplar = null;
	}
}
