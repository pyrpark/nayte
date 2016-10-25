package dao;

import java.sql.Connection;
import java.sql.ResultSet;


/**
 * T‰m‰ luokka perii <code>DAO.java</code> luokalta mm. usernamen, passwordin, driverit ja urlin.
 * 
 * @author Team Yellow
 * @version 0.1
 */

public class muutaAukioloDAO extends DAO {
	
	/**
	 * <code>muutaAukioloDAO</code> yritt‰‰ muuttaa tietokannan tietoja SQL-lauseiden muodossa.
	 * 
	 * @author Team Yellow
	 * @version 0.1
	 */	
	
public boolean muutaAukiolo(String sisalto, int id) {
	
	/**
	 * T‰m‰ metodi muuttaa aukioloajat kantaan.
	 *
	 * @param onnistui Boolean, joka kertoo onnistuiko lis‰ys vai ei
	 * @param ps Valmisteltu SQL-lause (PreparedStatement)
	 * @param yhteys Tietokantayhteys
	 * 
	 * @return onnistui Boolean, joka kertoo onnistuiko lis‰ys vai ei
	 */

		boolean onnistui = false;
	
		java.sql.PreparedStatement ps = null;
		Connection yhteys = null;
		
		
		try {
			yhteys = getConnection();
			ps = yhteys.prepareStatement("UPDATE AUKIOLOAJAT SET sisalto=? WHERE id=?");
			
			
			ps.setString(1, sisalto);
			ps.setInt(2, id);
			
			
			int rivejaLisattu = ps.executeUpdate();
			if(rivejaLisattu == 0){
				System.out.println("Ei p‰ivitetty aukioloaikoja.");
			}else{
				System.out.println("Aukioloajat p‰ivitetty.");
				onnistui = true;
			}

		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("Tietokantahaku aiheutti virheen");
		}finally {
			close(ps, yhteys);
			
		}
		
		return onnistui;
	}
	
}
