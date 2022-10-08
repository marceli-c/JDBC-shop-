

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeListener;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.NumberFormatter;

public class Item extends JPanel{
	static Map<String,JFormattedTextField> iloscinput=new HashMap<String,JFormattedTextField>();
	JButton zatwierdz=stworzPrzycisk("DO KOSZYKA");
	JTextArea opislabel;
	JLabel nazwaLabel;
	JFormattedTextField iloscinputfield;
	JButton usunZKoszyka=stworzPrzycisk("Usun");
	JLabel elo;
	int iloscBaza;
	float iledostepne;
	boolean czyAdmin;
	boolean Koszyk;
	JLabel zdjecie;
	Image newImage;
	ImageIcon zdjeciee;
	ImageIcon zdjecieskalowane;
	JTextArea dostepnosc;
	JButton zmienIlosc=stworzPrzycisk("Zmien ilość");
	JButton wyswietlProdukt;
	String nazwa1;
	int width=200;
	int height=200;
	boolean ekranPelny=false;
	JButton usun=stworzPrzycisk("USUN");
	Item(String opis,String nazwa){
			
			czyAdmin=Main.getAdmin();
			iloscBaza=Main.getIlosc(nazwa);	
			float iloscFloat=iloscBaza;
			iledostepne=iloscFloat/1000;
			System.out.println(nazwa+" ile dosteeeepne "+iledostepne);
			nazwa1=nazwa;
			dostepnosc=new JTextArea("Dostępność:");
			dostepnosc.setOpaque(false);
			dostepnosc.setFocusable(false);
			dostepnosc.setBackground(new Color(255,255,255,0));
			this.add(dostepnosc);
			
			
			
			
			nazwaLabel=new JLabel(nazwa);
			
			nazwaLabel.setFocusable(false);
			nazwaLabel.setBackground(new Color(255,255,255,0));
			nazwaLabel.setOpaque(false);
			nazwaLabel.setHorizontalAlignment(JTextField.CENTER);
			this.add(nazwaLabel);
			
			
			
			zdjeciee=new ImageIcon(getClass().getClassLoader().getResource("rev.jpg"));
			newImage = zdjeciee.getImage().getScaledInstance(150, 130, Image.SCALE_DEFAULT);;
			zdjecieskalowane=new ImageIcon(newImage);
			opislabel=stworzOpis(nazwa);
			opislabel.setOpaque(false);
			zdjecie=new JLabel();
			
			zdjecie.setIcon(zdjecieskalowane);
			zdjecie.setBorder(BorderFactory.createLineBorder(Color.black));
			this.setLayout(null);
			
			
			
			//this.add(opislabel);
			this.add(zdjecie);
			//int iloscBaza=Main.getIlosc(nazwa);			taaaak bylo
			
			iloscBazaMapaPut(nazwa, iloscBaza);
			//JLabel opislabel=new JLabel(opis);
			
			this.setOpaque(false);
			
			//this.setBackground(new Color(255,255,255,120));
			
			this.add(zatwierdz);
			NumberFormat format=NumberFormat.getNumberInstance();
			format.setMaximumIntegerDigits(4);
			NumberFormatter formatter=new NumberFormatter(format);
			iloscinputfield=new JFormattedTextField(formatter);
			
			iloscinput.put(nazwa, iloscinputfield);
			iloscinput.get(nazwa).setBackground(new Color(255,255,255,80));
			iloscinput.get(nazwa).setOpaque(false);
			iloscinput.get(nazwa).setForeground(Color.black);
			iloscinput.get(nazwa).setBorder(BorderFactory.createLineBorder(Color.black));
			iloscinput.get(nazwa).addKeyListener(new KeyAdapter() {
				public void keyPressed(KeyEvent e) {
					if(e.getKeyChar()>='0' && e.getKeyChar()<='9'||e.getKeyCode()==8||e.getKeyCode()==10) {
						
						iloscinput.get(nazwa).setEditable(true);
					}
					else {
						iloscinput.get(nazwa).setEditable(false);
				}
				}
		            
			});
			int ilosc=0;
			iloscMapaPut(nazwa, ilosc);

			//iloscinput.addPropertyChangeListener("value", hellou);
			//iloscinput.get(nazwa).setBounds(20, 172,70, 20);
			wyswietlProdukt=stworzPrzycisk("");
			listaInputyPut(nazwa,iloscinput.get(nazwa));
			this.add(listaInputyGet(nazwa));
			//panel.setBounds(10,20,250,250);
			polozenieSklep(nazwa);
			iteracja();
			przedmiotyAdd(nazwa);
			this.add(wyswietlProdukt);
			if(czyAdmin) {
				widokAdmina();
			}
		
	}
	
