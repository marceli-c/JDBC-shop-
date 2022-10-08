

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.NumberFormat;

import javax.sound.sampled.Line;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.NumberFormatter;

public class addItem extends JPanel{

	Object plusik;
	JButton przyciskDodaj;
	boolean halo;
	JTextField opis;
	JFormattedTextField elo;
	JTextField nazwa;
	JButton przyciskZatwierdz;
	addItem(){
		nazwa=stworzNazwa();
		halo=true;
		
		opis=stworzOpis();
		elo=stworzTextField();
		przyciskDodaj=stworzPrzycisk();
		przyciskZatwierdz=stworzPrzyciskZatwierdz();
		this.setSize(200,200);
		this.setPreferredSize(new Dimension(200,200));
		this.setVisible(true);
		//this.add(elo);
		this.add(przyciskDodaj);
		
		this.setOpaque(false);
		this.setLayout(null);
		
		
	}
	private JTextField stworzNazwa() {
		JTextField nazwa=new JTextField();
		nazwa.setBounds(110, 40, 80, 25);
		nazwa.setBackground(new Color(255,255,255,0));
		nazwa.setOpaque(false);
		nazwa.setBorder(BorderFactory.createLineBorder(Color.black));
		return nazwa;
	}
	public JTextField stworzOpis() {
		JTextField opis=new JTextField();
		opis.setBounds(10,120,180,70);
		opis.setBackground(new Color(255,255,255,0));
		opis.setOpaque(false);
		opis.setBorder(BorderFactory.createLineBorder(Color.black));
		return opis;
	}
	public JFormattedTextField stworzTextField() {
		NumberFormat format=NumberFormat.getNumberInstance();
		format.setMaximumIntegerDigits(4);
		NumberFormatter formatter=new NumberFormatter(format);
		JFormattedTextField iloscinputfield=new JFormattedTextField(formatter);
		
		
		iloscinputfield.setBackground(new Color(255,255,255,80));
		iloscinputfield.setOpaque(false);
		iloscinputfield.setForeground(Color.black);
		iloscinputfield.setBorder(BorderFactory.createLineBorder(Color.black));
		iloscinputfield.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if(e.getKeyChar()>='0' && e.getKeyChar()<='9'||e.getKeyCode()==8||e.getKeyCode()==10) {
					
					iloscinputfield.setEditable(true);
				}
				else {
					iloscinputfield.setEditable(false);
			}
			}
		});
		//iloscinputfield.setPreferredSize(new Dimension(35,25));
		iloscinputfield.setBounds(125,5,65,25);
		return iloscinputfield;
	}
	
	
	public void paintComponent(Graphics g) {
		Graphics2D g2d=(Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
			    RenderingHints.VALUE_ANTIALIAS_ON);
		super.paintComponent(g2d);
		Color kolor=new Color(255,255,255,125);
		g2d.setPaint(kolor);
		g2d.fillRect(0, 0, 200, 200);
		if(halo==true) {
			g2d.setPaint(new Color(255,255,255,120));
			g2d.setStroke(new BasicStroke(15,1,1));
			g2d.drawLine((this.getWidth()/2)-25, this.getHeight()/2, this.getWidth()/2+25, this.getHeight()/2);
			g2d.drawLine(this.getWidth()/2, this.getHeight()/2-25, this.getWidth()/2, this.getHeight()/2+25);
			g2d.setPaint(new Color(255,255,255,80));
			g2d.fillOval((this.getWidth()/2)-41, this.getHeight()/2-41, 85, 85);
		}
		if(halo==false) {
			g2d.setPaint(Color.black);
			g2d.drawRect(125, 70, 65, 25);
		}
		
		
	}
	private JButton stworzPrzycisk() {
		JButton przycisk=new JButton();
		przycisk.setOpaque(false);
		przycisk.setContentAreaFilled(false);
		przycisk.setBorderPainted(false);
		przycisk.setBackground(new Color(255,255,255,0));
		
		przycisk.setBounds(65, 65, 70, 70);
		return przycisk;
	}
	private JButton stworzPrzyciskZatwierdz() {
		JButton przycisk=new JButton("Zatwierdz");
		przycisk.setOpaque(false);
		przycisk.setContentAreaFilled(false);
		przycisk.setBorderPainted(false);
		przycisk.setBackground(new Color(255,255,255,0));
		przycisk.setBounds(110, 70, 95, 25);
		return przycisk;
	}
	public void zmiana() {
		this.halo=false;
		if(this.halo==false) {
			this.remove(przyciskDodaj);
			this.add(elo);
			this.add(opis);
			this.add(przyciskZatwierdz);
			this.add(nazwa);
		}
		this.repaint();
		
	}
	public String getNazwa() {
		return nazwa.getText();
	}
	public int getIlosc() {
		return Integer.parseInt(elo.getText());
	}
	public String getOpis() {
		return opis.getText();
	}
	
	
	
	
	
	
}
