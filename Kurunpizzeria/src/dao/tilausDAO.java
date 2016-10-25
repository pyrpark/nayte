package dao;

import java.sql.BatchUpdateException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import beans.AsiakasBean;
import beans.Aukiolo;
import beans.JuomaBean;
import beans.PizzaBean;
import beans.TilattuJuomaBean;
import beans.TilattuPizzaBean;
import beans.TilausBean;



/**
* Tämä luokka perii <code>DAO.java</code> luokalta mm. usernamen, passwordin, driverit ja urlin.
 * 
 * @author Team Yellow
 * @version 1.0
 */
public class tilausDAO extends DAO {
	
	/**
	 * <code>tilausDAO</code> hoitaa tilauksiin liittyvät tietokantayhteydet.
	 * 
	 * @author Team Yellow
	 * @version 1.0
	 */	
	
	String sqllause = null;
	
	public boolean teeTilaus(HashMap<Integer, PizzaBean> keratyt_pizzat, AsiakasBean asiakas, HashMap<Integer, JuomaBean> keratyt_juomat, String lisatieto, int tilaustyyppi){
		
		/**
		 * Metodi hoitaa tilauksen tekemisen.
		 * 
		 */
		
		boolean onnistui = false;
		
		System.out.println("Ostoskorissa olevat pizzat joihin tilaus kohdistuu = " +keratyt_pizzat);
		System.out.println("Asiakas joka toimii tilaajana " + asiakas.geteNimi());
		
		// Näitä tarvitaan autoincrement hakua varten
		Statement haku = null;
		ResultSet tulokset = null;
		int id = 0;
		
		//Näitä käytetään kantaan tiedon syöttämistä varten
		PreparedStatement lause = null;
		Connection yhteys = null;
		int onnistuneet = 0;
		int counter = 0;
		int maara = 0;
		
		boolean ok;
		
		OstoskorinKasittely kasittely = new OstoskorinKasittely();
		HashMap <String, Double > ostoskorinTiedot =  kasittely.LaskeOstoskorinHinta(keratyt_pizzat, keratyt_juomat);
		
		double hinta = ostoskorinTiedot.get("hinta");
		int asiakas_id = asiakas.getAsiakas_id(); 
		
		try {
			yhteys = getConnection();
			
			// TÄHÄN INSERT INTO TILAUS 
			// Määritettävä vielä tiedot muuttujista (asiakas_id jne.....)
			String sql1 = "INSERT INTO TILAUS (tilaus_hinta, asiakas_id, status_id, tilaus_tyyppi, lisatieto) VALUES (?, ?, ?, ?, ?);";
			
			lause = yhteys.prepareStatement(sql1);
			//tilauksen hinta
			lause.setDouble(1, hinta);
			//tilauksen asiakas_id
			lause.setInt(2, asiakas_id);
			//tilauksen status id
			lause.setInt(3, 1);
			//tilauksen tilaus_tyyppi
			lause.setInt(4, tilaustyyppi);
			//tilauksen lisätieto
			lause.setString(5, lisatieto);				

			onnistuneet = lause.executeUpdate();
			
			// Tässä haetaan autoincrementoitu id tilauksesta	
			String haeAutoInc = "SELECT tilaus_id AS tilaus_id FROM TILAUS WHERE tilaus_id = @@Identity";
			haku = yhteys.prepareStatement(haeAutoInc);
			tulokset = haku.executeQuery(haeAutoInc);
			
			// viedään autoincrementoitu luku id muuttujaan
			while(tulokset.next()) {
				id = tulokset.getInt("tilaus_id");
				System.out.println("Autoincrementoitu luku kannasta on: " + id);
			}
			
			if(onnistuneet == 1){

			// muodostetaan tarvittavat lauseet muuttujiin	
				String tilattu_pizza = "";	
				String tilattu_juoma = "";	
			
				try {

					//Tarkistetaan onko kerättyjä pizzoja yli 0 ja luodaan tämän jälkeen Iterator
					
					if(keratyt_pizzat.size() > 0){
						Set<Integer> pizzaids = keratyt_pizzat.keySet();
						Iterator<Integer> itr = pizzaids.iterator();
						int seuraavaPizzaId;
						
						int pizza_id;
						
						
						while(itr.hasNext()){
							seuraavaPizzaId = itr.next();
							pizza_id = keratyt_pizzat.get(seuraavaPizzaId).getPizza_id();
							maara = keratyt_pizzat.get(seuraavaPizzaId).getMaara();
							String kommentti = "'" + keratyt_pizzat.get(seuraavaPizzaId).getMauste1()+ "'";
							System.out.println("Kommentti on: " +kommentti);
							// KOMMENTTI KENTTÄ LISÄTTÄVÄ PIZZA OLIOON MIHIN TALLENNETAAN MM. lisätäytteet tai valkosipuli jne.
							// kommentti = keratyt_pizzat.get(seuraavaPizzaId).getKOMMENTTI 
							tilattu_pizza = "INSERT INTO TILATTU_PIZZA (tilaus_id, pizza_id, kommentti, maara) VALUES ("+id+","+pizza_id+","+kommentti+", "+maara+")";
							lause.addBatch(tilattu_pizza);
							counter = counter + 1 ;
						}		
					}

					if(keratyt_juomat != null){
						if(keratyt_juomat.size() > 0){
							Set<Integer> juomaids = keratyt_juomat.keySet();
							Iterator<Integer> itr = juomaids.iterator();
							int seuraavaJuomaId;
							
							int juoma_id;
							String kommentti;
							
							while(itr.hasNext()){
								seuraavaJuomaId = itr.next();
								juoma_id = keratyt_juomat.get(seuraavaJuomaId).getJuoma_id();
								maara = keratyt_juomat.get(seuraavaJuomaId).getMaara();
								tilattu_juoma = "INSERT INTO TILATTU_JUOMA (tilaus_id, juoma_id, maara) VALUES ("+id+","+juoma_id+","+maara+")";
								lause.addBatch(tilattu_juoma);
								counter = counter + 1 ;
							}		
						}
					}
				

					int results2[] = lause.executeBatch();
					
					if(results2.length == counter){
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
				
			
					if(onnistuneet == 1){
						ok = true;
						
						yhteys.commit();
						System.out.println("Commit suoritettu kantaan.");
						
						yhteys.close();
						System.out.println("Suljetaan yhteys.");
						
					}else{
						ok = false;
						
						yhteys.rollback();
						System.out.println("Rollback suoritettu.");
						
						yhteys.close();
						System.out.println("Yhteys suljetaan.");
					}
				
				}
				
				onnistui = true;

				} catch(Exception e) {
					e.printStackTrace();
					System.out.println("Yhteydenmuodostuksessa virhe");
				}
			
			return onnistui;

		}
	
	
		public static ArrayList<TilausBean> haeTilaus(){
			
			/**
			 * Metodi hakee tietyn tilauksen tiedot kannasta.
			 * 
			 * @return tilaus ArrayList, johon talletetaan TilausBean-objekteja, joiden attribuutit tuodaan kannasta
			 */
			
			ArrayList<TilausBean> tilaus = new ArrayList<TilausBean>();

			Statement haku = null;
			Connection yhteys = null;
			ResultSet tulokset = null;
			
			try {
				yhteys = getConnection();
				
				String sql = "SELECT t.tilaus_id, t.tilaus_aika, t.asiakas_id, t.status_id, t.tilaus_tyyppi, t.lisatieto, t.tilaus_hinta, a.etunimi, a.sukunimi, a.sahkoposti, a.postinro, a.osoite, a.puh_nro "
						+ "FROM TILAUS t LEFT JOIN ASIAKAS a ON t.asiakas_id = a.asiakas_id;";
				
				haku = yhteys.prepareStatement(sql);
				tulokset = haku.executeQuery(sql);
				
				System.out.println("----------------");
				System.out.println("TILAUKSET");
				System.out.println("----------------");
				
				
				while(tulokset.next()) {
					
					TilausBean tBean = new TilausBean();
					tBean.setTilaus_id(tulokset.getString("tilaus_id"));
					tBean.setTilaus_aika(tulokset.getDate("tilaus_aika"));
					tBean.setAsiakas_id(tulokset.getString("asiakas_id"));
					tBean.setStatus_id(tulokset.getString("status_id"));
					tBean.setTilaus_tyyppi(tulokset.getString("tilaus_tyyppi"));
					tBean.setLisatieto(tulokset.getString("lisatieto"));
					tBean.setTilaus_hinta(tulokset.getDouble("tilaus_hinta"));
					tBean.setEtunimi(tulokset.getString("etunimi"));
					tBean.setSukunimi(tulokset.getString("sukunimi"));
					tBean.setSahkoposti(tulokset.getString("sahkoposti"));
					tBean.setPostinro(tulokset.getString("postinro"));
					tBean.setOsoite(tulokset.getString("osoite"));
					tBean.setPuh_nro(tulokset.getString("puh_nro"));

					tilaus.add(tBean);
					System.out.println(tBean);
				}
				
			} catch(Exception e) {
				e.printStackTrace();
				System.out.println("Tietokantahaku aiheutti virheen");
			}finally {
				close(tulokset, haku, yhteys);
			}
			
			return tilaus;
		}
		
		public static ArrayList<TilattuPizzaBean> haeTilatutPizzat(){
			
			/**
			 * Metodi, joka hakee tilaukseen liittyvät tilatut pizzat.
			 * 
			 * @return pizzat ArrayList, johon tallennetaan TilattuPizzaBean, jonka arvot haetaan kannasta.
			 */
			
			Statement haku = null;
			Connection yhteys = null;
			ResultSet tulokset = null;
			
			ArrayList<TilattuPizzaBean> pizzat = new ArrayList<TilattuPizzaBean>();
			
			try {
			yhteys = getConnection();
			
			String sql = "SELECT t.tilaus_id, t.pizza_id, t.maara, t.kommentti, p.pizza_nimi FROM TILATTU_PIZZA t LEFT JOIN PIZZA p ON t.pizza_id = p.pizza_id";
			
			haku = yhteys.prepareStatement(sql);
			tulokset = haku.executeQuery(sql);

			System.out.println("----------------");
			System.out.println("TILAUKSIIN LIITTYVÄT TILATUT PIZZAT");
			System.out.println("----------------");
			
			while(tulokset.next()) {
				
				TilattuPizzaBean pBean = new TilattuPizzaBean();
				pBean.setTilaus_id(tulokset.getString("tilaus_id"));
				pBean.setPizza_id(tulokset.getString("pizza_id"));
				pBean.setMaara(tulokset.getInt("maara"));
				pBean.setKommentti(tulokset.getString("kommentti"));
				pBean.setPizza_nimi(tulokset.getString("pizza_nimi"));

				pizzat.add(pBean);
				System.out.println(pBean);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("Tietokantahaku aiheutti virheen");
		}finally {
			close(tulokset, haku, yhteys);
		}
			
		return pizzat;
		}
		
		
		
		public static ArrayList<TilattuJuomaBean> haeTilatutJuomat(){
			
			/**
			 * Metodi, joka hakee tilaukseen liittyvät tilatut juomat.
			 * 
			 * @return juomat ArrayList, johon tallennetaan TilattuJuomaBean, jonka arvot haetaan kannasta.
			 */
			
			ArrayList<TilattuJuomaBean> juomat = new ArrayList<TilattuJuomaBean>();
			
			Statement haku = null;
			Connection yhteys = null;
			ResultSet tulokset = null;
			
			try {
			yhteys = getConnection();
			
			String sql = "SELECT t.tilaus_id, t.juoma_id, t.maara, j.juoma_nimi FROM TILATTU_JUOMA t LEFT JOIN JUOMA j ON t.juoma_id = j.juoma_id";
			
			haku = yhteys.prepareStatement(sql);
			tulokset = haku.executeQuery(sql);

			System.out.println("----------------");
			System.out.println("TILAUKSIIN LIITTYVÄT TILATUT JUOMAT");
			System.out.println("----------------");
			
			while(tulokset.next()) {
				
				TilattuJuomaBean jBean = new TilattuJuomaBean();
				
				jBean.setJuoma_nimi(tulokset.getString("juoma_nimi"));
				jBean.setTilaus_id(tulokset.getString("tilaus_id"));
				jBean.setJuoma_id(tulokset.getString("juoma_id"));
				jBean.setMaara(tulokset.getInt("maara"));
				
				juomat.add(jBean);
				System.out.println(jBean);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("Tietokantahaku aiheutti virheen");
		}finally {
			close(tulokset, haku, yhteys);
		}

			return juomat;
		}
	
				
				
				
				
				
				
				
				
				
				
				
	}
