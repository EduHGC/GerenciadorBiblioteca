package br.edu.ifpe.lpoo.project.ui.funcionario;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import br.edu.ifpe.lpoo.project.business.funcionario.FuncionarioController;
import br.edu.ifpe.lpoo.project.entities.funcionario.Funcionario;
import br.edu.ifpe.lpoo.project.enums.Cargo;
import br.edu.ifpe.lpoo.project.enums.StatusFuncionario;
import br.edu.ifpe.lpoo.project.exception.BusinessException;

import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Arrays;

import javax.swing.JPasswordField;
import javax.swing.JCheckBox;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class JPanelAtualizarFuncionario extends JPanel {

	private static final long serialVersionUID = 1L;

	private JTextField textFieldCpf;
	private JTextField textFieldNome;
	private JTextField textFieldEmail;
	private JComboBox<String> comboBoxCargo;
	private JComboBox<String> comboBoxStatus;
	private JTextField textFieldId;
	private JTextField textFieldMatricula;
	private JPasswordField passwordFieldNovaSenha;
	private JPasswordField passwordFieldConfirNovaSenha;
	private JCheckBox chckbxHabilitarNovaSenha;
	private boolean logado;

	public JPanelAtualizarFuncionario() {

		setLayout(null);

		JLabel lblId = new JLabel("Digite o id");
		lblId.setFont(new Font("Arial Black", Font.BOLD, 15));
		lblId.setBounds(40, 0, 190, 20);
		add(lblId);

		textFieldId = new JTextField();
		textFieldId.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				String novoId = textFieldId.getText();

				limparCampos();

				textFieldId.setText(novoId);
			}
		});
		textFieldId.setFont(new Font("Arial Black", Font.BOLD, 15));
		textFieldId.setColumns(10);
		textFieldId.setBounds(40, 20, 100, 40);
		add(textFieldId);

		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textFieldId.getText().trim().isEmpty()) {
					JOptionPane.showMessageDialog(JPanelAtualizarFuncionario.this, "Insira um id válido", "Id Inválido",
							JOptionPane.WARNING_MESSAGE);
					return;
				}

				String idFuncionario = textFieldId.getText().trim();

				try {
					FuncionarioController funcionarioController = new FuncionarioController();
					Funcionario funcionario = funcionarioController.buscarPorId(idFuncionario);

					textFieldId.setText(String.valueOf(funcionario.getIdFuncionario()));
					textFieldCpf.setText(funcionario.getCpf());
					textFieldNome.setText(funcionario.getNome());
					textFieldEmail.setText(funcionario.getEmail());
					textFieldMatricula.setText(funcionario.getMatricula());
					comboBoxCargo.setSelectedItem(funcionario.getCargo().getCargo());
					comboBoxStatus.setSelectedItem(funcionario.getStatusFuncionario().getStatus());
					logado = funcionario.isLogado();
				} catch (BusinessException e1) {
					JOptionPane.showMessageDialog(JPanelAtualizarFuncionario.this, e1.getMessage(), "Erro",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnBuscar.setFont(new Font("Arial Black", Font.BOLD, 15));
		btnBuscar.setBounds(150, 20, 135, 40);
		add(btnBuscar);

		JLabel lblCpf = new JLabel("CPF");
		lblCpf.setFont(new Font("Arial Black", Font.BOLD, 15));
		lblCpf.setBounds(40, 70, 100, 20);
		add(lblCpf);

		textFieldCpf = new JTextField();
		textFieldCpf.setFont(new Font("Arial Black", Font.BOLD, 15));
		textFieldCpf.setBounds(40, 90, 400, 40);
		textFieldCpf.setColumns(10);
		add(textFieldCpf);

		textFieldNome = new JTextField();
		textFieldNome.setFont(new Font("Arial Black", Font.BOLD, 15));
		textFieldNome.setColumns(10);
		textFieldNome.setBounds(40, 159, 400, 40);
		add(textFieldNome);

		JLabel lblNome = new JLabel("Nome");
		lblNome.setFont(new Font("Arial Black", Font.BOLD, 15));
		lblNome.setBounds(40, 140, 100, 20);
		add(lblNome);

		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Arial Black", Font.BOLD, 15));
		lblEmail.setBounds(40, 209, 100, 20);
		add(lblEmail);

		textFieldEmail = new JTextField();
		textFieldEmail.setFont(new Font("Arial Black", Font.BOLD, 15));
		textFieldEmail.setColumns(10);
		textFieldEmail.setBounds(40, 228, 400, 40);
		add(textFieldEmail);

		JLabel lblNovaSenha = new JLabel("Nova senha");
		lblNovaSenha.setFont(new Font("Arial Black", Font.BOLD, 15));
		lblNovaSenha.setBounds(539, 70, 145, 20);
		add(lblNovaSenha);

		comboBoxCargo = new JComboBox<>();
		comboBoxCargo.setBounds(40, 371, 400, 40);
		comboBoxCargo.setFont(new Font("Arial Black", Font.BOLD, 13));
		comboBoxCargo.addItem("Selecione um cargo para o funcionário");
		for (Cargo cargo : Cargo.values()) {
			comboBoxCargo.addItem(cargo.getCargo());
		}
		add(comboBoxCargo);

		JLabel lblCargo = new JLabel("Cargo");
		lblCargo.setFont(new Font("Arial Black", Font.BOLD, 15));
		lblCargo.setBounds(40, 351, 100, 20);
		add(lblCargo);

		comboBoxStatus = new JComboBox<String>();
		comboBoxStatus.setFont(new Font("Arial Black", Font.BOLD, 13));
		comboBoxStatus.setBounds(40, 441, 400, 40);
		comboBoxStatus.addItem("Selecione o status do funcionário");
		for (StatusFuncionario statusFuncionario : StatusFuncionario.values()) {
			comboBoxStatus.addItem(statusFuncionario.getStatus());
		}
		add(comboBoxStatus);

		JLabel lblStatus = new JLabel("Status");
		lblStatus.setFont(new Font("Arial Black", Font.BOLD, 15));
		lblStatus.setBounds(40, 421, 100, 20);
		add(lblStatus);

		JButton btnNewButton = new JButton("Atualizar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cargo = (String) comboBoxCargo.getSelectedItem();
				String status = (String) comboBoxStatus.getSelectedItem();

				if (textFieldCpf.getText().trim().isEmpty() || textFieldNome.getText().trim().isEmpty()
						|| textFieldEmail.getText().trim().isEmpty() || textFieldMatricula.getText().trim().isEmpty()
						|| cargo.equals("Selecione um cargo para o funcionário")
						|| status.equals("Selecione o status do funcionário")) {

					JOptionPane.showMessageDialog(JPanelAtualizarFuncionario.this,
							"Por favor, preencha todos os campos para efetuar a atualização do funcionário.",
							"Campos Vazios", JOptionPane.WARNING_MESSAGE);

					return;
				}

				if (chckbxHabilitarNovaSenha.isSelected()) {
					char[] novaSenha = passwordFieldNovaSenha.getPassword();
					char[] novaSenhaConfir = passwordFieldConfirNovaSenha.getPassword();

					try {

						FuncionarioController funcionarioController = new FuncionarioController();
						String senhaAtual = funcionarioController.buscarPorId(textFieldId.getText()).getSenha();
						char[] senha = senhaAtual.toCharArray();

						if (Arrays.equals(novaSenha, senha)) {
							JOptionPane.showMessageDialog(JPanelAtualizarFuncionario.this,
									"A nova senha não pode ser igual a atual", "Atualização de senha",
									JOptionPane.WARNING_MESSAGE);
							return;
						}

						if (!Arrays.equals(novaSenha, novaSenhaConfir)) {
							JOptionPane.showMessageDialog(JPanelAtualizarFuncionario.this,
									"Nova senha e Confimar nova senha não estão iguais", "Confirmação de senha",
									JOptionPane.WARNING_MESSAGE);
							return;
						}

					} catch (BusinessException e1) {
						JOptionPane.showMessageDialog(JPanelAtualizarFuncionario.this, e1.getMessage(), "Erro",
								JOptionPane.ERROR_MESSAGE);
					}
				}

				int confirmacao = JOptionPane.showConfirmDialog(JPanelAtualizarFuncionario.this,
						"Tem certeza que deseja atualizar os dados deste funcionário?", "Confirmar Atualização",
						JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);

				if (confirmacao == JOptionPane.CANCEL_OPTION || confirmacao == JOptionPane.CLOSED_OPTION) {

					JOptionPane.showMessageDialog(JPanelAtualizarFuncionario.this, "Operação de atualização cancelada.",
							"Cancelado", JOptionPane.INFORMATION_MESSAGE);
					return;
				}

				String idUsuario = textFieldId.getText().trim();
				String cpf = textFieldCpf.getText().trim();
				String nome = textFieldNome.getText().trim();
				String email = textFieldEmail.getText().trim();
				String matricula = textFieldMatricula.getText().trim();
				String cargoSelecionado = cargo;
				String statusFuncionario = status;
				boolean habilitarMudarSenha = chckbxHabilitarNovaSenha.isSelected();
				char[] novaSenha = passwordFieldNovaSenha.getPassword();
				String novaSenhaString = new String(novaSenha);

				try {

					FuncionarioController funcionarioController = new FuncionarioController();
					funcionarioController.atualizar(idUsuario, cpf, nome, email, matricula, cargoSelecionado,
							statusFuncionario, habilitarMudarSenha, logado, novaSenhaString);

					JOptionPane.showMessageDialog(JPanelAtualizarFuncionario.this,
							"O funcionário foi atualizado com sucesso em nosso sistema.", "Atualiação Concluída",
							JOptionPane.INFORMATION_MESSAGE);

					limparCampos();

				} catch (BusinessException e1) {
					JOptionPane.showMessageDialog(JPanelAtualizarFuncionario.this, e1.getMessage(), "Erro",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnNewButton.setFont(new Font("Arial Black", Font.BOLD, 15));
		btnNewButton.setBounds(40, 491, 400, 40);
		add(btnNewButton);

		textFieldMatricula = new JTextField();
		textFieldMatricula.setFont(new Font("Arial Black", Font.BOLD, 15));
		textFieldMatricula.setColumns(10);
		textFieldMatricula.setBounds(40, 298, 400, 40);
		add(textFieldMatricula);

		JLabel llblMatricula = new JLabel("Matrícula");
		llblMatricula.setFont(new Font("Arial Black", Font.BOLD, 15));
		llblMatricula.setBounds(40, 278, 145, 20);
		add(llblMatricula);

		passwordFieldNovaSenha = new JPasswordField();
		passwordFieldNovaSenha.setEditable(false);
		passwordFieldNovaSenha.setEnabled(false);
		passwordFieldNovaSenha.setBounds(539, 89, 400, 40);
		add(passwordFieldNovaSenha);

		passwordFieldConfirNovaSenha = new JPasswordField();
		passwordFieldConfirNovaSenha.setEditable(false);
		passwordFieldConfirNovaSenha.setEnabled(false);
		passwordFieldConfirNovaSenha.setBounds(539, 158, 400, 40);
		add(passwordFieldConfirNovaSenha);

		JLabel lblConfirNovaSenha = new JLabel("Confirmar nova senha");
		lblConfirNovaSenha.setFont(new Font("Arial Black", Font.BOLD, 15));
		lblConfirNovaSenha.setBounds(539, 139, 197, 20);
		add(lblConfirNovaSenha);

		chckbxHabilitarNovaSenha = new JCheckBox("Selecione para modificar a senha");
		chckbxHabilitarNovaSenha.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				
				if(e.getStateChange() == ItemEvent.SELECTED) {
					
					passwordFieldNovaSenha.setEditable(true);
					passwordFieldNovaSenha.setEnabled(true);
					passwordFieldConfirNovaSenha.setEditable(true);
					passwordFieldConfirNovaSenha.setEnabled(true);
				}else {
					
					
					passwordFieldNovaSenha.setText("");
					passwordFieldConfirNovaSenha.setText("");
					passwordFieldNovaSenha.setEditable(false);
					passwordFieldNovaSenha.setEnabled(false);
					passwordFieldConfirNovaSenha.setEditable(false);
					passwordFieldConfirNovaSenha.setEnabled(false);
				}
			}
		});
		chckbxHabilitarNovaSenha.setFont(new Font("Arial Black", Font.BOLD, 15));
		chckbxHabilitarNovaSenha.setBounds(539, 39, 319, 21);
		add(chckbxHabilitarNovaSenha);

	}

	private void limparCampos() {

		textFieldId.setText("");
		textFieldCpf.setText("");
		textFieldNome.setText("");
		textFieldEmail.setText("");
		textFieldMatricula.setText("");
		comboBoxCargo.setSelectedIndex(0);
		comboBoxStatus.setSelectedIndex(0);
		logado = false;
		chckbxHabilitarNovaSenha.setSelected(false);
	}
}