	private JLabel iloscWKoszyku(String iloscString) {
		JLabel ilosc=new JLabel(iloscString);
		ilosc.setBounds(125,35,65,25);
		ilosc.setOpaque(false);
		return ilosc;
	}
	private JTextArea stworzOpis(String nazwa) {
		JTextArea opis=new JTextArea();
		String opisstring=Main.getOpis(nazwa);
		opis.setText(opisstring);
		opis.setBounds(5,120,190,70);
		opis.setLineWrap(true);
		opis.setWrapStyleWord(true);
		opis.setBackground(new Color(255,255,255,0));
		opis.setFocusable(false);
		return opis;
		
	}
	private JButton stworzPrzycisk(String tekst) {
		JButton przycisk=new JButton(tekst);
		przycisk.setOpaque(false);
		przycisk.setContentAreaFilled(false);
		przycisk.setBorderPainted(false);
		przycisk.setBackground(new Color(255,255,255,0));
		return przycisk;
	}


	public static void Dodaj(String nazwa,PropertyChangeListener hellou) {
		iloscinput.get(nazwa).addPropertyChangeListener("value",hellou);
	}
	
	public void paintComponent(Graphics g) {
		Graphics2D g2d=(Graphics2D) g;
		RenderingHints rh = new RenderingHints(
	             RenderingHints.KEY_ANTIALIASING,
	             RenderingHints.VALUE_ANTIALIAS_ON);
	    g2d.setRenderingHints(rh);
		super.paintComponent(g2d);
		Color kolor1=new Color(255,255,255,125);
		g2d.setPaint(kolor1);
		g2d.fillRoundRect(0, 0, this.getWidth(), this.getHeight(),15,15);
		if(czyAdmin!=true) {
			Narysuj(g2d);
		}
		
		
		
		
	}
	public void Narysuj(Graphics2D g) {

		if(ekranPelny!=true) {
			g.setPaint(Color.white);
			g.fillRect(width/2,width/2+54,70,10);
			
			g.setPaint(Color.green);
			
			int elo=Math.round(70*iledostepne);
			if(elo>70) {
				elo=70;
			}
			g.fillRect(width/2, width/2+54, elo, 10);
			
			g.setPaint(Color.black);
			g.setStroke(new BasicStroke(2));
			for(int i=14;i<=70;i+=14) {
				g.drawLine(width/2-2+i,height/2+52,width/2-2+i,height/2+63);
			}
		}
		if(ekranPelny) {
			g.setPaint(Color.white);
			g.fillRect(width/2,height/2,70,10);
			
			g.setPaint(Color.green);
			
			int elo=Math.round(70*iledostepne);
			if(elo>70) {
				elo=70;
			}
			g.fillRect(width/2,height/2, elo, 10);
			
			g.setPaint(Color.black);
			g.setStroke(new BasicStroke(2));
			for(int i=14;i<=70;i+=14) {
				g.drawLine(width/2-2+i,height/2+9,width/2-2+i,height/2-3);
			}
		}
		
	}
	private void iloscBazaMapaPut(String nazwa, int ilosc) {
		Shop.iloscBazaMapaSet(nazwa, ilosc);
	}
	private void iloscMapaPut(String nazwa, int ilosc) {
		Shop.iloscMapaSet(nazwa, ilosc);
	}
	private void listaInputyPut(String nazwa, JFormattedTextField iloscinput) {
		Shop.setlistainputy(nazwa, iloscinput);
	}
	private JFormattedTextField listaInputyGet(String nazwa){
		return Shop.listainputyGet(nazwa);
	}
	private void przedmiotyAdd(String nazwa) {
		Shop.przedmiotySet(nazwa);
	}
	private void iteracja(){
		Shop.iteracja();
	}
	void usun(int ilosc) {
		//this.remove(iloscinputfield);
		//elo=iloscWKoszyku(String.valueOf(ilosc));
		//this.add(elo);
		iloscinputfield.setOpaque(false);
		iloscinputfield.setText(String.valueOf(ilosc));
		this.add(usunZKoszyka);
		this.remove(zatwierdz);
	}
	void dodaj() {
		this.add(iloscinputfield);
		iloscinputfield.setOpaque(false);
		//this.remove(elo);
		this.add(zatwierdz);
		this.remove(usunZKoszyka);
		repaint();
		validate();
	}
	void widokAdmina() {
		this.remove(iloscinputfield);
		this.remove(usunZKoszyka);
		this.remove(zatwierdz);
		System.out.println("widok admina "+String.valueOf(iloscBaza));
		elo=new JLabel();
		elo.setText(String.valueOf(iloscBaza));
		elo.setBounds(125,145,65,25);
		this.add(usun);
		usun.setBounds(120,175,70,20);
		this.add(elo);
		repaint();
		validate();
	}
	void nacisniete() {
		this.setSize(500,600);
		
	}
	void polozenieKoszyk(int x, int y) {
		
		
		newImage = zdjeciee.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);;
		zdjecieskalowane=new ImageIcon(newImage);
		zdjecie.setIcon(zdjecieskalowane);
		zdjecie.setBounds(20,5,x,y);
		nazwaLabel.setBounds(65,15,60,20);
		iloscinputfield.setBounds(130,15,60,20);
		this.add(zmienIlosc);
		zmienIlosc.setBounds(180,15,120,20);
		usunZKoszyka.setBounds(270,15,80,20);
		this.remove(wyswietlProdukt);
		ekranPelny=false;
		
		
	}
	void polozenieSklep(String nazwa,List<String> lista,String uzywane,List<String> lista2) {
		nazwaLabel.setBounds(70,127,70,30);
		iloscinput.get(nazwa).setBounds(20, 172,70, 20);
		dostepnosc.setBounds(22,150,70,30);
		zdjecie.setBounds(25,5,150,130);
		if(lista.contains(nazwa) || lista2.contains(nazwa)) {
			newImage = zdjeciee.getImage().getScaledInstance(150, 130, Image.SCALE_DEFAULT);;
			zdjecieskalowane=new ImageIcon(newImage);
			zdjecie.setIcon(zdjecieskalowane);
		}
		if(uzywane.equals(nazwa)){
			newImage = zdjeciee.getImage().getScaledInstance(150, 130, Image.SCALE_DEFAULT);;
			zdjecieskalowane=new ImageIcon(newImage);
			zdjecie.setIcon(zdjecieskalowane);
		}
		zatwierdz.setBounds(80, 160,115, 45);
		this.add(wyswietlProdukt);
		ekranPelny=false;
		this.setPreferredSizee(new Dimension(200,200));
	}
	void polozenieSklep(String nazwa) {
		nazwaLabel.setBounds(70,127,70,30);
		iloscinput.get(nazwa).setBounds(20, 172,70, 20);
		dostepnosc.setBounds(22,150,70,30);
		zdjecie.setBounds(25,5,150,130);
		zatwierdz.setBounds(80, 160,100, 45);
		wyswietlProdukt.setBounds(zdjecie.getBounds());
		this.setPreferredSizee(new Dimension(200,200));
	}
	void polozenieCalosc(int width,int height) {
		this.setPreferredSizee(new Dimension(width,height));
		System.out.println("wysokosc "+height);
		this.width=width;
		this.height=height;
		if(width>600||height>600) {
			zdjecie.setBounds(width/2-150,10,300,300);
			newImage = zdjeciee.getImage().getScaledInstance(300, 300, Image.SCALE_DEFAULT);;
			zdjecieskalowane=new ImageIcon(newImage);
			nazwaLabel.setBounds(width/2-50,320,100,20);
		}
		if(width==600 && height==600) {
			zdjecie.setBounds(width/2-125,10,250,250);
			newImage = zdjeciee.getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT);;
			zdjecieskalowane=new ImageIcon(newImage);
			nazwaLabel.setBounds(width/2-50,270,100,20);
		}
		ekranPelny=true;
		zdjecie.setIcon(zdjecieskalowane);
		dostepnosc.setBounds(width/2-85,height/2-3,100,20);
		iloscinput.get(nazwa1).setBounds(width/2-85,height/2+30,60,20);
		zatwierdz.setBounds(width/2-15,height/2+30,120,20);
		this.add(opislabel);
		opislabel.setBounds(20,height/2+50,width-40,height-(height/2+50));
	}
	public void setPreferredSizee(Dimension dim) {
		width=(int)dim.getWidth();
		height=(int)dim.getHeight();
		this.setPreferredSize(dim);
	}
}
