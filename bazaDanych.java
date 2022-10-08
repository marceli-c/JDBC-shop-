

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class bazaDanych {

	public static Connection polacz(String url,String login, String haslo)  {
			Connection con;
			try {
				con = DriverManager.getConnection(url,login,haslo);
				return con;
			} catch (SQLException e) {
				
				e.printStackTrace();
				return null;
			}
			
	}
	
	public static Statement stworzStatement(Connection con)  {
		Statement stat;
		try {
			stat = con.createStatement();
			return stat;
		} catch (SQLException e) {
			
			e.printStackTrace();
			return null;
		}
		
		
		
	}
	public static ResultSet doQuery(Statement stat, String query)  {
		ResultSet result;
		try {
			result = stat.executeQuery(query);
			return result;
		} catch (SQLException e) {
			
			e.printStackTrace();
			return null;
		}
		
	}
	public static String getRezultat(ResultSet resultset,String kolumna)  {
		String result="";
		try {
			while(resultset.next()) {
				result+=resultset.getString(kolumna);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return result;
	}
	public static int getIloscInt(ResultSet resultset) {
		int ilosc=0;
		try {
			while(resultset.next()) {
				ilosc+=resultset.getInt("ilosc");
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return ilosc;
	}
	public static void execute(Statement stat,String query) {
		try {
			stat.execute(query);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	public static int getLiczbaProduktow(ResultSet resultset) {
		int ilosc=0;
		try {
			while(resultset.next()) {
				ilosc++;
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return ilosc;
	}
	public static List<String> getProduktyLista(ResultSet resultset){
		String result="";
		List<String> produkty=new ArrayList<String>();
		try {
			while(resultset.next()) {
				result=resultset.getString("nazwa");
				produkty.add(result);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return produkty;
	}
	public static boolean administracja(ResultSet resultset) {
		boolean czy=false;
		try {
			while(resultset.next()) {
				czy=resultset.getBoolean("Admin");
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		System.out.println("wyswietlam czy"+czy);
		return czy;
		
	}
	
	
	
}
