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
import br.edu.ifpe.lpoo.project.ui.gerenciamento.JPanelRegistrarEmprestimo;
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
	private JButton btnReservas;
	private JButton btnEmprestimos;
	private JButton btnUsuario;
	private JLabel lblNomeFuncionario;
	private Funcionario funcionarioLogado;
	private JButton btnDeslogar;

	
	public BibliotecaApp(Funcionario funcionarioLogado) {
		
		this.funcionarioLogado = funcionarioLogado;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
//        setBounds(100, 10, 1300, 800); 
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

		btnReservas = new JButton("Reservas");
		btnReservas.setFont(new Font("Arial Black", Font.BOLD, 20));
		btnReservas.setBounds(0, 300, 200, 100);
		painelEsquerdo.add(btnReservas);

		btnEmprestimos = new JButton("Empréstimos");
		btnEmprestimos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				carregarSecao("Empréstimos");
			}
		});
		btnEmprestimos.setFont(new Font("Arial Black", Font.BOLD, 20));
		btnEmprestimos.setBounds(0, 400, 200, 100);
		painelEsquerdo.add(btnEmprestimos);

		btnUsuario = new JButton("Usuários");
		btnUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				carregarSecao("Usuários");
			}
		});
		btnUsuario.setFont(new Font("Arial Black", Font.BOLD, 20));
		btnUsuario.setBounds(0, 500, 200, 100);
		painelEsquerdo.add(btnUsuario);
		
		btnFuncionario = new JButton("Funcionários");
		btnFuncionario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				carregarSecao("Funcionários");
			}
		});
		btnFuncionario.setFont(new Font("Arial Black", Font.BOLD, 20));
		btnFuncionario.setBounds(0, 600, 200, 100);
		painelEsquerdo.add(btnFuncionario);
		
		btnDeslogar = new JButton("Sair");
		btnDeslogar.setFont( new Font("Arial Black", Font.BOLD, 20));
		btnDeslogar.setBounds(0, 700, 200, 100);
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
		painelInicial.add(new JLabel("Selecione uma opção no menu à esquerda."));
		abas.addTab("Início", painelInicial);

		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, new JScrollPane(painelEsquerdo), abas);
		splitPane.setDividerLocation(200);
		splitPane.setOneTouchExpandable(true);

		// Centraliza o JSplitPane
		contentPane.add(splitPane, BorderLayout.CENTER);
		
		
	}

	private void carregarSecao(String secao) {
		abas.removeAll(); // Remove todas as abas existentes

		switch (secao) {
		case "Livros":
			abas.addTab("Cadastrar Livro", new JPainelCadastroLivro());
			abas.addTab("Lista de Livros", new JPanelListaLivros());
			abas.addTab("Atualizar informações", new JPainelAtualizarLivro());
			abas.addTab("Buscar Livros", new JPanelBuscarLivros());
//	            abas.addTab("Consultar Livros", new PainelConsultaLivro());
			break;
		case "Exemplares":
			abas.addTab("Adicionar Exemplar", new JPanelCadastroExemplar());
			abas.addTab("Lista Exemplares por Livro", new JPanelListaExemplares());
			abas.addTab("Atualizar status", new JPanelAtualizarStatus());
			break;
//	        case "Reservas":
//	            abas.addTab("Registrar Reserva", new PainelRegistroReserva());
//	            abas.addTab("Consultar Reservas", new PainelConsultaReserva());
//	            break;
	        case "Empréstimos":
	            abas.addTab("Registrar Empréstimo", new JPanelRegistrarEmprestimo(funcionarioLogado));
//	            abas.addTab("Consultar Empréstimos", new PainelConsultaEmprestimo());
	            break;
		case "Usuários":
			abas.addTab("Castratar", new JPanelCadastrarUsuario());
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

}
