package dao;

//import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
//import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import beans.Aukiolo;

/**
 * <code>AukioloDAO</code> luokkaa k‰ytet‰‰n aukiolo-tietojen hakemiseen tietokannasta.
 * 
 * @author Team Yellow
 * @version 0.1
 */

public class aukioloDAO extends DAO  {


public static ArrayList<Aukiolo> haeKaikki() {
	
	/**
	 * T‰m‰ metodi hakee voimassa olevat aukioloajat kannasta.
	 * 
	 * @param aukiolo Lista, johon aukioloajat ker‰t‰‰n
	 * @return ArrayList, johon aukioloajat on ker‰tty.
	 */
		
		ArrayList<Aukiolo> aukiolo = new ArrayList<Aukiolo>();
		
		PreparedStatement haku = null;
		Connection yhteys = null;
		ResultSet tulokset = null;
		
		try {
			yhteys = getConnection();
			
			String sql = "select id, paiva, sisalto from AUKIOLOAJAT";
			haku = yhteys.prepareStatement(sql);
			tulokset = haku.executeQuery(sql);
			
			
			while(tulokset.next()) {
				int id = tulokset.getInt("id");
				String paiva = tulokset.getString("paiva");
				String sisalto = tulokset.getString("sisalto");
				
				
				Aukiolo a = new Aukiolo(id, paiva, sisalto);
				aukiolo.add(a);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("Tietokantahaku aiheutti virheen");
		}finally {
			close(tulokset, haku, yhteys);
			
			//LOPULTA AINA SULJETAAN YHTEYS
//			try {
//				if (yhteys != null && !yhteys.isClosed())
//					yhteys.close();
//			} catch(Exception e) {
//				System.out.println("Tietokantayhteys ei jostain syyst√§ suostu menem√§√§n kiinni.");
//			}
		}
		
		System.out.println("HAETTIIN TIETOKANNASTA Aukioloajat: " +aukiolo.toString());
		return aukiolo;
	}

	
	
}
