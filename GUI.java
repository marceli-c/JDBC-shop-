

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class GUI extends JFrame implements ActionListener{
	
	private JButton rejestracja=stworzPrzycisk("Rejestracja",230,240,100,20);
	private JTextField logininput=stworzInput(130,170,200,20);
	private JPasswordField hasloinput=stworzPasswordInput(130,200,200,20);
	private boolean pokazHasloBoolean=true;
	private JTextField emailinput=stworzInput(130,230,200,20);
	private JButton loginbutton=stworzPrzycisk("Login",110,240,80,20);
	private JLabel rejestracjalabel=stworzLabel("Nowy?",210,240,50,20);
	private JButton register=stworzPrzycisk("Stworz konto",245,240,90,20);
	private JLabel ikonamail=stworzLabel(128,225,25,25,"ikonamail.png");
	
	private JLabel ikonalogin=stworzLabel(128,165,25,25,"ikonalogin.png");
	
	private JLabel ikonahaslo=stworzLabel(128,194,25,25,"lock.png");
	private JButton anuluj=stworzPrzycisk("Anuluj",260,270,60,20);
	private JLabel Tytul=stworzLabel("Zaloguj",130,120,200,40);
	private JButton pokazHaslo=stworzPrzycisk("",315,200,15,15);
	private static String uzytkownik;
	boolean przyciskrejestracja;
	int xprzyciskrejestracja;
	int yprzyciskrejestracja;
	int widthprzyciskrejestracja;
	int heightprzyciskrejestracja;
	Gradient panel=new Gradient();
	

	GUI(){
		przyciskrejestracja=false;
		Tytul.setFont(new Font("Verdana", Font.BOLD, 20));
		this.add(pokazHaslo);
		pokazHaslo.setIcon(new ImageIcon(getClass().getClassLoader().getResource("view.png")));
		this.setSize(470,470);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setTitle("Login/Register Platform");
		
		this.getContentPane().setForeground(Color.white);
		this.setLayout(null);
		this.add(Tytul);
		this.add(anuluj);
		anuluj.setVisible(false);
		Tytul.setForeground(Color.white);
		this.add(ikonamail);
		this.add(ikonalogin);
		this.add(ikonahaslo);
		ikonamail.setVisible(false);

		this.add(emailinput);

		emailinput.setVisible(false);
		this.add(rejestracja);
		rejestracja.setVisible(false);
		this.add(logininput);
		this.add(hasloinput);
		this.add(loginbutton);

		rejestracjalabel.setForeground(Color.gray);
		this.add(rejestracjalabel);
		this.add(register);
		this.add(panel);
		
		this.setVisible(true);
	}
	private JButton stworzPrzycisk(String tekst, int x,int y,int xx,int yy) {
		JButton przycisk=new JButton(tekst);
		przycisk.setBounds(x,y,xx,yy);
		przycisk.addActionListener(this);
		przycisk.setFocusable(false);
		przycisk.setBackground(new Color(0,0,0,0));
		przycisk.setForeground(Color.white);
		przycisk.setBorder(null);
		przycisk.setOpaque(false);
		przycisk.setContentAreaFilled(false);
		return przycisk;
	}
	private JLabel stworzLabel(String tekst,int x, int y,int xx,int yy) {
		JLabel label=new JLabel(tekst);
		label.setBounds(x,y,xx,yy);
		return label;
	}
	private JLabel stworzLabel(int x,int y,int xx,int yy,String path) {
		JLabel label=new JLabel(new ImageIcon(getClass().getClassLoader().getResource(path)));
		label.setBounds(x,y,xx,yy);
		return label;
	}
	private JTextField stworzInput(int x,int y,int xx,int yy) {
		JTextField tekstfield=new JTextField();
		tekstfield.setBounds(x,y,xx,yy);
		tekstfield.setBackground(new Color(0,0,0,0));
		tekstfield.setForeground(Color.white);
		tekstfield.setOpaque(false);
		tekstfield.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0,0,1,0,Color.white),
                BorderFactory.createEmptyBorder(0, 25, 0, 0)));
		
		
		return tekstfield;
	}
	private JPasswordField stworzPasswordInput(int x,int y,int xx,int yy) {
		JPasswordField passwordfield=new JPasswordField();
		passwordfield.setBounds(x,y,xx,yy);
		passwordfield.setBackground(new Color(0,0,0,0));
		passwordfield.setForeground(Color.white);
		passwordfield.setOpaque(false);
		passwordfield.setEchoChar('*');
		passwordfield.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0,0,1,0,Color.white),
                BorderFactory.createEmptyBorder(0, 25, 0, 0)));
		
		
		return passwordfield;
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==loginbutton) {
			//login="";
			//haslo="";
			String login=logininput.getText();
			String haslo=hasloinput.getText();
			if(Main.login(login, haslo)) {
				System.out.println("Zalogowales sie pomyslnie");
				uzytkownik=login;
				
				
				Main.czyAdmin(login,haslo);
				Main.zmienOkno();
				Main.okienko.validate();
				Main.okienko.repaint();
			}
			else {
				System.out.println("Podales zle haslo albo login");
				JOptionPane.showMessageDialog(null, "Podałeś złe hasło lub login", "Bład w logowaniu", JOptionPane.INFORMATION_MESSAGE);
			}
		}
		if(e.getSource()==rejestracja) {
			stworzKonto();
				
			}
			
			
		
		if(e.getSource()==register) {
			register();
		}
		if(e.getSource()==anuluj) {
			anuluj();
		}
		
			if(e.getSource()==pokazHaslo) {
				
				if(pokazHasloBoolean==true) {
					hasloinput.setEchoChar((char) 0);
					pokazHasloBoolean=false;
				}
				else if(pokazHasloBoolean==false) {
					hasloinput.setEchoChar('*');	
					pokazHasloBoolean=true;
				}
			}
		
	}

	private void register() {
		xprzyciskrejestracja=rejestracja.getX();
		yprzyciskrejestracja=rejestracja.getY();
		widthprzyciskrejestracja=rejestracja.getWidth();
		heightprzyciskrejestracja=rejestracja.getHeight();
		//this.remove(loginbutton);
		loginbutton.setVisible(false);
		rejestracja.setBounds(rejestracja.getX()-loginbutton.getWidth()-20,rejestracja.getY()+30,rejestracja.getWidth(),rejestracja.getHeight());
		emailinput.setVisible(true);
		//emaillabel.setVisible(true);
		//this.add(emailinput);
		//this.add(emaillabel);
		register.setVisible(false);
		rejestracjalabel.setVisible(false);
		rejestracja.setVisible(true);
		Tytul.setText("Stwórz konto");
		ikonamail.setVisible(true);
		anuluj.setVisible(true);
		pokazHaslo.setVisible(false);
		hasloinput.setEchoChar((char) 0);
		repaint();
		
		przyciskrejestracja=true;
	}
	private void stworzKonto() {
		String login=logininput.getText();
		String haslo=hasloinput.getText();
		String mail=emailinput.getText();
		Main.rejestracja(login, haslo, mail);
		przyciskrejestracja=false;
		//this.add(loginbutton);
		loginbutton.setVisible(true);
		rejestracja.setBounds(xprzyciskrejestracja,yprzyciskrejestracja,widthprzyciskrejestracja,heightprzyciskrejestracja);
		emailinput.setVisible(false);
		//emaillabel.setVisible(false);
		register.setVisible(true);
		rejestracjalabel.setVisible(true);
		Tytul.setText("Zaloguj");
		//this.remove(emailinput);
		//this.remove(emaillabel);
		ikonamail.setVisible(false);
		rejestracja.setVisible(false);
		anuluj.setVisible(false);
		pokazHaslo.setVisible(true);
		hasloinput.setEchoChar('*');
		repaint();
		
		//panel.repaint();
	}
	private void anuluj() {
		loginbutton.setVisible(true);
		rejestracja.setBounds(xprzyciskrejestracja,yprzyciskrejestracja,widthprzyciskrejestracja,heightprzyciskrejestracja);
		emailinput.setVisible(false);
		//emaillabel.setVisible(false);
		register.setVisible(true);
		rejestracjalabel.setVisible(true);
		Tytul.setText("Zaloguj");
		ikonamail.setVisible(false);
		anuluj.setVisible(false);
		rejestracja.setVisible(false);
		pokazHaslo.setVisible(true);
		hasloinput.setEchoChar('*');
		repaint();
	}
	public static String getUzytkownik() {
		
		return uzytkownik;
	}
	public boolean czyAdmin() {
		if(uzytkownik.equals("root")) {
			return true;
		}
		else {
			return false;
		}
	}
	
}
