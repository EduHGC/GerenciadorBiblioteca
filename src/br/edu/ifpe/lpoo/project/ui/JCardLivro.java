package br.edu.ifpe.lpoo.project.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import br.edu.ifpe.lpoo.project.entities.acervo.Livro;

public class JCardLivro extends JPanel {

	private static final long serialVersionUID = 1L;

	private JLabel lblTitulo;

	public JCardLivro(Livro livro) {

		setLayout(null);
		setBorder(new LineBorder(Color.BLACK, 3));
		setPreferredSize(new Dimension(595, 374));

		JPanel panelTitulo = new JPanel();
		panelTitulo.setBounds(0, 0, 595, 40);
		panelTitulo.setBorder(new LineBorder(Color.BLACK, 3));
		add(panelTitulo);

		lblTitulo = new JLabel("Título");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Arial", Font.BOLD, 22));

		panelTitulo.setLayout(new BorderLayout());
		panelTitulo.add(lblTitulo, BorderLayout.CENTER);
		
		addComponentListener(new ComponentAdapter() {
	        @Override
	        public void componentResized(ComponentEvent e) {
	            panelTitulo.setBounds(0, 0, getWidth(), 40);
	        }
	    });

		JLabel lblCapaLivro = new JLabel("Capa do livro ausênte");
		lblCapaLivro.setFont(new Font("Arial Black", Font.PLAIN, 15));
		lblCapaLivro.setHorizontalAlignment(SwingConstants.CENTER);
		lblCapaLivro.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		lblCapaLivro.setBounds(338, 47, 247, 290);
		add(lblCapaLivro);

		JLabel lblCaracteristicas = new JLabel("Características:");
		lblCaracteristicas.setFont(new Font("Arial Black", Font.PLAIN, 18));
		lblCaracteristicas.setBounds(10, 60, 200, 20);
		add(lblCaracteristicas);

		JLabel lblAutor = new JLabel("Autor: ");
		lblAutor.setFont(new Font("Arial Black", Font.BOLD, 14));
		lblAutor.setVerticalAlignment(SwingConstants.TOP);
		lblAutor.setHorizontalAlignment(SwingConstants.LEFT);
		lblAutor.setBounds(10, 128, 280, 40);
		add(lblAutor);

		JLabel lblEditora = new JLabel("Editora: ");
		lblEditora.setFont(new Font("Arial Black", Font.BOLD, 14));
		lblEditora.setVerticalAlignment(SwingConstants.TOP);
		lblEditora.setHorizontalAlignment(SwingConstants.LEFT);
		lblEditora.setBounds(10, 168, 280, 40);
		add(lblEditora);

		JLabel lblAnoPublic = new JLabel("Ano de publicação: ");
		lblAnoPublic.setFont(new Font("Arial Black", Font.BOLD, 14));
		lblAnoPublic.setVerticalAlignment(SwingConstants.TOP);
		lblAnoPublic.setHorizontalAlignment(SwingConstants.LEFT);
		lblAnoPublic.setBounds(10, 208, 280, 40);
		add(lblAnoPublic);

		JLabel lblGenero = new JLabel("Gênero: ");
		lblGenero.setFont(new Font("Arial Black", Font.BOLD, 14));
		lblGenero.setVerticalAlignment(SwingConstants.TOP);
		lblGenero.setHorizontalAlignment(SwingConstants.LEFT);
		lblGenero.setBounds(10, 248, 280, 40);
		add(lblGenero);

		JLabel lblIdioma = new JLabel("Idioma: ");
		lblIdioma.setFont(new Font("Arial Black", Font.BOLD, 14));
		lblIdioma.setVerticalAlignment(SwingConstants.TOP);
		lblIdioma.setHorizontalAlignment(SwingConstants.LEFT);
		lblIdioma.setBounds(10, 288, 280, 40);
		add(lblIdioma);

		JLabel lblNumPagina = new JLabel("Número de páginas: ");
		lblNumPagina.setFont(new Font("Arial Black", Font.BOLD, 14));
		lblNumPagina.setVerticalAlignment(SwingConstants.TOP);
		lblNumPagina.setHorizontalAlignment(SwingConstants.LEFT);
		lblNumPagina.setBounds(10, 328, 280, 40);
		add(lblNumPagina);
		
		JLabel lblIdLivro = new JLabel("Identificador:");
		lblIdLivro.setVerticalAlignment(SwingConstants.TOP);
		lblIdLivro.setHorizontalAlignment(SwingConstants.LEFT);
		lblIdLivro.setFont(new Font("Arial Black", Font.BOLD, 14));
		lblIdLivro.setBounds(10, 88, 280, 40);
		add(lblIdLivro);
		
		if (livro != null) {
			lblTitulo.setText(livro.getTitulo());
			lblIdLivro.setText("Identificador: " + livro.getIdItem());
			lblAutor.setText("Autor: " + livro.getAutor());
			lblEditora.setText("Editora: " + livro.getEditora());
			lblAnoPublic.setText("Ano de publicação: " + livro.getAnoPublicacao());
			lblGenero.setText("Gênero: " + livro.getGenero());
			lblIdioma.setText("Idioma: " + livro.getIdioma());
			lblNumPagina.setText("Número de páginas: " + livro.getNumeroPaginas());

			
			String pathAppBiblioteca = System.getenv("APPDATA");
			File pastaCapas = new File(pathAppBiblioteca, "Biblioteca/Capas");
			
			String[] extensoes = { "png", "jpg", "jpeg" };
			
			for(String extensao : extensoes) {
				File capa = new File (pastaCapas, livro.getIdItem() + "." + extensao);
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
			
		} else {
			
			lblTitulo.setText("Livro Não Encontrado");
			lblIdLivro.setText("Identificador: N/A");
			lblAutor.setText("Autor: N/A");
			lblEditora.setText("Editora: N/A");
			lblAnoPublic.setText("Ano de publicação: N/A");
			lblGenero.setText("Gênero: N/A");
			lblIdioma.setText("Idioma: N/A");
			lblNumPagina.setText("Número de páginas: N/A");
		}
		
	}
}
