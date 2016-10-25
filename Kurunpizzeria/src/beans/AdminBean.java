package beans;

public class AdminBean {
	
	/**
	 * Tämä konstruktori luo omistajan käyttäjätunnuksen ja antaa niille arvot.
	 * 
	 * @param omistaja_id  omistajan yksilöllinen tunnus
	 * @param etunimi omistajan etunimi
	 * @param sukunimi omistajan sukunimi
	 * @param kayt_tun Kirjautumiseen käytettävä käyttäjänimi
	 * @param salasana omistajan määrittämä salasana
	 */
	
	private int omistaja_id;
	private String etunimi, sukunimi, kayt_tun, salasana;
	
	public AdminBean() {
		super();
	}

	public AdminBean(int omistaja_id, String etunimi, String sukunimi,
			String kayt_tun, String salasana) {
		super();
		this.omistaja_id = omistaja_id;
		this.etunimi = etunimi;
		this.sukunimi = sukunimi;
		this.kayt_tun = kayt_tun;
		this.salasana = salasana;
	}

	public int getOmistaja_id() {
		return omistaja_id;
	}

	public void setOmistaja_id(int omistaja_id) {
		this.omistaja_id = omistaja_id;
	}

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

	public String getKayt_tun() {
		return kayt_tun;
	}

	public void setKayt_tun(String kayt_tun) {
		this.kayt_tun = kayt_tun;
	}

	public String getSalasana() {
		return salasana;
	}

	public void setSalasana(String salasana) {
		this.salasana = salasana;
	}

	@Override
	public String toString() {
		return "AdminBean [omistaja_id=" + omistaja_id + ", etunimi=" + etunimi
				+ ", sukunimi=" + sukunimi + ", kayt_tun=" + kayt_tun
				+ ", salasana=" + salasana + "]";
	} 

}
