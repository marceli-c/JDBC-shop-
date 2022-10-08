import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class twojeKonto extends JPanel{

	Map<String,JTextArea> polaLista=new HashMap<String,JTextArea>();
	String[] polaNazwy=new String[]{"login","haslo","email"};
	List<JLabel> nazwyLista=new ArrayList<JLabel>();
	JButton zatwierdz;
	//Gradient gradient=new Gradient();
	//JPanel panel;
	twojeKonto(){
		polaLista.clear();
		int i=0;
		zatwierdz=new JButton("Zatwierdz");
		zatwierdz.setBackground(new Color(255,255,255,0));
		zatwierdz.setOpaque(false);
		zatwierdz.setBorderPainted(true);
		zatwierdz.setFocusable(false);
		zatwierdz.setBounds(400,460,90,25);
		zatwierdz.setContentAreaFilled(false);
		
		this.setOpaque(false);
		this.add(zatwierdz);
		//this.add(gradient);
		this.setPreferredSize(new Dimension(500,500));
		//this.setBounds(0,0,500,500);
		this.setLayout(null);
		
		
		int y=50;
		
		for(String linia:polaNazwy) {
			createLabel(linia,10,y);
			createTextArea(linia,50,y);
			this.add(polaLista.get(linia));
			this.add(nazwyLista.get(i));
			System.out.println("numer indeksu twojeKonto "+i);
			i++;
			y+=50;
		}
		y=0;
		
	}
	

	private void createTextArea(String nazwa,int x,int y) {
		JTextArea pole=new JTextArea();
		String syntax="select "+nazwa+" from uzytkownicy where login like '"+GUI.getUzytkownik()+"';";
		//pole.setOpaque(false);
		pole.setText(Main.getResultDB(nazwa, syntax));
		pole.setBounds(x,y,150,25);
		//pole.setBackground(new Color(255,255,255,255));
		pole.setForeground(Color.black);
		polaLista.put(nazwa,pole);
	}
	private void createLabel(String nazwa,int x,int y) {
		JLabel label=new JLabel(nazwa);
		label.setForeground(Color.black);
		label.setOpaque(false);
		label.setBounds(x,y,40,25);
		nazwyLista.add(label);
	}
	public void paintComponent(Graphics g) {
		Graphics2D g2d=(Graphics2D) g;
		g2d.setPaint(new Color(255,255,255,125));
		g2d.fillRect(0, 0, 500, 500);
	}
	public String getHaslo() {
		return polaLista.get("haslo").getText();
	}
	public String getLogin() {
		return polaLista.get("login").getText();
	}
	public String getEmail() {
		return polaLista.get("email").getText();
	}
}
