package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import beans.AsiakasBean;
import beans.PizzaBean;
import beans.AdminBean;

public class LoginDAO extends DAO {
	
	
	/**
	 * <code>LoginDAO</code> hoitaa kirjautumisen tietokantayhteyden toiminnot niin asiakkaan kuin omistajankin puolella.
	 * 
	 * @param tulokset Tietokantahaun tulokset
	 * @param yhteys Tietokantayhteys
	 * @param lauseke Tietokannan hakulauseke
	 * @param ps Tietokannan valmisteltu lauseke (PreparedStatement)
	 * 
	 * 
	 * @author Team Yellow
	 * @version 0.1
	 */	
	
	static ResultSet tulokset = null;
	static Connection yhteys = null;
	static Statement lauseke = null;
	static java.sql.PreparedStatement ps = null;
	

	public AsiakasBean kirjauduSisaan(String username, String password)
			throws SQLException {
		
		/**
		 * T‰m‰ metodi kirjaa asiakkaan sis‰‰n palveluun.
		 *
		 * @return asiakas Sis‰‰nkirjautuva asiakas
		 */

		System.out.println("Asiakaskirjautuminen -> siirryttiin kirjuaudSis‰‰n metodiin");

		//Muutetaan prepared statementiksi
		//String hakulause = "SELECT asiakas_id, etunimi, sukunimi, sahkoposti, postinro, osoite, puh_nro FROM ASIAKAS WHERE kayt_tun='asiakas' AND salasana='asiakas'";
		
		
		AsiakasBean asiakas = null;
		
		try {
			yhteys = getConnection();
			ps = yhteys.prepareStatement("SELECT asiakas_id, etunimi, sukunimi, sahkoposti, postinro, osoite, puh_nro FROM ASIAKAS WHERE kayt_tun=? AND salasana=?");
			ps.setString(1, username);
			ps.setString(2, password);	
			tulokset = ps.executeQuery();
			
			while(tulokset.next()){
				asiakas = new AsiakasBean();
				asiakas.seteNimi(tulokset.getString("etunimi"));
				asiakas.setsNimi(tulokset.getString("sukunimi"));
				asiakas.setsPosti(tulokset.getString("sahkoposti"));
				asiakas.setPostiNro(tulokset.getString("postinro"));
				asiakas.setOsoite(tulokset.getString("osoite"));
				asiakas.setPuhNro(tulokset.getString("puh_nro"));
				asiakas.setAsiakas_id(tulokset.getInt("asiakas_id"));
				
			}
	
		}catch (Exception e){
			e.printStackTrace();
			System.out.println("Tietokantahaku aiheutti virheen.");
		} finally{
			close(tulokset, lauseke, yhteys);
		}
		
		return asiakas;
		
		}
	
	
	public AdminBean adminKirjaudu(String username, String password)
			throws SQLException{
		
		/**
		 * T‰m‰ metodi kirjaa omistajan sis‰‰n henkilˆkunnan palveluun.
		 *
		 * @return admin Sis‰‰nkirjautuva omistaja
		 */
		
		System.out.println("Siirryttiin adminKirjaudu metodiin.");
		
		AdminBean admin = null;
		
		try {
			yhteys = getConnection();
			ps = yhteys.prepareStatement("SELECT omistaja_id, etunimi, sukunimi, kayt_tun, salasana FROM OMISTAJA WHERE kayt_tun=? AND salasana=?");
			ps.setString(1, username);
			ps.setString(2, password);
			tulokset = ps.executeQuery();
			
			while(tulokset.next()){
				
				admin = new AdminBean();
				admin.setEtunimi(tulokset.getString("etunimi"));
				admin.setSukunimi(tulokset.getString("sukunimi"));
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Tietokantahaku aiheutti virheen.");	
		}
		finally{
			
				close(tulokset, lauseke, yhteys);
			}
		return admin;
		
	}
	
}
