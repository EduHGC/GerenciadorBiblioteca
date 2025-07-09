package br.edu.ifpe.lpoo.project.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.BorderFactory;
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
		setPreferredSize(new Dimension(595, 350));

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
		lblAutor.setBounds(10, 88, 280, 40);
		add(lblAutor);

		JLabel lblEditora = new JLabel("Editora: ");
		lblEditora.setFont(new Font("Arial Black", Font.BOLD, 14));
		lblEditora.setVerticalAlignment(SwingConstants.TOP);
		lblEditora.setHorizontalAlignment(SwingConstants.LEFT);
		lblEditora.setBounds(10, 128, 280, 40);
		add(lblEditora);

		JLabel lblAnoPublic = new JLabel("Ano de publicação: ");
		lblAnoPublic.setFont(new Font("Arial Black", Font.BOLD, 14));
		lblAnoPublic.setVerticalAlignment(SwingConstants.TOP);
		lblAnoPublic.setHorizontalAlignment(SwingConstants.LEFT);
		lblAnoPublic.setBounds(10, 168, 280, 40);
		add(lblAnoPublic);

		JLabel lblGenero = new JLabel("Gênero: ");
		lblGenero.setFont(new Font("Arial Black", Font.BOLD, 14));
		lblGenero.setVerticalAlignment(SwingConstants.TOP);
		lblGenero.setHorizontalAlignment(SwingConstants.LEFT);
		lblGenero.setBounds(10, 208, 280, 40);
		add(lblGenero);

		JLabel lblIdioma = new JLabel("Idioma: ");
		lblIdioma.setFont(new Font("Arial Black", Font.BOLD, 14));
		lblIdioma.setVerticalAlignment(SwingConstants.TOP);
		lblIdioma.setHorizontalAlignment(SwingConstants.LEFT);
		lblIdioma.setBounds(10, 248, 280, 40);
		add(lblIdioma);

		JLabel lblNumPagina = new JLabel("Número de páginas: ");
		lblNumPagina.setFont(new Font("Arial Black", Font.BOLD, 14));
		lblNumPagina.setVerticalAlignment(SwingConstants.TOP);
		lblNumPagina.setHorizontalAlignment(SwingConstants.LEFT);
		lblNumPagina.setBounds(10, 288, 280, 40);
		add(lblNumPagina);
		
		if (livro != null) {
			lblTitulo.setText(livro.getTitulo());
			lblAutor.setText("Autor: " + livro.getAutor());
			lblEditora.setText("Editora: " + livro.getEditora());
			lblAnoPublic.setText("Ano de publicação: " + livro.getAnoPublicacao());
			lblGenero.setText("Gênero: " + livro.getGenero());
			lblIdioma.setText("Idioma: " + livro.getIdioma());
			lblNumPagina.setText("Número de páginas: " + livro.getNumeroPaginas());

			
			//Inserir lógica de carregar imagem
			
		} else {
			
			lblTitulo.setText("Livro Não Encontrado");
			lblAutor.setText("Autor: N/A");
			lblEditora.setText("Editora: N/A");
			lblAnoPublic.setText("Ano de publicação: N/A");
			lblGenero.setText("Gênero: N/A");
			lblIdioma.setText("Idioma: N/A");
			lblNumPagina.setText("Número de páginas: N/A");
		}
		
	}
}
