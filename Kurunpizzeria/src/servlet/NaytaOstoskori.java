package servlet;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.OstoskorinKasittely;
import beans.JuomaBean;
import beans.Ostoskori;
import beans.PizzaBean;

/**
 * Servlet implementation class NaytaOstoskori
 */
@WebServlet("/NaytaOstoskori")
public class NaytaOstoskori extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
     * @see HttpServlet#HttpServlet()
     * 
     * <code>NaytaOstoskori</code> m‰‰ritt‰‰ metodit, jotka servletin t‰ytyy toteuttaa.
     * 
     * @author Team Yellow
     * @version 0.1
     */
    public NaytaOstoskori() {
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
	 * <code>doPost</code> metodi palauttaa ostoskorin sis‰llˆn (sis‰ltˆ ja hinta) parametrina jsp-sivulle. 
	 * 
	 * @author Team Yellow
	 * @version 0.1
	 */
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		Ostoskori ostoskori;
		ostoskori = (Ostoskori) session.getAttribute("Ostoskori");

		if(ostoskori == null){
			ostoskori = new Ostoskori();
			session.setAttribute("Ostoskori", ostoskori);
		}
		
		HashMap<Integer, PizzaBean> keratyt_pizzat = Ostoskori.getKeratyt_pizzat();
		HashMap<Integer, JuomaBean> keratyt_juomat = Ostoskori.getKeratyt_juomat();
		
		OstoskorinKasittely kasittely = new OstoskorinKasittely();
		
		HashMap<String, Double> OstoskorinData = kasittely.LaskeOstoskorinHinta(keratyt_pizzat, keratyt_juomat);
		double ostoskorinHinta = OstoskorinData.get("hinta");
		double tuotteet = OstoskorinData.get("juomien_maara")+OstoskorinData.get("pizzojen_maara");
		int tuotteetYhteensa = (int) tuotteet;
		System.out.println("Ostoskorissa ker‰ttyj‰ tuotteit‰ yhteens‰: " + tuotteetYhteensa);
		
		
		// lasketaan tuotteet kuinka monta valittuna ja n‰ytet‰‰n jsp:n yl‰kulmassa
		// lis‰t‰‰n booleanit pizzoille ja juomille
		// jsp:ss‰ jos boolean ei ole true niin j‰tet‰‰n n‰ytt‰m‰tt‰ ker‰tyt pizzat tai juomat formit
		
		session.setAttribute("hinta", ostoskorinHinta);
		session.setAttribute("keratyt_pizzat", keratyt_pizzat);
		session.setAttribute("tuotteetYhteensa", tuotteetYhteensa);
		
		request.setAttribute("Ostoskori", ostoskori);
		request.getRequestDispatcher("ostoskori.jsp").forward(request, response);
	
	}

}
