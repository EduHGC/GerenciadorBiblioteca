package br.edu.ifpe.lpoo.project.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;

import br.edu.ifpe.lpoo.project.ui.acervo.JPainelAtualizarLivro;
import br.edu.ifpe.lpoo.project.ui.acervo.JPainelCadastroLivro;
import br.edu.ifpe.lpoo.project.ui.acervo.JPanelAtualizarStatus;
import br.edu.ifpe.lpoo.project.ui.acervo.JPanelBuscarLivros;
import br.edu.ifpe.lpoo.project.ui.acervo.JPanelCadastroExemplar;
import br.edu.ifpe.lpoo.project.ui.acervo.JPanelListaExemplares;
import br.edu.ifpe.lpoo.project.ui.acervo.JPanelListaLivros;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BibliotecaApp extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTabbedPane abas;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BibliotecaApp frame = new BibliotecaApp();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public BibliotecaApp() {

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

		JButton btnLivros = new JButton("Livros");
		btnLivros.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				carregarSecao("Livros");
			}
		});
		btnLivros.setFont(new Font("Arial Black", Font.BOLD, 20));
		btnLivros.setBounds(0, 0, 200, 100);
		painelEsquerdo.add(btnLivros);

		JButton btnPeriodicos = new JButton("Periódicos");
		btnPeriodicos.setFont(new Font("Arial Black", Font.BOLD, 20));
		btnPeriodicos.setBounds(0, 100, 200, 100);
		painelEsquerdo.add(btnPeriodicos);

		JButton btnExemplar = new JButton("Exemplares");
		btnExemplar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				carregarSecao("Exemplares");
			}
		});
		btnExemplar.setFont(new Font("Arial Black", Font.BOLD, 20));
		btnExemplar.setBounds(0, 200, 200, 100);
		painelEsquerdo.add(btnExemplar);

		JButton btnReservas = new JButton("Reservas");
		btnReservas.setFont(new Font("Arial Black", Font.BOLD, 20));
		btnReservas.setBounds(0, 300, 200, 100);
		painelEsquerdo.add(btnReservas);

		JButton btnEmprestimos = new JButton("Empréstimos");
		btnEmprestimos.setFont(new Font("Arial Black", Font.BOLD, 20));
		btnEmprestimos.setBounds(0, 400, 200, 100);
		painelEsquerdo.add(btnEmprestimos);

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
//	        case "Empréstimos":
//	            abas.addTab("Registrar Empréstimo", new PainelRegistroEmprestimo());
//	            abas.addTab("Consultar Empréstimos", new PainelConsultaEmprestimo());
//	            break;
//		   	case "Periódicos":
//	            abas.addTab("Cadastrar Periódico", new PainelCadastroPeriodico());
//	            abas.addTab("Consultar Periódicos", new PainelConsultaPeriodico());
//	            break;
		default:
			JPanel painel = new JPanel();
			painel.add(new JLabel("Seção não reconhecida."));
			abas.addTab("Erro", painel);
			break;
		}
	}

}
