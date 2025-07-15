package br.edu.ifpe.lpoo.project.ui.usuario;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import br.edu.ifpe.lpoo.project.business.user.FuncionarioController;
import br.edu.ifpe.lpoo.project.entities.user.Usuario;
import br.edu.ifpe.lpoo.project.enums.StatusUsuario;
import br.edu.ifpe.lpoo.project.enums.TipoUsuario;
import br.edu.ifpe.lpoo.project.exception.BusinessException;

import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class JPanelAtualizarUsuario extends JPanel {

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
	private JTextField textFieldId;
	private JTextField textFieldDebito;

	public JPanelAtualizarUsuario() {

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
					JOptionPane.showMessageDialog(JPanelAtualizarUsuario.this, "Insira um id válido", "Id Inválido",
							JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				String idUsuario = textFieldId.getText().trim();
				
				try {
					FuncionarioController funcionarioController = new FuncionarioController();
					Usuario usuario = funcionarioController.buscarPorId(idUsuario);
					
					textFieldId.setText(String.valueOf(usuario.getIdUsuario()));
					textFieldCpf.setText(usuario.getCpf());
					textMatricula.setText(usuario.getMatricula());
					textNome.setText(usuario.getNome());
					textFieldEmail.setText(usuario.getEmail());
					textFieldTelefone.setText(usuario.getTelefone());
					textDepartamento.setText(usuario.getDepartamentoCurso());
					comboBoxCategoria.setSelectedItem(usuario.getTipoUsuario().getTipo());
					comboBoxStatus.setSelectedItem(usuario.getStatusUsuario().getStatus());
					textFieldInstituicao.setText(usuario.getInstituicao());
					textFieldDebito.setText(String.format("%.2f", usuario.getDebito()));

				}catch (BusinessException e1) {
					JOptionPane.showMessageDialog(JPanelAtualizarUsuario.this, e1.getMessage(), "Erro",
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

		textMatricula = new JTextField();
		textMatricula.setFont(new Font("Arial Black", Font.BOLD, 15));
		textMatricula.setColumns(10);
		textMatricula.setBounds(40, 160, 400, 40);
		add(textMatricula);

		JLabel lblMatricula = new JLabel("Matrícula");
		lblMatricula.setFont(new Font("Arial Black", Font.BOLD, 15));
		lblMatricula.setBounds(40, 140, 100, 20);
		add(lblMatricula);

		textNome = new JTextField();
		textNome.setFont(new Font("Arial Black", Font.BOLD, 15));
		textNome.setColumns(10);
		textNome.setBounds(40, 230, 400, 40);
		add(textNome);

		JLabel lblNome = new JLabel("Nome");
		lblNome.setFont(new Font("Arial Black", Font.BOLD, 15));
		lblNome.setBounds(40, 210, 100, 20);
		add(lblNome);

		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Arial Black", Font.BOLD, 15));
		lblEmail.setBounds(40, 280, 100, 20);
		add(lblEmail);

		textFieldEmail = new JTextField();
		textFieldEmail.setFont(new Font("Arial Black", Font.BOLD, 15));
		textFieldEmail.setColumns(10);
		textFieldEmail.setBounds(40, 300, 400, 40);
		add(textFieldEmail);

		JLabel lblTelefone = new JLabel("Telefone");
		lblTelefone.setFont(new Font("Arial Black", Font.BOLD, 15));
		lblTelefone.setBounds(40, 350, 100, 20);
		add(lblTelefone);

		textFieldTelefone = new JTextField();
		textFieldTelefone.setFont(new Font("Arial Black", Font.BOLD, 15));
		textFieldTelefone.setColumns(10);
		textFieldTelefone.setBounds(40, 370, 400, 40);
		add(textFieldTelefone);

		JLabel lblDepartamento = new JLabel("Departamento");
		lblDepartamento.setFont(new Font("Arial Black", Font.BOLD, 15));
		lblDepartamento.setBounds(40, 420, 145, 20);
		add(lblDepartamento);

		textDepartamento = new JTextField();
		textDepartamento.setFont(new Font("Arial Black", Font.BOLD, 15));
		textDepartamento.setColumns(10);
		textDepartamento.setBounds(40, 440, 400, 40);
		add(textDepartamento);

		comboBoxCategoria = new JComboBox<>();
		comboBoxCategoria.setBounds(40, 510, 400, 40);
		comboBoxCategoria.setFont(new Font("Arial Black", Font.BOLD, 13));
		comboBoxCategoria.addItem("Selecione uma categoria de usuário");
		for (TipoUsuario tipoUsuario : TipoUsuario.values()) {
			comboBoxCategoria.addItem(tipoUsuario.getTipo());
		}
		add(comboBoxCategoria);

		JLabel lblCategoria = new JLabel("Categoria");
		lblCategoria.setFont(new Font("Arial Black", Font.BOLD, 15));
		lblCategoria.setBounds(40, 490, 100, 20);
		add(lblCategoria);

		comboBoxStatus = new JComboBox<>();
		comboBoxStatus.setBounds(40, 580, 400, 40);
		comboBoxStatus.setFont(new Font("Arial Black", Font.BOLD, 13));
		comboBoxStatus.addItem("Selecione um status para o usuário");
		for (StatusUsuario statusUsuario : StatusUsuario.values()) {
			comboBoxStatus.addItem(statusUsuario.getStatus());
		}
		add(comboBoxStatus);

		JLabel lblStatus = new JLabel("Status do Usuário");
		lblStatus.setFont(new Font("Arial Black", Font.BOLD, 15));
		lblStatus.setBounds(40, 560, 171, 20);
		add(lblStatus);

		JLabel lblIstituicao = new JLabel("Instituição");
		lblIstituicao.setFont(new Font("Arial Black", Font.BOLD, 15));
		lblIstituicao.setBounds(40, 630, 100, 20);
		add(lblIstituicao);

		textFieldInstituicao = new JTextField();
		textFieldInstituicao.setFont(new Font("Arial Black", Font.BOLD, 15));
		textFieldInstituicao.setColumns(10);
		textFieldInstituicao.setBounds(40, 650, 400, 40);
		add(textFieldInstituicao);

		JButton btnNewButton = new JButton("Atualizar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String categoria = (String) comboBoxCategoria.getSelectedItem();
				String status = (String) comboBoxStatus.getSelectedItem();

				if (textFieldCpf.getText().trim().isEmpty() || textMatricula.getText().trim().isEmpty()
						|| textNome.getText().trim().isEmpty() || textFieldEmail.getText().trim().isEmpty()
						|| textFieldTelefone.getText().trim().isEmpty() || textDepartamento.getText().trim().isEmpty()
						|| categoria.equals("Selecione uma categoria de usuário")
						|| status.equals("Selecione um status para o usuário") || textFieldInstituicao.getText().trim().isEmpty()
						|| textFieldDebito.getText().trim().isEmpty()) {

					JOptionPane.showMessageDialog(JPanelAtualizarUsuario.this,
							"Por favor, preencha todos os campos para efetuar a atualização do usuário.", "Campos Vazios",
							JOptionPane.WARNING_MESSAGE);

					return;
				}
				
				int confirmacao = JOptionPane.showConfirmDialog(JPanelAtualizarUsuario.this,
		                "Tem certeza que deseja atualizar os dados deste livro?", "Confirmar Atualização",
		                JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
				
				if(confirmacao == JOptionPane.CANCEL_OPTION || confirmacao == JOptionPane.CLOSED_OPTION) {
					
					JOptionPane.showMessageDialog(JPanelAtualizarUsuario.this,
		                    "Operação de atualização cancelada.", "Cancelado",
		                    JOptionPane.INFORMATION_MESSAGE);
		            return;
				}
				
				String idUsuario = textFieldId.getText().trim();
				String cpf = textFieldCpf.getText().trim();
				String matricula = textMatricula.getText().trim();
				String nome = textNome.getText().trim();
				String email = textFieldEmail.getText().trim();
				String telefone = textFieldTelefone.getText().trim();
				String departamento = textDepartamento.getText().trim();
				String tipoUsuario = categoria;
				String statusUsuario = status;
				String instituicao = textFieldInstituicao.getText().trim();
				String debito = textFieldDebito.getText().trim();
				String debitoSting = debito.replace(",", ".");
				
				try {
					
					FuncionarioController funcionarioController = new FuncionarioController();
					funcionarioController.atualizar(idUsuario, cpf, matricula, nome, email, telefone, departamento, tipoUsuario, statusUsuario, instituicao, debitoSting);
					
					JOptionPane.showMessageDialog(JPanelAtualizarUsuario.this,
							"O usuário foi atualizado com sucesso em nosso sistema.", "Atualiação Concluída",
							JOptionPane.INFORMATION_MESSAGE);
					
					limparCampos();
					
				} catch (BusinessException e1) {
					JOptionPane.showMessageDialog(JPanelAtualizarUsuario.this, e1.getMessage(), "Erro",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnNewButton.setFont(new Font("Arial Black", Font.BOLD, 15));
		btnNewButton.setBounds(295, 20, 145, 40);
		add(btnNewButton);
		
		textFieldDebito = new JTextField();
		textFieldDebito.setFont(new Font("Arial Black", Font.BOLD, 15));
		textFieldDebito.setColumns(10);
		textFieldDebito.setBounds(40, 720, 400, 40);
		add(textFieldDebito);
		
		JLabel lblDebito = new JLabel("Débito");
		lblDebito.setFont(new Font("Arial Black", Font.BOLD, 15));
		lblDebito.setBounds(40, 700, 100, 20);
		add(lblDebito);
		
		

	}

	private void limparCampos() {

		textFieldId.setText("");
		textFieldCpf.setText("");
		textMatricula.setText("");
		textNome.setText("");
		textFieldEmail.setText("");
		textFieldTelefone.setText("");
		textDepartamento.setText("");
		comboBoxCategoria.setSelectedIndex(0);
		comboBoxStatus.setSelectedIndex(0);
		textFieldInstituicao.setText("");
		textFieldDebito.setText("");
	}
}
