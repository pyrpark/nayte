package beans;

/**
 * <code>TilattuJuoma</code>-luokkaa k‰ytet‰‰n luomaan TilattuJuoma-objekti,
 *  jolle voidaan v‰litt‰‰ tietokannasta haetut arvot.
 *  Objektia k‰ytet‰‰n tietyn tilauksen juoman(tai juomien) m‰‰rittelyyn. 
 * 
 * @author Team Yellow
 * @version 0.1
 */

public class TilattuJuomaBean {
	
	/**
	 * T‰m‰ konstruktori luo tilatun juoman tarvitsemat attribuutit ja antaa niille arvot.
	 * 
	 * @param tilaus_id  Tilaus, johon tilattu juoma liittyy
	 * @param juoma_id Juoma, joka on tilattu
	 * @param juoma_nimi Tilatun juoman nimi
	 * @param maara Tilatun juoman kappalem‰‰r‰
	 */

	private String tilaus_id, juoma_id, juoma_nimi;
	private int maara;
	
	public TilattuJuomaBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getJuoma_nimi() {
		return juoma_nimi;
	}
	public void setJuoma_nimi(String juoma_nimi) {
		this.juoma_nimi = juoma_nimi;
	}
	public TilattuJuomaBean(String tilaus_id, String juoma_id, int maara) {
		super();
		this.tilaus_id = tilaus_id;
		this.juoma_id = juoma_id;
		this.maara = maara;
	}
	public String getTilaus_id() {
		return tilaus_id;
	}
	public void setTilaus_id(String tilaus_id) {
		this.tilaus_id = tilaus_id;
	}
	public String getJuoma_id() {
		return juoma_id;
	}
	public void setJuoma_id(String juoma_id) {
		this.juoma_id = juoma_id;
	}
	public int getMaara() {
		return maara;
	}
	public void setMaara(int maara) {
		this.maara = maara;
	}
	@Override
	public String toString() {
		return "TilattuJuomaBean [tilaus_id=" + tilaus_id + ", juoma_id=" + juoma_id + ", juoma_nimi=" + juoma_nimi
				+ ", maara=" + maara + "]";
	}
	
	
}
