package meujogo.Modelo;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;




public class Fase extends JPanel implements ActionListener {

	/*criando a fase do jogo*/
	
	private Image fundo;
	private Jogador jogador;
	private Timer timer;
	
	public Fase() {
		
		setFocusable(true);
		setDoubleBuffered(true);
		
		ImageIcon referencia = new ImageIcon("res\\Fundo-preto.png");
		fundo = referencia.getImage();
		
		jogador = new Jogador();
		jogador.load();
		
		addKeyListener(new TecladoAdapter());
		
		
		timer = new Timer(5, this);
		timer.start();
	}
	
	public void paint(Graphics g) {
		Graphics2D graficos = (Graphics2D)g;
		graficos.drawImage(fundo, 0, 0, null);
		graficos.drawImage(jogador.getImagem(), jogador.getX(), jogador.getY(), this);
		g.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		jogador.update();
		repaint();
		
	}
	
	
	private class TecladoAdapter extends KeyAdapter {
		
		@Override
		public void keyPressed(KeyEvent e) {
			jogador.keyPressed(e);
			
		}
		
		@Override
		public void keyReleased(KeyEvent e) {
			jogador.keyRelease(e);
			
		}
	}
}
