package br.edu.ifpe.lpoo.project.ui.gerenciamento;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.io.File;
import java.time.format.DateTimeFormatter;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import br.edu.ifpe.lpoo.project.entities.gerenciamento.CardEmprestimo;

public class JPanelCardEmprestimo extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel lblCapaLivro;
	private JLabel lblIdentificadorValor;
	private JLabel lblNomeValor;
	private JLabel lblCpfValor;
	private JLabel lblBibliotecarioValor;
	private JLabel lblTituloValor;
	private JLabel lblIdExemplarValor;
	private JLabel lblDataEmprestimoValor;
	private JLabel lblDataParaDevolucaoValor;
	private JLabel lblDataRealDevolucaoValor;
	private JLabel lblStatusDoEmprestimoValor;
	private DateTimeFormatter formatarData = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	
	public JPanelCardEmprestimo(CardEmprestimo cardEmprestimo) {

		setLayout(null);
		setBorder(new LineBorder(Color.BLACK, 3));
		setPreferredSize(new Dimension(595, 468));

		lblCapaLivro = new JLabel("Capa do exemplar ausênte");
		lblCapaLivro.setFont(new Font("Arial Black", Font.PLAIN, 15));
		lblCapaLivro.setHorizontalAlignment(SwingConstants.CENTER);
		lblCapaLivro.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		lblCapaLivro.setBounds(338, 10, 247, 290);
		add(lblCapaLivro);

		JLabel lblNome = new JLabel("Nome: ");
		lblNome.setFont(new Font("Arial Black", Font.BOLD, 14));
		lblNome.setVerticalAlignment(SwingConstants.TOP);
		lblNome.setHorizontalAlignment(SwingConstants.LEFT);
		lblNome.setBounds(10, 60, 280, 20);
		add(lblNome);

		JLabel lblCpf = new JLabel("CPF:");
		lblCpf.setFont(new Font("Arial Black", Font.BOLD, 14));
		lblCpf.setVerticalAlignment(SwingConstants.TOP);
		lblCpf.setHorizontalAlignment(SwingConstants.LEFT);
		lblCpf.setBounds(10, 110, 280, 20);
		add(lblCpf);

		JLabel lblBibliotecario = new JLabel("Bibliotecário: ");
		lblBibliotecario.setFont(new Font("Arial Black", Font.BOLD, 14));
		lblBibliotecario.setVerticalAlignment(SwingConstants.TOP);
		lblBibliotecario.setHorizontalAlignment(SwingConstants.LEFT);
		lblBibliotecario.setBounds(10, 160, 280, 20);
		add(lblBibliotecario);

		JLabel lblTitulo = new JLabel("Título:");
		lblTitulo.setFont(new Font("Arial Black", Font.BOLD, 14));
		lblTitulo.setVerticalAlignment(SwingConstants.TOP);
		lblTitulo.setHorizontalAlignment(SwingConstants.LEFT);
		lblTitulo.setBounds(10, 210, 280, 20);
		add(lblTitulo);

		JLabel lblIdExemplar = new JLabel("Identificador do Exemplar:");
		lblIdExemplar.setFont(new Font("Arial Black", Font.BOLD, 14));
		lblIdExemplar.setVerticalAlignment(SwingConstants.TOP);
		lblIdExemplar.setHorizontalAlignment(SwingConstants.LEFT);
		lblIdExemplar.setBounds(10, 260, 280, 20);
		add(lblIdExemplar);

		JLabel lblDataEmprestimo = new JLabel("Data do empréstimo:");
		lblDataEmprestimo.setFont(new Font("Arial Black", Font.BOLD, 14));
		lblDataEmprestimo.setVerticalAlignment(SwingConstants.TOP);
		lblDataEmprestimo.setHorizontalAlignment(SwingConstants.LEFT);
		lblDataEmprestimo.setBounds(10, 310, 280, 20);
		add(lblDataEmprestimo);
		
		JLabel lblIdLivro = new JLabel("Identificador:");
		lblIdLivro.setVerticalAlignment(SwingConstants.TOP);
		lblIdLivro.setHorizontalAlignment(SwingConstants.LEFT);
		lblIdLivro.setFont(new Font("Arial Black", Font.BOLD, 14));
		lblIdLivro.setBounds(10, 10, 280, 20);
		add(lblIdLivro);
		
		lblIdentificadorValor = new JLabel("");
		lblIdentificadorValor.setVerticalAlignment(SwingConstants.TOP);
		lblIdentificadorValor.setHorizontalAlignment(SwingConstants.LEFT);
		lblIdentificadorValor.setFont(new Font("Arial Black", Font.BOLD, 14));
		lblIdentificadorValor.setBounds(10, 30, 280, 20);
		add(lblIdentificadorValor);
		
		lblNomeValor = new JLabel("");
		lblNomeValor.setVerticalAlignment(SwingConstants.TOP);
		lblNomeValor.setHorizontalAlignment(SwingConstants.LEFT);
		lblNomeValor.setFont(new Font("Arial Black", Font.BOLD, 14));
		lblNomeValor.setBounds(10, 80, 280, 20);
		add(lblNomeValor);
		
		lblCpfValor = new JLabel("");
		lblCpfValor.setVerticalAlignment(SwingConstants.TOP);
		lblCpfValor.setHorizontalAlignment(SwingConstants.LEFT);
		lblCpfValor.setFont(new Font("Arial Black", Font.BOLD, 14));
		lblCpfValor.setBounds(10, 130, 280, 20);
		add(lblCpfValor);
		
		lblBibliotecarioValor = new JLabel("");
		lblBibliotecarioValor.setVerticalAlignment(SwingConstants.TOP);
		lblBibliotecarioValor.setHorizontalAlignment(SwingConstants.LEFT);
		lblBibliotecarioValor.setFont(new Font("Arial Black", Font.BOLD, 14));
		lblBibliotecarioValor.setBounds(10, 180, 280, 20);
		add(lblBibliotecarioValor);
		
		lblTituloValor = new JLabel("");
		lblTituloValor.setVerticalAlignment(SwingConstants.TOP);
		lblTituloValor.setHorizontalAlignment(SwingConstants.LEFT);
		lblTituloValor.setFont(new Font("Arial Black", Font.BOLD, 14));
		lblTituloValor.setBounds(10, 230, 280, 20);
		add(lblTituloValor);
		
		lblIdExemplarValor = new JLabel("");
		lblIdExemplarValor.setVerticalAlignment(SwingConstants.TOP);
		lblIdExemplarValor.setHorizontalAlignment(SwingConstants.LEFT);
		lblIdExemplarValor.setFont(new Font("Arial Black", Font.BOLD, 14));
		lblIdExemplarValor.setBounds(10, 280, 280, 20);
		add(lblIdExemplarValor);
		
		lblDataEmprestimoValor = new JLabel("");
		lblDataEmprestimoValor.setVerticalAlignment(SwingConstants.TOP);
		lblDataEmprestimoValor.setHorizontalAlignment(SwingConstants.LEFT);
		lblDataEmprestimoValor.setFont(new Font("Arial Black", Font.BOLD, 14));
		lblDataEmprestimoValor.setBounds(10, 330, 280, 20);
		add(lblDataEmprestimoValor);
		
		lblDataParaDevolucaoValor = new JLabel("");
		lblDataParaDevolucaoValor.setVerticalAlignment(SwingConstants.TOP);
		lblDataParaDevolucaoValor.setHorizontalAlignment(SwingConstants.LEFT);
		lblDataParaDevolucaoValor.setFont(new Font("Arial Black", Font.BOLD, 14));
		lblDataParaDevolucaoValor.setBounds(10, 380, 280, 20);
		add(lblDataParaDevolucaoValor);
		
		JLabel lblDataParaDevolucao = new JLabel("Data para devolução:");
		lblDataParaDevolucao.setVerticalAlignment(SwingConstants.TOP);
		lblDataParaDevolucao.setHorizontalAlignment(SwingConstants.LEFT);
		lblDataParaDevolucao.setFont(new Font("Arial Black", Font.BOLD, 14));
		lblDataParaDevolucao.setBounds(10, 360, 280, 20);
		add(lblDataParaDevolucao);
		
		JLabel lblDataRealDevolucao = new JLabel("Data real da devolução:");
		lblDataRealDevolucao.setVerticalAlignment(SwingConstants.TOP);
		lblDataRealDevolucao.setHorizontalAlignment(SwingConstants.LEFT);
		lblDataRealDevolucao.setFont(new Font("Arial Black", Font.BOLD, 14));
		lblDataRealDevolucao.setBounds(10, 410, 280, 20);
		add(lblDataRealDevolucao);
		
		lblDataRealDevolucaoValor = new JLabel("");
		lblDataRealDevolucaoValor.setVerticalAlignment(SwingConstants.TOP);
		lblDataRealDevolucaoValor.setHorizontalAlignment(SwingConstants.LEFT);
		lblDataRealDevolucaoValor.setFont(new Font("Arial Black", Font.BOLD, 14));
		lblDataRealDevolucaoValor.setBounds(10, 430, 280, 20);
		add(lblDataRealDevolucaoValor);
		
		JLabel lblStatusDoEmprestimo = new JLabel("Status do emprestimo:");
		lblStatusDoEmprestimo.setVerticalAlignment(SwingConstants.TOP);
		lblStatusDoEmprestimo.setHorizontalAlignment(SwingConstants.LEFT);
		lblStatusDoEmprestimo.setFont(new Font("Arial Black", Font.BOLD, 14));
		lblStatusDoEmprestimo.setBounds(338, 310, 221, 20);
		add(lblStatusDoEmprestimo);
		
		lblStatusDoEmprestimoValor = new JLabel("");
		lblStatusDoEmprestimoValor.setVerticalAlignment(SwingConstants.TOP);
		lblStatusDoEmprestimoValor.setHorizontalAlignment(SwingConstants.LEFT);
		lblStatusDoEmprestimoValor.setFont(new Font("Arial Black", Font.BOLD, 14));
		lblStatusDoEmprestimoValor.setBounds(338, 330, 247, 20);
		add(lblStatusDoEmprestimoValor);
		
		if (cardEmprestimo != null) {
			
			
			lblIdentificadorValor.setText(String.valueOf(cardEmprestimo.getIdEmprestimo()));
			lblNomeValor.setText(cardEmprestimo.getNomeUsuario());
			lblCpfValor.setText(cardEmprestimo.getCpfUsuario());
			lblBibliotecarioValor.setText(cardEmprestimo.getNomeFuncionario());
			lblTituloValor.setText(cardEmprestimo.getTitulo());
			lblIdExemplarValor.setText(String.valueOf(cardEmprestimo.getIdExemplar()));
			lblDataEmprestimoValor.setText(cardEmprestimo.getDataEmprestimo().format(formatarData));
			lblDataParaDevolucaoValor.setText(cardEmprestimo.getDataParaDevolucao().format(formatarData));
			if(cardEmprestimo.getDataRealDevolucao() != null) {
				lblDataRealDevolucaoValor.setText(cardEmprestimo.getDataRealDevolucao().format(formatarData));
			}else {
				lblDataRealDevolucaoValor.setText("-");
			}
			
			lblStatusDoEmprestimoValor.setText(cardEmprestimo.getStatusExemplar().getStatus());
			
			String pathAppBiblioteca = System.getenv("APPDATA");
			File pastaCapas = new File(pathAppBiblioteca, "Biblioteca/Capas");
			
			String[] extensoes = { "png", "jpg", "jpeg" };
			
			for(String extensao : extensoes) {
				File capa = new File (pastaCapas, cardEmprestimo.getIdLivro() + "." + extensao);
				if(capa.exists()) {
					ImageIcon imageIco = new ImageIcon(capa.getAbsolutePath());
					
					int widthLblCapaLivro = lblCapaLivro.getWidth();
					int heightLblCapaLivro = lblCapaLivro.getHeight();
					
					Image originalImage = imageIco.getImage();
					Image redimensionar = originalImage.getScaledInstance(widthLblCapaLivro, heightLblCapaLivro, Image.SCALE_SMOOTH);
					ImageIcon imagemCapa = new ImageIcon(redimensionar);
					
					lblCapaLivro.setIcon(imagemCapa);
					lblCapaLivro.setText("");
				}
			}
			
		}
	}
}
