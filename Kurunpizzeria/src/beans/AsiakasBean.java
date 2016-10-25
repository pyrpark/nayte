package beans;

/**
 * <code>AsiakasBean</code> luokkaa käytetään luomaan AsiakasBean-objekti,
 *  jonka avulla pizzerian asiakas voi rekisteröityä palvelun käyttäjäksi.
 *  AsiakasBean-muuttujaa käytetään, myös uusien arvojen syöttämiseen tietokantaan. 
 * 
 * @author Team Yellow
 * @version 0.1
 */

public class AsiakasBean {
	
	/**
	 * Tämä konstruktori luo asiakkaan luomiseen tarvittavat objektit ja antaa niille arvot.
	 * 
	 * @param eNimi  asiakkaan etunimi
	 * @param sNimi asiakkaan sukunimi
	 * @param sPosti asiakkaan sähköposti
	 * @param osoite asiakkaan kotiosoite
	 * @param postiNro asiakkaan postinumero
	 * @param postiTmp asiakkaan postiTmp
	 * @param puhNro asiakkaan puhelinnumero
	 * @param user asiakkaan käyttäjätunnus
	 * @param pw asiakkaan salasana
	 */
	
	 private String eNimi, sNimi, sPosti, osoite, postiNro, postiTmp, puhNro, user, pw;
	 private int asiakas_id;

	public AsiakasBean(String eNimi, String sNimi, String sPosti,
			String osoite, String postiNro, String postiTmp, String puhNro,
			String user, String pw, int asiakas_id) {
		super();
		this.eNimi = eNimi;
		this.sNimi = sNimi;
		this.sPosti = sPosti;
		this.osoite = osoite;
		this.postiNro = postiNro;
		this.postiTmp = postiTmp;
		this.puhNro = puhNro;
		this.user = user;
		this.pw = pw;
		this.asiakas_id = asiakas_id;
	}
	
	

	@Override
	public String toString() {
		return "AsiakasBean [eNimi=" + eNimi + ", sNimi=" + sNimi + ", sPosti="
				+ sPosti + ", osoite=" + osoite + ", postiNro=" + postiNro
				+ ", postiTmp=" + postiTmp + ", puhNro=" + puhNro + ", user="
				+ user + ", pw=" + pw + ", asiakas_id=" + asiakas_id + "]";
	}



	public int getAsiakas_id() {
		return asiakas_id;
	}

	public void setAsiakas_id(int asiakas_id) {
		this.asiakas_id = asiakas_id;
	}

	public AsiakasBean(String eNimi, String sNimi, String sPosti,
			String osoite, String postiNro, String postiTmp, String puhNro,
			String user, String pw) {
		super();
		this.eNimi = eNimi;
		this.sNimi = sNimi;
		this.sPosti = sPosti;
		this.osoite = osoite;
		this.postiNro = postiNro;
		this.postiTmp = postiTmp;
		this.puhNro = puhNro;
		this.user = user;
		this.pw = pw;
	}

	public String getPostiTmp() {
		return postiTmp;
	}

	public void setPostiTmp(String postiTmp) {
		this.postiTmp = postiTmp;
	}

	public String geteNimi() {
		return eNimi;
	}

	public void seteNimi(String eNimi) {
		this.eNimi = eNimi;
	}

	public String getsNimi() {
		return sNimi;
	}

	public void setsNimi(String sNimi) {
		this.sNimi = sNimi;
	}

	public String getsPosti() {
		return sPosti;
	}

	public void setsPosti(String sPosti) {
		this.sPosti = sPosti;
	}

	public String getOsoite() {
		return osoite;
	}

	public void setOsoite(String osoite) {
		this.osoite = osoite;
	}

	public String getPostiNro() {
		return postiNro;
	}

	public void setPostiNro(String postiNro) {
		this.postiNro = postiNro;
	}

	public String getPuhNro() {
		return puhNro;
	}

	public void setPuhNro(String puhNro) {
		this.puhNro = puhNro;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public AsiakasBean() {
		super();
	}

}
