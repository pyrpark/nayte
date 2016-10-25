package dao;

import java.sql.Connection;
import java.sql.ResultSet;


/**
 * T‰m‰ luokka perii <code>DAO.java</code> luokalta mm. usernamen, passwordin, driverit ja urlin.
 * 
 * @author Team Yellow
 * @version 0.1
 */

public class muutaJuomaDAO extends DAO {
	
	/**
	 * <code>muutaJuomaDAO</code> yritt‰‰ muuttaa tietokannan tietoja juomien osalta SQL-lauseiden muodossa.
	 * 
	 * @author Team Yellow
	 * @version 0.1
	 */	
	
	public static void muutaJuoma(String juoma_nimi, int juoma_id, String juoma_hinta) {
		
		/**
		 * T‰m‰ metodi muuttaa juomien tietoja kannassa.
		 *
		 * @param ps Valmisteltu SQL-lause (PreparedStatement)
		 * @param yhteys Tietokantayhteys
		 * 
		 */
	
		
		java.sql.PreparedStatement ps = null;
		Connection yhteys = null;
	
		
		try {
			yhteys = getConnection();			
	
				ps = yhteys.prepareStatement("INSERT IGNORE INTO JUOMA (juoma_id, juoma_nimi, juoma_hinta) VALUES(?,?,?)");
				
		
				ps.setInt(1, juoma_id);
				ps.setString(2, juoma_nimi);
				ps.setString(3, juoma_hinta);
				
				
				int rivejaLisattu = ps.executeUpdate();
				if(rivejaLisattu == 0){
					System.out.println("Ei lis‰tty uutta juomaa. Tarkista kent‰t.");
				}else{
					System.out.println("Juoma lis‰tty.");
				}
		

		

		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("Tietokantahaku aiheutti virheen");
		}finally {
			close(ps, yhteys);
			
		}
		
	
	}
public static boolean poistaJuoma(int poistajuoma_id) {
	
	/**
	 * T‰m‰ metodi poistaa halutun juoman kannasta.
	 *
	 * @param ps Valmisteltu SQL-lause (PreparedStatement)
	 * @param yhteys Tietokantayhteys
	 * 
	 * @return onnistui Boolean-muuttuja, joka kertoo onnistuiko poisto vai ei.
	 */
	
	java.sql.PreparedStatement ps = null;
	Connection yhteys = null;
	boolean onnistui = false;
	

	
	try {
		yhteys = getConnection();
		yhteys.setAutoCommit(false);
		yhteys.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
		
		ps = yhteys.prepareStatement("DELETE FROM JUOMA WHERE juoma_id = '"+poistajuoma_id+"'");
		
		
		int rivejaLisattu = ps.executeUpdate();
		
		if(rivejaLisattu == 1){
			onnistui = true;
			
			yhteys.commit();
			yhteys.close();
		}else{
			onnistui = false;
			
			yhteys.rollback();
			yhteys.close();
		}
		

	} catch (Exception e) {
		e.printStackTrace();
		System.out.println("Tietokantahaku aiheutti virheen");
	}
	
	return onnistui;

}
	
}
