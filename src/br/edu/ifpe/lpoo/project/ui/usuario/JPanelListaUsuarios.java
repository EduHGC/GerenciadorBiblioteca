package br.edu.ifpe.lpoo.project.ui.usuario;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import br.edu.ifpe.lpoo.project.business.user.FuncionarioController;
import br.edu.ifpe.lpoo.project.entities.user.Usuario;
import br.edu.ifpe.lpoo.project.exception.BusinessException;

public class JPanelListaUsuarios extends JPanel {

	private static final long serialVersionUID = 1L;

	private JPanel painelCards;

	public JPanelListaUsuarios() {

		setLayout(new BorderLayout());
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {
				atualizarLista();
			}
		});

		JLabel titulo = new JLabel("Usuários cadastrados");
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		titulo.setFont(new Font("Arial Black", Font.BOLD, 20));
		add(titulo, BorderLayout.NORTH);

		painelCards = new JPanel(new GridLayout(0, 2, 10, 10));
		painelCards.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		
		carregarUsuarios();
		
		JScrollPane scrollPane = new JScrollPane(painelCards);
		scrollPane.getVerticalScrollBar().setUnitIncrement(16);
		add(scrollPane, BorderLayout.CENTER);

	}

	private void carregarUsuarios() {
		
		painelCards.removeAll();
		
		try {
			List<Usuario> usuarios = new ArrayList<Usuario>();
			FuncionarioController funcionarioController = new FuncionarioController();
			usuarios.addAll(funcionarioController.listarUsuarios());
			
			if(usuarios.isEmpty()) {
				JLabel mensagemVazia = new JLabel("Nenhum usuário cadastrado.", SwingConstants.CENTER);
				mensagemVazia.setFont(new Font("Arial", Font.PLAIN, 16));
				painelCards.add(mensagemVazia);
			}else {
				
				for(Usuario usuario : usuarios) {
					painelCards.add(new JPanelCardUsuario(usuario));
				}
			}
			
		}catch (BusinessException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
		}
		
		painelCards.revalidate();
        painelCards.repaint();
	}
	
	private void atualizarLista() {
		carregarUsuarios();
	}

}
