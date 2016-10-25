package beans;

/**
 * <code>TilausBean</code>-luokkaa k‰ytet‰‰n luomaan Tilaus-objekti,
 *  jolle voidaan v‰litt‰‰ tietokannasta haetut arvot.
 *  Objektia k‰ytet‰‰n tilauksen tietojen (asiakas, mik‰ tilaus, status) m‰‰rittelyyn. 
 * 
 * @author Team Yellow
 * @version 0.1
 */

import java.util.Date;

public class TilausBean {
	
	/**
	 * T‰m‰ konstruktori luo tilauksen tarvitsemat attribuutit ja antaa niille arvot.
	 * 
	 * @param tilaus_id  Tilauksen yksilˆllinen tunnus
	 * @param asiakas_id Asiakkaan tunnus, joka tekee tilauksen
	 * @param status_id Tilauksen tilan tunnus
	 * @param tilaus_tyyppi Tilauksen tyyppi
	 * @param lisatieto Asiakkaan mahdollisia lis‰toiveita, tms.
	 */

	private String tilaus_id, asiakas_id, status_id, tilaus_tyyppi, lisatieto;
	private Date tilaus_aika;
	private double tilaus_hinta;
	
	private String etunimi, sukunimi, sahkoposti, postinro, osoite, puh_nro;
	
	public String getEtunimi() {
		return etunimi;
	}
	public void setEtunimi(String etunimi) {
		this.etunimi = etunimi;
	}
	public String getSukunimi() {
		return sukunimi;
	}
	public void setSukunimi(String sukunimi) {
		this.sukunimi = sukunimi;
	}
	public String getSahkoposti() {
		return sahkoposti;
	}
	public void setSahkoposti(String sahkoposti) {
		this.sahkoposti = sahkoposti;
	}
	public String getPostinro() {
		return postinro;
	}
	public void setPostinro(String postinro) {
		this.postinro = postinro;
	}
	public String getOsoite() {
		return osoite;
	}
	public void setOsoite(String osoite) {
		this.osoite = osoite;
	}
	public String getPuh_nro() {
		return puh_nro;
	}
	public void setPuh_nro(String puh_nro) {
		this.puh_nro = puh_nro;
	}
	public String getTilaus_id() {
		return tilaus_id;
	}
	public void setTilaus_id(String tilaus_id) {
		this.tilaus_id = tilaus_id;
	}
	public String getAsiakas_id() {
		return asiakas_id;
	}
	public void setAsiakas_id(String asiakas_id) {
		this.asiakas_id = asiakas_id;
	}
	public String getStatus_id() {
		return status_id;
	}
	public void setStatus_id(String status_id) {
		this.status_id = status_id;
	}
	public String getTilaus_tyyppi() {
		return tilaus_tyyppi;
	}
	public void setTilaus_tyyppi(String tilaus_tyyppi) {
		this.tilaus_tyyppi = tilaus_tyyppi;
	}
	public String getLisatieto() {
		return lisatieto;
	}
	public void setLisatieto(String lisatieto) {
		this.lisatieto = lisatieto;
	}
	public Date getTilaus_aika() {
		return tilaus_aika;
	}
	public void setTilaus_aika(Date tilaus_aika) {
		this.tilaus_aika = tilaus_aika;
	}
	public double getTilaus_hinta() {
		return tilaus_hinta;
	}
	public void setTilaus_hinta(double tilaus_hinta) {
		this.tilaus_hinta = tilaus_hinta;
	}
	public TilausBean(String tilaus_id, String asiakas_id, String status_id, String tilaus_tyyppi, String lisatieto,
			Date tilaus_aika, double tilaus_hinta) {
		super();
		this.tilaus_id = tilaus_id;
		this.asiakas_id = asiakas_id;
		this.status_id = status_id;
		this.tilaus_tyyppi = tilaus_tyyppi;
		this.lisatieto = lisatieto;
		this.tilaus_aika = tilaus_aika;
		this.tilaus_hinta = tilaus_hinta;
	}
	public TilausBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "TilausBean [tilaus_id=" + tilaus_id + ", asiakas_id="
				+ asiakas_id + ", status_id=" + status_id + ", tilaus_tyyppi="
				+ tilaus_tyyppi + ", lisatieto=" + lisatieto + ", tilaus_aika="
				+ tilaus_aika + ", tilaus_hinta=" + tilaus_hinta + ", etunimi="
				+ etunimi + ", sukunimi=" + sukunimi + ", sahkoposti="
				+ sahkoposti + ", postinro=" + postinro + ", osoite=" + osoite
				+ ", puh_nro=" + puh_nro + "]";
	}
	
	
	
	
}
