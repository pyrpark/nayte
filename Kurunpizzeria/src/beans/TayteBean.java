package beans;

/**
 * <code>TayteBean</code>-luokkaa käytetään luomaan Tayte-objekti,
 *  jolle voidaan syöttää tietokannasta haetut arvot.
 *  TayteBean-muuttujaa käytetään, myös uusien arvojen syöttämiseen tietokantaan. 
 * 
 * @author Team Yellow
 * @version 0.1
 */

public class TayteBean {
	
	/**
	 * Tämä konstruktori luo täytteen tarvitsemat attribuutit ja antaa niille arvot.
	 * 
	 * @param tayte_id  Täytteen tunnus
	 * @param nimi Täytteen nimi
	 * @param pizza_id Pizza, johon täyte liittyy
	 */

	private String tayte_id, nimi, pizza_id;

	public String getPizza_id() {
		return pizza_id;
	}

	public void setPizza_id(String pizza_id) {
		this.pizza_id = pizza_id;
	}

	public TayteBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TayteBean(String tayte_id, String nimi) {
		super();
		this.tayte_id = tayte_id;
		this.nimi = nimi;
	}

	public String getTayte_id() {
		return tayte_id;
	}

	public void setTayte_id(String tayte_id) {
		this.tayte_id = tayte_id;
	}

	public String getNimi() {
		return nimi;
	}

	public void setNimi(String nimi) {
		this.nimi = nimi;
	}

	@Override
	public String toString() {
		return "TayteBean [tayte_id=" + tayte_id + ", nimi=" + nimi
				+ ", pizza_id=" + pizza_id + "]";
	}
	
	
	
}
