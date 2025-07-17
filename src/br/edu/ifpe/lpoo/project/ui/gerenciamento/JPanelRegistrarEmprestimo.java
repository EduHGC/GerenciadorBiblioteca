package br.edu.ifpe.lpoo.project.ui.gerenciamento;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextField;
import javax.swing.SwingConstants;

import br.edu.ifpe.lpoo.project.business.acervo.ExemplarController;
import br.edu.ifpe.lpoo.project.business.acervo.LivroController;
import br.edu.ifpe.lpoo.project.business.gerenciamento.EmprestimoController;
import br.edu.ifpe.lpoo.project.business.user.UsuarioController;
import br.edu.ifpe.lpoo.project.entities.acervo.Exemplar;
import br.edu.ifpe.lpoo.project.entities.acervo.Livro;
import br.edu.ifpe.lpoo.project.entities.funcionario.Funcionario;
import br.edu.ifpe.lpoo.project.entities.gerenciamento.PrazoService;
import br.edu.ifpe.lpoo.project.entities.user.Usuario;
import br.edu.ifpe.lpoo.project.enums.StatusExemplar;
import br.edu.ifpe.lpoo.project.exception.BusinessException;
import br.edu.ifpe.lpoo.project.ui.BibliotecaApp;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class JPanelRegistrarEmprestimo extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private JTextField textFieldIdExemplar;
	private JLabel lblCapaLivro;
	private JLabel lblId;
	private JLabel lblIdentificadorValor;
	private JLabel lblTitulo;
	private JLabel lblTituloValor;
	private JLabel lblDisponibilidadeValor;
	private JLabel lblDisponibilidade;
	private JButton btnBuscarUsuario;
	private JLabel lblIdUsuario;
	private JTextField textFieldIdUsuario;
	private JLabel lblDataEmprestimoValor;
	private JLabel lblDataEmprestimo;
	private JLabel lblCpfValor;
	private JLabel lblCpf;
	private JLabel lblNomeValor;
	private JLabel lblNome;
	private JLabel lblDataParaDevolucaoValor;
	private JLabel lblDataParaDevolucao;
	private JLabel lblStatusUsuario;
	private JLabel lblStatusUsuarioValor;
	private JButton btnRegistrar;
	private DateTimeFormatter formatarData = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	private boolean buscaUsuario = false;
	private boolean buscaExemplar = false;
	private JLabel lblIdentificador;
	private JLabel lblIdentificadorUsuarioValor;
	private Funcionario funcionarioLogado;

	private BibliotecaApp app;
	
	public JPanelRegistrarEmprestimo(Funcionario funcionarioLogado, BibliotecaApp app) {
		
		this.funcionarioLogado = funcionarioLogado;
		this.app = app;
		
		setPreferredSize(new Dimension(1200, 700));
		setLayout(null);

		JLabel lblIdItem = new JLabel("Identificador do item");
		lblIdItem.setFont(new Font("Arial Black", Font.BOLD, 13));
		lblIdItem.setBounds(40, 40, 164, 20);
		add(lblIdItem);

		textFieldIdExemplar = new JTextField();
		textFieldIdExemplar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
					String novoId = textFieldIdExemplar.getText();

					limparCamposExemplar();

					textFieldIdExemplar.setText(novoId);

				}
			}
		});
		textFieldIdExemplar.setFont(new Font("Arial Black", Font.BOLD, 15));
		textFieldIdExemplar.setBounds(40, 60, 164, 40);
		add(textFieldIdExemplar);
		textFieldIdExemplar.setColumns(10);

		JButton btnBuscarExemplar = new JButton("Buscar");
		btnBuscarExemplar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (textFieldIdExemplar.getText().trim().isEmpty()) {
					JOptionPane.showMessageDialog(JPanelRegistrarEmprestimo.this, "Insira um id válido", "Id Inválido",
							JOptionPane.WARNING_MESSAGE);
					return;
				}

				String idExemplar = textFieldIdExemplar.getText().trim();

				try {

					ExemplarController exemplarController = new ExemplarController();
					LivroController livroController = new LivroController();

					Exemplar exemplar = exemplarController.buscarExemplarId(idExemplar);
					Livro livro = livroController.buscarLivroId(String.valueOf(exemplar.getIdItem()));

					lblIdentificadorValor.setText(String.valueOf(exemplar.getIdExemplar()));
					lblTituloValor.setText(livro.getTitulo());
					lblDisponibilidadeValor.setText(exemplar.getStatus().getDescricao());

					String pathAppBiblioteca = System.getenv("APPDATA");
					File pastaCapas = new File(pathAppBiblioteca, "Biblioteca/Capas");

					String[] extensoes = { "png", "jpg", "jpeg" };

					for (String extensao : extensoes) {
						File capa = new File(pastaCapas, livro.getIdItem() + "." + extensao);
						if (capa.exists()) {
							ImageIcon imageIcon = new ImageIcon(capa.getAbsolutePath());

							int widthCapaLivro = lblCapaLivro.getWidth();
							int heightCapaLivro = lblCapaLivro.getHeight();
							Image capaOriginal = imageIcon.getImage();
							Image redimencionarCapa = capaOriginal.getScaledInstance(widthCapaLivro, heightCapaLivro,
									Image.SCALE_SMOOTH);
							ImageIcon imagemCapa = new ImageIcon(redimencionarCapa);

							lblCapaLivro.setIcon(imagemCapa);
							lblCapaLivro.setText("");
						}
					}

					buscaExemplar = true;

					if (buscaExemplar && buscaUsuario) {
						btnRegistrar.setEnabled(true);
					}

				} catch (Exception e1) {
					JOptionPane.showMessageDialog(JPanelRegistrarEmprestimo.this, e1.getMessage(), "Erro",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnBuscarExemplar.setFont(new Font("Arial Black", Font.BOLD, 15));
		btnBuscarExemplar.setBounds(214, 60, 133, 40);
		add(btnBuscarExemplar);

		lblCapaLivro = new JLabel("Pré-visualização da Capa");
		lblCapaLivro.setFont(new Font("Arial Black", Font.BOLD, 15));
		lblCapaLivro.setHorizontalAlignment(SwingConstants.CENTER);
		lblCapaLivro.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		lblCapaLivro.setBounds(40, 120, 265, 348);
		add(lblCapaLivro);
		
		lblIdentificador = new JLabel("Identificador:");
		lblIdentificador.setFont(new Font("Arial Black", Font.BOLD, 13));
		lblIdentificador.setBounds(763, 120, 133, 20);
		add(lblIdentificador);
		
		lblIdentificadorUsuarioValor = new JLabel("");
		lblIdentificadorUsuarioValor.setFont(new Font("Arial Black", Font.BOLD, 13));
		lblIdentificadorUsuarioValor.setBounds(763, 140, 427, 20);
		add(lblIdentificadorUsuarioValor);
		
		lblId = new JLabel("Identificador:");
		lblId.setFont(new Font("Arial Black", Font.BOLD, 13));
		lblId.setBounds(315, 120, 112, 20);
		add(lblId);

		lblIdentificadorValor = new JLabel("");
		lblIdentificadorValor.setFont(new Font("Arial Black", Font.BOLD, 13));
		lblIdentificadorValor.setBounds(315, 140, 378, 20);
		add(lblIdentificadorValor);

		lblTitulo = new JLabel("Título:");
		lblTitulo.setFont(new Font("Arial Black", Font.BOLD, 13));
		lblTitulo.setBounds(315, 170, 100, 20);
		add(lblTitulo);

		lblTituloValor = new JLabel("");
		lblTituloValor.setFont(new Font("Arial Black", Font.BOLD, 13));
		lblTituloValor.setBounds(315, 190, 378, 20);
		add(lblTituloValor);

		lblDisponibilidadeValor = new JLabel("");
		lblDisponibilidadeValor.setFont(new Font("Arial Black", Font.BOLD, 13));
		lblDisponibilidadeValor.setBounds(315, 240, 350, 20);
		add(lblDisponibilidadeValor);

		lblDisponibilidade = new JLabel("Disponibilidade:");
		lblDisponibilidade.setFont(new Font("Arial Black", Font.BOLD, 13));
		lblDisponibilidade.setBounds(315, 220, 126, 20);
		add(lblDisponibilidade);

		btnBuscarUsuario = new JButton("Buscar");
		btnBuscarUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (textFieldIdUsuario.getText().trim().isEmpty()) {
					JOptionPane.showMessageDialog(JPanelRegistrarEmprestimo.this, "Insira um id válido", "Id Inválido",
							JOptionPane.WARNING_MESSAGE);
					return;
				}

				String idUsuario = textFieldIdUsuario.getText().trim();

				try {

					UsuarioController usuarioController = new UsuarioController();
					Usuario usuario = usuarioController.buscarPorId(idUsuario);
					
					lblIdentificadorUsuarioValor.setText(String.valueOf(usuario.getIdUsuario()));
					lblNomeValor.setText(usuario.getNome());
					lblCpfValor.setText(usuario.getCpf());

					LocalDate dataAtual = LocalDate.now();
					lblDataEmprestimoValor.setText(dataAtual.format(formatarData));
					LocalDate dataParaDevolucao;
					if (usuario.getTipoUsuario().name().equalsIgnoreCase("ALUNO")) {
						dataParaDevolucao = dataAtual.plusDays(PrazoService.PRAZO_ALUNO);
					} else if (usuario.getTipoUsuario().name().equalsIgnoreCase("PROFESSOR")) {
						dataParaDevolucao = dataAtual.plusDays(PrazoService.PRAZO_PROFESSOR);
					} else {
						dataParaDevolucao = dataAtual.plusDays(PrazoService.PRAZO_PESQUISADOR);
					}
					lblDataParaDevolucaoValor.setText(dataParaDevolucao.format(formatarData));

					lblStatusUsuarioValor.setText(usuario.getStatusUsuario().getStatus());

					buscaUsuario = true;

					if (buscaExemplar && buscaUsuario) {
						btnRegistrar.setEnabled(true);
					}

				} catch (BusinessException e1) {
					JOptionPane.showMessageDialog(JPanelRegistrarEmprestimo.this, e1.getMessage(), "Erro",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnBuscarUsuario.setFont(new Font("Arial Black", Font.BOLD, 15));
		btnBuscarUsuario.setBounds(964, 60, 133, 40);
		add(btnBuscarUsuario);

		lblIdUsuario = new JLabel("Identificardor do usuário");
		lblIdUsuario.setFont(new Font("Arial Black", Font.BOLD, 13));
		lblIdUsuario.setBounds(763, 40, 190, 20);
		add(lblIdUsuario);

		textFieldIdUsuario = new JTextField();
		textFieldIdUsuario.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				String novoId = textFieldIdUsuario.getText();

				limparCamposUsuario();

				textFieldIdUsuario.setText(novoId);
			}
		});
		textFieldIdUsuario.setFont(new Font("Arial Black", Font.BOLD, 15));
		textFieldIdUsuario.setColumns(10);
		textFieldIdUsuario.setBounds(763, 60, 190, 40);
		add(textFieldIdUsuario);

		lblDataEmprestimoValor = new JLabel("");
		lblDataEmprestimoValor.setFont(new Font("Arial Black", Font.BOLD, 13));
		lblDataEmprestimoValor.setBounds(763, 290, 314, 20);
		add(lblDataEmprestimoValor);

		lblDataEmprestimo = new JLabel("Data do empréstimo:");
		lblDataEmprestimo.setFont(new Font("Arial Black", Font.BOLD, 13));
		lblDataEmprestimo.setBounds(763, 270, 158, 20);
		add(lblDataEmprestimo);

		lblCpfValor = new JLabel("");
		lblCpfValor.setFont(new Font("Arial Black", Font.BOLD, 13));
		lblCpfValor.setBounds(763, 240, 334, 20);
		add(lblCpfValor);

		lblCpf = new JLabel("CPF:");
		lblCpf.setFont(new Font("Arial Black", Font.BOLD, 13));
		lblCpf.setBounds(763, 220, 100, 20);
		add(lblCpf);

		lblNomeValor = new JLabel("");
		lblNomeValor.setFont(new Font("Arial Black", Font.BOLD, 13));
		lblNomeValor.setBounds(763, 190, 427, 20);
		add(lblNomeValor);

		lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Arial Black", Font.BOLD, 13));
		lblNome.setBounds(763, 170, 100, 20);
		add(lblNome);

		lblDataParaDevolucaoValor = new JLabel("");
		lblDataParaDevolucaoValor.setFont(new Font("Arial Black", Font.BOLD, 13));
		lblDataParaDevolucaoValor.setBounds(763, 340, 314, 20);
		add(lblDataParaDevolucaoValor);

		lblDataParaDevolucao = new JLabel("Data para devolução:");
		lblDataParaDevolucao.setFont(new Font("Arial Black", Font.BOLD, 13));
		lblDataParaDevolucao.setBounds(763, 320, 175, 20);
		add(lblDataParaDevolucao);

		lblStatusUsuario = new JLabel("Status do usuário:");
		lblStatusUsuario.setFont(new Font("Arial Black", Font.BOLD, 13));
		lblStatusUsuario.setBounds(763, 370, 175, 20);
		add(lblStatusUsuario);

		lblStatusUsuarioValor = new JLabel("");
		lblStatusUsuarioValor.setFont(new Font("Arial Black", Font.BOLD, 13));
		lblStatusUsuarioValor.setBounds(763, 390, 175, 20);
		add(lblStatusUsuarioValor);

		btnRegistrar = new JButton("Registrar");
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (!lblStatusUsuarioValor.getText().equals("Ativo")) {
					JOptionPane.showMessageDialog(JPanelRegistrarEmprestimo.this,
							"Usuário não está liberado para fazer empréstimos. Verifique se existe pendências",
							"Usuário com pendência", JOptionPane.WARNING_MESSAGE);
					return;
				}

				if (!lblDisponibilidadeValor.getText().equals("Disponível")) {
					JOptionPane.showMessageDialog(JPanelRegistrarEmprestimo.this,
							"Exemplar não está diponível no momento", "Disponibilidade de Exemplar",
							JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				String idExemplar = lblIdentificadorValor.getText();
				String idUsuario = lblIdentificadorUsuarioValor.getText();
				String idBibliotecario = String.valueOf(funcionarioLogado.getIdFuncionario());
				LocalDate dataEmprestimo = LocalDate.parse(lblDataEmprestimoValor.getText(), formatarData);
				LocalDate dataParaDevolucao = LocalDate.parse(lblDataParaDevolucaoValor.getText(), formatarData);
				
				try {
					EmprestimoController emprestimoController = new EmprestimoController();
					emprestimoController.registrar(idExemplar, idUsuario, idBibliotecario, dataEmprestimo, dataParaDevolucao, "Aberto");
					
					ExemplarController exemplarController = new ExemplarController();
					Exemplar exemplar = exemplarController.buscarExemplarId(lblIdentificadorValor.getText());
					exemplar.setStatus(StatusExemplar.EMPRESTADO);
					exemplarController.atualizarStatus(exemplar);
					
					JOptionPane.showMessageDialog(JPanelRegistrarEmprestimo.this,
							"Empréstimo realizado com sucesso.", "Empréstimo Concluído",
							JOptionPane.INFORMATION_MESSAGE);
					
					limparCamposExemplar();
					limparCamposUsuario();
					app.atualizarAbaListagemEmprestimos();
					
				} catch (BusinessException e2) {
					JOptionPane.showMessageDialog(JPanelRegistrarEmprestimo.this, e2.getMessage(), "Erro",
							JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		btnRegistrar.setEnabled(false);
		btnRegistrar.setFont(new Font("Arial Black", Font.BOLD, 15));
		btnRegistrar.setBounds(40, 507, 265, 40);
		add(btnRegistrar);

	}

	private void limparCamposExemplar() {

		lblIdentificadorValor.setText("");
		lblTituloValor.setText("");
		lblDisponibilidadeValor.setText("");
		lblCapaLivro.setIcon(null);
		lblCapaLivro.setText("Capa do livro");
		buscaExemplar = false;
		btnRegistrar.setEnabled(false);
		textFieldIdExemplar.setText("");
	}

	private void limparCamposUsuario() {
		lblIdentificadorUsuarioValor.setText("");
		lblNomeValor.setText("");
		lblCpfValor.setText("");
		lblDataEmprestimoValor.setText("");
		lblDataParaDevolucaoValor.setText("");
		lblStatusUsuarioValor.setText("");
		buscaUsuario = false;
		btnRegistrar.setEnabled(false);
		textFieldIdUsuario.setText("");
	}
}
