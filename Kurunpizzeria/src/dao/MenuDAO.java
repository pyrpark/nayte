package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Locale;

import beans.JuomaBean;
import beans.PizzaBean;
import beans.TayteBean;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class MenuDAO extends DAO {
	
	/**
	 * <code>MenuDAO</code> mahdollistaa pizza- ja juomalistan tulostamisen asiakkaan sek‰ omistajan puolella.
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
	static PreparedStatement ps = null;
	
public static ArrayList<PizzaBean> listaaPizzat(){
	
	/**
	 * T‰m‰ metodi listaa kaikki pizzat.
	 *
	 * @return pizzat ArrayList olemassaolevista pizzoista.
	 */
	
	ArrayList<PizzaBean> pizzat = new ArrayList<PizzaBean>();
	try {
		yhteys = getConnection();
		String hakulause = "SELECT * FROM PIZZA ORDER BY CAST(pizza_id as int) ASC";
			lauseke = yhteys.prepareStatement(hakulause);
		tulokset = lauseke.executeQuery(hakulause);
		
		while(tulokset.next()){
			PizzaBean pizza = new PizzaBean();
			pizza.setPizza_id(tulokset.getInt("pizza_id"));
			pizza.setPizza_nimi(tulokset.getString("pizza_nimi"));
			pizza.setHinta(tulokset.getDouble("pizza_hinta"));
			pizza.setKoko(tulokset.getInt("koko_id"));
			pizzat.add(pizza);
		}
		
	}catch (Exception e){
		e.printStackTrace();
		System.out.println("Tietokantahaku aiheutti virheen.");
	} finally{
		close(tulokset, lauseke, yhteys);
	}
		return pizzat;
	}

public static ArrayList<TayteBean> listaaTaytteet(){
	
	/**
	 * T‰m‰ metodi listaa kaikki t‰ytteet.
	 *
	 * @return taytteet ArrayList olemassaolevista t‰ytteist‰.
	 */
	
	ArrayList<TayteBean> taytteet = new ArrayList<TayteBean>();
	
	try {
		yhteys = getConnection();
		String hakulause = "SELECT * FROM PIZZA_TAYTE p JOIN TAYTTEET t ON t.tayte_id = p.tayte_id order by cast(pizza_id as int) ASC";
			lauseke = yhteys.prepareStatement(hakulause);
		tulokset = lauseke.executeQuery(hakulause);
		
		while(tulokset.next()){
			TayteBean tayte = new TayteBean();
			tayte.setTayte_id(tulokset.getString("tayte_id"));
			tayte.setPizza_id(tulokset.getString("pizza_id"));
			tayte.setNimi(tulokset.getString("tayte_nimi"));
			taytteet.add(tayte);
		}
		
	}catch (Exception e){
		e.printStackTrace();
		System.out.println("Tietokantahaku aiheutti virheen.");
		
	} finally{
		close(tulokset, lauseke, yhteys);
	}
	return taytteet;
	
}

public static ArrayList<JuomaBean> listaaJuomat(){
	
	/**
	 * T‰m‰ metodi listaa kaikki juomat.
	 *
	 * @return juomat ArrayList olemassaolevista juomista.
	 */
	
	ArrayList<JuomaBean> juomat = new ArrayList<JuomaBean>();
	
	try {
		yhteys = getConnection();
		String hakulause = "SELECT * FROM JUOMA";
			lauseke = yhteys.prepareStatement(hakulause);
		tulokset = lauseke.executeQuery(hakulause);
		
		while(tulokset.next()){
			JuomaBean juoma = new JuomaBean();
			juoma.setJuoma_id(tulokset.getInt("juoma_id"));
			juoma.setNimi(tulokset.getString("juoma_nimi"));
			juoma.setHinta(tulokset.getDouble("juoma_hinta"));
			juomat.add(juoma);
		}
		
	}catch (Exception e){
		e.printStackTrace();
		System.out.println("Tietokantahaku aiheutti virheen.");
		
	} finally{
		close(tulokset, lauseke, yhteys);
	}
	return juomat;
}

public static PizzaBean HaeTiettyPizza(int pizza_id){
	
	/**
	 * T‰m‰ metodi mahdollistaa tietyn pizzan haun.
	 *
	 * @return pizza Haettava pizza.
	 */
	
	PizzaBean pizza = new PizzaBean();
	
	try {
		yhteys = getConnection();
		String hakulause = "SELECT * FROM PIZZA WHERE pizza_id = "+pizza_id;
		System.out.println("SQL HAKULAUSEKE TIETYN PIZZAN HAKUUN : " + hakulause);
		// select lausekkeet muutettava preparedStatementeiksi
		lauseke = yhteys.prepareStatement(hakulause);
		tulokset = lauseke.executeQuery(hakulause);
		
		while(tulokset.next()){
			pizza.setPizza_id(tulokset.getInt("pizza_id"));
			pizza.setPizza_nimi(tulokset.getString("nimi"));
			pizza.setHinta(tulokset.getDouble("hinta"));
			pizza.setKoko(tulokset.getInt("koko_id"));	
		}
	
		System.out.println(pizza);
	
	}catch (Exception e){
		e.printStackTrace();
		System.out.println("Tietokantahaku aiheutti virheen.");
	} finally{
		close(tulokset, lauseke, yhteys);
	}
	return pizza;	
}

