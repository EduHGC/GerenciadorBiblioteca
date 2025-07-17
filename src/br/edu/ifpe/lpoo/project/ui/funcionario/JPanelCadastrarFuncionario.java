package br.edu.ifpe.lpoo.project.ui.funcionario;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import br.edu.ifpe.lpoo.project.business.funcionario.FuncionarioController;
import br.edu.ifpe.lpoo.project.enums.Cargo;
import br.edu.ifpe.lpoo.project.exception.BusinessException;

import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class JPanelCadastrarFuncionario extends JPanel {

	private static final long serialVersionUID = 1L;

	private JTextField textFieldCpf;
	private JTextField textNome;
	private JTextField textEmail;
	private JTextField textMatricula;
	private JComboBox<String> comboBoxCargo;
	private JPasswordField passwordFieldSenha;
	private JPasswordField passwordFieldConfirSenha;
	
	
	public JPanelCadastrarFuncionario() {
		
		
		setLayout(null);

		JLabel lblCpf = new JLabel("CPF");
		lblCpf.setFont(new Font("Arial Black", Font.BOLD, 15));
		lblCpf.setBounds(40, 20, 100, 20);
		add(lblCpf);

		textFieldCpf = new JTextField();
		textFieldCpf.setFont(new Font("Arial Black", Font.BOLD, 15));
		textFieldCpf.setBounds(40, 40, 400, 40);
		textFieldCpf.setColumns(10);
		add(textFieldCpf);

		textNome = new JTextField();
		textNome.setFont(new Font("Arial Black", Font.BOLD, 15));
		textNome.setColumns(10);
		textNome.setBounds(40, 110, 400, 40);
		add(textNome);

		JLabel lblNome = new JLabel("Nome");
		lblNome.setFont(new Font("Arial Black", Font.BOLD, 15));
		lblNome.setBounds(40, 90, 100, 20);
		add(lblNome);

		textEmail = new JTextField();
		textEmail.setFont(new Font("Arial Black", Font.BOLD, 15));
		textEmail.setColumns(10);
		textEmail.setBounds(40, 180, 400, 40);
		add(textEmail);

		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Arial Black", Font.BOLD, 15));
		lblEmail.setBounds(40, 160, 100, 20);
		add(lblEmail);

		passwordFieldSenha = new JPasswordField();
		passwordFieldSenha.setBounds(40, 249, 400, 40);
		add(passwordFieldSenha);

		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setFont(new Font("Arial Black", Font.BOLD, 15));
		lblSenha.setBounds(40, 230, 100, 20);
		add(lblSenha);

		passwordFieldConfirSenha = new JPasswordField();
		passwordFieldConfirSenha.setBounds(40, 320, 400, 40);
		add(passwordFieldConfirSenha);

		JLabel lblConfirmarSenha = new JLabel("Confirmar senha");
		lblConfirmarSenha.setFont(new Font("Arial Black", Font.BOLD, 15));
		lblConfirmarSenha.setBounds(40, 300, 145, 20);
		add(lblConfirmarSenha);

		textMatricula = new JTextField();
		textMatricula.setFont(new Font("Arial Black", Font.BOLD, 15));
		textMatricula.setColumns(10);
		textMatricula.setBounds(40, 390, 400, 40);
		add(textMatricula);

		JLabel lblMatricula = new JLabel("Matrícula");
		lblMatricula.setFont(new Font("Arial Black", Font.BOLD, 15));
		lblMatricula.setBounds(40, 370, 145, 20);
		add(lblMatricula);

		comboBoxCargo = new JComboBox<>();
		comboBoxCargo.setBounds(40, 460, 400, 40);
		comboBoxCargo.setFont(new Font("Arial Black", Font.BOLD, 13));
		comboBoxCargo.addItem("Selecione um cargo para o funcionário");
		for (Cargo cargo : Cargo.values()) {
			comboBoxCargo.addItem(cargo.getCargo());
		}
		add(comboBoxCargo);

		JLabel lblCargo = new JLabel("Cargo");
		lblCargo.setFont(new Font("Arial Black", Font.BOLD, 15));
		lblCargo.setBounds(40, 440, 100, 20);
		add(lblCargo);

		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cargo = (String) comboBoxCargo.getSelectedItem();

				if (textFieldCpf.getText().trim().isEmpty() || textNome.getText().trim().isEmpty()
						|| textEmail.getText().trim().isEmpty() || textMatricula.getText().trim().isEmpty()
						|| cargo.equals("Selecione um cargo para o funcionário")) {

					JOptionPane.showMessageDialog(JPanelCadastrarFuncionario.this,
							"Por favor, preencha todos os campos para efetuar o cadastro do usuário.", "Campos Vazios",
							JOptionPane.WARNING_MESSAGE);

					return;
				}
				
				char[] senhaOriginal = passwordFieldSenha.getPassword();
				char[] senhaAConfirmar = passwordFieldConfirSenha.getPassword();
				
				if (!Arrays.equals(senhaOriginal, senhaAConfirmar)) {
					JOptionPane.showMessageDialog(JPanelCadastrarFuncionario.this,
							"Senha e Confimar senha não estão iguais", "Confirmação de senha",
							JOptionPane.WARNING_MESSAGE);
					return;
				}

				String cpf = textFieldCpf.getText().trim();
				String nome = textNome.getText().trim();
				String email = textEmail.getText().trim();
				char[] senhaArray = passwordFieldSenha.getPassword();
				String senha = new String (senhaArray);
				String matricula = textMatricula.getText().trim();
				String cagoFuncionario = cargo;

				try {

					FuncionarioController funcionarioController = new FuncionarioController();
					funcionarioController.inserir(cpf, nome, email, senha, matricula, cagoFuncionario);

					JOptionPane.showMessageDialog(JPanelCadastrarFuncionario.this,
							"O funcionário foi cadastrado com sucesso em nosso sistema.", "Cadastro Concluído",
							JOptionPane.INFORMATION_MESSAGE);

					limparCampos();

				} catch (BusinessException e1) {
					JOptionPane.showMessageDialog(JPanelCadastrarFuncionario.this, e1.getMessage(), "Erro",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnCadastrar.setFont(new Font("Arial Black", Font.BOLD, 15));
		btnCadastrar.setBounds(40, 510, 400, 40);
		add(btnCadastrar);

	}

	private void limparCampos() {

		textFieldCpf.setText("");
		textNome.setText("");
		textEmail.setText("");
		textMatricula.setText("");
		comboBoxCargo.setSelectedIndex(0);
		passwordFieldSenha.setText("");
		passwordFieldConfirSenha.setText("");
	}
}
