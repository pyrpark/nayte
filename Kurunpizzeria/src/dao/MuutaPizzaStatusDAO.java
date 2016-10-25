package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import beans.Aukiolo;

/**
 * T‰m‰ luokka perii <code>DAO.java</code> luokalta mm. usernamen, passwordin, driverit ja urlin.
 * 
 * @author Team Yellow
 * @version 0.1
 */

public class MuutaPizzaStatusDAO extends DAO {
	
	/**
	 * <code>muutaPizzaStatusDAO</code> yritt‰‰ muuttaa tietokannan tietoja tilauksessa olevan pizzan statuksen osalta.
	 * 
	 * @author Team Yellow
	 * @version 0.1
	 */	
	
	public boolean MuutaPizzaStatus(int sArvo){
		
		/**
		 * T‰m‰ metodi muuttaa tilatun pizzan statuksen.
		 *
		 * @param ps Valmisteltu SQL-lause (PreparedStatement)
		 * @param yhteys Tietokantayhteys
		 * 
		 * @return paluu Boolean, joka kertoo onnistuiko muutos vai ei
		 */ 
		
		boolean paluu = false;
		
		Statement haku = null;
		Connection yhteys = null;
		ResultSet tulokset = null;
		
		try {
			yhteys = getConnection();
			
			String sql = "UPDATE TILAUS SET status_id='2' WHERE tilaus_id = "+sArvo;
			// select lausekkeet muutettava preparedStatementeiksi
			haku = yhteys.prepareStatement(sql);
			tulokset = haku.executeQuery(sql);
			
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("Tietokantahaku aiheutti virheen");
		}finally {
			close(tulokset, haku, yhteys);
			
		}
		
		
		
		return paluu;
	}
	
}
