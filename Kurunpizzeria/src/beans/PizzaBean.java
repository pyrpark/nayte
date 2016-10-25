package beans;

/**
 * <code>Pizza</code>-luokkaa k�ytet��n luomaan Pizza-objekti,
 *  jolle voidaan sy�tt�� tietokannasta haetut arvot.
 *  Pizza-muuttujaa k�ytet��n, my�s uusien arvojen sy�tt�miseen tietokantaan. 
 * 
 * @author Team Yellow
 * @version 0.1
 */

public class PizzaBean {
	
	/**
	 * T�m� konstruktori luo pizzan tarvitsemat attribuutit ja antaa niille arvot.
	 * 
	 * @param pizza_nimi  Pizzan nimi
	 * @param hinta Pizzan hinta (euroissa)
	 * @param koko Pizzan koko (normaali tai perhe)
	 * @param pizza_id Pizzan tunnus
	 * @param maara Monta kpl kyseist� pizzaa asiakas on tilannut
	 * @param mauste1 Attribuutti, joka s�ilytt�� mausteet ja Fantasia-pizzan t�ytteet.
	 */
	
	private String pizza_nimi;
	private double hinta;
	private int koko;
	private int pizza_id;
	private int maara;
	//mauste 1 k�ytet��n mauste1, mauste2 ja fantasian t�ytteiden s�ilytt�miseen
	private String mauste1 ="";;
	
	public String getMauste1() {
		return mauste1;
	}

	public void setMauste1(String mauste1) {
		this.mauste1 = mauste1;
	}

	public PizzaBean(String pizza_nimi, double hinta, int koko, int pizza_id,
			int maara) {
		super();
		this.pizza_nimi = pizza_nimi;
		this.hinta = hinta;
		this.koko = koko;
		this.pizza_id = pizza_id;
		this.maara = maara;
	}

	public int getMaara() {
		return maara;
	}
	
	public void setMaara(int maara) {
		this.maara = maara;
	}
	
	public PizzaBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	


	public String getPizza_nimi() {
		return pizza_nimi;
	}




	public void setPizza_nimi(String pizza_nimi) {
		this.pizza_nimi = pizza_nimi;
	}



	public double getHinta() {
		return hinta;
	}



	public void setHinta(double hinta) {
		this.hinta = hinta;
	}



	public int getKoko() {
		return koko;
	}



	public void setKoko(int koko) {
		this.koko = koko;
	}



	public int getPizza_id() {
		return pizza_id;
	}



	public void setPizza_id(int pizza_id) {
		this.pizza_id = pizza_id;
	}

	@Override
	public String toString() {
		return "PizzaBean [pizza_nimi=" + pizza_nimi + ", hinta=" + hinta + ", koko=" + koko + ", pizza_id=" + pizza_id
				+ ", maara=" + maara + ", mauste1=" + mauste1 + "]";
	}

	
	

}
