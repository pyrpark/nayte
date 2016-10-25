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
 * Servlet implementation class MuutaAukioloServlet
 */
@WebServlet("/lisaa_juoma")
public class MuutaJuomaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     * 
     * <code>MuutaJuomaServlet</code> m‰‰ritt‰‰ metodit, jotka servletin t‰ytyy toteuttaa.
     * 
     * @author Team Yellow
     * @version 0.1
     */
    public MuutaJuomaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * 
	 * <code>doGet</code> metodi palauttaa t‰ss‰ yhteydess‰ alempana olevan doPost metodin.
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
	 * <code>doPost</code> metodi palauttaa parametrit ma_pe, la_su sek‰ lounas 
	 * riippuen mit‰ niist‰ k‰ytt‰j‰ on halunnut muuttaa.
	 * 
	 * @author Team Yellow
	 * @version 0.1
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/*Servlet yritt‰‰ muuttaa jsp:lt‰ saadut parametrit (t‰ss‰ tapauksessa vain ID:n ja hinnan) ennen niiden v‰litt‰mist‰
		 * muutaJuomaDAO:n metodille. Servlet ilmoittaa henkilokunta.jsp:ll‰, onnistuiko lis‰ys vai ei.*/
		
		
			try{
		
				int onnistuiko = 0;
				
				String juoma_iid = request.getParameter("juoma_id");
				String juoma_nimi = request.getParameter("juoma_nimi");
				String juoma_hinta = request.getParameter("juoma_hinta");
				
				
				
				if (request.getParameter("juoma_id") != null){
					try {
			
						int juoma_id = Integer.parseInt(juoma_iid);
						muutaJuomaDAO.muutaJuoma(juoma_nimi,juoma_id,juoma_hinta);
						onnistuiko = 1;
						
					} catch (Exception e) {
						
						System.out.println("Juoman ID:t‰ ei voi muuttaa kokonaisluvuksi!");
						request.setAttribute("ilmoitus", "Et syˆtt‰nyt oikean muotoista juoman ID:t‰. Syˆt‰ ID kokonaislukuna.");
						 request.setAttribute("vari", "#FF0000");
					}
					
					if(onnistuiko == 1){
						request.setAttribute("ilmoitus", "Juoman lis‰‰minen onnistui!");
						 request.setAttribute("vari", "#009933");
					}else{
						System.out.println("Kentt‰ tyhj‰n‰");
						
						request.setAttribute("ilmoitus", "Juoman lis‰‰minen ei onnistunut!.");
						request.setAttribute("vari", "#FF0000");
					}

				}
					

				List<Juoma> juoma = juomaDAO.haeKaikki();
				
				// requestiin talteen
				request.setAttribute("juoma", juoma);
				
				// jsp hoitaa muotoilun
				request.getRequestDispatcher("henkilokunnanServlet").forward(request, response);
	}catch(Exception e){
		e.printStackTrace();
	}
	}

}
