package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import beans.AsiakasBean;


import java.util.ArrayList;


/**
 * Tämä luokka perii <code>DAO.java</code> luokalta mm. usernamen, passwordin, driverit ja urlin.
 * 
 * @author Team Yellow
 * @version 0.1
 */

public class RekisterointiDAO extends DAO{
	
	/**
	 * <code>RekisterointiDAO</code> hoitaa rekisteröitymiseen liittyvän tietokantayhteyden.
	 * 
	 * @author Team Yellow
	 * @version 0.1
	 */	

	public void RekisteroiAsiakas (AsiakasBean bean) {
		
		/**
		 * Tämä metodi hoitaa asiakkaan rekisteröitymisen palveluun.
		 * 
		 * @param bean Rekisteröitävä asiakas
		 */
		
		java.sql.PreparedStatement ps = null;
		Connection yhteys = null;
		ResultSet tulokset = null;
		
		String eNimi = bean.geteNimi();
		String sNimi = bean.getsNimi();
		String sPosti = bean.getsPosti();
		String osoite = bean.getOsoite();
		String postiNro = bean.getPostiNro();
		String puhNro = bean.getPuhNro();
		String user = bean.getUser();
		String pw = bean.getPw();
		
		
		try {
			yhteys = getConnection();
			
			ps = yhteys.prepareStatement("INSERT INTO ASIAKAS (etunimi, sukunimi, sahkoposti, postinro , osoite, puh_nro,"
					+ "kayt_tun, salasana) VALUES (?,?,?,?,?,?,?,?)");
			
			ps.setString(1, eNimi);
			ps.setString(2, sNimi);
			ps.setString(3, sPosti);
			ps.setString(4, postiNro);
			ps.setString(5, osoite);
			ps.setString(6, puhNro);
			ps.setString(7, user);
			ps.setString(8, pw);
			
			int i = ps.executeUpdate();
			
			if (i == 0){
				//Rivejä lisätty 0
				System.out.println("Virhe! Tarkista kentät.");	
			}
			else{
				System.out.println("Rekisteröinti onnistui.");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Tietokantahaku aiheutti virheen.");
		}
		finally {
			close(tulokset, ps, yhteys);
	
		}
		
	}
	
	
	
	public ArrayList<String> PostiNroListaan (){
		
		/**
		 * Metodi yhdistää postinumeron ja -toimipaikan yhteen listaan.
		 * 
		 * @return PostiTmp ArrayList, jossa on asiakkaan kotiosoitteen postinumero ja -toimipaikka.
		 */
		
		ArrayList<String> PostiTmp = new ArrayList<String>();
		
		Statement haku = null;
		Connection yhteys = null;
		ResultSet tulokset = null;
		
		try {
			yhteys = getConnection();
			
			String sql = "select postinro from POSTITMP";
			haku = yhteys.prepareStatement(sql);
			tulokset = haku.executeQuery(sql);
			
			
			
			while(tulokset.next()) {
				String postiNro = tulokset.getString("postinro");
				
				
	
				PostiTmp.add(postiNro);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("Tietokantahaku aiheutti virheen");
		}finally {
			close(tulokset, haku, yhteys);
		
		}
		return PostiTmp;
		
		
		
	}
	
	
	
	
		public void LisaaPostiTmp (String postinro, String postiTmp){
			
			/**
			 * Metodi lisää postitiedot kantaan.
			 */
			
			java.sql.PreparedStatement ps = null;
			Connection yhteys = null;
			ResultSet tulokset = null;
		
			
			try {
				yhteys = getConnection();
				
				ps = yhteys.prepareStatement("INSERT INTO POSTITMP (postinro, postitmp) VALUES (?,?)");
				
				ps.setString(1, postinro);
				ps.setString(2, postiTmp);
				
				
				int i = ps.executeUpdate();
				
				if (i == 0){
					//Rivejä lisätty 0
					System.out.println("Virhe! Tarkista kentät.");	
				}
				else{
					System.out.println("Postinumero lisätty.");
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Tietokantahaku aiheutti virheen.");
			}
			finally {
				close(tulokset, ps, yhteys);
		
			}
		}

}
