

import java.sql.*;
import java.util.List;

import javax.swing.JFrame;

public class Main {

	static GUI okno;
	static JFrame okienko;
	static boolean czy=false;
	static Connection konekt;
	static Statement statement;
	static boolean czyAdminemJest;
	public static void main(String[] args) throws SQLException{
		
		
		
		
		czyAdminemJest=false;
		okno=new GUI();
		polaczDB();
		
		
		
		
		
		
	}
	public static void zmienOkno() {
		
			okno.dispose();
			okienko=new Shop();

	}
	
	
	private static void polaczDB() {
		
			konekt=bazaDanych.polacz("jdbc:mysql://localhost:3306/sklep", "admin", "admin");
			statement=bazaDanych.stworzStatement(konekt);
		
		
	}
	private static void transakcja(String uzytkownik,String nazwa,int ilosc) {
		String query="select ilosc from magazyn where nazwa like '"+nazwa+"';";
		int iloscbaza=0;
		
		ResultSet resultset=bazaDanych.doQuery(statement,query);
		iloscbaza=bazaDanych.getIloscInt(resultset);
		System.out.println("ilosc baza hello"+iloscbaza);
		System.out.println("ilosc  hello"+ilosc);
		if(iloscbaza>0 && ilosc<=iloscbaza) {
			iloscbaza-=ilosc;
		}
		String query2=" update magazyn set ilosc = "+iloscbaza+" where nazwa like '"+nazwa+"';";
		doQuery(query2);
	}
	private static List<String> getProduktyNazwyLista() {
		String query="Select * from magazyn;";
		ResultSet resultset=bazaDanych.doQuery(statement, query);
		List<String> lista=bazaDanych.getProduktyLista(resultset);
		return lista;
	}
	private static void doQuery(String query) {
		
			bazaDanych.execute(statement,query);
		
	}
	
	public static boolean login(String login,String haslo) {
		String rezultat="";
		boolean czy=false;
		String query="select * from uzytkownicy where login like "+"'"+login+"'";
		rezultat=getResultDB("haslo",query);
		if(haslo.equals(rezultat)) {
			czy=true;
		}
		return czy;
	}
	
	public static void rejestracja(String login,String haslo,String mail) {
		
		String query="insert into uzytkownicy(Login,Email,Haslo) values ('"+login+"','"+mail+"','"+haslo+"');";
		doQuery(query);
		
		
	}
	static String getResultDB(String kolumna,String query) {
		String rezultat="";
		
			ResultSet resultset=bazaDanych.doQuery(statement, query);
			rezultat=bazaDanych.getRezultat(resultset, kolumna);
		
		return rezultat;
	}
	public static int getIlosc(String nazwa) {
		String query="Select ilosc from magazyn where nazwa like '"+nazwa+"';";
		ResultSet resultset=bazaDanych.doQuery(statement, query);
		int ilosc=bazaDanych.getIloscInt(resultset);
		System.out.println("Oddaje ilosc ="+ilosc);
		return ilosc;
	}
	public static void AddItem(String nazwa,int ilosc) {
		String query="insert into magazyn(ilosc,nazwa) values("+ilosc+",'"+nazwa+"');";
		bazaDanych.execute(statement, query);
	}
	public static List<String> getProduktyNazwy() {
		return getProduktyNazwyLista();
	}
	private static String Opis(String nazwa) {
		String query="select opis from opisy where nazwa like '"+nazwa+"';";
		ResultSet resultset=bazaDanych.doQuery(statement, query);
		return bazaDanych.getRezultat(resultset, "opis");
		
	}
	public static String getOpis (String nazwa) {
		return Opis(nazwa);
	}
	public static void addItem(String nazwa,int ilosc,String opis) {
		String query="insert into magazyn(ilosc,nazwa) values("+ilosc+",'"+nazwa+"');";
		String query2="insert into opisy(nazwa,opis) values('"+nazwa+"','"+opis+"');";
		bazaDanych.execute(statement, query);
		bazaDanych.execute(statement, query2);
	}
	public static void wykonajTransakcje(String uzytkownik,String nazwa,int ilosc) {
		System.out.println("wykonuje transakcje dla "+uzytkownik+" przedmiot "+nazwa+" sztuk "+ilosc);
		transakcja(uzytkownik,nazwa,ilosc);
	}
	static void czyAdmin(String login, String haslo) {
		boolean czyAdmin=false;
		String query="select Admin from uzytkownicy where login like '"+login+"' and haslo like '"+haslo+"'";
		ResultSet resultset=bazaDanych.doQuery(statement, query);
		czyAdmin=bazaDanych.administracja(resultset);
		if(czyAdmin) {
			czyAdminemJest=true;
		}
		else {
			czyAdminemJest=false;
		}
		//czyAdminemJest=bazaDanych.administracja(resultset);
	}
	static boolean getAdmin() {
		return czyAdminemJest;
	}
	static void zmienDane(String login,String haslo,String email) {
		String user=GUI.getUzytkownik();
		String query="update uzytkownicy set login= '"+login+"', haslo= '"+haslo+"', email= '"+email+"'"
				+ " where login like '"+user+"';";
		bazaDanych.execute(statement, query);
	}
	static void usunPrzedmiot(String nazwa) {
		if(czyAdminemJest) {
			String query="delete from magazyn where nazwa like '"+nazwa+"';";
			bazaDanych.execute(statement, query);
		}
		
	}
}
