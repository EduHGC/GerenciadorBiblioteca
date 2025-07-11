package br.edu.ifpe.lpoo.project.ui;

import javax.swing.*;

import br.edu.ifpe.lpoo.project.business.acervo.ExemplarController;
import br.edu.ifpe.lpoo.project.business.acervo.LivroController;
import br.edu.ifpe.lpoo.project.data.implement.ExemplarRepository;
import br.edu.ifpe.lpoo.project.entities.acervo.Livro;
import br.edu.ifpe.lpoo.project.exception.BusinessException;

import java.awt.*;
import java.io.File;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class JPanelCadastroExemplar extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTextField textFieldIdItem;
	private JLabel lblCapa;
	private JLabel lblCapaLivro;
	private JLabel idLivro;
	private JButton btnBuscarLivro;
	private Livro livro;
	private JLabel lblValorIsbn;
	private JLabel lblValorTitulo;
	private JLabel lblValorAutor;
	private JLabel lblValorEditora;
	private JLabel lblValorAnoPublicacao;
	private JLabel lblValorGenero;
	private JLabel lblValorIdioma;
	private JLabel lblValorNumPagina;
	private JLabel lblValorNumPaginas;
	private JLabel lblNumExemplar;
	private JTextField textFieldQtdExemplar;
	private JButton btnCadastrar;
	private JLabel lblQtdExemplares;
	private JLabel lblValorQtdExemplares;

	public JPanelCadastroExemplar() {

		setLayout(null);

		JLabel lblIsbn = new JLabel("ISBN");
		lblIsbn.setFont(new Font("Arial Black", Font.BOLD, 13));
		lblIsbn.setBounds(40, 90, 100, 20);
		add(lblIsbn);

		JLabel lblTitulo = new JLabel("Título");
		lblTitulo.setFont(new Font("Arial Black", Font.BOLD, 13));
		lblTitulo.setBounds(40, 140, 100, 20);
		add(lblTitulo);

		JLabel lblAutor = new JLabel("Autor");
		lblAutor.setFont(new Font("Arial Black", Font.BOLD, 13));
		lblAutor.setBounds(40, 190, 100, 20);
		add(lblAutor);

		JLabel lblEditora = new JLabel("Editora");
		lblEditora.setFont(new Font("Arial Black", Font.BOLD, 13));
		lblEditora.setBounds(40, 240, 100, 20);
		add(lblEditora);

		JLabel lblAnoPublicacao = new JLabel("Ano da Publicação");
		lblAnoPublicacao.setFont(new Font("Arial Black", Font.BOLD, 13));
		lblAnoPublicacao.setBounds(40, 290, 155, 20);
		add(lblAnoPublicacao);

		JLabel lblGenero = new JLabel("Gênero");
		lblGenero.setFont(new Font("Arial Black", Font.BOLD, 13));
		lblGenero.setBounds(40, 340, 100, 20);
		add(lblGenero);

		JLabel lblIdioma = new JLabel("Idioma");
		lblIdioma.setFont(new Font("Arial Black", Font.BOLD, 13));
		lblIdioma.setBounds(40, 390, 100, 20);
		add(lblIdioma);

		JLabel lblNumPagina = new JLabel("Número de páginas");
		lblNumPagina.setFont(new Font("Arial Black", Font.BOLD, 13));
		lblNumPagina.setBounds(40, 440, 155, 20);
		add(lblNumPagina);

		JLabel lblIdLivro = new JLabel("Digite o id do livro");
		lblIdLivro.setFont(new Font("Arial Black", Font.BOLD, 13));
		lblIdLivro.setBounds(40, 19, 214, 20);
		add(lblIdLivro);

		textFieldIdItem = new JTextField();
		textFieldIdItem.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

				if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
					if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
						String novoId = textFieldIdItem.getText();

						limparCampos();

						textFieldIdItem.setText(novoId);
						textFieldQtdExemplar.setEditable(false);
						textFieldQtdExemplar.setEnabled(false);
					}
				}
			}
		});
		textFieldIdItem.setFont(new Font("Arial Black", Font.BOLD, 15));
		textFieldIdItem.setColumns(10);
		textFieldIdItem.setBounds(40, 40, 155, 40);
		add(textFieldIdItem);

		idLivro = new JLabel("Digite o id");
		idLivro.setFont(new Font("Arial Black", Font.BOLD, 13));
		idLivro.setBounds(40, 19, 214, 20);
		add(idLivro);

		btnBuscarLivro = new JButton("Buscar");
		btnBuscarLivro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (textFieldIdItem.getText().trim().isEmpty()) {
					JOptionPane.showMessageDialog(JPanelCadastroExemplar.this, "Insira um id válido", "Id Inválido",
							JOptionPane.WARNING_MESSAGE);
					return;
				}

				String idLivro = textFieldIdItem.getText();
				try {

					LivroController livroCotroller = new LivroController();

					livro = livroCotroller.buscarLivroId(idLivro);

					if (livro != null) {
						
						ExemplarController exemplarController = new ExemplarController();
						int quantidadeExemplar = exemplarController.listarExemplaresIdItem(livro.getIdItem()).size();
						
						lblValorIsbn.setText(livro.getIsbn());
						lblValorTitulo.setText(livro.getTitulo());
						lblValorAutor.setText(livro.getAutor());
						lblValorEditora.setText(livro.getEditora());
						lblValorAnoPublicacao.setText(String.valueOf(livro.getAnoPublicacao()));
						lblValorGenero.setText(livro.getGenero());
						lblValorIdioma.setText(livro.getIdioma());
						lblValorNumPaginas.setText(String.valueOf(livro.getNumeroPaginas()));
						lblValorQtdExemplares.setText(String.valueOf(quantidadeExemplar));
						textFieldQtdExemplar.setEditable(true);
						textFieldQtdExemplar.setEnabled(true);
					
					}

					String pathAppBiblioteca = System.getenv("APPDATA");
					File pastaCapas = new File(pathAppBiblioteca, "Biblioteca/Capas");

					String[] extensoes = { "png", "jpg", "jpeg" };

					for (String extensao : extensoes) {
						File capa = new File(pastaCapas, livro.getIdItem() + "." + extensao);
						if (capa.exists()) {
							ImageIcon imageIco = new ImageIcon(capa.getAbsolutePath());

							int widthLblCapaLivro = lblCapaLivro.getWidth();
							int heightLblCapaLivro = lblCapaLivro.getHeight();

							Image originalImage = imageIco.getImage();
							Image redimensionar = originalImage.getScaledInstance(widthLblCapaLivro, heightLblCapaLivro,
									Image.SCALE_SMOOTH);
							ImageIcon imagemCapa = new ImageIcon(redimensionar);

							lblCapaLivro.setIcon(imagemCapa);
							lblCapaLivro.setText("");
						}
					}
					
					
				} catch (BusinessException e1) {
					JOptionPane.showMessageDialog(JPanelCadastroExemplar.this, e1.getMessage(), "Erro",
							JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		btnBuscarLivro.setFont(new Font("Arial Black", Font.BOLD, 15));
		btnBuscarLivro.setBounds(207, 40, 120, 40);
		add(btnBuscarLivro);

		lblCapa = new JLabel("Capa do livro");
		lblCapa.setFont(new Font("Arial Black", Font.BOLD, 13));
		lblCapa.setBounds(574, 19, 100, 20);
		add(lblCapa);

		lblCapaLivro = new JLabel("Pré-visualização da Capa");
		lblCapaLivro.setHorizontalAlignment(SwingConstants.CENTER);
		lblCapaLivro.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		lblCapaLivro.setBounds(470, 40, 316, 352);
		add(lblCapaLivro);

		lblValorIsbn = new JLabel("");
		lblValorIsbn.setFont(new Font("Arial Black", Font.BOLD, 13));
		lblValorIsbn.setBounds(40, 110, 410, 20);
		add(lblValorIsbn);

		lblValorTitulo = new JLabel("");
		lblValorTitulo.setFont(new Font("Arial Black", Font.BOLD, 13));
		lblValorTitulo.setBounds(40, 160, 401, 20);
		add(lblValorTitulo);

		lblValorAutor = new JLabel("");
		lblValorAutor.setFont(new Font("Arial Black", Font.BOLD, 13));
		lblValorAutor.setBounds(40, 210, 401, 20);
		add(lblValorAutor);

		lblValorEditora = new JLabel("");
		lblValorEditora.setFont(new Font("Arial Black", Font.BOLD, 13));
		lblValorEditora.setBounds(40, 260, 420, 20);
		add(lblValorEditora);

		lblValorAnoPublicacao = new JLabel("");
		lblValorAnoPublicacao.setFont(new Font("Arial Black", Font.BOLD, 13));
		lblValorAnoPublicacao.setBounds(40, 310, 420, 20);
		add(lblValorAnoPublicacao);

		lblValorGenero = new JLabel("");
		lblValorGenero.setFont(new Font("Arial Black", Font.BOLD, 13));
		lblValorGenero.setBounds(40, 360, 401, 20);
		add(lblValorGenero);

		lblValorIdioma = new JLabel("");
		lblValorIdioma.setFont(new Font("Arial Black", Font.BOLD, 13));
		lblValorIdioma.setBounds(40, 410, 401, 20);
		add(lblValorIdioma);

		lblValorNumPaginas = new JLabel("");
		lblValorNumPaginas.setFont(new Font("Arial Black", Font.BOLD, 13));
		lblValorNumPaginas.setBounds(40, 460, 401, 20);
		add(lblValorNumPaginas);

		lblNumExemplar = new JLabel("Quantidade de novos exemplares");
		lblNumExemplar.setFont(new Font("Arial Black", Font.BOLD, 13));
		lblNumExemplar.setBounds(40, 555, 298, 20);
		add(lblNumExemplar);

		lblValorNumPagina = new JLabel("Número de páginas");
		lblValorNumPagina.setFont(new Font("Arial Black", Font.BOLD, 13));
		lblValorNumPagina.setBounds(40, 460, 155, 20);

		textFieldQtdExemplar = new JTextField();
		textFieldQtdExemplar.setEditable(false);
		textFieldQtdExemplar.setEnabled(false);
		textFieldQtdExemplar.setFont(new Font("Arial Black", Font.BOLD, 15));
		textFieldQtdExemplar.setBounds(40, 575, 270, 40);
		add(textFieldQtdExemplar);
		textFieldQtdExemplar.setColumns(10);

		btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (textFieldQtdExemplar.getText().trim().isEmpty()) {

					JOptionPane.showMessageDialog(JPanelCadastroExemplar.this, "Insira um id válido", "Id Inválido",
							JOptionPane.WARNING_MESSAGE);
					return;
				}
				

				try {
					String idLivro = textFieldIdItem.getText();
					String quantidade = textFieldQtdExemplar.getText();
					ExemplarController exemplarController = new ExemplarController();

					exemplarController.cadastarExemplar(idLivro, quantidade);
					
					JOptionPane.showMessageDialog(JPanelCadastroExemplar.this,
							"Foi cadastrado a quantidade de exemplar com sucesso.", "Atualização Concluída",
							JOptionPane.INFORMATION_MESSAGE);
					
					limparCampos();
					
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(JPanelCadastroExemplar.this, e1.getMessage(), "Erro",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnCadastrar.setFont(new Font("Arial Black", Font.BOLD, 15));
		btnCadastrar.setBounds(40, 625, 270, 40);
		add(btnCadastrar);
		
		lblQtdExemplares = new JLabel("Quantidade de exemplares");
		lblQtdExemplares.setFont(new Font("Arial Black", Font.BOLD, 13));
		lblQtdExemplares.setBounds(40, 490, 214, 20);
		add(lblQtdExemplares);
		
		lblValorQtdExemplares = new JLabel("");
		lblValorQtdExemplares.setFont(new Font("Arial Black", Font.BOLD, 13));
		lblValorQtdExemplares.setBounds(40, 510, 401, 20);
		add(lblValorQtdExemplares);

	}

	private void limparCampos() {
		lblValorIsbn.setText("");
		lblValorTitulo.setText("");
		lblValorAutor.setText("");
		lblValorEditora.setText("");
		lblValorAnoPublicacao.setText("");
		lblValorGenero.setText("");
		lblValorIdioma.setText("");
		lblValorNumPaginas.setText("");
		lblValorQtdExemplares.setText("");
		textFieldIdItem.setText("");
		textFieldQtdExemplar.setText("");
		textFieldQtdExemplar.setEditable(false);
		textFieldQtdExemplar.setEnabled(false);
		lblCapaLivro.setIcon(null);
		lblCapaLivro.setText("Capa do livro");
	}

}
