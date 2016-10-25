package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.AsiakasBean;
import dao.LoginDAO;
import beans.Ostoskori;
import beans.PizzaBean;

/**
 * Servlet implementation class AsiakasLogin
 */
@WebServlet("/AsiakasLogin")
public class AsiakasLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
     * @see HttpServlet#HttpServlet()
     * 
     * <code>AsiakasLogin</code> m‰‰ritt‰‰ metodit, jotka servletin t‰ytyy toteuttaa.
     * 
     * @author Team Yellow
     * @version 0.1
     */
    public AsiakasLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * 
	 * <code>doGet</code> metodi ei t‰ss‰ yhteydess‰ tee mit‰‰n.
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
	 * <code>AsiakasLogin</code> :in teht‰v‰ on sallia asiakkaan kirjautua omalle, luodulle tililleen sis‰‰n, jotta
	 * h‰n voisi tilata pizzoja ja juomia palvelun kautta.
	 *  
	 * @author Team Yellow
	 * @version 0.1
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username = request.getParameter("user");
		String password = request.getParameter("password");
		HttpSession session = request.getSession(true);
		AsiakasBean asiakas = null;
		
		LoginDAO login = new LoginDAO();
		
		try {
			asiakas = login.kirjauduSisaan(username, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(asiakas!=null){
			System.out.println("Tunnukset oikein");
			System.out.println("Kannasta haetun asiakkaan tiedot: " +asiakas.geteNimi() + " " +asiakas.getsNimi() + " asiakas id: " + asiakas.getAsiakas_id());
			session.setAttribute("AsiakasBean", asiakas);
		}else{
			System.out.println("K‰ytt‰j‰tunnus tai salasana on v‰‰r‰");
		}
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/AukioloServlet");
		dispatcher.forward(request, response);
	}

}
