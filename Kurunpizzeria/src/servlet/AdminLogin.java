package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.AdminBean;
import dao.LoginDAO;

/**
 * Servlet implementation class AdminLogin
 */
@WebServlet("/AdminLogin")
public class AdminLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
     * @see HttpServlet#HttpServlet()
     * 
     * <code>AdminLogin</code> m‰‰ritt‰‰ metodit, jotka servletin t‰ytyy toteuttaa.
     * 
     * @author Team Yellow
     * @version 0.1
     */
    public AdminLogin() {
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
	 * <code>doPost</code> varmistaa, ett‰ henkilˆkunnan puolelle p‰‰see kirjautumaan omistaja tai tyˆntekij‰.
	 *  
	 * @author Team Yellow
	 * @version 0.1
	 */

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username = request.getParameter("user");
		String password = request.getParameter("password");
		HttpSession session = request.getSession(true);
		AdminBean admin = null;
		
		LoginDAO login = new LoginDAO();
		
		try {
			admin = login.adminKirjaudu(username, password);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (admin != null){
			System.out.println("Tunnukset oikein!");
			System.out.println("Omistajan tiedot: ");
			System.out.println(admin.getEtunimi());
			System.out.println(admin.getSukunimi());
			session.setAttribute("AdminBean", admin);
			
			//Ohjaus henkilokunnan sivulle 
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/henkilokunnanServlet");
			dispatcher.forward(request, response);
			//request.getRequestDispatcher("henkilokunta.jsp").forward(request, response);
			
			
		}else{
			System.out.println("K‰ytt‰j‰tunnus tai salasana on v‰‰r‰.");
			
			//Ilmoitus kirjaudu sis‰‰n sivulle
			
			request.setAttribute("loginError", "K‰ytt‰j‰tunnus tai salasana on v‰‰r‰!");
			request.getRequestDispatcher("admin_login.jsp").forward(request, response);
		}
		
		
	}

}
