package br.edu.ifpe.lpoo.project.ui.acervo;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import br.edu.ifpe.lpoo.project.business.acervo.ExemplarController;
import br.edu.ifpe.lpoo.project.business.acervo.LivroController;
import br.edu.ifpe.lpoo.project.entities.acervo.Exemplar;
import br.edu.ifpe.lpoo.project.entities.acervo.Livro;
import br.edu.ifpe.lpoo.project.exception.BusinessException;

import javax.swing.JScrollPane;

public class JPanelListaExemplares extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textFieldIdItem;
	private JButton btnBuscarLivro;
	private JLabel lblCapaLivro;
	private JLabel lblCapa;
	private JLabel lblValorIsbn;
	private JLabel lblValorTitulo;
	private JLabel lblValorAutor;
	private JLabel lblValorEditora;
	private JLabel lblValorAnoPublicacao;
	private JLabel lblValorGenero;
	private JLabel lblValorIdioma;
	private JLabel lblValorNumPaginas;
	private JScrollPane scrollPane;
	private JLabel lblListaExemplares;
	private JPanel painelExemplares;
	
	public JPanelListaExemplares() {
		
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
		
					painelExemplares.removeAll();
					painelExemplares.revalidate();
					painelExemplares.repaint();
					
					String novoId = textFieldIdItem.getText();
					limparCampos();
					textFieldIdItem.setText(novoId);
				}
			}
		});
		textFieldIdItem.setFont(new Font("Arial Black", Font.BOLD, 15));
		textFieldIdItem.setColumns(10);
		textFieldIdItem.setBounds(40, 40, 155, 40);
		add(textFieldIdItem);
		
		btnBuscarLivro = new JButton("Buscar");
		btnBuscarLivro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				if (textFieldIdItem.getText().trim().isEmpty()) {
					JOptionPane.showMessageDialog(JPanelListaExemplares.this, "Insira um id válido", "Id Inválido",
							JOptionPane.WARNING_MESSAGE);
					return;
				}

				String idLivro = textFieldIdItem.getText();
				try {

					LivroController livroCotroller = new LivroController();

					Livro livro = livroCotroller.buscarLivroId(idLivro);

					if (livro != null) {
						lblValorIsbn.setText(livro.getIsbn());
						lblValorTitulo.setText(livro.getTitulo());
						lblValorAutor.setText(livro.getAutor());
						lblValorEditora.setText(livro.getEditora());
						lblValorAnoPublicacao.setText(String.valueOf(livro.getAnoPublicacao()));
						lblValorGenero.setText(livro.getGenero());
						lblValorIdioma.setText(livro.getIdioma());
						lblValorNumPaginas.setText(String.valueOf(livro.getNumeroPaginas()));
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
						
						ExemplarController exemplarController = new ExemplarController();
						
						List<Exemplar> exemplares = new ArrayList<Exemplar>();
						
						exemplares = exemplarController.listarExemplaresIdItem(livro.getIdItem());
						
						carregarExemplares(exemplares);
					}
					
				} catch (BusinessException e1) {
					JOptionPane.showMessageDialog(JPanelListaExemplares.this, e1.getMessage(), "Erro",
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
		lblCapaLivro.setFont(new Font("Arial Black", Font.BOLD, 15));
		lblCapaLivro.setHorizontalAlignment(SwingConstants.CENTER);
		lblCapaLivro.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		lblCapaLivro.setBounds(414, 40, 390, 460);
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
		
		painelExemplares = new JPanel();
		painelExemplares.setLayout(new BoxLayout(painelExemplares, BoxLayout.Y_AXIS));
		
		scrollPane = new JScrollPane(painelExemplares);
		scrollPane.setBounds(840, 40, 440, 530);
		add(scrollPane);
		
		
		lblListaExemplares = new JLabel("Lista exemplares");
		lblListaExemplares.setFont(new Font("Arial Black", Font.BOLD, 13));
		lblListaExemplares.setBounds(1005, 19, 140, 20);
		add(lblListaExemplares);
	}
	
	private void carregarExemplares(List<Exemplar> exemplares) {
		
		painelExemplares.removeAll();
		
		if(exemplares.isEmpty()) {
			JOptionPane.showMessageDialog(JPanelListaExemplares.this, "Não esxite exemplar cadastrado", "Atenção",
					JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		
		for(Exemplar exemplar : exemplares) {
			painelExemplares.add(new JPanelCardExemplar(exemplar));
			 painelExemplares.add(Box.createVerticalStrut(10));
		}
		
		painelExemplares.revalidate();
		painelExemplares.repaint();
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
		lblCapaLivro.setIcon(null);
		lblCapaLivro.setText("Pré-visualização da Capa");
	}
}
