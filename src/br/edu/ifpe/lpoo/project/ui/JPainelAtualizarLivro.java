package br.edu.ifpe.lpoo.project.ui;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import br.edu.ifpe.lpoo.project.business.acervo.LivroController;
import br.edu.ifpe.lpoo.project.entities.acervo.Livro;
import br.edu.ifpe.lpoo.project.exception.BusinessException;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class JPainelAtualizarLivro extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTextField textFieldISBN;
	private JTextField textFieldTitulo;
	private JTextField textFieldAutor;
	private JTextField textFieldEditora;
	private JTextField textFieldAnoPubli;
	private JTextField textFieldGenero;
	private JTextField textFieldIdioma;
	private JTextField textFieldNumPaginas;
	private JTextField textFieldIdLivro;
	private JButton btnAtualizar;
	private JButton btnSelecionarCapa;
	private File arquivoCapaSelecionada;
	private JLabel lblCapa;
	private JLabel lblCapaLivro;
	private JLabel idLivro;
	private JButton btnBuscarLivro;
	private Livro livro;
	private boolean imagemCarregada = false;
	private boolean atualizarCapa = false;

	public JPainelAtualizarLivro() {

		setLayout(null);

		JLabel lblIsbn = new JLabel("ISBN");
		lblIsbn.setFont(new Font("Arial Black", Font.BOLD, 13));
		lblIsbn.setBounds(40, 90, 100, 20);
		add(lblIsbn);

		textFieldISBN = new JTextField();
		textFieldISBN.setEnabled(false);
		textFieldISBN.setFont(new Font("Arial Black", Font.BOLD, 15));
		textFieldISBN.setBounds(40, 110, 400, 40);
		add(textFieldISBN);
		textFieldISBN.setColumns(10);

		textFieldTitulo = new JTextField();
		textFieldTitulo.setFont(new Font("Arial Black", Font.BOLD, 15));
		textFieldTitulo.setColumns(10);
		textFieldTitulo.setBounds(40, 180, 400, 40);
		add(textFieldTitulo);

		JLabel lblTitulo = new JLabel("Título");
		lblTitulo.setFont(new Font("Arial Black", Font.BOLD, 13));
		lblTitulo.setBounds(40, 160, 100, 20);
		add(lblTitulo);

		textFieldAutor = new JTextField();
		textFieldAutor.setFont(new Font("Arial Black", Font.BOLD, 15));
		textFieldAutor.setColumns(10);
		textFieldAutor.setBounds(40, 250, 400, 40);
		add(textFieldAutor);

		JLabel lblAutor = new JLabel("Autor");
		lblAutor.setFont(new Font("Arial Black", Font.BOLD, 13));
		lblAutor.setBounds(40, 230, 100, 20);
		add(lblAutor);

		textFieldEditora = new JTextField();
		textFieldEditora.setFont(new Font("Arial Black", Font.BOLD, 15));
		textFieldEditora.setColumns(10);
		textFieldEditora.setBounds(40, 320, 400, 40);
		add(textFieldEditora);

		JLabel lblEditora = new JLabel("Editora");
		lblEditora.setFont(new Font("Arial Black", Font.BOLD, 13));
		lblEditora.setBounds(40, 300, 100, 20);
		add(lblEditora);

		textFieldAnoPubli = new JTextField();
		textFieldAnoPubli.setFont(new Font("Arial Black", Font.BOLD, 15));
		textFieldAnoPubli.setColumns(10);
		textFieldAnoPubli.setBounds(40, 390, 400, 40);
		add(textFieldAnoPubli);

		JLabel lblAnoPublicacao = new JLabel("Ano da Publicação");
		lblAnoPublicacao.setFont(new Font("Arial Black", Font.BOLD, 13));
		lblAnoPublicacao.setBounds(40, 370, 155, 20);
		add(lblAnoPublicacao);

		JLabel lblGenero = new JLabel("Gênero");
		lblGenero.setFont(new Font("Arial Black", Font.BOLD, 13));
		lblGenero.setBounds(40, 440, 100, 20);
		add(lblGenero);

		textFieldGenero = new JTextField();
		textFieldGenero.setFont(new Font("Arial Black", Font.BOLD, 15));
		textFieldGenero.setColumns(10);
		textFieldGenero.setBounds(40, 460, 400, 40);
		add(textFieldGenero);

		JLabel lblIdioma = new JLabel("Idioma");
		lblIdioma.setFont(new Font("Arial Black", Font.BOLD, 13));
		lblIdioma.setBounds(40, 510, 100, 20);
		add(lblIdioma);

		textFieldIdioma = new JTextField();
		textFieldIdioma.setFont(new Font("Arial Black", Font.BOLD, 15));
		textFieldIdioma.setColumns(10);
		textFieldIdioma.setBounds(40, 530, 400, 40);
		add(textFieldIdioma);

		JLabel lblNumPagina = new JLabel("Número de páginas");
		lblNumPagina.setFont(new Font("Arial Black", Font.BOLD, 13));
		lblNumPagina.setBounds(40, 580, 155, 20);
		add(lblNumPagina);

		textFieldNumPaginas = new JTextField();
		textFieldNumPaginas.setFont(new Font("Arial Black", Font.BOLD, 15));
		textFieldNumPaginas.setColumns(10);
		textFieldNumPaginas.setBounds(40, 600, 400, 40);
		add(textFieldNumPaginas);

		JLabel lblIdLivro = new JLabel("Digite o id do livro");
		lblIdLivro.setFont(new Font("Arial Black", Font.BOLD, 13));
		lblIdLivro.setBounds(40, 19, 214, 20);
		add(lblIdLivro);

		textFieldIdLivro = new JTextField();
		textFieldIdLivro.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				
				if(e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
					String novoId = textFieldIdLivro.getText();
					
					limparCampos();
					
					textFieldIdLivro.setText(novoId);
				}
			}
		});
		textFieldIdLivro.setFont(new Font("Arial Black", Font.BOLD, 15));
		textFieldIdLivro.setColumns(10);
		textFieldIdLivro.setBounds(40, 40, 155, 40);
		add(textFieldIdLivro);

		idLivro = new JLabel("Digite o id");
		idLivro.setFont(new Font("Arial Black", Font.BOLD, 13));
		idLivro.setBounds(40, 19, 214, 20);
		add(idLivro);

		btnBuscarLivro = new JButton("Buscar");
		btnBuscarLivro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (textFieldIdLivro.getText().trim().isEmpty()) {
					JOptionPane.showMessageDialog(JPainelAtualizarLivro.this, "Insira um id válido", "Id Inválido",
							JOptionPane.WARNING_MESSAGE);
					return;
				}

				String idLivro = textFieldIdLivro.getText();

				try {

					LivroController livroCotroller = new LivroController();

					livro = livroCotroller.buscarLivroId(idLivro);

					textFieldISBN.setText(livro.getIsbn());
					textFieldTitulo.setText(livro.getTitulo());
					textFieldAutor.setText(livro.getAutor());
					textFieldEditora.setText(livro.getEditora());
					textFieldAnoPubli.setText(String.valueOf(livro.getAnoPublicacao()));
					textFieldGenero.setText(livro.getGenero());
					textFieldIdioma.setText(livro.getIdioma());
					textFieldNumPaginas.setText(String.valueOf(livro.getNumeroPaginas()));
					
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
					
				} catch (BusinessException e1) {
					JOptionPane.showMessageDialog(JPainelAtualizarLivro.this, e1.getMessage(), "Erro",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnBuscarLivro.setFont(new Font("Arial Black", Font.BOLD, 15));
		btnBuscarLivro.setBounds(207, 40, 120, 40);
		add(btnBuscarLivro);

		btnAtualizar = new JButton("Atualizar");
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textFieldTitulo.getText().trim().isEmpty() || textFieldAutor.getText().trim().isEmpty()
						|| textFieldEditora.getText().trim().isEmpty() || textFieldAnoPubli.getText().trim().isEmpty()
						|| textFieldGenero.getText().trim().isEmpty() || textFieldIdioma.getText().trim().isEmpty()
						|| textFieldNumPaginas.getText().trim().isEmpty()) {

					JOptionPane.showMessageDialog(JPainelAtualizarLivro.this,
							"Por favor, preencha todos os campos para efetuar a atualização do livro.", "Campos Vazios",
							JOptionPane.WARNING_MESSAGE);

					return;
				}
				
				int confirmacao = JOptionPane.showConfirmDialog(JPainelAtualizarLivro.this,
		                "Tem certeza que deseja atualizar os dados deste livro?", "Confirmar Atualização",
		                JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
				
				if(confirmacao == JOptionPane.OK_CANCEL_OPTION) {
					
					JOptionPane.showMessageDialog(JPainelAtualizarLivro.this,
		                    "Operação de atualização cancelada.", "Cancelado",
		                    JOptionPane.INFORMATION_MESSAGE);
		            return;
				}
				
				String idLivro = textFieldIdLivro.getText();
				String isbn = textFieldISBN.getText();
				String titulo = textFieldTitulo.getText();
				String autor = textFieldAutor.getText();
				String editora = textFieldEditora.getText();
				String anoPubli = textFieldAnoPubli.getText();
				String genero = textFieldGenero.getText();
				String idioma = textFieldIdioma.getText();
				String numPaginas = textFieldNumPaginas.getText();

				try {
					LivroController livroController = new LivroController();
					
					livroController.atualizarLivro(idLivro, titulo, autor, editora, anoPubli, genero, idioma, numPaginas, isbn);
					
					String pathAppBiblioteca = System.getenv("APPDATA");
					File pastaCapas = new File(pathAppBiblioteca, "Biblioteca/Capas");
					
					if(!pastaCapas.exists()) {
						pastaCapas.mkdirs();
					}
					
					if(atualizarCapa) {
						
						String nomeOriginal = arquivoCapaSelecionada.getName();
						String extensao = nomeOriginal.substring(nomeOriginal.lastIndexOf(".") + 1);
						String nomeCapaTemp = livro.getIdItem() + "temp" + "." + extensao;
						String nomeCapa = livro.getIdItem() + ".";
						
						File destino = new File(pastaCapas, nomeCapaTemp);
						BufferedImage imagem = ImageIO.read(arquivoCapaSelecionada);
						
						if(extensao.equals("jpg") || extensao.equals("jpeg") || extensao.equals("png")) {
							ImageIO.write(imagem, extensao, destino);
						}else {
							throw new IOException("Formato de imagem não suportado para gravação: " + extensao);
						}
						
						if(imagemCarregada) {
							String[] extensoes = {"jpg", "jpeg", "png"};
							for(String ext : extensoes) {
								File capaAntiga = new File(pastaCapas, nomeCapa + ext);
								if(capaAntiga.exists()) {
									capaAntiga.delete();
								}
							}
						}
						
						File novaCapa = new File(pastaCapas, nomeCapa + extensao);
						destino.renameTo(novaCapa);
						imagemCarregada = false;
						atualizarCapa = false;
					}
				
					JOptionPane.showMessageDialog(JPainelAtualizarLivro.this,
							"O livro foi atualizado com sucesso em nosso sistema.", "Atualização Concluída",
							JOptionPane.INFORMATION_MESSAGE);
					limparCampos();

				} catch (BusinessException e1) {
					JOptionPane.showMessageDialog(JPainelAtualizarLivro.this, e1.getMessage(), "Erro",
							JOptionPane.ERROR_MESSAGE);
				}catch(IOException e2) {
					JOptionPane.showMessageDialog(JPainelAtualizarLivro.this, "Erro ao salvar a imagem da capa: " + e2.getMessage() , "Erro",
							JOptionPane.ERROR_MESSAGE);
				}

			}
		});

		btnAtualizar.setFont(new Font("Arial Black", Font.BOLD, 15));
		btnAtualizar.setBounds(40, 650, 400, 40);
		add(btnAtualizar);

		// Inserir imagem inicio
		lblCapa = new JLabel("Capa do livro");
		lblCapa.setFont(new Font("Arial Black", Font.BOLD, 13));
		lblCapa.setBounds(500, 20, 100, 20);
		add(lblCapa);
		arquivoCapaSelecionada = null;
		
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
			ImageIcon existeImagem = (ImageIcon) lblCapaLivro.getIcon();
			
			if(existeImagem != null) {
				imagemCarregada = true;
			}
			
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
					
					lblCapaLivro.setIcon(null);
					lblCapaLivro.setIcon(scaledIcon);
					lblCapaLivro.setText("");
					atualizarCapa = true;
					
				} catch (Exception ex) {
					lblCapaLivro.setText("Erro ao carregar imagem!");
					lblCapaLivro.setIcon(null);
					ex.printStackTrace();
				}
			}
		});
		// Inserir imagem fim
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
		textFieldIdLivro.setText("");
		lblCapaLivro.setIcon(null);
		lblCapaLivro.setText("Capa do livro");
		arquivoCapaSelecionada = null;
	}
}
