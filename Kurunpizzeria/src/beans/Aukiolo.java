package beans;

/**
 * <code>Aukiolo</code> luokkaa käytetään luomaan Aukiolo-muuttuja,
 *  jolle voidaan syöttää tietokannasta haetut arvot.
 *  Aukiolo-muuttujaa käytetään, myös uusien arvojen syöttämiseen tietokantaan. 
 * 
 * @author Team Yellow
 * @version 0.1
 */

public class Aukiolo {

	/**
	 * Tämä konstruktori luo Aukiolon tarvitsemat objektit ja antaa niille arvot.
	 * 
	 * @param id  aukioloajan tunnus
	 * @param paiva viikonpäivä
	 * @param sisalto kellonajat
	 */
	
	int id;
	String paiva, sisalto;
	public Aukiolo() {
		super();
	}
	public Aukiolo(int id, String paiva, String sisalto) {
		super();
		this.id = id;
		this.paiva = paiva;
		this.sisalto = sisalto;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPaiva() {
		return paiva;
	}
	public void setPaiva(String paiva) {
		this.paiva = paiva;
	}
	public String getSisalto() {
		return sisalto;
	}
	public void setSisalto(String sisalto) {
		this.sisalto = sisalto;
	}
	@Override
	public String toString() {
		return "Aukiolo [id=" + id + ", paiva=" + paiva + ", sisalto="
				+ sisalto + "]";
	}
	
}
