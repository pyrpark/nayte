package beans;

/**
 * <code>Juoma</code> luokkaa k‰ytet‰‰n luomaan Juoma-objekti,
 *  jolle voidaan syˆtt‰‰ tietokannasta haetut arvot.
 *  Juoma-luokkaa k‰ytet‰‰n juomalistan k‰sittelyss‰. 
 * 
 * @author Team Yellow
 * @version 0.1
 */

public class Juoma {

	/**
	 * T‰m‰ konstruktori luo juomien luomiseen tarvittavat objektit ja antaa niille arvot.
	 * 
	 * @param juoma_id  juoman tunnus
	 * @param juoma_hinta juoman hinta (euroissa)
	 * @param juoma_nimi juoman nimi
	 */

	int juoma_id;
	double juoma_hinta;
	String juoma_nimi;

	public Juoma() {
		super();
	}
	
	
	public Juoma(int juoma_id, double juoma_hinta, String juoma_nimi) {
		super();
		this.juoma_id = juoma_id;
		this.juoma_hinta = juoma_hinta;
		this.juoma_nimi = juoma_nimi;
	}


	public int getJuoma_id() {
		return juoma_id;
	}
	public void setJuoma_id(int juoma_id) {
		this.juoma_id = juoma_id;
	}
	public String getJuoma_nimi() {
		return juoma_nimi;
	}
	public void setJuoma_nimi(String juoma_nimi) {
		this.juoma_nimi = juoma_nimi;
	}
	public double getJuoma_hinta() {
		return juoma_hinta;
	}
	public void setJuoma_hinta(double juoma_hinta) {
		this.juoma_hinta = juoma_hinta;
	}
	@Override
	public String toString() {
		return "Juoma [juoma_id=" + juoma_id + ", juoma_hinta=" + juoma_hinta + ", juoma_nimi="
				+ juoma_nimi + "]";
	}
	
}
