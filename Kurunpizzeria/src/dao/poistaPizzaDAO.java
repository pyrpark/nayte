package dao;


import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * Tämä luokka perii <code>DAO.java</code> luokalta mm. usernamen, passwordin, driverit ja urlin.
 * 
 * @author Team Yellow
 * @version 0.1
 */

public class poistaPizzaDAO extends DAO {
	
	/**
	 * <code>poistaPizzaDAO</code> yrittää poistaa tietokannan tietoja SQL-lauseiden muodossa.
	 * 
	 * @author Team Yellow
	 * @version 0.1
	 */	
	
	String poistosql = null;
	
public boolean poistaPizza(int poistettava_id) {
	
	/**
	 * Tämä metodi yrittää poistaa pizzan kannasta.
	 * 
	 * @param poistettava_id Poistettavan pizzan id
	 * @return onnistui Onnistuiko poisto vai ei
	 */

	//Metodin käyttämät muuttujat:
	
		int onnistuneet;
	
		boolean onnistui = false;
	
		PreparedStatement lause = null;
		Connection yhteys = null;
		
		//Alustetaan transaktio
		
		try {
			yhteys = getConnection();
			yhteys.setAutoCommit(false);
			yhteys.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
			
			/*Käydään läpi taulut järjestyksessä: TILAUS --> PIZZA_TAYTE --> PIZZA
			 * Jos tauluista TILAUS tai PIZZA_TAYTE ei löydy vastaavuuksia, ei ongelmia. Ainoastaan PIZZA taulussa
			 * on oltava haluttu poistettava ID.*/
			
				String poisto2 = "DELETE FROM TILATTU_PIZZA WHERE pizza_id = '"+poistettava_id+"'";
				
				System.out.println("---------------------------");
				System.out.println("Poistetaan seuraavalla lauseella: "+poisto2);
				
				lause = yhteys.prepareStatement(poisto2);
				
				onnistuneet = lause.executeUpdate(poisto2);
				System.out.println("Monta onnistui: "+onnistuneet);
								
			if(onnistuneet >= 0 && onnistuneet <= 5){				
				String poistosql2 = "DELETE FROM PIZZA_TAYTE WHERE pizza_id = '"+poistettava_id+"'";
				System.out.println("Ajetaan toinen poistolause: "+poistosql2);
				
				lause = yhteys.prepareStatement(poistosql2);
				
				onnistuneet = lause.executeUpdate(poistosql2);

				System.out.println("Montako poistoa onnistui: "+onnistuneet);
					
				
				}else{
					System.out.println("---------------------------");
					System.out.println("Poisto ei onnistunut!");
					yhteys.rollback();
					System.out.println("Rollback suoritettu.");
					yhteys.close();
					System.out.println("Yhteys suljetaan.");
				}
			
			if (onnistuneet >= 0 && onnistuneet <= 5){
				
				poistosql = "DELETE FROM PIZZA WHERE pizza_id = '"+poistettava_id+"'";
				System.out.println("Ajetaan toinen poistolause: "+poistosql);
				
				lause = yhteys.prepareStatement(poistosql);
				
				onnistuneet = lause.executeUpdate(poistosql);
			}else{
				System.out.println("---------------------------");
				System.out.println("Poisto ei onnistunut!");
				yhteys.rollback();
				System.out.println("Rollback suoritettu.");
				yhteys.close();
				System.out.println("Yhteys suljetaan.");
			}
					
			
			if(onnistuneet == 1){
				onnistui = true;
				
				yhteys.commit();
				System.out.println("Poisto commitoitu.");
				
				yhteys.close();
				System.out.println("Yhteys suljetaan.");
				
			}
			

		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("Tietokantahaku aiheutti virheen");
		}
		
		return onnistui;
	}

}
