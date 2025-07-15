package br.edu.ifpe.lpoo.project.ui.usuario;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import br.edu.ifpe.lpoo.project.business.user.UsuarioController;
import br.edu.ifpe.lpoo.project.enums.StatusUsuario;
import br.edu.ifpe.lpoo.project.enums.TipoUsuario;
import br.edu.ifpe.lpoo.project.exception.BusinessException;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JPanelCadastrarUsuario extends JPanel {

	private static final long serialVersionUID = 1L;

	private JTextField textFieldCpf;
	private JTextField textMatricula;
	private JTextField textNome;
	private JTextField textFieldEmail;
	private JTextField textFieldTelefone;
	private JTextField textDepartamento;
	private JTextField textFieldInstituicao;
	private JComboBox<String> comboBoxCategoria;
	private JComboBox<String> comboBoxStatus;

	public JPanelCadastrarUsuario() {

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

		textMatricula = new JTextField();
		textMatricula.setFont(new Font("Arial Black", Font.BOLD, 15));
		textMatricula.setColumns(10);
		textMatricula.setBounds(40, 110, 400, 40);
		add(textMatricula);

		JLabel lblMatricula = new JLabel("Matrícula");
		lblMatricula.setFont(new Font("Arial Black", Font.BOLD, 15));
		lblMatricula.setBounds(40, 90, 100, 20);
		add(lblMatricula);

		textNome = new JTextField();
		textNome.setFont(new Font("Arial Black", Font.BOLD, 15));
		textNome.setColumns(10);
		textNome.setBounds(40, 180, 400, 40);
		add(textNome);

		JLabel lblNome = new JLabel("Nome");
		lblNome.setFont(new Font("Arial Black", Font.BOLD, 15));
		lblNome.setBounds(40, 160, 100, 20);
		add(lblNome);

		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Arial Black", Font.BOLD, 15));
		lblEmail.setBounds(40, 230, 100, 20);
		add(lblEmail);

		textFieldEmail = new JTextField();
		textFieldEmail.setFont(new Font("Arial Black", Font.BOLD, 15));
		textFieldEmail.setColumns(10);
		textFieldEmail.setBounds(40, 250, 400, 40);
		add(textFieldEmail);

		JLabel lblTelefone = new JLabel("Telefone");
		lblTelefone.setFont(new Font("Arial Black", Font.BOLD, 15));
		lblTelefone.setBounds(40, 300, 100, 20);
		add(lblTelefone);

		textFieldTelefone = new JTextField();
		textFieldTelefone.setFont(new Font("Arial Black", Font.BOLD, 15));
		textFieldTelefone.setColumns(10);
		textFieldTelefone.setBounds(40, 320, 400, 40);
		add(textFieldTelefone);

		JLabel lblDepartamento = new JLabel("Departamento");
		lblDepartamento.setFont(new Font("Arial Black", Font.BOLD, 15));
		lblDepartamento.setBounds(40, 370, 145, 20);
		add(lblDepartamento);

		textDepartamento = new JTextField();
		textDepartamento.setFont(new Font("Arial Black", Font.BOLD, 15));
		textDepartamento.setColumns(10);
		textDepartamento.setBounds(40, 390, 400, 40);
		add(textDepartamento);

		comboBoxCategoria = new JComboBox<>();
		comboBoxCategoria.setBounds(40, 460, 400, 40);
		comboBoxCategoria.setFont(new Font("Arial Black", Font.BOLD, 13));
		comboBoxCategoria.addItem("Selecione uma categoria de usuário");
		for (TipoUsuario tipoUsuario : TipoUsuario.values()) {
			comboBoxCategoria.addItem(tipoUsuario.getTipo());
		}
		add(comboBoxCategoria);

		JLabel lblCategoria = new JLabel("Categoria");
		lblCategoria.setFont(new Font("Arial Black", Font.BOLD, 15));
		lblCategoria.setBounds(40, 440, 100, 20);
		add(lblCategoria);

		comboBoxStatus = new JComboBox<>();
		comboBoxStatus.setBounds(40, 530, 400, 40);
		comboBoxStatus.setFont(new Font("Arial Black", Font.BOLD, 13));
		comboBoxStatus.addItem("Selecione um status para o usuário");
		for (StatusUsuario statusUsuario : StatusUsuario.values()) {
			comboBoxStatus.addItem(statusUsuario.getStatus());
		}
		add(comboBoxStatus);

		JLabel lblStatus = new JLabel("Status do Usuário");
		lblStatus.setFont(new Font("Arial Black", Font.BOLD, 15));
		lblStatus.setBounds(40, 510, 171, 20);
		add(lblStatus);

		JLabel lblIstituicao = new JLabel("Instituição");
		lblIstituicao.setFont(new Font("Arial Black", Font.BOLD, 15));
		lblIstituicao.setBounds(40, 580, 100, 20);
		add(lblIstituicao);

		textFieldInstituicao = new JTextField();
		textFieldInstituicao.setFont(new Font("Arial Black", Font.BOLD, 15));
		textFieldInstituicao.setColumns(10);
		textFieldInstituicao.setBounds(40, 600, 400, 40);
		add(textFieldInstituicao);

		JButton btnNewButton = new JButton("Cadastrar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String categoria = (String) comboBoxCategoria.getSelectedItem();
				String status = (String) comboBoxStatus.getSelectedItem();

				if (textFieldCpf.getText().trim().isEmpty() || textMatricula.getText().trim().isEmpty()
						|| textNome.getText().trim().isEmpty() || textFieldEmail.getText().trim().isEmpty()
						|| textFieldTelefone.getText().trim().isEmpty() || textDepartamento.getText().trim().isEmpty()
						|| categoria.equals("Selecione uma categoria de usuário")
						|| status.equals("Selecione um status para o usuário") || textFieldInstituicao.getText().trim().isEmpty()) {

					JOptionPane.showMessageDialog(JPanelCadastrarUsuario.this,
							"Por favor, preencha todos os campos para efetuar o cadastro do usuário.", "Campos Vazios",
							JOptionPane.WARNING_MESSAGE);

					return;
				}
				
				String cpf = textFieldCpf.getText().trim();
				String matricula = textMatricula.getText().trim();
				String nome = textNome.getText().trim();
				String email = textFieldEmail.getText().trim();
				String telefone = textFieldTelefone.getText().trim();
				String departamento = textDepartamento.getText().trim();
				String tipoUsuario = categoria;
				String statusUsuario = status;
				String instituicao = textFieldInstituicao.getText().trim();
				
				try {
					
					UsuarioController usuarioController = new UsuarioController();
					usuarioController.inserir(cpf, matricula, nome, email, telefone, departamento, tipoUsuario, statusUsuario, instituicao);
					
					JOptionPane.showMessageDialog(JPanelCadastrarUsuario.this,
							"O usuário foi cadastrado com sucesso em nosso sistema.", "Cadastro Concluído",
							JOptionPane.INFORMATION_MESSAGE);
					
					limparCampos();
					
				} catch (BusinessException e1) {
					JOptionPane.showMessageDialog(JPanelCadastrarUsuario.this, e1.getMessage(), "Erro",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnNewButton.setFont(new Font("Arial Black", Font.BOLD, 15));
		btnNewButton.setBounds(40, 650, 400, 40);
		add(btnNewButton);

	}
	
	private void limparCampos(){
		
		textFieldCpf.setText("");
	    textMatricula.setText("");
	    textNome.setText("");
	    textFieldEmail.setText("");
	    textFieldTelefone.setText("");
	    textDepartamento.setText("");
	    comboBoxCategoria.setSelectedIndex(0);
	    comboBoxStatus.setSelectedIndex(0);
	    textFieldInstituicao.setText("");
	}
}
