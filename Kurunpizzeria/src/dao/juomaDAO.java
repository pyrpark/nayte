package dao;

//import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
//import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import beans.JuomaBean;
import beans.Juoma;


/**
 * <code>juomaDAO</code> - luokkaa k‰ytet‰‰n Juomien hakemiseen tietokannasta.
 * 
 * @author Team Yellow
 * @version 0.1
 */

public class juomaDAO extends DAO  {
	

public static ArrayList<Juoma> haeKaikki() {
	
	/**
	 * T‰m‰ metodi hakee kaikki juomat, mit‰ tietokannassa on.
	 * 
	 * @param juoma ArrayList, johon juomat lis‰t‰‰n k‰sittely‰ varten.
	 * @param haku Prepared SQL Statement-objekti
	 * @param yhteys Tietokantayhteys-objekti
	 * @param tulokset Tietokantahaun tulokset-objekti
	 * 
	 * @return juoma ArrayList, johon on lis‰tty kaikki kannassa olevat juomat.
	 */

		ArrayList<Juoma> juoma = new ArrayList<Juoma>();
		
		PreparedStatement haku = null;
		Connection yhteys = null;
		ResultSet tulokset = null;
		
		try {
			yhteys = getConnection();
			
			String sql = "select * from JUOMA";
			haku = yhteys.prepareStatement(sql);
			tulokset = haku.executeQuery(sql);
			
			
			while(tulokset.next()) {
				int juoma_id = tulokset.getInt("juoma_id");
				String juoma_nimi = tulokset.getString("juoma_nimi");
				double juoma_hinta = tulokset.getDouble("juoma_hinta");
				
				Juoma j = new Juoma();
				j.setJuoma_id(juoma_id);
				j.setJuoma_nimi(juoma_nimi);
				j.setJuoma_hinta(juoma_hinta);
				
				juoma.add(j);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("Tietokantahaku aiheutti virheen");
		}finally {
			close(tulokset, haku, yhteys);

		}
		
		System.out.println("HAETTIIN TIETOKANNASTA Juomat: " +juoma.toString());
		return juoma;
	}

	
	
}
