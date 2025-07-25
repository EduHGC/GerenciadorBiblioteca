package br.edu.ifpe.lpoo.project.ui.usuario;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.SwingConstants;

import br.edu.ifpe.lpoo.project.business.user.UsuarioController;
import br.edu.ifpe.lpoo.project.entities.user.Usuario;
import br.edu.ifpe.lpoo.project.exception.BusinessException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class JPanelBuscarUsuario extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel painelCards;
	private JTextField textFieldInfoBusca;
	private JComboBox<String> comboBoxOpcoesBusca;
	private UsuarioController usuarioController;

	/**
	 * Create the panel.
	 */
	public JPanelBuscarUsuario() {
		setLayout(new BorderLayout(0, 0));

		JPanel panelBusca = new JPanel();
		add(panelBusca, BorderLayout.NORTH);
		panelBusca.setPreferredSize(new Dimension(0, 200));
		panelBusca.setLayout(null);

		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				painelCards.removeAll();

				try {

					String opcao = (String) comboBoxOpcoesBusca.getSelectedItem();
					if (opcao.equals("Id")) {
						usuarioController = new UsuarioController();
						String info = textFieldInfoBusca.getText();
						Usuario usuario = usuarioController.buscarPorId(info);
						painelCards.add(new JPanelCardUsuario(usuario));
					}

					if (opcao.equals("Palavra-Chave")) {
						usuarioController = new UsuarioController();
						String info = textFieldInfoBusca.getText();
						List<Usuario> usuarios = usuarioController.buscarPorPalavaraChave(info);

						if (usuarios.isEmpty()) {
							JLabel mensagemVazia = new JLabel("Nenhum usuário foi encontrado com essa palavra chave.",
									SwingConstants.CENTER);
							mensagemVazia.setFont(new Font("Arial", Font.PLAIN, 16));
							painelCards.add(mensagemVazia);
						} else {
							for (Usuario usuario : usuarios) {
								painelCards.add(new JPanelCardUsuario(usuario));
							}
						}
					}
				} catch (BusinessException e1) {
					JOptionPane.showMessageDialog(JPanelBuscarUsuario.this, e1.getMessage(), "Erro",
							JOptionPane.ERROR_MESSAGE);
				}

				painelCards.revalidate();
				painelCards.repaint();
			}
		});

		btnBuscar.setFont(new Font("Arial Black", Font.BOLD, 13));
		btnBuscar.setEnabled(false);
		btnBuscar.setBounds(40, 105, 302, 40);

		panelBusca.add(btnBuscar);

		textFieldInfoBusca = new JTextField();
		textFieldInfoBusca.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

				if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {

					if (textFieldInfoBusca.getText().length() == 1) {
						painelCards.removeAll();
						painelCards.revalidate();
						painelCards.repaint();
					}
				}
			}
		});
		textFieldInfoBusca.setFont(new Font("Arial Black", Font.BOLD, 15));
		textFieldInfoBusca.setBounds(40, 60, 302, 40);
		textFieldInfoBusca.setEnabled(false);
		textFieldInfoBusca.setEditable(false);
		panelBusca.add(textFieldInfoBusca);

		JLabel lblInformacao = new JLabel("Inforção de busca");
		lblInformacao.setFont(new Font("Arial Black", Font.BOLD, 13));
		lblInformacao.setBounds(40, 40, 148, 20);
		panelBusca.add(lblInformacao);

		comboBoxOpcoesBusca = new JComboBox<>(new String[] { "Selecione uma opção para busca", "Id", "Palavra-Chave" });
		comboBoxOpcoesBusca.setFont(new Font("Arial Black", Font.BOLD, 13));
		comboBoxOpcoesBusca.setBounds(352, 60, 302, 40);
		comboBoxOpcoesBusca.addActionListener(e -> {
			String opcaoSelecionada = (String) comboBoxOpcoesBusca.getSelectedItem();

			if ("Selecione uma opção para busca".equals(opcaoSelecionada)) {
				textFieldInfoBusca.setEnabled(false);
				textFieldInfoBusca.setEditable(false);
				btnBuscar.setEnabled(false);
				textFieldInfoBusca.setText("");
			} else {
				textFieldInfoBusca.setEnabled(true);
				textFieldInfoBusca.setEditable(true);
				btnBuscar.setEnabled(true);
				textFieldInfoBusca.requestFocus();
			}
		});
		panelBusca.add(comboBoxOpcoesBusca);

		JLabel lblTitulo = new JLabel("Buscas");
		lblTitulo.setFont(new Font("Arial Black", Font.PLAIN, 20));
		lblTitulo.setBounds(519, 10, 135, 20);
		panelBusca.add(lblTitulo);

		painelCards = new JPanel(new GridLayout(0, 2, 10, 10));
		painelCards.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		JScrollPane scrollPane = new JScrollPane(painelCards);
		scrollPane.getVerticalScrollBar().setUnitIncrement(16);
		add(scrollPane, BorderLayout.CENTER);

	}
}
