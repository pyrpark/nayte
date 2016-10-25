package servlet;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.JuomaBean;
import beans.Ostoskori;


/**
 * Servlet implementation class PoistaPizzaOstoskorista
 */
@WebServlet("/PoistaJuomaOstoskorista")
public class PoistaJuomaOstoskorista extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
     * @see HttpServlet#HttpServlet()
     * 
     * <code>PoistaJuomaOstoskorista</code> m‰‰ritt‰‰ metodit, jotka servletin t‰ytyy toteuttaa.
     * 
     * @author Team Yellow
     * @version 0.1
     */
    public PoistaJuomaOstoskorista() {
        super();
        // TODO Auto-generated constructor stub
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * 
	 * <code>doGet</code> ei t‰ss‰ tapauksessa tee mit‰‰n.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * 
	 * <code>doPost</code> metodi poistaa juoman ostoskorista ja palauttaa tiedon siit‰, onnistuiko se vai ei.
	 * 
	 * @author Team Yellow
	 * @version 0.1
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String juomaArvo = request.getParameter("juoma_id");
		
		int poistettavaJuoma = Integer.parseInt(juomaArvo);
		
		
		HttpSession session = request.getSession(true);
		Ostoskori ostoskori;
		ostoskori = (Ostoskori) session.getAttribute("Ostoskori");

		if(ostoskori == null){
			ostoskori = new Ostoskori();
			session.setAttribute("Ostoskori", ostoskori);
		}
		
		
		HashMap<Integer, JuomaBean> keratyt_juomat = ostoskori.getKeratyt_juomat();
		
	
		
		if(keratyt_juomat.get(poistettavaJuoma).getMaara()>1){
			keratyt_juomat.get(poistettavaJuoma).setMaara(keratyt_juomat.get(poistettavaJuoma).getMaara()-1);
		}else{
			keratyt_juomat.remove(poistettavaJuoma);
		}
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/NaytaOstoskori");
		dispatcher.forward(request, response);
		
		
		
		
	}

}