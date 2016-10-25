package servlet;

import java.io.IOException;



import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;











import beans.Juoma;
import dao.juomaDAO;
import dao.muutaJuomaDAO;

/**
 * Servlet implementation class PoistaJuomaServlet
 */
@WebServlet("/poista_juoma")
public class PoistaJuomaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     * 
     * <code>PoistaJuomaServlet</code> on servlet, jonka avulla hoidetaan juomien poistaminen.
     * 
     * @author Team Yellow
     * @version 0.1
     */
    public PoistaJuomaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * 
	 * <code>doGet</code> metodi palauttaa tässä yhteydessä alempana olevan doPost metodin.
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
	 * <code>doPost</code> metodi vastaanottaa jsp:lle syötetyn arvon (poistajuoma_id), jonka perusteella se kutsuu PoistaJuomaDAO:n
	 * metodia, jonka avulla juomat poistetaan kannasta. Servlet sisältää virheenkäsittelyn tilanteisiin, joissa poistettavan
	 * juoman ID on väärän muotoinen tai ei ole olemassa.
	 * 
	 * @author Team Yellow
	 * @version 0.1
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String poista = request.getParameter("poistajuoma_id");
			ArrayList<Juoma> juomat = juomaDAO.haeKaikki();	
			boolean okei;
			int poistajuoma_id = 0;
				
				if (request.getParameter("poistajuoma_id") != null){
										
					System.out.println("Stringin poista arvo: "+poista);
					
					try {
						poistajuoma_id = Integer.parseInt(poista);
						System.out.println("poistajuoma_id: "+poistajuoma_id);
					
						
					} catch (Exception e) {
						System.out.println("Juoman ID:tä ei voi muuttaa kokonaisluvuksi!");
						request.setAttribute("ilmoitus", "Et syöttänyt oikean muotoista juoman ID:tä. Syötä ID kokonaislukuna.");
						 request.setAttribute("vari", "#FF0000");
					}
					
					okei = muutaJuomaDAO.poistaJuoma(poistajuoma_id);
					System.out.println("Onnistuiko poistaminen: "+okei);
					
					if(okei == true){
						System.out.println("----------------------");
						System.out.println("Poistettava juoma: "+poistajuoma_id);
						System.out.println("----------------------");
						
						request.setAttribute("ilmoitus", "Juoman poisto onnistui!");
						 request.setAttribute("vari", "#00FF00");
						 
						 for(int q = 0;q<juomat.size();q++){
							 if(juomat.get(q).getJuoma_id() == poistajuoma_id){
								 juomat.remove(q);
								 System.out.println("Juoma poistettu juomalistasta.");
							 } 
						 }
					}else if(okei == false){
						request.setAttribute("ilmoitus", "Juoman poisto ei onnistunut! Syötä olemassa olevan juoman ID kokonaislukuna.");
						request.setAttribute("vari", "#FF0000");
						
					}
					
				}else{
			
					System.out.println("Kenttä tyhjänä");
					 request.setAttribute("ilmoitus", "Et ole täyttänyt juoman poistamiseen vaadittavia kenttiä!");
					 request.setAttribute("vari", "#FF0000");
					
				}

				request.getRequestDispatcher("henkilokunnanServlet").forward(request, response);
	}

}