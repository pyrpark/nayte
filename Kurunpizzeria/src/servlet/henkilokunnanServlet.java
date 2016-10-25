package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.AdminBean;
import beans.Aukiolo;
import beans.Juoma;
import beans.JuomaBean;
import beans.PizzaBean;
import beans.TilattuJuomaBean;
import beans.TilattuPizzaBean;
import beans.TilausBean;
import dao.MenuDAO;
import dao.aukioloDAO;
import dao.juomaDAO;
import dao.tilausDAO;

/**
 * Servlet implementation class henkilokunnanServlet
 */
@WebServlet("/henkilokunnanServlet")
public class henkilokunnanServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
     * @see HttpServlet#HttpServlet()
     * 
     * <code>henkilokunnanServlet</code> m‰‰ritt‰‰ metodit, jotka servletin t‰ytyy toteuttaa.
     * 
     * @author Team Yellow
     * @version 0.1
     */
	
    public henkilokunnanServlet() {
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
	 * <code>doPost</code> :in teht‰v‰ on listata ajantasaiset pizza- ja juomalistat henkilokunta.jsp:lle jokaisen
	 * muutoksen (lis‰ys, poisto) j‰lkeen.
	 * 
	 * @author Team Yellow
	 * @version 0.1
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(true);
		String kirjautunut = "";
		AdminBean admin;
		admin = (AdminBean) session.getAttribute("AdminBean");
		if (admin==null){
			System.out.println("Et ole kirjautunut sis‰‰n.");
		}
		else{
			System.out.println("Olet kirjautunut sis‰‰n.");
			kirjautunut = "Hei "+ admin.getEtunimi();
		



		
		
		
		/*Kaikkien listausten tekeminen (pizzat, juomat, tilaukset, aukioloajat...):
		 * - Ensin tallenetaan tiedot ArrayListeihin kyseess‰ olevan DAO:n metodilla.
		 * - Sen j‰lkeen asetetaan jsp:n attribuuteille ArrayListit arvoiksi, jotka k‰sitell‰‰n
		 * jsp:n puolella.*/
		
		

		ArrayList<String> pizzatvalmis = MenuDAO.teePizzat();
		ArrayList<Juoma> juomat = juomaDAO.haeKaikki();
		
		ArrayList<TilausBean> tilatutTilaukset = tilausDAO.haeTilaus();
		ArrayList<TilattuPizzaBean> tilatutPizzat = tilausDAO.haeTilatutPizzat();
		ArrayList<TilattuJuomaBean> tilatutJuomat= tilausDAO.haeTilatutJuomat();
		
		ArrayList<Aukiolo> aukiolot = aukioloDAO.haeKaikki();
		request.setAttribute("tilaukset", tilatutTilaukset);
		request.setAttribute("tilatutPizzat", tilatutPizzat);
		request.setAttribute("tilatutJuomat", tilatutJuomat);
		request.setAttribute("aukiolo", aukiolot);
		
		request.setAttribute("pizzat", pizzatvalmis);
		request.setAttribute("juomat", juomat);
		
		request.setAttribute("kirjautunut", kirjautunut);
		
		request.getRequestDispatcher("henkilokunta.jsp").forward(request, response);
		}
	}

}
