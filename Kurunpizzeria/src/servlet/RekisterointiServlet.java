package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.RekisterointiDAO;
import beans.AsiakasBean;

/**
 * Servlet implementation class RekisterointiServlet
 */
@WebServlet("/RekisterointiServlet")
public class RekisterointiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
     * @see HttpServlet#HttpServlet()
     * 
     * <code>RekisterointiServlet</code> m��ritt�� metodit, jotka servletin t�ytyy toteuttaa.
     * 
     * @author Team Yellow
     * @version 0.1
     */
    public RekisterointiServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * 
	 * <code>doGet</code> metodi palauttaa t�ss� yhteydess� alempana olevan doPost metodin.
	 * 
	 * @author Team Yellow
	 * @version 0.1
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * 
	 * <code>doPost</code> metodi kysyy asiakkaalta tietoja, jotta h�net voidaan rekister�id� palvelun asiakkaaksi.
	 * Metodi ilmoittaa, jos vaadittavia tietoja ei ole annettu tai rekister�inti ei onnistu.
	 * 
	 * @author Team Yellow
	 * @version 0.1
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String eNimi = request.getParameter("etunimi");
		String sNimi  = request.getParameter("sukunimi");
		String sPosti = request.getParameter("sahkoposti");
		String osoite = request.getParameter("osoite");
		String postiNro = request.getParameter("postinro");
		String postiTmp = request.getParameter("postiTmp");
		String puhNro = request.getParameter("puh_nro");
		String user = request.getParameter("user");
		String pw = request.getParameter("password");
		
		boolean oikein=true;
		String errorViesti="Tarkista ";
		
		if (eNimi.isEmpty() || sNimi.isEmpty() || sPosti.isEmpty() || osoite.isEmpty() || postiNro.isEmpty() || puhNro.isEmpty() || user.isEmpty()
				|| pw.isEmpty() || postiTmp.isEmpty() ){
			//Asiakkalle ilmoitetaan tyhj�st� kent�st�
			
			System.out.println("Kentt� tyhj�n�!");
			errorViesti= errorViesti + "Kentt� tyhj�n�. ";
			oikein=false;
		}
		
		if(eNimi.matches("[0-9]+")==true ||sNimi.matches("[0-9]+")==true ){
			
			System.out.println("Tarkista nimesi.");
			errorViesti= errorViesti + "Nimi. ";
			oikein=false;
			
		}
		
		if (postiNro.length() != 5 || postiNro.matches("[a-zA-Z]+")==true){
			
			System.out.println("Tarkista postinumero");
			errorViesti= errorViesti + "Postinumero. ";
			oikein=false;
			
		}
		
		if (postiTmp.matches("[0-9]+")==true){
			System.out.println("Tarkista postitoimipaikka");
			errorViesti= errorViesti + "Postitoimipaikka. ";
			oikein=false;
		}
		
		if (puhNro.matches("[a-zA-Z]+")==true){
			System.out.println("Tarkista puhelinnumero");
			errorViesti= errorViesti + "Puhelin. ";
			oikein=false;
		}
		
		if (sPosti.indexOf("@")==-1){
			
			System.out.println("Virheellinen s�hk�posti.");
			errorViesti= errorViesti + "S�hk�posti. ";
			oikein=false;
			
		}
		
		if (oikein==false){
			
			request.setAttribute("errorMessage", errorViesti);
			request.getRequestDispatcher("rekisterointi.jsp").forward(request, response);
		}
		
		if (oikein==true) {
			
			RekisterointiDAO rek = new RekisterointiDAO();
			ArrayList <String> postinumerot = rek.PostiNroListaan();
			
			
			boolean l�ytyy = false;
			
			//Etsit��n l�ytyyk� sy�tetty postinumero jo kannasta
			
			for (int i = 0; i < postinumerot.size(); i++) {
				
				if (postiNro.equalsIgnoreCase(postinumerot.get(i))){
					System.out.println("Postinumero l�ytyy.");
					l�ytyy=true;
					break;
				}
				
			}
			if (l�ytyy==false){
				//Kun postinumeroa ei l�ydy, postinumero ja postitoimipaikka lis�t��n POSTITMP tauluun
				rek.LisaaPostiTmp(postiNro, postiTmp);
				
					
			}
			
			AsiakasBean asiakas = new AsiakasBean ();
			asiakas.seteNimi(eNimi);
			asiakas.setsNimi(sNimi);
			asiakas.setsPosti(sPosti);
			asiakas.setOsoite(osoite);
			asiakas.setPostiNro(postiNro);
			asiakas.setPuhNro(puhNro);
			asiakas.setUser(user);
			asiakas.setPw(pw);
			
			//asiakas bean lis�t��n tietokantaan
			rek.RekisteroiAsiakas(asiakas);
			
			//asiakas ohjataan eteenp�in
			request.setAttribute("tilausvahvistus", "Rekister�inti onnistui! Voit nyt kirjautua sis��n.");
			request.setAttribute("image", true);
			request.getRequestDispatcher("tilausvahvistus.jsp").forward(request, response);
			
			
			
			
		}
		
		
	}

}
