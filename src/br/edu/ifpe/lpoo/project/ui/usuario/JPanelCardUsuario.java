package br.edu.ifpe.lpoo.project.ui.usuario;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import br.edu.ifpe.lpoo.project.entities.user.Usuario;

public class JPanelCardUsuario extends JPanel {

	private static final long serialVersionUID = 1L;

	private JLabel lblNome;

	public JPanelCardUsuario(Usuario usuario) {

		setLayout(null);
		setBorder(new LineBorder(Color.BLACK, 3));
		setPreferredSize(new Dimension(500, 500));

		JPanel panelTitulo = new JPanel();
		panelTitulo.setBounds(0, 0, 500, 40);
		panelTitulo.setBorder(new LineBorder(Color.BLACK, 3));
		add(panelTitulo);

		lblNome = new JLabel("Nome");
		lblNome.setHorizontalAlignment(SwingConstants.CENTER);
		lblNome.setFont(new Font("Arial", Font.BOLD, 22));

		panelTitulo.setLayout(new BorderLayout());
		panelTitulo.add(lblNome, BorderLayout.CENTER);
		
		addComponentListener(new ComponentAdapter() {
	        @Override
	        public void componentResized(ComponentEvent e) {
	            panelTitulo.setBounds(0, 0, getWidth(), 40);
	        }
	    });

		JLabel lblInformacoes = new JLabel("Informações:");
		lblInformacoes.setFont(new Font("Arial Black", Font.PLAIN, 18));
		lblInformacoes.setBounds(10, 50, 200, 20);
		add(lblInformacoes);

		JLabel lblCategoria = new JLabel("Categoria:");
		lblCategoria.setFont(new Font("Arial Black", Font.BOLD, 14));
		lblCategoria.setVerticalAlignment(SwingConstants.TOP);
		lblCategoria.setHorizontalAlignment(SwingConstants.LEFT);
		lblCategoria.setBounds(10, 324, 480, 40);
		add(lblCategoria);

		JLabel lblCpf = new JLabel("CPF: ");
		lblCpf.setFont(new Font("Arial Black", Font.BOLD, 14));
		lblCpf.setVerticalAlignment(SwingConstants.TOP);
		lblCpf.setHorizontalAlignment(SwingConstants.LEFT);
		lblCpf.setBounds(10, 128, 480, 40);
		add(lblCpf);

		JLabel lblMatricula = new JLabel("Matrícula:");
		lblMatricula.setFont(new Font("Arial Black", Font.BOLD, 14));
		lblMatricula.setVerticalAlignment(SwingConstants.TOP);
		lblMatricula.setHorizontalAlignment(SwingConstants.LEFT);
		lblMatricula.setBounds(10, 168, 480, 40);
		add(lblMatricula);

		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Arial Black", Font.BOLD, 14));
		lblEmail.setVerticalAlignment(SwingConstants.TOP);
		lblEmail.setHorizontalAlignment(SwingConstants.LEFT);
		lblEmail.setBounds(10, 208, 480, 40);
		add(lblEmail);

		JLabel lblTelefone = new JLabel("Telefone:");
		lblTelefone.setFont(new Font("Arial Black", Font.BOLD, 14));
		lblTelefone.setVerticalAlignment(SwingConstants.TOP);
		lblTelefone.setHorizontalAlignment(SwingConstants.LEFT);
		lblTelefone.setBounds(10, 248, 480, 40);
		add(lblTelefone);

		JLabel lblDepartamento = new JLabel("Departamento/Curso:");
		lblDepartamento.setFont(new Font("Arial Black", Font.BOLD, 14));
		lblDepartamento.setVerticalAlignment(SwingConstants.TOP);
		lblDepartamento.setHorizontalAlignment(SwingConstants.LEFT);
		lblDepartamento.setBounds(10, 288, 480, 40);
		add(lblDepartamento);
		
		JLabel lblIdentificador = new JLabel("Identificador:");
		lblIdentificador.setVerticalAlignment(SwingConstants.TOP);
		lblIdentificador.setHorizontalAlignment(SwingConstants.LEFT);
		lblIdentificador.setFont(new Font("Arial Black", Font.BOLD, 14));
		lblIdentificador.setBounds(10, 88, 480, 40);
		add(lblIdentificador);
		
		JLabel lblStatus = new JLabel("Status:");
		lblStatus.setVerticalAlignment(SwingConstants.TOP);
		lblStatus.setHorizontalAlignment(SwingConstants.LEFT);
		lblStatus.setFont(new Font("Arial Black", Font.BOLD, 14));
		lblStatus.setBounds(10, 361, 480, 40);
		add(lblStatus);
		
		JLabel lblInstituicao = new JLabel("Instituição:");
		lblInstituicao.setVerticalAlignment(SwingConstants.TOP);
		lblInstituicao.setHorizontalAlignment(SwingConstants.LEFT);
		lblInstituicao.setFont(new Font("Arial Black", Font.BOLD, 14));
		lblInstituicao.setBounds(10, 398, 480, 40);
		add(lblInstituicao);
		
		JLabel lblDebito = new JLabel("Débito:");
		lblDebito.setVerticalAlignment(SwingConstants.TOP);
		lblDebito.setHorizontalAlignment(SwingConstants.LEFT);
		lblDebito.setFont(new Font("Arial Black", Font.BOLD, 14));
		lblDebito.setBounds(10, 438, 480, 40);
		add(lblDebito);
		
		if (usuario != null) {
			lblNome.setText(usuario.getNome());
			lblIdentificador.setText("Identificador: " + usuario.getIdUsuario());
			lblCpf.setText("CPF: " + usuario.getCpf());
			lblMatricula.setText("Matrícula: " + usuario.getMatricula());
			lblEmail.setText("Email: " + usuario.getEmail());
			lblTelefone.setText("Telefone: " + usuario.getTelefone());
			lblDepartamento.setText("Departamento/Curso: " + usuario.getDepartamentoCurso());
			lblCategoria.setText("Categoria: " + usuario.getTipoUsuario().getTipo());
			lblStatus.setText("Status: " + usuario.getStatusUsuario().getStatus());
			lblInstituicao.setText("Instituição: " + usuario.getInstituicao());
			lblDebito.setText("Débito: " + "R$" + String.format("%.2f", usuario.getDebito()));
			
		} 
	}
}
