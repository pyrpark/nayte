package dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
 * <code>DAO</code> luokkaa k‰ytet‰‰n tietokantayhteyden muodostamiseen.
 * 
 * @author Team Yellow 
 * @version 0.1
 */

public class DAO {

	
	protected static Connection getConnection() {
		
		/**
		 * T‰m‰ metodi hoitaa yhteyden muodostuksen. Kirjautumiseen tarvittavat tiedot saadaan ACCOUNTS-luokasta.
		 * 
		 * @param yhteys  Yhteys-objekti
		* @param url  kirjautumis osoite
		* @param password kirjautumis salasana
		* @param driver k‰ytett‰v‰ ajuri
		* @param username Kirjautumiseen k‰ytett‰v‰ k‰ytt‰j‰nimi
		* @return yhteys Yhteys-objekti
		 */
		
		Connection yhteys = null;
		
		
		String url = ACCOUNTS.url;
		String password = ACCOUNTS.password;
		String driver = ACCOUNTS.driver;
		String username = ACCOUNTS.username;

		
		try {
			Class.forName(driver).newInstance();
			
			//avataan yhteys
			yhteys = DriverManager.getConnection(url, username, password);
			
		} catch (Exception e) {
			System.out.println("Virhe tietokantayhteyden muodostuksessa");
			throw new RuntimeException(e);
		}
		return yhteys;
	}
	
	protected static void close(Statement stmt, Connection connection) {
		
		/**
		 * T‰m‰ metodi hoitaa yhteyden sulkemisen.
		 */
		
		close (null, stmt, connection);
	}
	
	protected static void close(ResultSet rs, Statement stmt, Connection conn ) {
		
		/**
		 * T‰m‰ metodi hoitaa yhteyden sulkemisen.
		 */
		
		try {
			if (rs != null) { 
				rs.close();
			}
			if (stmt != null) { 
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	
	
	
}
