



import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.text.MaskFormatter;
import javax.swing.text.NumberFormatter;


public class Shop extends JFrame implements PropertyChangeListener,ActionListener{
	
	//-------------------------------------------DEKLARACJE-------------------------------------------------------//
	boolean czyAdministracja;
	static int i=0;
	//final private String uzytkownik=GUI.getUzytkownik();
	private Gradient gradient=new Gradient();
	private static Map<String,Integer> iloscMapa=new HashMap<String,Integer>();
	private static Map<String,JFormattedTextField> listainputy=new HashMap<String,JFormattedTextField>();
	private static Map <String, Integer> iloscBazaMapa=new HashMap<String,Integer>();
	private static List <String> przedmioty=new ArrayList<String>();
	private Map<String,Item> panele=new HashMap<String,Item>();
	private List<String> nazwyBaza=new ArrayList<String>();
	private List<addItem> paneleDodaj=new ArrayList<addItem>();
	private List<String> Koszyk=new ArrayList<String>();
	private Map<String,Integer> KoszykIlosc=new HashMap<String,Integer>();
	private List<String> KoszykUsuniete=new ArrayList<String>();
	private addItem panelDodaj;
	JScrollPane gradientscroll;
	twojeKonto konto;
	menu menu;
	String opisDodanie;
	int liczbaDodanie;
	boolean koszykActive;
	private static String uzywane="";
	JButton przyciskKup;
	
	
	//-------------------------------------------DEKLARACJE-------------------------------------------------------//

	Shop(){
		koszykActive=false;
		menu=new menu();
		czyAdministracja=Main.getAdmin();
		gradient.setOpaque(false);
		gradient.add(menu);
		menu.koszyk.addActionListener(this);
		menu.sklep.addActionListener(this);
		System.out.println("Czy jest adminem? "+czyAdministracja );
		menu.twojProfil.addActionListener(this);
		
		
		this.setTitle("Sklep");
		
		
		dodajElementy();
		if(czyAdministracja) {
			wyswietlanieAdmina();
		}
		gradienty();
		
		panel();
		
		
		konto=new twojeKonto();
		konto.zatwierdz.addActionListener(this);
		przyciskKup=stworzPrzycisk(this.getWidth()-50,this.getHeight()-50,"KUP");
		
	}
	private void newItem() {
		panelDodaj=new addItem();
		panelDodaj.przyciskDodaj.addActionListener(this);
		panelDodaj.przyciskZatwierdz.addActionListener(this);
		paneleDodaj.add(panelDodaj);
	}
	private JButton stworzPrzycisk(int x,int y,String tekst) {
		JButton przycisk=new JButton(tekst);
		przycisk.setBounds(x,y,100,20);
		przycisk.setBackground(new Color(255,255,255,0));
		przycisk.setBorderPainted(false);
		przycisk.setFocusable(false);
		przycisk.setOpaque(false);
		przycisk.addActionListener(this);
		return przycisk;
		
	}
	
	
	private void gradienty() {
		
		gradient.setOpaque(false);
		//gradient.add(paneleDodaj.get(0));
		gradient.setLayout(new WrapLayout());
		gradientscroll=new JScrollPane(gradient);
		gradientscroll.setAutoscrolls(true);
		gradientscroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		//gradientscroll.setOpaque(false);
	}
	
	
	private void panel() {
		this.setMinimumSize(new Dimension(700,700));
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(rootPane);
		this.add(gradientscroll);
		this.setSize(700,700);
		zmienWielkosc();
		this.setVisible(true);
		this.addComponentListener(new ComponentAdapter() 
		{  
	        public void componentResized(ComponentEvent evt) {
	            Component c = (Component)evt.getSource();
	            int x=c.getWidth();
	            
	            validate();
	            zmienWielkosc();
	        }
	       

	});
	}
	
	
	
	private void dodajElementy() {
		panele.clear();
		nazwyBaza=Main.getProduktyNazwy();
		przedmioty.clear();
		for(String linia:nazwyBaza) {
			przedmioty.add(linia);
			panele.put(linia,new Item("",linia));
			panele.get(linia).zatwierdz.addActionListener(this);
			panele.get(linia).zmienIlosc.addActionListener(this);
			panele.get(linia).wyswietlProdukt.addActionListener(this);
			if(czyAdministracja) {
				panele.get(linia).widokAdmina();
				panele.get(linia).usun.addActionListener(this);
				panele.get(linia).remove(panele.get(linia).zatwierdz);
			}
		}
		for(String linia:przedmioty) {
			panele.get(linia).Dodaj(linia, this);
			
			gradient.add(panele.get(linia));
		}
		
	}
	
	
	
