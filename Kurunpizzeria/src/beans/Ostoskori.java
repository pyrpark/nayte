package beans;

/**
 * <code>Ostoskori</code>-luokkaa k‰ytet‰‰n luomaan ostoskori,
 *  johon asiakas voi ker‰t‰ haluamiaan pizzoja ja juomia tilatakseen ne. 
 * 
 * @author Team Yellow
 * @version 0.1
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import dao.MenuDAO;

	public class Ostoskori {
		
		/**
		 * T‰m‰ konstruktori luo ostoskorin toimintaan vaadittavat objektit ja antaa niille arvot.
		 * 
		 * @param keratyt_pizzat  HashMap-lista k‰ytt‰j‰n ker‰‰mist‰ pizzoista.
		 * @param keratyt_juomat HashMap-lista k‰ytt‰j‰n ker‰‰mist‰ juomista.
		 */
		
	private static HashMap<Integer, PizzaBean> keratyt_pizzat;
	private static HashMap<Integer, JuomaBean> keratyt_juomat;


	
public static HashMap<Integer, JuomaBean> LisaaJuomaOstoskoriin(int juoma_id){
	
		if(keratyt_juomat==null){
			keratyt_juomat = new HashMap<>();
		}
				
		ArrayList<JuomaBean> juomat;
		JuomaBean juoma = new JuomaBean();

		juomat = MenuDAO.listaaJuomat();
				
		for(int i=0; i<juomat.size(); i++){
			if(juomat.get(i).getJuoma_id()==juoma_id){
				juoma = juomat.get(i);
				juoma.setMaara(1);
				
			System.out.println("juomat listassa");
			
			if(keratyt_juomat.size() > 0){
				Set<Integer> juomaids = keratyt_juomat.keySet();
				Iterator<Integer> itr = juomaids.iterator();
				int seuraavaJuomaId;
				
				
				while(itr.hasNext()){
					seuraavaJuomaId = itr.next();
					if(seuraavaJuomaId == juoma_id){
						System.out.println("Juoma lˆytyy jo ostoskorista, lis‰t‰‰n m‰‰r‰‰ yhdell‰");
						juoma.setMaara(keratyt_juomat.get(seuraavaJuomaId).getMaara()+1);
						System.out.println("Juoman " +juoma.getNimi()+" m‰‰r‰ on nyt: " + juoma.getMaara());
					}
				}		
			}	
		}	
		}

		System.out.println("For loopin j‰lkeen haettu juoma on " +juoma);

		keratyt_juomat.put(juoma.getJuoma_id(), juoma);
		
		System.out.println("Ker‰tyt juomat hashmap"+ keratyt_juomat);
				
		return keratyt_juomat;
			}
	
	
	
	
	public static HashMap<Integer, PizzaBean> LisaaPizzaOstoskoriin(int pizza_id, String mauste1, String mauste2, String fantasiaTaytteet){
		
		if(keratyt_pizzat==null){
			keratyt_pizzat = new HashMap<>();
		}
				
		ArrayList<PizzaBean> pizzat;
		PizzaBean pizza = new PizzaBean();

		pizzat = MenuDAO.listaaPizzat();
				
		for(int i=0; i<pizzat.size(); i++){
			if(pizzat.get(i).getPizza_id()==pizza_id){
				pizza = pizzat.get(i);
				pizza.setMaara(1);
				if(keratyt_pizzat.get(pizza_id) != null){
					pizza.setMauste1(keratyt_pizzat.get(pizza_id).getMauste1() + "|<br>|" + mauste1 + " " + mauste2 + " " +fantasiaTaytteet);
				}else{
					pizza.setMauste1(mauste1 + " " + mauste2 + " " +fantasiaTaytteet);
				}
				
			if(keratyt_pizzat.size() > 0){
				Set<Integer> pizzaids = keratyt_pizzat.keySet();
				Iterator<Integer> itr = pizzaids.iterator();
				int seuraavaPizzaId;
				
				
				while(itr.hasNext()){
					seuraavaPizzaId = itr.next();
					if(seuraavaPizzaId == pizza_id){
						System.out.println("Pizza lˆytyy jo ostoskorista, lis‰t‰‰n m‰‰r‰‰ yhdell‰");
						pizza.setMaara(keratyt_pizzat.get(seuraavaPizzaId).getMaara()+1);
						System.out.println("Pizzan " +pizza.getPizza_nimi()+" m‰‰r‰ on nyt: " + pizza.getMaara());
					}
				}		
			}	
		}	
		}

		System.out.println("For loopin j‰lkeen haettu pizza on " +pizza);

		keratyt_pizzat.put(pizza.getPizza_id(), pizza);
		
		System.out.println("Ker‰tyt pizzat hashmap"+ keratyt_pizzat);
				
		return keratyt_pizzat;
			}
		
		public static HashMap<Integer, PizzaBean> getKeratyt_pizzat(){
		return keratyt_pizzat;
			}
		
		public static HashMap<Integer, JuomaBean> getKeratyt_juomat(){
			return keratyt_juomat;
				}
		
		public static void tyjenna(){
			if(keratyt_pizzat != null){
				keratyt_pizzat.clear();
			}
			if(keratyt_juomat != null){
				keratyt_juomat.clear();
			}
	
		}

	@Override
	public String toString() {
		return "Ostoskori [getKeratyt_juomat()=" + /*getKeratyt_juomat()
				+*/ ", getKeratyt_pizzat()=" +"]";
	}
	
}