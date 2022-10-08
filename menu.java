

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextArea;

public class menu extends JPanel implements ActionListener{
	
	JButton uzytkownikprzycisk;
	JPopupMenu menu;
	JMenuItem twojProfil;
	JMenuItem wyloguj;
	JButton koszyk;
	JButton sklep;
	menu(){
		
		
		String uzytkownik=GUI.getUzytkownik();;
		
		sklep=createButton("Sklep");
		koszyk=createButton("Koszyk");
		wyloguj=createMenuItem("Wyloguj");
		twojProfil=createMenuItem("Twoje konto");
		menu=createUzytkownik("elo");
		if(uzytkownik!=null) {
			uzytkownikprzycisk=createButton(uzytkownik);
		}
		else {
			uzytkownikprzycisk=createButton("uzytkownik");
		}
		
		
		menu.add(twojProfil);
		menu.add(wyloguj);
		
		this.add(sklep);
		this.add(koszyk);
		this.add(uzytkownikprzycisk);
		
		this.setLayout(null);
		
		
		
		
		
		
		this.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
		
		this.setOpaque(false);
		
	}
	private JButton createButton(String tekst) {
		JButton  przycisk = new JButton (tekst);
		przycisk.setBounds(450,0,150,20);
		//label.setForeground(Color.black);
		przycisk.setPreferredSize(new Dimension(150,50));
		przycisk.setBackground(new Color(255,255,255,0));
		przycisk.setOpaque(false);
		przycisk.setFocusable(false);
		przycisk.setContentAreaFilled(false);
		przycisk.setBorderPainted(false);
		przycisk.addActionListener(this);
		return przycisk;
	}
	private JPopupMenu createUzytkownik(String tekst) {
		JPopupMenu menu=new JPopupMenu(tekst);
		
		
		//menu.setBounds(0,0,150,20);
		
		//menu.setEnabled(true);
		
		return menu;
	}
	public void paintComponent(Graphics g) {
		Graphics2D g2d=(Graphics2D)g;
		g2d.setPaint(new Color(255,255,255,125));
		g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
	}
	private JMenuItem createMenuItem(String tekst) {
		JMenuItem item=new JMenuItem(tekst);
		item.addActionListener(this);
		return item;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==twojProfil) {
			System.out.println("Pokazuje profil");
		}
		if(e.getSource()==wyloguj) {
			
			Main.okienko.dispose();
			Main.okno=new GUI();
			
		}
		
		
		if(e.getSource()==uzytkownikprzycisk) {
			popup();
		}
		
	}
	public void popup() {
		menu.show(uzytkownikprzycisk,
				15,uzytkownikprzycisk.getY()+25);
	}
	public void removeKoszyk() {
		this.remove(koszyk);
	}
}
