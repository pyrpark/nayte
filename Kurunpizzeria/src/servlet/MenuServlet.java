package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.JuomaBean;
import beans.Ostoskori;
import beans.PizzaBean;
import beans.TayteBean;
import dao.MenuDAO;
import dao.OstoskorinKasittely;

/**
 * Servlet implementation class MenuServlet
 */
@WebServlet("/MenuServlet")
public class MenuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     * 
     * <code>MenuServlet</code> m‰‰ritt‰‰ metodit, jotka servletin t‰ytyy toteuttaa.
     */
    public MenuServlet() {
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
	 * <code>doPost</code> :in teht‰vi‰ ovat:
	 * 1. Listata ruoka- ja juomalistat niin, ett‰ menu.jsp voi ne n‰ytt‰‰
	 * 2. K‰sitell‰ ostoskoria ja n‰ytt‰‰ siell‰ olevien tuotteiden lkm.
	 *  
	 * @author Team Yellow
	 * @version 1.0
	 */
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			// Jos ostoskorissa on tavaraa lis‰t‰‰n ne if lausekkeella requestiin/responseen
		
			try {
			
			ArrayList<PizzaBean> pizzat = MenuDAO.listaaPizzat();
			ArrayList<TayteBean> taytteet = MenuDAO.listaaTaytteet();
			ArrayList<JuomaBean> juomat = MenuDAO.listaaJuomat();
			ArrayList<String> pizzatvalmis = MenuDAO.teePizzat();
			ArrayList<String> juomatvalmis = MenuDAO.teeJuomat();
			ArrayList<TayteBean> Taytelista = MenuDAO.TayteLista();
			
			
			request.setAttribute("pizzat", pizzat);
			request.setAttribute("taytteet", taytteet);
			request.setAttribute("juomat", juomat);
			request.setAttribute("valmiitpizzat", pizzatvalmis);
			request.setAttribute("valmiitjuomat", juomatvalmis);
			request.setAttribute("Taytelista", Taytelista);
			
			OstoskorinKasittely kasittely = new OstoskorinKasittely();
			
			HashMap<String, Double> OstoskorinData = kasittely.LaskeOstoskorinHinta(Ostoskori.getKeratyt_pizzat(), Ostoskori.getKeratyt_juomat());
			double tuotteet = OstoskorinData.get("juomien_maara")+OstoskorinData.get("pizzojen_maara");
			int tuotteetYhteensa = (int) tuotteet;
			System.out.println("Ostoskorissa ker‰ttyj‰ tuotteit‰ yhteens‰: " + tuotteetYhteensa);
			
			request.setAttribute("tuotteetYhteensa", tuotteetYhteensa);
			
			
			System.out.println(pizzat);
			System.out.println(taytteet);
			System.out.println(juomat);
			System.out.println(Taytelista);
			request.getRequestDispatcher("menu.jsp").forward(request, response);
			
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Virhe servletin toiminnassa. Katso lis‰tiedot virheilmoituksesta.");
		}
	}
			
	}


