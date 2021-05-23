package meujogo;

import javax.swing.JFrame;

/*criando a tela do jogo*/
public class Conteiner extends JFrame {

	public Conteiner () {
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
