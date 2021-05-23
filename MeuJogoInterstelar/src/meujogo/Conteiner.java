package meujogo;

import javax.swing.JFrame;

import meujogo.Modelo.Fase;

/*criando a tela do jogo*/
public class Conteiner extends JFrame {

	public Conteiner () {
		add(new Fase());
		setTitle("Interstelar");
		setSize(1024,728);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		this.setResizable(false);
		setVisible(true);
	}
	
	public static void main (String []args) {
		new	 Conteiner();
	}
	
}
