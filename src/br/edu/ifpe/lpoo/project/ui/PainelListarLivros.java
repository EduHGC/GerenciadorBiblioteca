package br.edu.ifpe.lpoo.project.ui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import br.edu.ifpe.lpoo.project.business.acervo.LivroController;
import br.edu.ifpe.lpoo.project.entities.acervo.Livro;
import br.edu.ifpe.lpoo.project.exception.BusinessException;

public class PainelListarLivros extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private LivroController livroController;
	private JTable tabelaLivros;
	
	public PainelListarLivros() {
		
		setLayout(new BorderLayout());

		livroController = new LivroController();

		JLabel titulo = new JLabel("Lista de Livros");
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		titulo.setFont(new Font("Arial Black", Font.BOLD, 20));
		add(titulo, BorderLayout.NORTH);

		String[] colunas = { "ID", "ISBN", "Título", "Autor", "Editora", "Ano", "Gênero", "Idioma", "Páginas" };

		DefaultTableModel modelo = new DefaultTableModel(colunas, 0);

		try {
			List<Livro> livros = livroController.listarLivros();

			for (Livro livro : livros) {
				Object[] linha = {
					livro.getIdItem(),
					livro.getIsbn(),
					livro.getTitulo(),
					livro.getAutor(),
					livro.getEditora(),
					livro.getAnoPublicacao(),
					livro.getGenero(),
					livro.getIdioma(),
					livro.getNumeroPaginas()
				
				};
				modelo.addRow(linha);
			}
		} catch (BusinessException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
		}

		tabelaLivros = new JTable(modelo);
		JScrollPane scrollPane = new JScrollPane(tabelaLivros);
		add(scrollPane, BorderLayout.CENTER);
	}

}
