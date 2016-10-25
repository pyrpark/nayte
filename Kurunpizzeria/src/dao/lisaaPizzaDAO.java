package dao;


import java.sql.BatchUpdateException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import beans.PizzaBean;
import beans.TayteBean;

/**
 * Tämä luokka perii <code>DAO.java</code> luokalta mm. usernamen, passwordin, driverit ja urlin.
 * 
 * @author Team Yellow
 * @version 0.1
 */

public class lisaaPizzaDAO extends DAO {
	
	/**
	 * <code>lisaaPizzaDAO</code> yrittää muuttaa tietokannan tietoja SQL-lauseiden muodossa lisätäkseen pizzan tietokantaan.
	 * 
	 * @param taytesql SQL-lause, jolla lisättävän pizzan tiedot haetaan.
	 * @author Team Yellow
	 * @version 0.1
	 */	
	
	String taytesql = null;


public boolean lisaaPizzav2(String uusi_nimi, double uusi_hinta, String uusi_koko, int uusi_id, ArrayList<Integer> taytteiden_idt, int laskuri){
	
	/**
	 * Tämä metodi lisää omistajan syöttämien arvojen mukaisen pizzan kantaan.
	 * 
	 * @param yhteys Tietokantayhteys-objekti
	 * @param lause Tietokantahakulause-objekti
	 * @param sql1 SQL-lause, joka lisää pizzan PIZZA-tauluun
	 * 
	 * @return onnistui Boolean-muuttuja, joka kertoo onnistuiko lisäys vai ei
	 */
	
	boolean onnistui = false;
	
	Connection yhteys = null;
//	ResultSet tulokset = null;
	PreparedStatement lause = null;
	
	try {
		yhteys = getConnection();
		yhteys.setAutoCommit(false);
		yhteys.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
		
		String sql1 = "INSERT INTO PIZZA (pizza_id, pizza_nimi, pizza_hinta, koko_id) VALUES ('"+uusi_id+"', '"+uusi_nimi+"', '"+uusi_hinta+"', '"+uusi_koko+"')";
		
		lause = yhteys.prepareStatement(sql1);		
		int onnistuneet = lause.executeUpdate(sql1);
		
		if(onnistuneet == 1){
			
			if(laskuri == 1){
				taytesql = "INSERT INTO PIZZA_TAYTE (pizza_id, tayte_id) VALUES ('"+uusi_id+"', '"+taytteiden_idt.get(0)+"')";
				lause = yhteys.prepareStatement(taytesql);
				onnistuneet = lause.executeUpdate(taytesql);
				
			}else if(laskuri == 2){
				taytesql = "INSERT INTO PIZZA_TAYTE (pizza_id, tayte_id) VALUES ('"+uusi_id+"', '"+taytteiden_idt.get(0)+"')";
				String taytesql2 = "INSERT INTO PIZZA_TAYTE (pizza_id, tayte_id) VALUES ('"+uusi_id+"', '"+taytteiden_idt.get(1)+"')";
				
				
				try {
					
					lause.addBatch(taytesql);
					lause.addBatch(taytesql2);
					
					int results2[] = lause.executeBatch();
					
					if(results2.length == 2){
						onnistuneet = 1;
					}else{
						onnistuneet = 0;
					}
					
				//Tarkistaa toteutuvatko lausekkeet
				} catch (BatchUpdateException ex) {
					int insertCount[] = ex.getUpdateCounts();
					
					int count = 1;
					
					for (int i : insertCount){
						if(i == PreparedStatement.EXECUTE_FAILED){
							System.out.println("Virhe käsittelypyynnössä "+count+": Riviä ei käsitelty.");
						}else{
							System.out.println("Käsittelypyyntö "+count+" OK.");
						}
						count++;
					}
				}

			}else if(laskuri == 3){
				taytesql = "INSERT INTO PIZZA_TAYTE (pizza_id, tayte_id) VALUES ('"+uusi_id+"', '"+taytteiden_idt.get(0)+"')";
				String taytesql2a = "INSERT INTO PIZZA_TAYTE (pizza_id, tayte_id) VALUES ('"+uusi_id+"', '"+taytteiden_idt.get(1)+"')";
				String taytesql3a = "INSERT INTO PIZZA_TAYTE (pizza_id, tayte_id) VALUES ('"+uusi_id+"', '"+taytteiden_idt.get(2)+"')";
				
				try {
					lause.addBatch(taytesql);
					lause.addBatch(taytesql2a);
					lause.addBatch(taytesql3a);
					
					int results3[] = lause.executeBatch();
					
					if(results3.length == 3){
						onnistuneet = 1;
					}else{
						onnistuneet = 0;
					}
					
				//Tarkistaa toteutuvatko lausekkeet
				} catch (BatchUpdateException ex) {
					int insertCount[] = ex.getUpdateCounts();
					
					int count = 1;
					
					for (int i : insertCount){
						if(i == PreparedStatement.EXECUTE_FAILED){
							System.out.println("Virhe käsittelypyynnössä "+count+": Riviä ei käsitelty.");
						}else{
							System.out.println("Käsittelypyyntö "+count+" OK.");
						}
						count++;
					}
				}
			
				
			}else if (laskuri == 4){
				taytesql = "INSERT INTO PIZZA_TAYTE (pizza_id, tayte_id) VALUES ('"+uusi_id+"', '"+taytteiden_idt.get(0)+"')";
				String taytesql2b = "INSERT INTO PIZZA_TAYTE (pizza_id, tayte_id) VALUES ('"+uusi_id+"', '"+taytteiden_idt.get(1)+"')";
				String taytesql3b = "INSERT INTO PIZZA_TAYTE (pizza_id, tayte_id) VALUES ('"+uusi_id+"', '"+taytteiden_idt.get(2)+"')";
				String taytesql4 = "INSERT INTO PIZZA_TAYTE (pizza_id, tayte_id) VALUES ('"+uusi_id+"', '"+taytteiden_idt.get(3)+"')";
				
				try {
					lause.addBatch(taytesql);
					lause.addBatch(taytesql2b);
					lause.addBatch(taytesql3b);
					lause.addBatch(taytesql4);
					
					int results4[] = lause.executeBatch();
					
					if(results4.length == 4){
						onnistuneet = 1;
					}else{
						onnistuneet = 0;
					}
					
				//Tarkistaa toteutuvatko lausekkeet
				} catch (BatchUpdateException ex) {
					int insertCount[] = ex.getUpdateCounts();
					
					int count = 1;
					
					for (int i : insertCount){
						if(i == PreparedStatement.EXECUTE_FAILED){
							System.out.println("Virhe käsittelypyynnössä "+count+": Riviä ei käsitelty.");
						}else{
							System.out.println("Käsittelypyyntö "+count+" OK.");
						}
						count++;
					}
				}
								
			}else if(laskuri == 5){
				taytesql = "INSERT INTO PIZZA_TAYTE (pizza_id, tayte_id) VALUES ('"+uusi_id+"', '"+taytteiden_idt.get(0)+"')";
				String taytesql2b = "INSERT INTO PIZZA_TAYTE (pizza_id, tayte_id) VALUES ('"+uusi_id+"', '"+taytteiden_idt.get(1)+"')";
				String taytesql3b = "INSERT INTO PIZZA_TAYTE (pizza_id, tayte_id) VALUES ('"+uusi_id+"', '"+taytteiden_idt.get(2)+"')";
				String taytesql4 = "INSERT INTO PIZZA_TAYTE (pizza_id, tayte_id) VALUES ('"+uusi_id+"', '"+taytteiden_idt.get(3)+"')";
				String taytesql5 = "INSERT INTO PIZZA_TAYTE (pizza_id, tayte_id) VALUES ('"+uusi_id+"', '"+taytteiden_idt.get(4)+"')";
				
				try {
					lause.addBatch(taytesql);
					lause.addBatch(taytesql2b);
					lause.addBatch(taytesql3b);
					lause.addBatch(taytesql4);
					lause.addBatch(taytesql5);
					
					int results4[] = lause.executeBatch();
					
					if(results4.length == 5){
						onnistuneet = 1;
					}else{
						onnistuneet = 0;
					}
					
				//Tarkistaa toteutuvatko lausekkeet
				} catch (BatchUpdateException ex) {
					int insertCount[] = ex.getUpdateCounts();
					
					int count = 1;
					
					for (int i : insertCount){
						if(i == PreparedStatement.EXECUTE_FAILED){
							System.out.println("Virhe käsittelypyynnössä "+count+": Riviä ei käsitelty.");
						}else{
							System.out.println("Käsittelypyyntö "+count+" OK.");
						}
						count++;
					}
				}
				
			}
			
	
			lause.close();
		}
				
		if(onnistuneet == 1){
			
			
			yhteys.commit();
			System.out.println("Commit suoritettu kantaan.");
			
			yhteys.close();
			System.out.println("Suljetaan yhteys.");
			
		}else{
			
			
			yhteys.rollback();
			System.out.println("Rollback suoritettu.");
			
			yhteys.close();
			System.out.println("Yhteys suljetaan.");
		}
		
		
		onnistui = true;

	} catch(Exception e) {
		e.printStackTrace();
		System.out.println("Tietokantahaku aiheutti virheen");
	}
	
	return onnistui;
	
}

	
}