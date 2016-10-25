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
import beans.PizzaBean;


/**
 * Servlet implementation class OstoskoriServlet
 */
@WebServlet("/OstoskoriServlet")
public class OstoskoriServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
     * @see HttpServlet#HttpServlet()
     * 
     * <codeOstoskoriServlet</code> m‰‰ritt‰‰ metodit, jotka servletin t‰ytyy toteuttaa.
     * 
     * @author Team Yellow
     * @version 0.1
     */
	
    public OstoskoriServlet() {
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
	 * <code>doPost</code> metodi lis‰‰ ostoskoriin k‰ytt‰j‰n ker‰‰m‰t pizzat (mahdollisine lis‰toiveineen ja mausteineen) sek‰
	 * juomat.
	 * 
	 * @author Team Yellow
	 * @version 0.1
	 */
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		Ostoskori ostoskori;
		ostoskori = (Ostoskori) session.getAttribute("Ostoskori");

		
		HashMap<Integer, PizzaBean> keratyt_pizzat;
		HashMap<Integer, JuomaBean> keratyt_juomat;
		
		if(ostoskori == null){
			ostoskori = new Ostoskori();
			session.setAttribute("Ostoskori", ostoskori);
		}
		
		
		
		String mauste1 = request.getParameter("mauste1");
		String mauste2 = request.getParameter("mauste2");
		
		String tayte1 = request.getParameter("tayte1");
		if(tayte1 == null){
			tayte1 = "";
		}
		String tayte2 = request.getParameter("tayte2");
		if(tayte2 == null){
			tayte2 = "";
		}
		String tayte3 = request.getParameter("tayte3");
		if(tayte3 == null){
			tayte3 = "";
		}
		String tayte4 = request.getParameter("tayte4");
		if(tayte4 == null){
			tayte4 = "";
		}
		
		String fantasiaTaytteet = tayte1 + " " + tayte2 + " " + tayte3 + " " + tayte4 + " ";
		
		// FANTASIA TƒYTEHOMMA JƒI KESKEN.. NYT TƒYTTEET YHDESSƒ STRINGISSƒ JOTKA LIIKUTETAAN OSTOSKORIIN --> teht‰v‰ oma muuttuja.
		
		if(mauste1 == null){
			mauste1 = "";
		}
		
		if(mauste2 == null){
			mauste2 = "";
		}
		
		
		String pizza_id = request.getParameter("pizza_id");
		System.out.println("Haettu pizza id on: " +pizza_id);
		if (pizza_id!=null){
			int pizza_id_int = Integer.parseInt(pizza_id);
			keratyt_pizzat = ostoskori.LisaaPizzaOstoskoriin(pizza_id_int, mauste1, mauste2, fantasiaTaytteet);
			session.setAttribute("keratyt_pizzat", keratyt_pizzat);
			System.out.println("Sessionin ostoskorissa on pizzoja: " + keratyt_pizzat);
		}
		
		
		String juoma_id = request.getParameter("juoma_id");
		System.out.println("Haettu juoma id on: " + juoma_id);
		if (juoma_id!=null){
			int juoma_id_int = Integer.parseInt(juoma_id);
			keratyt_juomat = ostoskori.LisaaJuomaOstoskoriin(juoma_id_int);
			session.setAttribute("keratyt_juomat", keratyt_juomat);
			System.out.println("Sessionin ostoskorissa on juomia: " + keratyt_juomat);
		}
		
		System.out.println("SESSION SET ATTRIBUTE ON"+session);
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/MenuServlet");
		dispatcher.forward(request, response);
		
	}

}