	private void oswiezElementy(String nazwa) {
			nazwyBaza=Main.getProduktyNazwy();
			System.out.println("oswiezElementy nazwyBaza " +nazwyBaza);
			panele.put(nazwa, new Item("chuj",nazwa));
			panele.get(nazwa).Dodaj(nazwa,this);
			gradient.add(panele.get(nazwa));
	
		
	}
	
	
	private JButton przyciskDodaj() {
		JButton przycisk=new JButton();
		return przycisk;
	}
	
	
	//--------------------------WYKONYWANIE AKCJI-------------------------------------//
	
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		Object source= evt.getSource();
		Pattern patern = Pattern.compile("\\d+");
		System.out.println("propertyChange przedmioty "+przedmioty);
			for(String linia:przedmioty){		
				if(source==listainputy.get(linia)) {
				sprawdzIlosc(linia,patern);	
				}
			}
		

	}
	public void sprawdzIlosc(String nazwa,Pattern p) {
		Matcher m=p.matcher(listainputy.get(nazwa).getText());
		if(m.matches())
		{
			
				
				if(listainputy.get(nazwa).getText()!="") {
					
					if(Integer.parseInt(listainputy.get(nazwa).getText())>iloscBazaMapa.get(nazwa)) {
						
						
						listainputy.get(nazwa).setText(String.valueOf(iloscBazaMapa.get(nazwa)));
						System.out.println("liczba w sprawdz ilosc "+iloscBazaMapa.get(nazwa));
						repaint();
					}
				}
				
			
		}
		
		
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Pattern patern = Pattern.compile("\\d+");
		
		
			
		if(czyAdministracja) {
			akcjeAdmina(e);
		}
	
		for(String linia:przedmioty){	
			
			if(czyAdministracja!=true) {
				if(e.getSource()==panele.get(linia).zatwierdz) {
					
					sprawdzIlosc(linia,patern);	
					Koszyk.add(linia);
					
					if(KoszykIlosc.get(linia)!=null) {
						KoszykIlosc.put(linia, Integer.parseInt(panele.get(linia).iloscinputfield.getText())+KoszykIlosc.get(linia));
					}
					else {
						KoszykIlosc.put(linia, Integer.parseInt(panele.get(linia).iloscinputfield.getText()));
					}
					System.out.println("ilosc w koszu "+KoszykIlosc.get(linia));
					
					break;
					//new Transakcja(uzytkownik,linia,Integer.parseInt(panele.get(linia).iloscinputfield.getText()));
				}
				if(e.getSource()==panele.get(linia).wyswietlProdukt) {
					pokazPrzedmiot(linia);
					uzywane=linia;
					break;
				}
			}
			
			
			if(e.getSource()==panele.get(linia).usun) {
				
				USUN(linia);
				gradient.removeAll();
				gradient.add(menu);
				dodajElementy();
				
				gradient.add(paneleDodaj.get(0));
				repaint();
				validate();
				break;
			}
			
		}
		int s=0;
		for(String linia:Koszyk) {
			
			if(e.getSource()==panele.get(linia).usunZKoszyka) {
				Koszyk.remove(s);
				KoszykIlosc.remove(linia);
				s=0;
				pokazKoszyk();
				panele.get(linia).dodaj();
				KoszykUsuniete.add(linia);
				break;
			}
			s++;
			if(e.getSource()==panele.get(linia).zmienIlosc) {
				zmienIlosc(linia);
			}
		}
		
		if(e.getSource()==menu.koszyk) {
			pokazKoszyk();
			for(String linia:Koszyk) {
				panele.get(linia).usun(KoszykIlosc.get(linia));
			}
		}
		if(e.getSource()==menu.sklep) {
			pokazSklep();
			for(String linia:Koszyk) {
				panele.get(linia).dodaj();
			}
		}
		if(e.getSource()==menu.twojProfil) {
			gradient.removeAll();
			gradient.add(menu);
			gradient.add(konto);
			validate();
			repaint();
		}
		if(e.getSource()==konto.zatwierdz){
			zmienDaneKonto();
		}
		if(e.getSource()==przyciskKup) {
			
			String uzytkownik=GUI.getUzytkownik();
			for(String linia:Koszyk) {
				
				new Transakcja(uzytkownik,linia,KoszykIlosc.get(linia));
			}
			
		}
		
		
	}
	
	//----------------------------KONIEC AKCJI-------------------------------------//
	
	
	public static void iloscBazaMapaSet(String nazwa,int ilosc) {
		iloscBazaMapa.put(nazwa,ilosc);
	}
	public static void iloscMapaSet(String nazwa,int ilosc) {
		iloscMapa.put(nazwa,ilosc);
	}
	public static void przedmiotySet(String nazwa) {
		przedmioty.add(nazwa);
	}
	public static void iteracja() {
		i++;
	}
	public static JFormattedTextField listainputyGet(String nazwa){
		return listainputy.get(nazwa);
	}
	public static void setlistainputy(String nazwa,JFormattedTextField iloscinput) {
		listainputy.put(nazwa, iloscinput);
	}

	public void zmienWielkosc() {
		menu.setPreferredSize(new Dimension(this.getWidth()-25,25));
		menu.setSize(this.getWidth()-25,25);
		menu.uzytkownikprzycisk.setBounds(this.getWidth()-135,0,110,25);
		menu.koszyk.setBounds(this.getWidth()-200,0,75,25);
		menu.sklep.setBounds(10,0,70,25);
		if(koszykActive) {
			for(String linia:Koszyk) {
				panele.get(linia).setPreferredSizee(new Dimension(this.getWidth()-50,50));
			}
		}
	}
	public void pokazKoszyk() {
			koszykActive=true;
			gradient.removeAll();
			gradient.add(menu);
			zapelnijKoszyk();
			for(String linia:Koszyk) {
				panele.get(linia).setPreferredSizee(new Dimension(this.getWidth()-50,50));
				panele.get(linia).polozenieKoszyk(40,40);
			}
			if(Koszyk.size()>0) {
				
				gradient.add(przyciskKup);
			}
			
			validate();
			repaint();
			
		
	}
	public void zapelnijKoszyk() {
		for(String linia:Koszyk) {
			
			gradient.add(panele.get(linia));
			panele.get(linia).usunZKoszyka.addActionListener(this);
		}
	}
	public void pokazSklep() {
		koszykActive=false;
		gradient.removeAll();
		gradient.add(menu);
		
		for(String linia:przedmioty) {
			panele.get(linia).validate();
			panele.get(linia).iloscinputfield.setText("");
			panele.get(linia).polozenieSklep(linia,Koszyk,uzywane,KoszykUsuniete);
			gradient.add(panele.get(linia));
			
		}
		if(czyAdministracja) {
			
			gradient.add(paneleDodaj.get(0));
			
		}
		
		validate();
		repaint();
		
	}
	private void wyswietlanieAdmina() {
		newItem();	
		menu.removeKoszyk();
		gradient.add(paneleDodaj.get(0));
		
	}
	private void akcjeAdmina(ActionEvent e) {
		if(czyAdministracja) {
		for(int i=0;i<=paneleDodaj.size()-1;i++) {
			if(e.getSource()==paneleDodaj.get(i).przyciskDodaj) {
				paneleDodaj.get(i).zmiana();
				this.repaint();
				this.validate();
			}
			if(e.getSource()==paneleDodaj.get(i).przyciskZatwierdz) {
				
				String nazwa=paneleDodaj.get(i).getNazwa();
				String opis=paneleDodaj.get(i).getOpis();
				int ilosc=paneleDodaj.get(i).getIlosc();
				Main.addItem(nazwa, ilosc, opis);
				
				gradient.remove(paneleDodaj.get(i));						
				oswiezElementy(nazwa);
				addItem panelDodaj2=new addItem();
				
				panelDodaj2.przyciskDodaj.addActionListener(this);
				panelDodaj2.przyciskZatwierdz.addActionListener(this);
				paneleDodaj.add(panelDodaj2);
				gradient.add(paneleDodaj.get(paneleDodaj.size()-1));
				
				this.repaint();
			}
		}
		}
	}
	
	private void zmienDaneKonto() {
		String login=konto.getLogin();
		String haslo=konto.getHaslo();
		String email=konto.getEmail();
		Main.zmienDane(login, haslo, email);
	}
	private void zmienIlosc(String nazwa) {
		int ilosc=Integer.parseInt(panele.get(nazwa).iloscinputfield.getText());
		KoszykIlosc.replace(nazwa, ilosc);
		
	}
	private void pokazPrzedmiot(String nazwa) {
		
		gradient.removeAll();
		gradient.add(menu);
		
		int x=this.getWidth();
		//panele.get(nazwa).setPreferredSize(new Dimension(x,this.getHeight()-100));
		panele.get(nazwa).polozenieCalosc(this.getWidth()-100,this.getHeight()-100);
		gradient.add(panele.get(nazwa));
		repaint();
		validate();
	}
	private void USUN(String nazwa) {
		Main.usunPrzedmiot(nazwa);
	}
	
}
