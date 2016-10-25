package servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.tilausDAO;
import beans.AsiakasBean;
import beans.JuomaBean;
import beans.Ostoskori;
import beans.PizzaBean;

/**
 * Servlet implementation class TilausServlet
 */
@WebServlet("/TilausServlet")
public class TilausServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
     * @see HttpServlet#HttpServlet()
     * 
     * <code>TilausServlet</code> m‰‰ritt‰‰ metodit, jotka servletin t‰ytyy toteuttaa.
     * 
     * @author Team Yellow
     * @version 0.1
     */
    public TilausServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * 
	 * <code>doGet</code> metodi k‰sittelee tilauksen tuotteiden valitsemisen j‰lkeen ja siirt‰‰ tilauksen k‰sittelyyn,
	 * jos asiakas on rekisterˆitynyt palveluun. Muutoin palautuu virheilmoitus.
	 * 
	 * @author Team Yellow
	 * @version 0.1
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(true);
		AsiakasBean asiakas;
		asiakas = (AsiakasBean) session.getAttribute("AsiakasBean");
		
		Ostoskori ostoskori;
		ostoskori = (Ostoskori) session.getAttribute("Ostoskori");

		if(ostoskori == null){
			ostoskori = new Ostoskori();
			session.setAttribute("Ostoskori", ostoskori);
		}
		
		String lisatieto = request.getParameter("lisatietoja");
		String tt = request.getParameter("tilaustyyppi");
		if(tt==null){
			request.setAttribute("tilausvahvistus", "Valitse haluatko tilauksellesi kuljetuspalvelun, vai noudatko sen itse.");
			request.setAttribute ("image",false); 
			request.getRequestDispatcher("tilausvahvistus.jsp").forward(request, response);
			System.out.println("Muista merkit‰ haluatko tilauksen kuljetettuna vai noudettuna");
		}
		
		int tilaustyyppi = Integer.parseInt(tt);
		
		int pizzacounter = 0;
		
		HashMap<Integer, PizzaBean> keratyt_pizzat = Ostoskori.getKeratyt_pizzat();
		
		if(keratyt_pizzat!=null){
			Set<Integer> pizzaids = keratyt_pizzat.keySet();
			Iterator<Integer> itr = pizzaids.iterator();
			int seuraavaPizzaId;
			int pizza_id;
			
			while(itr.hasNext()){
				seuraavaPizzaId = itr.next();
				pizza_id = keratyt_pizzat.get(seuraavaPizzaId).getPizza_id();
				pizzacounter = pizzacounter + 1 ;
				System.out.println("Pizzojen m‰‰r‰ ostoskorissa ennen tilausta: " + pizzacounter);
			}	
		}
		
		int juomacounter = 0;
		
		HashMap<Integer, JuomaBean> keratyt_juomat = Ostoskori.getKeratyt_juomat();
		
		if(keratyt_juomat!=null){
			Set<Integer> juomaids = keratyt_juomat.keySet();
			Iterator<Integer> itr2 = juomaids.iterator();
			int seuraavaJuomaId;
			int juoma_id;
			
			while(itr2.hasNext()){
				seuraavaJuomaId = itr2.next();
				juoma_id = keratyt_juomat.get(seuraavaJuomaId).getJuoma_id();
				juomacounter = juomacounter + 1 ;
				System.out.println("Juomien m‰‰r‰ ostoskorissa ennen tilausta " + juomacounter);
			}	
		}
				
		if(juomacounter==0){
			session.removeAttribute("keratyt_juomat");
			
		}
		
		if(asiakas == null){
			request.setAttribute("tilausvahvistus", "Sinun pit‰‰ kirjautua sis‰‰n tehd‰ksesi tilauksen");
			request.setAttribute ("image",false); 
			request.getRequestDispatcher("tilausvahvistus.jsp").forward(request, response);
			System.out.println("Sinun pit‰‰ kirjautua sis‰‰n tehd‰ksesi tilauksen");
		}else{
			System.out.println("Olet kirjautunut sis‰‰n ja voit tilata. Nimesi on: " + asiakas.geteNimi());
			ostoskori = (Ostoskori) session.getAttribute("Ostoskori");
			
			//if(ostoskori.getKeratyt_pizzat() == null){
			if(pizzacounter<1){
				// Jos ostoskori on tyhj‰ niin kerrotaan k‰ytt‰j‰lle ett‰ tuotteita pit‰‰ lis‰t‰ ostoskoriin ennen tilausta
				request.setAttribute("tilausvahvistus", "Tilaukseen tulee sis‰lty‰ v‰hint‰‰n yksi pizza");
				request.setAttribute ("image",false); 
				request.getRequestDispatcher("tilausvahvistus.jsp").forward(request, response);
				System.out.println("Ostoskorissa ei ole pizzoja");
			}else{
				
				System.out.println("Ostoskorissa on tuotteita, siirryt‰‰n tekem‰‰n tilausta");
			
				// Siirryt‰‰n tilausDAOon ja kirjataan tilaus kantaan
				tilausDAO tilaus = new tilausDAO();
				boolean onnistui = tilaus.teeTilaus(keratyt_pizzat, asiakas, keratyt_juomat, lisatieto, tilaustyyppi);
				
				if(onnistui == false){
					System.out.println("Tilaus ep‰onnistui");
		
					request.setAttribute("tilausvahvistus", "Tilaus ep‰onnistui");
					request.setAttribute ("image",false); 
					request.getRequestDispatcher("tilausvahvistus.jsp").forward(request, response);
					
					
					// Siirret‰‰n asiakas virhesivulle
				}else{
					System.out.println("Tilaus onnistui");

					// NOLLAA OSTOSKORI
					Ostoskori.tyjenna();
					request.setAttribute("tilausvahvistus", "Tilaus onnistui");
					request.setAttribute ("image",true); ;
					request.getRequestDispatcher("tilausvahvistus.jsp").forward(request, response);
					
					// Siirret‰‰n asiakas kiitossivulle
				}
			}
	
		}
		
		// Haetaan ostoskori

		//Onnistuiko tilauksen tekeminen? jos kyll‰ ohjaus kiitos sivulle jos ei niin virhesanoma
		
		// Nollataan Session?? jotta ostoskorikin tyhjenee
		
		// Siirret‰‰n k‰ytt‰j‰ kiitos tilauksesta sivulle
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}
	
}
