package br.edu.ifpe.lpoo.project.main;

import javax.swing.SwingUtilities;

import br.edu.ifpe.lpoo.project.ui.login.JFrameLogin;

public class Main {
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater( () -> {
			JFrameLogin jFrameLogin = new JFrameLogin();
			jFrameLogin.setVisible(true);
		});
	}
}
