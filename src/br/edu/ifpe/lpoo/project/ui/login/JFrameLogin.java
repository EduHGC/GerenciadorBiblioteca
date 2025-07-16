package br.edu.ifpe.lpoo.project.ui.login;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.edu.ifpe.lpoo.project.business.funcionario.LoginController;
import br.edu.ifpe.lpoo.project.entities.funcionario.Funcionario;
import br.edu.ifpe.lpoo.project.exception.BusinessException;
import br.edu.ifpe.lpoo.project.ui.BibliotecaApp;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JFrameLogin extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldUsuario;
	private JPasswordField passwordFieldSenha;
	
	public JFrameLogin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 510);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblBemVindo = new JLabel("Bem Vindo");
		lblBemVindo.setFont(new Font("Arial Black", Font.BOLD, 20));
		lblBemVindo.setBounds(285, 100, 130, 20);
		contentPane.add(lblBemVindo);
		
		JLabel lblUsuario = new JLabel("Usuário");
		lblUsuario.setFont(new Font("Arial Black", Font.BOLD, 15));
		lblUsuario.setBounds(150, 130, 83, 20);
		contentPane.add(lblUsuario);
		
		textFieldUsuario = new JTextField();
		textFieldUsuario.setFont(new Font("Arial Black", Font.BOLD, 15));
		textFieldUsuario.setBounds(150, 150, 400, 40);
		contentPane.add(textFieldUsuario);
		textFieldUsuario.setColumns(10);
		
		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setFont(new Font("Arial Black", Font.BOLD, 15));
		lblSenha.setBounds(150, 200, 83, 20);
		contentPane.add(lblSenha);
		
		passwordFieldSenha = new JPasswordField();
		passwordFieldSenha.setFont(new Font("Arial Black", Font.BOLD, 15));
		passwordFieldSenha.setBounds(150, 220, 400, 40);
		contentPane.add(passwordFieldSenha);
		
		JButton btnEntrar = new JButton("Entrar");
		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(textFieldUsuario.getText().trim().isEmpty()) {
					JOptionPane.showMessageDialog(JFrameLogin.this,
							"Por favor, preencha o campo Usuário.", "Campos Vazios",
							JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				char[] senhaArray = passwordFieldSenha.getPassword();
				if(senhaArray.length == 0) {
					JOptionPane.showMessageDialog(JFrameLogin.this,
							"Por favor, preencha o campo Senha.", "Campos Vazios",
							JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				String email = textFieldUsuario.getText().trim();
				String senha = new String(senhaArray);
				
				try {
					
					LoginController loginController = new LoginController();
					Funcionario funcionario = loginController.buscarLogin(email);
					
					if(senha.equals(funcionario.getSenha())) {
						funcionario.setLogado(true);
						loginController.atualizarParaLogin(funcionario);
						dispose();
						new BibliotecaApp(funcionario).setVisible(true);
					}else {
						JOptionPane.showMessageDialog(JFrameLogin.this,
								"Senha incorreta. Insira novamente.", "Senha incorreta",
								JOptionPane.ERROR_MESSAGE);
						return;
					}
					
					
				}catch (BusinessException e1) {
					JOptionPane.showMessageDialog(JFrameLogin.this,
							"Email não cadastrado. Verifique o email digitado.", "Usuário não encontrado",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		
		});
		btnEntrar.setFont(new Font("Arial Black", Font.BOLD, 15));
		btnEntrar.setBounds(150, 270, 400, 40);
		contentPane.add(btnEntrar);

	}
}
