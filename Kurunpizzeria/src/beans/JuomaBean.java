package beans;

/**
 * <code>JuomaBean</code>-luokkaa k‰ytet‰‰n luomaan JuomaBean-objekti,
 *  jolle voidaan syˆtt‰‰ tietokannasta haetut arvot.
 *  JuomaBean-luokkaa k‰ytet‰‰n ostoskorin toimintojen yhteydess‰.
 * 
 * @author Team Yellow
 * @version 0.1
 */

public class JuomaBean {
	
	/**
	 * T‰m‰ konstruktori luo juomien luomiseen tarvittavat objektit ja antaa niille arvot.
	 * 
	 * @param juoma_id  juoman tunnus
	 * @param nimi juoman nimi
	 * @param hinta juoman hinta (euroissa)
	 * @param maara monta kappaletta t‰t‰ juomaa on valittu
	 */
	
	private int juoma_id;
	private String nimi;
	private double hinta;
	private int maara;
	
	public int getMaara() {
		return maara;
	}

	public void setMaara(int maara) {
		this.maara = maara;
	}

	public JuomaBean(int juoma_id, String nimi, double hinta, int maara) {
		super();
		this.juoma_id = juoma_id;
		this.nimi = nimi;
		this.hinta = hinta;
		this.maara = maara;
	}

	public JuomaBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public JuomaBean(int juoma_id, String nimi, double hinta) {
		super();
		this.juoma_id = juoma_id;
		this.nimi = nimi;
		this.hinta = hinta;
	}

	public int getJuoma_id() {
		return juoma_id;
	}

	public void setJuoma_id(int juoma_id) {
		this.juoma_id = juoma_id;
	}


	public String getNimi() {
		return nimi;
	}

	public void setNimi(String nimi) {
		this.nimi = nimi;
	}

	public double getHinta() {
		return hinta;
	}

	public void setHinta(double hinta) {
		this.hinta = hinta;
	}

	@Override
	public String toString() {
		return "JuomaBean [juoma_id=" + juoma_id + ", nimi=" + nimi
				+ ", hinta=" + hinta + ", maara=" + maara + "]";
	}
	
	

}
