package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.AsiakasBean;
import beans.Aukiolo;
import dao.aukioloDAO;

/**
 * Servlet implementation class AukioloServlet
 */
@WebServlet("/AukioloServlet")
public class AukioloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet() 
     * 
     * AukioloServlet m‰‰ritt‰‰ metodit, jotka servletin t‰ytyy toteuttaa.
     * 
     * @author Team Yellow
     * @version 0.1
     */
    public AukioloServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * 
	 * <code>doGet</code> metodi palauttaa objektin aukioloDAO.java tiedostosta.
	 * 
	 * @author Team Yellow
	 * @version 0.1
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		aukioloDAO aDao = new aukioloDAO();
		List<Aukiolo> aukiolo = aDao.haeKaikki();
		String kirjautunut = "";
		HttpSession session = request.getSession(true);
		AsiakasBean asiakas;
		asiakas = (AsiakasBean) session.getAttribute("AsiakasBean");
		if(asiakas == null){
			System.out.println("Et ole kirjautunut sis‰‰n");
		}else{
			System.out.println("Olet kirjautunut sis‰‰n");
			kirjautunut = "Hei " +asiakas.geteNimi();
		}
		
		// requestiin talteen
		request.setAttribute("aukiolo", aukiolo);
		request.setAttribute("kirjautunut", kirjautunut);
		
		// jsp hoitaa muotoilun
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * 
	 * <code>doPost</code> metodi ei t‰ss‰ yhteydess‰ palauta mit‰‰n
	 * 
	 * @author Team Yellow
	 * @version 0.1
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
