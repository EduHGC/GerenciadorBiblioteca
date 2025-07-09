package br.edu.ifpe.lpoo.project.ui;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import br.edu.ifpe.lpoo.project.business.acervo.LivroController;
import br.edu.ifpe.lpoo.project.exception.BusinessException;

import java.awt.*;
import java.io.File;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JPainelCadastroLivro extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTextField textFieldISBN;
	private JTextField textFieldTitulo;
	private JTextField textFieldAutor;
	private JTextField textFieldEditora;
	private JTextField textFieldAnoPubli;
	private JTextField textFieldGenero;
	private JTextField textFieldIdioma;
	private JTextField textFieldNumPaginas;
	private JTextField textFieldQtdExemplares;
	private JButton btnCadastrarLivro;
	private JButton btnSelecionarCapa;
	private File arquivoCapaSelecionada;
	private JLabel lblCapa;
	private JLabel lblCapaLivro;

	public JPainelCadastroLivro() {

		setLayout(null);

		JLabel lblIsbn = new JLabel("ISBN");
		lblIsbn.setFont(new Font("Arial Black", Font.BOLD, 13));
		lblIsbn.setBounds(40, 20, 100, 20);
		add(lblIsbn);

		textFieldISBN = new JTextField();
		textFieldISBN.setFont(new Font("Arial Black", Font.BOLD, 15));
		textFieldISBN.setBounds(40, 40, 400, 40);
		add(textFieldISBN);
		textFieldISBN.setColumns(10);

		textFieldTitulo = new JTextField();
		textFieldTitulo.setFont(new Font("Arial Black", Font.BOLD, 15));
		textFieldTitulo.setColumns(10);
		textFieldTitulo.setBounds(40, 110, 400, 40);
		add(textFieldTitulo);

		JLabel lblTitulo = new JLabel("Título");
		lblTitulo.setFont(new Font("Arial Black", Font.BOLD, 13));
		lblTitulo.setBounds(40, 90, 100, 20);
		add(lblTitulo);

		textFieldAutor = new JTextField();
		textFieldAutor.setFont(new Font("Arial Black", Font.BOLD, 15));
		textFieldAutor.setColumns(10);
		textFieldAutor.setBounds(40, 180, 400, 40);
		add(textFieldAutor);

		JLabel lblAutor = new JLabel("Autor");
		lblAutor.setFont(new Font("Arial Black", Font.BOLD, 13));
		lblAutor.setBounds(40, 160, 100, 20);
		add(lblAutor);

		textFieldEditora = new JTextField();
		textFieldEditora.setFont(new Font("Arial Black", Font.BOLD, 15));
		textFieldEditora.setColumns(10);
		textFieldEditora.setBounds(40, 250, 400, 40);
		add(textFieldEditora);

		JLabel lblEditora = new JLabel("Editora");
		lblEditora.setFont(new Font("Arial Black", Font.BOLD, 13));
		lblEditora.setBounds(40, 230, 100, 20);
		add(lblEditora);

		textFieldAnoPubli = new JTextField();
		textFieldAnoPubli.setFont(new Font("Arial Black", Font.BOLD, 15));
		textFieldAnoPubli.setColumns(10);
		textFieldAnoPubli.setBounds(40, 320, 400, 40);
		add(textFieldAnoPubli);

		JLabel lblAnoPublicacao = new JLabel("Ano da Publicação");
		lblAnoPublicacao.setFont(new Font("Arial Black", Font.BOLD, 13));
		lblAnoPublicacao.setBounds(40, 300, 155, 20);
		add(lblAnoPublicacao);

		JLabel lblGenero = new JLabel("Gênero");
		lblGenero.setFont(new Font("Arial Black", Font.BOLD, 13));
		lblGenero.setBounds(40, 370, 100, 20);
		add(lblGenero);

		textFieldGenero = new JTextField();
		textFieldGenero.setFont(new Font("Arial Black", Font.BOLD, 15));
		textFieldGenero.setColumns(10);
		textFieldGenero.setBounds(40, 390, 400, 40);
		add(textFieldGenero);

		JLabel lblIdioma = new JLabel("Idioma");
		lblIdioma.setFont(new Font("Arial Black", Font.BOLD, 13));
		lblIdioma.setBounds(40, 440, 100, 20);
		add(lblIdioma);

		textFieldIdioma = new JTextField();
		textFieldIdioma.setFont(new Font("Arial Black", Font.BOLD, 15));
		textFieldIdioma.setColumns(10);
		textFieldIdioma.setBounds(40, 460, 400, 40);
		add(textFieldIdioma);

		JLabel lblNumPagina = new JLabel("Número de páginas");
		lblNumPagina.setFont(new Font("Arial Black", Font.BOLD, 13));
		lblNumPagina.setBounds(40, 510, 155, 20);
		add(lblNumPagina);

		textFieldNumPaginas = new JTextField();
		textFieldNumPaginas.setFont(new Font("Arial Black", Font.BOLD, 15));
		textFieldNumPaginas.setColumns(10);
		textFieldNumPaginas.setBounds(40, 530, 400, 40);
		add(textFieldNumPaginas);

		JLabel lblQtdExemplares = new JLabel("Quantidade de exemplares");
		lblQtdExemplares.setFont(new Font("Arial Black", Font.BOLD, 13));
		lblQtdExemplares.setBounds(40, 579, 214, 20);
		add(lblQtdExemplares);

		textFieldQtdExemplares = new JTextField();
		textFieldQtdExemplares.setFont(new Font("Arial Black", Font.BOLD, 15));
		textFieldQtdExemplares.setColumns(10);
		textFieldQtdExemplares.setBounds(40, 600, 400, 40);
		add(textFieldQtdExemplares);

		btnCadastrarLivro = new JButton("Cadastrar");
		btnCadastrarLivro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textFieldTitulo.getText().trim().isEmpty() || textFieldAutor.getText().trim().isEmpty()
						|| textFieldEditora.getText().trim().isEmpty() || textFieldAnoPubli.getText().trim().isEmpty()
						|| textFieldGenero.getText().trim().isEmpty() || textFieldIdioma.getText().trim().isEmpty()
						|| textFieldNumPaginas.getText().trim().isEmpty()
						|| textFieldQtdExemplares.getText().trim().isEmpty()) {

					JOptionPane.showMessageDialog(JPainelCadastroLivro.this,
							"Por favor, preencha todos os campos para efetuar o cadastro do livro. Isbn pode ser vazio",
							"Campos Vazios", JOptionPane.WARNING_MESSAGE);

					return;
				}

				String isbn = textFieldISBN.getText();
				String titulo = textFieldTitulo.getText();
				String autor = textFieldAutor.getText();
				String editora = textFieldEditora.getText();
				String anoPubli = textFieldAnoPubli.getText();
				String genero = textFieldGenero.getText();
				String idioma = textFieldIdioma.getText();
				String numPaginas = textFieldNumPaginas.getText();
				String qtdExemplares = textFieldQtdExemplares.getText();

				try {
					LivroController livroController = new LivroController();
					livroController.cadastarLivro(titulo, autor, editora, anoPubli, genero, idioma, numPaginas, isbn,
							qtdExemplares);
					JOptionPane.showMessageDialog(JPainelCadastroLivro.this,
							"O livro foi cadastrado com sucesso em nosso sistema.", "Cadastro Concluído",
							JOptionPane.INFORMATION_MESSAGE);
					limparCampos();

				} catch (BusinessException e1) {
					JOptionPane.showMessageDialog(JPainelCadastroLivro.this, e1.getMessage(), "Erro",
							JOptionPane.ERROR_MESSAGE);
				}

			}
		});

		btnCadastrarLivro.setFont(new Font("Arial Black", Font.BOLD, 15));
		btnCadastrarLivro.setBackground(new Color(240, 240, 240));
		btnCadastrarLivro.setBounds(40, 650, 400, 40);
		add(btnCadastrarLivro);

		// Inserir imagem inicio

		lblCapaLivro = new JLabel("Pré-visualização da Capa");
		lblCapaLivro.setHorizontalAlignment(SwingConstants.CENTER);
		lblCapaLivro.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		lblCapaLivro.setBounds(500, 40, 250, 300);
		add(lblCapaLivro);

		btnSelecionarCapa = new JButton("Selecionar Capa");
		btnSelecionarCapa.setFont(new Font("Arial Black", Font.BOLD, 13));
		btnSelecionarCapa.setBounds(500, 350, 250, 40);
		add(btnSelecionarCapa);

		// Selecionar a Capa
		btnSelecionarCapa.addActionListener(e -> {
			JFileChooser fileChooser = new JFileChooser();
			FileNameExtensionFilter filter = new FileNameExtensionFilter("Arquivos de Imagem", "jpg", "jpeg", "png",
					"gif");
			fileChooser.setFileFilter(filter);

			int resultado = fileChooser.showOpenDialog(this);

			if (resultado == JFileChooser.APPROVE_OPTION) {
				arquivoCapaSelecionada = fileChooser.getSelectedFile();
				try {

					ImageIcon imagemCapa = new ImageIcon(arquivoCapaSelecionada.getAbsolutePath());

					int labelWidth = lblCapaLivro.getWidth();
					int labelHeight = lblCapaLivro.getHeight();

					Image originalImage = imagemCapa.getImage();
					Image scaledImage = originalImage.getScaledInstance(labelWidth, labelHeight, Image.SCALE_SMOOTH);
					ImageIcon scaledIcon = new ImageIcon(scaledImage);

					lblCapaLivro.setIcon(scaledIcon);
					lblCapaLivro.setText("");
				} catch (Exception ex) {
					lblCapaLivro.setText("Erro ao carregar imagem!");
					lblCapaLivro.setIcon(null);

					lblCapa = new JLabel("Capa do livro");
					lblCapa.setFont(new Font("Arial Black", Font.BOLD, 13));
					lblCapa.setBounds(500, 20, 100, 20);
					add(lblCapa);
					arquivoCapaSelecionada = null;
					ex.printStackTrace();
				}
			}
		});
		// Inserir imagem fim
	}

	private File getArquivoCapaSelecionada() {
		return arquivoCapaSelecionada;
	}

	private void limparCampos() {
		textFieldISBN.setText("");
		textFieldTitulo.setText("");
		textFieldAutor.setText("");
		textFieldEditora.setText("");
		textFieldAnoPubli.setText("");
		textFieldGenero.setText("");
		textFieldIdioma.setText("");
		textFieldNumPaginas.setText("");
		textFieldQtdExemplares.setText("");
		lblCapaLivro.setIcon(null);
		lblCapaLivro.setText("Capa do livro");
		arquivoCapaSelecionada = null;
	}
}