public static ArrayList<String> teePizzat(){
	
	/**
	 * T‰m‰ metodi "valmistaa" pizzan niin, ett‰ se voidaan n‰ytt‰‰ ruokalistassa.
	 *
	 * @param pizzat Olemassa olevat pizzat
	 * @param taytteet Olemassa olevat t‰ytteet
	 * @param tayte Pizzaan liittyv‰t t‰ytteet
	 * @param pizzatvalmis ArrayList, johon valmiit pizzat kootaan.
	 * @param valmispizza String, joka sijoitetaan pizzatvalmis -listaan.
	 * 
	 * @return pizzatvalmis ArrayList, jossa valmiit pizzat ovat
	 */
	
	ArrayList<PizzaBean> pizzat = listaaPizzat();
	ArrayList<TayteBean> taytteet = listaaTaytteet();
	String tayte = "";
	ArrayList<String> pizzatvalmis = new ArrayList<String>();
	String valmispizza = "";
		
	for(int i=0;i<pizzat.size();i++){
		
		int pizza_id = pizzat.get(i).getPizza_id();
		
		
		String pizzaid = Integer.toString(pizza_id);
		
		String pizzanimi = pizzat.get(i).getPizza_nimi();
		
		double HiNtA = pizzat.get(i).getHinta();
		
		String hinta = Double.toString(HiNtA);
		
		for (int j = 0; j < taytteet.size(); j++) {
		
			
					if(taytteet.get(j).getPizza_id().equalsIgnoreCase(pizzaid)){
				tayte = tayte+ " | " + taytteet.get(j).getNimi()+" | ";	
					
			}
						
		}
		
		valmispizza = pizzaid+ ". " +pizzanimi+" // "+hinta+" e //"+System.lineSeparator()+""+System.lineSeparator()+" "+tayte+" ";
		
		System.out.println("---------------------");
		System.out.println(valmispizza);
		System.out.println("---------------------");
		
		pizzatvalmis.add(valmispizza);
	
		tayte="";

		}
	return pizzatvalmis;
	
}

public static ArrayList<TayteBean> TayteLista(){
	
	/**
	 * T‰m‰ metodi listaa t‰ytteet tayte_id:n mukaiseen j‰rjestykseen.
	 *
	 * @return TayteLista ArrayList olemassaolevista t‰ytteist‰.
	 */
	
	ArrayList<TayteBean> TayteLista = new ArrayList<TayteBean>();
	
	try {
		yhteys = getConnection();
		String hakulause = "SELECT * FROM TAYTTEET ORDER BY CAST(tayte_id AS int) ASC";
			lauseke = yhteys.prepareStatement(hakulause);
		tulokset = lauseke.executeQuery(hakulause);
		
		while(tulokset.next()){
			TayteBean tayte = new TayteBean();
			tayte.setTayte_id(tulokset.getString("tayte_id"));
//			tayte.setPizza_id(tulokset.getString("pizza_id"));
			tayte.setNimi(tulokset.getString("tayte_nimi"));
			TayteLista.add(tayte);
		}
		
	}catch (Exception e){
		e.printStackTrace();
		System.out.println("Tietokantahaku aiheutti virheen.");
		
	} finally{
		close(tulokset, lauseke, yhteys);
	}
	return TayteLista;
	
}

public static ArrayList<String> teeJuomat(){
	
	/**
	 * T‰m‰ metodi "valmistaa" juomat niin, ett‰ se voidaan n‰ytt‰‰ ruokalistassa.
	 *
	 * @param juomat Olemassa olevat juomat
	 * @param juomatvalmis ArrayList, johon valmiit juomat sijoitetaan
	 * @param valmisjuoma String, joka sijoitetaan juomatvalmis -listaan.
	 * 
	 * @return juomatvalmis ArrayList, jossa valmiit pizzat ovat
	 */
	
	ArrayList<JuomaBean> juomat = listaaJuomat();
	ArrayList<String> juomatvalmis = new ArrayList<String>();
	String valmisjuoma = "";
		
	for(int i=0;i<juomat.size();i++){
		
		int juomaid = juomat.get(i).getJuoma_id();
		
		
		String juoma_id = Integer.toString(juomaid);
		
		String nimi = juomat.get(i).getNimi();
		
		double hinta = juomat.get(i).getHinta();
		
		
		
		
						
		
		
		valmisjuoma = juoma_id+ ". " +nimi+" // "+hinta+" e //";
		
		System.out.println("---------------------");
		System.out.println(valmisjuoma);
		System.out.println("---------------------");
		
		juomatvalmis.add(valmisjuoma);
	
		
		}
		
	return juomatvalmis;
	
}

}
