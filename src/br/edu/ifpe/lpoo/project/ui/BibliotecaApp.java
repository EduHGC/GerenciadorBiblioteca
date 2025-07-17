package br.edu.ifpe.lpoo.project.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import br.edu.ifpe.lpoo.project.entities.funcionario.Funcionario;
import br.edu.ifpe.lpoo.project.ui.acervo.JPainelAtualizarLivro;
import br.edu.ifpe.lpoo.project.ui.acervo.JPainelCadastroLivro;
import br.edu.ifpe.lpoo.project.ui.acervo.JPanelAtualizarStatus;
import br.edu.ifpe.lpoo.project.ui.acervo.JPanelBuscarLivros;
import br.edu.ifpe.lpoo.project.ui.acervo.JPanelCadastroExemplar;
import br.edu.ifpe.lpoo.project.ui.acervo.JPanelListaExemplares;
import br.edu.ifpe.lpoo.project.ui.acervo.JPanelListaLivros;
import br.edu.ifpe.lpoo.project.ui.funcionario.JPanelAtualizarFuncionario;
import br.edu.ifpe.lpoo.project.ui.funcionario.JPanelCadastrarFuncionario;
import br.edu.ifpe.lpoo.project.ui.gerenciamento.JPanelFinalizarEmprestimo;
import br.edu.ifpe.lpoo.project.ui.gerenciamento.JPanelListarEmprestimos;
import br.edu.ifpe.lpoo.project.ui.gerenciamento.JPanelRegistrarEmprestimo;
import br.edu.ifpe.lpoo.project.ui.login.JFrameLogin;
import br.edu.ifpe.lpoo.project.ui.usuario.JPanelAtualizarUsuario;
import br.edu.ifpe.lpoo.project.ui.usuario.JPanelBuscarUsuario;
import br.edu.ifpe.lpoo.project.ui.usuario.JPanelCadastrarUsuario;
import br.edu.ifpe.lpoo.project.ui.usuario.JPanelListaUsuarios;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BibliotecaApp extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTabbedPane abas;
	private JButton btnLivros;
	private JButton btnExemplar;
	private JButton btnFuncionario;
	private JButton btnEmprestimos;
	private JButton btnUsuario;
	private JLabel lblNomeFuncionario;
	private Funcionario funcionarioLogado;
	private JButton btnDeslogar;

	
	public BibliotecaApp(Funcionario funcionarioLogado) {
		
		this.funcionarioLogado = funcionarioLogado;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setMinimumSize(new java.awt.Dimension(1500, 800));
		contentPane = new JPanel(new BorderLayout());
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		// Lado esquerdo
		JPanel painelEsquerdo = new JPanel();
		painelEsquerdo.setBackground(new Color(240, 240, 255));
		painelEsquerdo.setLayout(null);
		
		lblNomeFuncionario = new JLabel("");
		lblNomeFuncionario.setFont(new Font("Arial Black", Font.BOLD, 13));
		lblNomeFuncionario.setBounds(0, 0, 200, 100);
		painelEsquerdo.add(lblNomeFuncionario);
		
		btnLivros = new JButton("Livros");
		btnLivros.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				carregarSecao("Livros");
			}
		});
		btnLivros.setFont(new Font("Arial Black", Font.BOLD, 20));
		btnLivros.setBounds(0, 100, 200, 100);
		painelEsquerdo.add(btnLivros);


		btnExemplar = new JButton("Exemplares");
		btnExemplar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				carregarSecao("Exemplares");
			}
		});
		btnExemplar.setFont(new Font("Arial Black", Font.BOLD, 20));
		btnExemplar.setBounds(0, 200, 200, 100);
		painelEsquerdo.add(btnExemplar);

		
		btnEmprestimos = new JButton("Empréstimos");
		btnEmprestimos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				carregarSecao("Empréstimos");
			}
		});
		btnEmprestimos.setFont(new Font("Arial Black", Font.BOLD, 20));
		btnEmprestimos.setBounds(0, 300, 200, 100);
		painelEsquerdo.add(btnEmprestimos);

		btnUsuario = new JButton("Usuários");
		btnUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				carregarSecao("Usuários");
			}
		});
		btnUsuario.setFont(new Font("Arial Black", Font.BOLD, 20));
		btnUsuario.setBounds(0, 400, 200, 100);
		painelEsquerdo.add(btnUsuario);
		
		btnFuncionario = new JButton("Funcionários");
		btnFuncionario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				carregarSecao("Funcionários");
			}
		});
		btnFuncionario.setFont(new Font("Arial Black", Font.BOLD, 20));
		btnFuncionario.setBounds(0, 500, 200, 100);
		painelEsquerdo.add(btnFuncionario);
		
		btnDeslogar = new JButton("Sair");
		btnDeslogar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				funcionarioLogado.setLogado(false);
				dispose();
				JFrameLogin jFrameLogin = new JFrameLogin();
				jFrameLogin.setVisible(true);
				
			}
		});
		btnDeslogar.setFont( new Font("Arial Black", Font.BOLD, 20));
		btnDeslogar.setBounds(0, 600, 200, 100);
		painelEsquerdo.add(btnDeslogar);
		
		if(!funcionarioLogado.getCargo().getCargo().equals("Supervisor")) {
			btnFuncionario.setEnabled(false);
		}
		
		String[] nome = funcionarioLogado.getNome().split(" ");
		
		lblNomeFuncionario.setText("<html><div style='text-align: center;'>Bem vindo<br>" + nome[0] + "</div></html>");
		lblNomeFuncionario.setHorizontalAlignment(SwingConstants.CENTER);
		
		
		
		// Abas a direita
		abas = new JTabbedPane();
		abas.setBackground(new Color(255, 255, 255));

		JPanel painelInicial = new JPanel();
		painelInicial.setLayout(new BorderLayout());
		JLabel lblInicio = new JLabel("Selecione uma opção no menu à esquerda.");
		lblInicio.setFont(new Font("Arial Black", Font.BOLD, 20));
		lblInicio.setHorizontalAlignment(SwingConstants.CENTER);
		lblInicio.setVerticalAlignment(SwingConstants.CENTER);
		painelInicial.add(lblInicio, BorderLayout.CENTER);
		abas.addTab("Início", painelInicial);

		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, new JScrollPane(painelEsquerdo), abas);
		splitPane.setDividerLocation(200);

		// Centraliza o JSplitPane
		contentPane.add(splitPane, BorderLayout.CENTER);
		
		
	}

	private void carregarSecao(String secao) {
		abas.removeAll(); // Remove todas as abas existentes

		switch (secao) {
		case "Livros":
			abas.addTab("Cadastrar Livro", new JPainelCadastroLivro(this));
			abas.addTab("Lista de Livros", new JPanelListaLivros());
			abas.addTab("Atualizar informações", new JPainelAtualizarLivro());
			abas.addTab("Buscar Livros", new JPanelBuscarLivros());
			break;
		case "Exemplares":
			abas.addTab("Adicionar Exemplar", new JPanelCadastroExemplar(this));
			abas.addTab("Lista Exemplares por Livro", new JPanelListaExemplares());
			abas.addTab("Atualizar status", new JPanelAtualizarStatus());
			break;
        case "Empréstimos":
            abas.addTab("Registrar Empréstimo", new JPanelRegistrarEmprestimo(funcionarioLogado, this));
            abas.addTab("Lista de empréstimos", new JPanelListarEmprestimos());
            abas.addTab("Finalizar empréstimo", new JPanelFinalizarEmprestimo(this));
            break;
		case "Usuários":
			abas.addTab("Castratar", new JPanelCadastrarUsuario(this));
			abas.addTab("Listagem de usuários", new JPanelListaUsuarios());
			abas.addTab("Atualizar informações", new JPanelAtualizarUsuario());
			abas.addTab("Buscar Usuários", new JPanelBuscarUsuario());
			break;
		   	case "Funcionários":
	            abas.addTab("Cadastrar", new JPanelCadastrarFuncionario());
	            abas.addTab("Atualizar informações", new JPanelAtualizarFuncionario());
	            break;
		default:
			JPanel painel = new JPanel();
			painel.add(new JLabel("Seção não reconhecida."));
			abas.addTab("Erro", painel);
			break;
		}
	}
	
	public void atualizarAbaListagemEmprestimos() {
	    for (int i = 0; i < abas.getTabCount(); i++) {
	        if (abas.getTitleAt(i).equals("Lista de empréstimos")) {
	            abas.setComponentAt(i, new JPanelListarEmprestimos());
	            break;
	        }
	    }
	}
	
	public void atualizarAbaListagemLivros() {
	    for (int i = 0; i < abas.getTabCount(); i++) {
	        if (abas.getTitleAt(i).equals("Lista de Livros")) {
	            abas.setComponentAt(i, new JPanelListaLivros());
	            break;
	        }
	    }
	}
	
	public void atualizarAbaListagemExemplares() {
	    for (int i = 0; i < abas.getTabCount(); i++) {
	        if (abas.getTitleAt(i).equals("Lista Exemplares por Livro")) {
	            abas.setComponentAt(i, new JPanelListaExemplares());
	            break;
	        }
	    }
	}
	
	public void atualizarAbaListagemUsuarios() {
	    for (int i = 0; i < abas.getTabCount(); i++) {
	        if (abas.getTitleAt(i).equals("Listagem de usuários")) {
	            abas.setComponentAt(i, new JPanelListaUsuarios());
	            break;
	        }
	    }
	}
}
