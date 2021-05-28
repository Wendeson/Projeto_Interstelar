package meujogo.Modelo;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
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
	private List<Inimigo1> inimigo1;
	private boolean emJogo;
	
	public Fase() {
		
		setFocusable(true);
		setDoubleBuffered(true);
		
		ImageIcon referencia = new ImageIcon("res\\fundo_cidadep.png");
		fundo = referencia.getImage();
		
		jogador = new Jogador();
		jogador.load();
		
		addKeyListener(new TecladoAdapter());
		
		
		timer = new Timer(5, this);
		timer.start();
		
		inicializaInimigos();
		emJogo = true;
	}
	
	public void inicializaInimigos() {
		int condernadas[] = new int [40];
		inimigo1 = new ArrayList<Inimigo1>();
		
		for (int i = 0; i < condernadas.length; i++) {
			int x = (int)(Math.random() * 8000 + 1024);
			int y = (int)(Math.random() * 650 + 30);
			inimigo1.add(new Inimigo1(x, y));
		}
	}
	
	public void paint(Graphics g) {
		Graphics2D graficos = (Graphics2D)g;
		if(emJogo == true) {
			
			graficos.drawImage(fundo, 0, 0, null);
			graficos.drawImage(jogador.getImagem(), jogador.getX(), jogador.getY(), this);
			
			List<Tiro> tiros = jogador.getTiros();
			for(int i = 0; i < tiros.size(); i++) {
				Tiro m = tiros.get(i);
				m.load();
				graficos.drawImage(m.getImagem(), m.getX(), m.getY(), this);
			}
			
			for (int w = 0; w < inimigo1.size(); w++) {
				Inimigo1 in = inimigo1.get(w);
				in.load();
				graficos.drawImage(in.getImagem(), in.getX(), in.getY(), this);
			}
		}else {
			ImageIcon fimJogo = new ImageIcon("res\\GameOver2.png");
			graficos.drawImage(fimJogo.getImage(), 0, 0, null);
		}
		
		
		g.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		jogador.update();
		
		List<Tiro> tiros = jogador.getTiros();
		for(int i = 0; i < tiros.size(); i++) {
			Tiro m = tiros.get(i);
				if(m.isVisivel()) {
					m.update();
				}else {
					tiros.remove(i);
				}
		}
		
		for (int w = 0; w < inimigo1.size(); w++) {
			Inimigo1 in = inimigo1.get(w);
				if (in.isVisivel()) {
					in.update();
				}else {
					inimigo1.remove(w);
				}
		}
		
		checarColisoes();
		repaint();
		
	}
	//checar colisoes
	public void checarColisoes() {
		Rectangle formaNave = jogador.getBounds();
		Rectangle formaInimigo1;
		Rectangle formaTiro;
		
		for(int i = 0; i < inimigo1.size(); i++) {
			Inimigo1 tempInimigo1 = inimigo1.get(i);
			formaInimigo1 = tempInimigo1.getBounds();
				if(formaNave.intersects(formaInimigo1)) {
					jogador.setVisivel(false);
					tempInimigo1.setVisivel(false);
					emJogo = false;
				}
		}
		
		List<Tiro> tiros = jogador.getTiros();
		for (int j = 0; j < tiros.size(); j++) {
			Tiro tempTiro= tiros.get(j);
			formaTiro= tempTiro.getBounds();
			for(int a = 0; a < inimigo1.size(); a++) {
				Inimigo1 tempInimigo1 = inimigo1.get(a);
				formaInimigo1 = tempInimigo1.getBounds();
				if(formaTiro.intersects(formaInimigo1)) {
					tempInimigo1.setVisivel(false);
					tempTiro.setVisivel(false);
				}
			}
		}
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
