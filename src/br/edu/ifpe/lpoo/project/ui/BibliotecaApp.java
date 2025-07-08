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
        setBounds(100, 10, 1300, 800); 
        contentPane = new JPanel(new BorderLayout()); 
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
		
        //Lado esquerdo
        JPanel painelEsquerdo = new JPanel();
        painelEsquerdo.setBorder(BorderFactory.createTitledBorder("Menu"));
        painelEsquerdo.setBackground(new Color(240, 240, 255)); 
        painelEsquerdo.setLayout(null); 

        
        JButton btnLivros = new JButton("Livros");
        btnLivros.setFont(new Font("Arial Black", Font.BOLD, 20));
        btnLivros.setBounds(0, 40, 200, 100);
        painelEsquerdo.add(btnLivros);

        JButton btnPeriodicos = new JButton("Periódicos");
        btnPeriodicos.setFont(new Font("Arial Black", Font.BOLD, 20));
        btnPeriodicos.setBounds(0, 140, 200, 100);
        painelEsquerdo.add(btnPeriodicos);

        JButton btnExemplar = new JButton("Exemplares");
        btnExemplar.setFont(new Font("Arial Black", Font.BOLD, 20));
        btnExemplar.setBounds(0, 240, 200, 100);
        painelEsquerdo.add(btnExemplar);

        JButton btnReservas = new JButton("Reservas");
        btnReservas.setFont(new Font("Arial Black", Font.BOLD, 20));
        btnReservas.setBounds(0, 340, 200, 100);
        painelEsquerdo.add(btnReservas);

        JButton btnEmprestimos = new JButton("Empréstimos");
        btnEmprestimos.setFont(new Font("Arial Black", Font.BOLD, 20));
        btnEmprestimos.setBounds(0, 440, 200, 100);
        painelEsquerdo.add(btnEmprestimos);
        
        //Abas a direita
        abas = new JTabbedPane();
        abas.setBorder(BorderFactory.createTitledBorder("Conteúdo"));
        abas.setBackground(new Color(255, 240, 240)); 

        
        JPanel painelInicial = new JPanel();
        painelInicial.add(new JLabel("Selecione uma opção no menu à esquerda."));
        abas.addTab("Início", painelInicial);
        
        
        JSplitPane splitPane = new JSplitPane(
                JSplitPane.HORIZONTAL_SPLIT, 
                new JScrollPane(painelEsquerdo),  
                abas   
        );
        splitPane.setDividerLocation(200); 
        splitPane.setOneTouchExpandable(true); 

        // Centraliza o JSplitPane 
        contentPane.add(splitPane, BorderLayout.CENTER);

        
		
	}
	
}
