package servlet;

 
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;








import beans.Juoma;
import beans.PizzaBean;
import beans.TayteBean;
import dao.MenuDAO;
import dao.juomaDAO;
import dao.lisaaPizzaDAO;
import dao.poistaPizzaDAO;


/**
 * Servlet implementation class MenuServlet
 */
@WebServlet("/Pizzojen_muokkausServlet")
public class Pizzojen_muokkausServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     * 
     * * <code>Pizzojen_muokkausServlet</code> on servlet, jonka avulla hoidetaan pizzojen lis‰‰minen sek‰ poistaminen.
     * 
     * @author Team Yellow
     * @version 0.1
     */
    public Pizzojen_muokkausServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * 
	 * * <code>doGet</code> metodi palauttaa t‰ss‰ yhteydess‰ alempana olevan doPost metodin.
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
	 * <code>doPost</code> metodi palauttaa ilmoituksen toiminnon onnistumisesta tai ep‰onnistumisesta, vaan puhtaasti lis‰‰ tai poistaa pizzoja k‰ytt‰j‰n
	 * henkilokunta.jsp:lle syˆtt‰mien arvojen perusteella. Servlet sis‰lt‰‰ kaikille arvoille virheentarkistuksen tilanteille, joissa
	 * annettu arvo on v‰‰r‰n muotoinen tai ei ole olemassa.
	 * 
	 * Servlet ohjautuu aina toimintojen j‰lkeen henkilokuntaServletille.
	 * 
	 * @author Team Yellow
	 * @version 0.1
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
				
		//---------------------
		//Pizzan lis‰ys kantaan
		//---------------------

		try {
			
			//Tavaraa laskuria ja lis‰tt‰vien pizzojen k‰sittely‰ varten
			
			ArrayList<TayteBean> TayteLista = MenuDAO.TayteLista();
			ArrayList<PizzaBean> pizzat = MenuDAO.listaaPizzat();
			ArrayList<Integer> taytteiden_idt = new ArrayList<Integer>();
			ArrayList<String> attribuutit = new ArrayList<String>();
						
			int laskuri = 0;
			int vaatimus = 0;
			boolean onnistui = false;

			
			int uusi_id = 0;
			String uusi_nimi = null;
			String uusi_koko = null;
			double uusi_hinta = 0;
			

			/*Aloitetaan lis‰tt‰v‰n pizzan tietojen ker‰‰minen, jos arvoja on syˆtetty.
			 * Arvojen tilanne testataan if-lauseilla.*/
			
				//ID
			if(request.getParameter("uusi_pizzaid") != null){
				boolean kaannos_onnistui = true;
				
				try{
					
				String id_dump = request.getParameter("uusi_pizzaid");
				uusi_id = Integer.parseInt(id_dump);
				
				}catch(Exception e){
					System.out.println("Arvoa ei voitu muuttaa kokonaisluvuksi.");
					kaannos_onnistui = false;
				}
				
				if(kaannos_onnistui == true){
				
				System.out.println("-----------------------------------"+System.lineSeparator()+"Uuden pizzan id on "+uusi_id+"");
				vaatimus++;
				}
			}
			
				//NIMI
			if(request.getParameter("uusi_nimi") != null){
				
				uusi_nimi = request.getParameter("uusi_nimi");
				
				System.out.println("Uuden pizzan nimi on "+uusi_nimi);
				vaatimus++;
			}
			
				//KOKO
			if(request.getParameter("pizzauusi_paivitys") != null){
				
				String dumpkoko = request.getParameter("pizzauusi_paivitys");
					
				if(dumpkoko.equalsIgnoreCase("normaali")){
						uusi_koko = "1";
					}else if(dumpkoko.equalsIgnoreCase("perhe")){
						uusi_koko = "2";
					}
					
				System.out.println("Uuden pizzan koko on "+uusi_koko);
				vaatimus++;
			}
			
				//TƒYTTEET
			attribuutit.add(request.getParameter("tayte1"));	
			attribuutit.add(request.getParameter("tayte2"));
			attribuutit.add(request.getParameter("tayte3"));
			attribuutit.add(request.getParameter("tayte4"));
			attribuutit.add(request.getParameter("tayte5"));
			attribuutit.add(request.getParameter("tayte6"));
			attribuutit.add(request.getParameter("tayte7"));
			attribuutit.add(request.getParameter("tayte8"));
			attribuutit.add(request.getParameter("tayte9"));
			attribuutit.add(request.getParameter("tayte10"));
			attribuutit.add(request.getParameter("tayte11"));
			attribuutit.add(request.getParameter("tayte12"));
			attribuutit.add(request.getParameter("tayte13"));
			attribuutit.add(request.getParameter("tayte14"));
			
			if(attribuutit.size() > 0){
			vaatimus++;
			}
				//HINTA
			if(request.getParameter("pizza_hinta") != null){
				
				String hintadump = request.getParameter("pizza_hinta");
				boolean hinta_kaannos = false;
				
				try {
					uusi_hinta = Double.parseDouble(hintadump);
				} catch (Exception e) {
					System.out.println("Syˆtt‰m‰‰si arvoa ei voitu muuttaa desimaaliluvuksi!");
					hinta_kaannos = true;
				}
				
			
			if(hinta_kaannos == false){	
			System.out.println("Uuden pizzan hinta on "+uusi_hinta+""+System.lineSeparator()+"-----------------------------------");				
			vaatimus++;
				}
			}
			
			
			//Selvitet‰‰n, mitk‰ t‰ytteet on valittu.
			
			for(int i=0;i<attribuutit.size();i++){
				
				if(attribuutit.get(i) != null){
									
					String arvo = attribuutit.get(i);
					int taytteen_id = Integer.parseInt(arvo);
					
					taytteiden_idt.add(taytteen_id);
					
					laskuri++;
				}
				
			}
			
			System.out.println("Laskurin arvo: "+laskuri);
			System.out.println("Valitut t‰ytteet(ID:t): "+taytteiden_idt);
			
			//Pizzan lis‰ys kantaan (jos arvoja ylip‰‰t‰‰n annettu). L‰htee k‰yntiin vain, jos kaikki kohdat on t‰ytetty.
			
			if(vaatimus == 5){
				
				//Aloitetaan transaktio pizzan lis‰‰miseksi.
				onnistui = new lisaaPizzaDAO().lisaaPizzav2(uusi_nimi, uusi_hinta, uusi_koko, uusi_id, taytteiden_idt, laskuri);
				
				/*Jos transaktio onnistui tai ep‰onnistui, tulostetaan henkilokunta.jsp:lle ilmoitus.*/
				if(onnistui == true){
					
					System.out.println("Pizza on lis‰tty kantaan.");
					request.setAttribute("ilmoitus", "Pizza "+uusi_nimi+" on lis‰tty ruokalistaan onnistuneesti!");
					request.setAttribute("vari", "#009933");
					
				}else{
					System.out.println("Uutta pizzaa ei ole luotu!");
					request.setAttribute("ilmoitus", "Tapahtui virhe, jonka vuoksi pizzaa ei lis‰tty! Katso konsolista lis‰tietoja.");
					request.setAttribute("vari", "#FF0000");	
				}

				
			}else{
				System.out.println("Virhe pizzaa luotaessa.");
				request.setAttribute("ilmoitus", "Pizzaa ei luotu!");
				request.setAttribute("vari", "#FF0000");
			}
			
			//----------------------
			//Pizzan poisto kannasta
			//----------------------
			
			/*Aloitetaan pizzan poistaminen, jos arvo "poistapizza_id" on ylip‰‰t‰‰n annettu.*/
			
			try {
				if(request.getParameter("poistapizza_id") != null){
					
					//Otetaan vastaan poistettava id ja k‰‰nnet‰‰n se int-muuttujaksi.
					String id_dumppi = request.getParameter("poistapizza_id");
					System.out.println("---------------------------");
					System.out.println("Poistettavan pizzan id: "+id_dumppi);
					System.out.println("---------------------------");
					System.out.println(System.lineSeparator());
					
					int poistettava_id = Integer.parseInt(id_dumppi);
					boolean OK;
					
					OK = new poistaPizzaDAO().poistaPizza(poistettava_id);
					
					/*Riippuen onnistuiko poisto vai ei n‰ytet‰‰n ilmoitus henkilokunta.jsp:ll‰. Jos poisto
					 * onnistui, pizza myˆs poistetaan kaikkien pizzojen listasta.*/
					if(OK == true){
						System.out.println("Poisto OK.");
						
						for (int c = 0;c<pizzat.size();c++){
							
							if(pizzat.get(c).getPizza_id() == poistettava_id){
								pizzat.remove(c);
							}	
						}
						System.out.println("Pizza poistettu listasta.");
						pizzat = MenuDAO.listaaPizzat();
						request.setAttribute("ilmoitus", "Pizza on poistettu listasta.");
						request.setAttribute("vari", "#009933");
						
					}else{
						System.out.println("Pizzaa ei ole olemassa t‰ll‰ ID:ll‰");
						request.setAttribute("ilmoitus", "Pizzaa ei poistettu kannasta! Pizzaa ei ole olemassa syˆtt‰m‰ll‰si ID:ll‰.");
						request.setAttribute("vari", "#FF0000");
					}
				}
				
				
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Virhe poistettaessa pizzaa. Katso lis‰tiedot stackista.");
			}

			
			System.out.println(pizzat);
			System.out.println(TayteLista);


			request.getRequestDispatcher("henkilokunnanServlet").forward(request, response);
			
					
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Virhe servletin toiminnassa. Katso lis‰tiedot virheilmoituksesta.");
		}
	}
	

			
	}


