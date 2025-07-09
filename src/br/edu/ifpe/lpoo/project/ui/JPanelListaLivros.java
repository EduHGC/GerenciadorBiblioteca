package br.edu.ifpe.lpoo.project.ui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import br.edu.ifpe.lpoo.project.business.acervo.LivroController;
import br.edu.ifpe.lpoo.project.entities.acervo.Livro;
import br.edu.ifpe.lpoo.project.exception.BusinessException;

public class JPanelListaLivros extends JPanel {

	private static final long serialVersionUID = 1L;

	private LivroController livroController;
	private JPanel painelCards;
	
	public JPanelListaLivros() {
		
		setLayout(new BorderLayout());
		
		livroController = new LivroController();

        JLabel titulo = new JLabel("Lista de Livros");
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        titulo.setFont(new Font("Arial Black", Font.BOLD, 20));
        add(titulo, BorderLayout.NORTH);
        
        painelCards = new JPanel(new GridLayout(0, 2, 10, 10));
        painelCards.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        try {
        	List<Livro> livros = livroController.listarLivros();
        	
        	if (livros.isEmpty()) {
                JLabel mensagemVazia = new JLabel("Nenhum livro cadastrado.", SwingConstants.CENTER);
                mensagemVazia.setFont(new Font("Arial", Font.PLAIN, 16));
                painelCards.add(mensagemVazia); 
            } else {
                for (Livro livro : livros) {
                    painelCards.add(new JCardLivro(livro));
                }
            }
        	
        }catch(BusinessException e) {
        	JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        
        JScrollPane scrollPane = new JScrollPane(painelCards);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        add(scrollPane, BorderLayout.CENTER);
	}
	
}
