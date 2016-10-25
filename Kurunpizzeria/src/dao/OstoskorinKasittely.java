package dao;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import beans.JuomaBean;
import beans.PizzaBean;

/**
 * Tämä luokka perii <code>DAO.java</code> luokalta mm. usernamen, passwordin, driverit ja urlin.
 * 
 * @author Team Yellow
 * @version 0.1
 */

public class OstoskorinKasittely {
	
	/**
	 * Tämä metodi hoitaa Ostoskorin käsittelyn
	 * 
	 * @param keratyt_pizzat Asiakkaan keräämät pizzat
	 * @param keratyt_juomat Asiakkaan keräämät juomat
	 * @return OstoskorinData Ostoskorin sisältö
	 */
	
	public HashMap<String, Double> LaskeOstoskorinHinta(HashMap<Integer, PizzaBean> keratyt_pizzat, HashMap<Integer, JuomaBean> keratyt_juomat){
		double paluu = 0;
		double juomatCounter = 0;
		double pizzatCounter = 0;
		double pizzatHinta = 0;
		double juomatHinta = 0;
		double hintaYhteensa = 0;
		int maara = 1;
		HashMap<String, Double> OstoskorinData = new HashMap<String, Double>();
		
		if(keratyt_pizzat!=null){
			Set<Integer> pizzaids = keratyt_pizzat.keySet();
			Iterator<Integer> itr = pizzaids.iterator();
			int seuraavaPizzaId;
			int pizza_id;
			
			while(itr.hasNext()){
				seuraavaPizzaId = itr.next();
				pizza_id = keratyt_pizzat.get(seuraavaPizzaId).getPizza_id();
				double hinta = keratyt_pizzat.get(pizza_id).getHinta();
				if(keratyt_pizzat.get(pizza_id).getMaara()>1){
					hinta = hinta * keratyt_pizzat.get(pizza_id).getMaara();
				}
				pizzatHinta = pizzatHinta + hinta;
				if(keratyt_pizzat.get(seuraavaPizzaId).getMaara()>1){
					maara = keratyt_pizzat.get(seuraavaPizzaId).getMaara();
				}
				pizzatCounter = pizzatCounter + (double) maara;
				maara = 1;
			}	
		}
		
		if(keratyt_juomat!=null){
			Set<Integer> juomaids = keratyt_juomat.keySet();
			Iterator<Integer> itr2 = juomaids.iterator();
			int seuraavaJuomaId;
			int juoma_id;
			
			while(itr2.hasNext()){
				seuraavaJuomaId = itr2.next();
				juoma_id = keratyt_juomat.get(seuraavaJuomaId).getJuoma_id();
				double hinta = keratyt_juomat.get(juoma_id).getHinta();
				if(keratyt_juomat.get(juoma_id).getMaara()>1){
					hinta = hinta * keratyt_juomat.get(juoma_id).getMaara();
				}
				juomatHinta = juomatHinta + hinta;
				if(keratyt_juomat.get(seuraavaJuomaId).getMaara()>1){
					maara = keratyt_juomat.get(seuraavaJuomaId).getMaara();
				}
				juomatCounter = juomatCounter + (double) maara;
				maara = 1;
			}	
		}
		
		hintaYhteensa = juomatHinta + pizzatHinta;
		
		OstoskorinData.put("hinta", hintaYhteensa);
		OstoskorinData.put("pizzojen_maara", pizzatCounter);
		OstoskorinData.put("juomien_maara", juomatCounter);
		
		return OstoskorinData;
	}
}
