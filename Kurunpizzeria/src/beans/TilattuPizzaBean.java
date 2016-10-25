package beans;

/**
 * <code>TilattuPizza</code>-luokkaa k‰ytet‰‰n luomaan TilattuPizza-objekti,
 *  jolle voidaan v‰litt‰‰ tietokannasta haetut arvot.
 *  Objektia k‰ytet‰‰n tietyn tilauksen pizzan(tai pizzojen) m‰‰rittelyyn. 
 * 
 * @author Team Yellow
 * @version 0.1
 */

public class TilattuPizzaBean {
	
	/**
	 * T‰m‰ konstruktori luo tilatun pizzan tarvitsemat attribuutit ja antaa niille arvot.
	 * 
	 * @param tilaus_id  Tilaus, johon tilattu pizza liittyy
	 * @param pizza_id Pizza, joka on tilattu
	 * @param kommentti Mahdollisia asiakkaan toiveita sis‰lt‰v‰ attribuutti
	 * @param pizza_nimi Tilatun pizzan nimi
	 * @param maara Tilatun pizzan kappalem‰‰r‰
	 */
	
	private String tilaus_id, pizza_id, kommentti, pizza_nimi;
	private int maara;
	public TilattuPizzaBean(String tilaus_id, String pizza_id, String kommentti, String pizza_nimi, int maara) {
		super();
		this.tilaus_id = tilaus_id;
		this.pizza_id = pizza_id;
		this.kommentti = kommentti;
		this.pizza_nimi = pizza_nimi;
		this.maara = maara;
	}
	public TilattuPizzaBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getTilaus_id() {
		return tilaus_id;
	}
	public void setTilaus_id(String tilaus_id) {
		this.tilaus_id = tilaus_id;
	}
	public String getPizza_id() {
		return pizza_id;
	}
	public void setPizza_id(String pizza_id) {
		this.pizza_id = pizza_id;
	}
	public String getKommentti() {
		return kommentti;
	}
	public void setKommentti(String kommentti) {
		this.kommentti = kommentti;
	}
	public String getPizza_nimi() {
		return pizza_nimi;
	}
	public void setPizza_nimi(String pizza_nimi) {
		this.pizza_nimi = pizza_nimi;
	}
	public int getMaara() {
		return maara;
	}
	public void setMaara(int maara) {
		this.maara = maara;
	}
	@Override
	public String toString() {
		return "TilattuPizzaBean [tilaus_id=" + tilaus_id + ", pizza_id=" + pizza_id + ", kommentti=" + kommentti
				+ ", pizza_nimi=" + pizza_nimi + ", maara=" + maara + "]";
	}
	
	
	
}
