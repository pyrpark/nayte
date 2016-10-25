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
import beans.Ostoskori;
import beans.PizzaBean;

/**
 * Servlet implementation class PoistaPizzaOstoskorista
 */
@WebServlet("/PoistaPizzaOstoskorista")
public class PoistaPizzaOstoskorista extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
     * @see HttpServlet#HttpServlet()
     * 
     * <code>PoistaPizzaOstoskorista</code> m‰‰ritt‰‰ metodit, jotka servletin t‰ytyy toteuttaa.
     * 
     * @author Team Yellow
     * @version 0.1
     */
    public PoistaPizzaOstoskorista() {
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
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * 
	 * <code>doPost</code> metodi poistaa pizzoja ostoskorista ja palauttaa tiedon siit‰, onnistuiko se vai ei.
	 * 
	 * @author Team Yellow
	 * @version 0.1
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pizzaArvo = request.getParameter("pizza_id");
		int poistettavaPizza = Integer.parseInt(pizzaArvo);
		
		
		HttpSession session = request.getSession(true);
		Ostoskori ostoskori;
		ostoskori = (Ostoskori) session.getAttribute("Ostoskori");

		if(ostoskori == null){
			ostoskori = new Ostoskori();
			session.setAttribute("Ostoskori", ostoskori);
		}
		
		HashMap<Integer, PizzaBean> keratyt_pizzat = ostoskori.getKeratyt_pizzat();
		
		if(keratyt_pizzat.get(poistettavaPizza).getMaara()>1){
			keratyt_pizzat.get(poistettavaPizza).setMaara(keratyt_pizzat.get(poistettavaPizza).getMaara()-1);
		}else{
			keratyt_pizzat.remove(poistettavaPizza);
		}
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/NaytaOstoskori");
		dispatcher.forward(request, response);
		
		
		
		
	}

}
