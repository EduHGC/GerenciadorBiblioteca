package br.edu.ifpe.lpoo.project.ui.acervo;

import javax.swing.JPanel;

import br.edu.ifpe.lpoo.project.entities.acervo.Exemplar;

import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

public class JPanelCardExemplar extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel lblValorIdExemplar;
	private JLabel lblValorRegistro;
	private JLabel lblValorStatus;
	
	public JPanelCardExemplar(Exemplar exemplar) {
		setLayout(null);
		
		setPreferredSize(new Dimension(430, 60));
		setMaximumSize(new Dimension(430, 60));
		setMinimumSize(new Dimension(430, 60));
		
		JLabel lblIdExemplar = new JLabel("Identificador:");
		lblIdExemplar.setFont(new Font("Arial Black", Font.BOLD, 15));
		lblIdExemplar.setBounds(10, 10, 118, 20);
		add(lblIdExemplar);
		
		lblValorIdExemplar = new JLabel("");
		lblValorIdExemplar.setFont(new Font("Arial Black", Font.BOLD, 15));
		lblValorIdExemplar.setBounds(10, 30, 118, 15);
		add(lblValorIdExemplar);
		
		JLabel lblRegistro = new JLabel("Registro:");
		lblRegistro.setFont(new Font("Arial Black", Font.BOLD, 15));
		lblRegistro.setBounds(146, 10, 118, 20);
		add(lblRegistro);
		
		lblValorRegistro = new JLabel("");
		lblValorRegistro.setFont(new Font("Arial Black", Font.BOLD, 15));
		lblValorRegistro.setBounds(146, 30, 118, 15);
		add(lblValorRegistro);
		
		JLabel lblStatus = new JLabel("Status:");
		lblStatus.setFont(new Font("Arial Black", Font.BOLD, 15));
		lblStatus.setBounds(280, 10, 118, 20);
		add(lblStatus);
		
		lblValorStatus = new JLabel("");
		lblValorStatus.setFont(new Font("Arial Black", Font.BOLD, 15));
		lblValorStatus.setBounds(280, 30, 140, 15);
		add(lblValorStatus);
		
		lblValorIdExemplar.setText(String.valueOf(exemplar.getIdExemplar()));
		lblValorRegistro.setText(exemplar.getRegistro());
		if(exemplar.getStatus().name().equals("DISPONIVEL")) {
			lblValorStatus.setForeground(Color.GREEN);
		}else {
			lblValorStatus.setForeground(Color.RED);
		}
		lblValorStatus.setText(exemplar.getStatus().name());
		
	}
}
