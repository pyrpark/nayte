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
 * Servlet implementation class YhteystiedotAukiolo
 */
@WebServlet("/YhteystiedotAukiolo")
public class YhteystiedotAukiolo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
     * @see HttpServlet#HttpServlet()
     * 
     * <code>YhteystiedotAukiolo</code> m‰‰ritt‰‰ metodit, jotka servletin t‰ytyy toteuttaa.
     * 
     * @author Team Yellow
     * @version 0.1
     */
    public YhteystiedotAukiolo() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * 
	 * <code>doGet</code> metodi palauttaa ajantasaiset parametrit ma_pe, la_su sek‰ lounas 
	 * yhteystiedot.jsp -sivulle.
	 * 
	 * @author Team Yellow
	 * @version 0.1
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		aukioloDAO aDao = new aukioloDAO();
		List<Aukiolo> aukiolo = aDao.haeKaikki();
		
		// requestiin talteen
		request.setAttribute("aukiolo", aukiolo);
				
		// jsp hoitaa muotoilun
		request.getRequestDispatcher("Yhteystiedot.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * 
	 * <code>doPost</code> metodi palauttaa t‰ss‰ yhteydess‰ ylemp‰n‰ olevan doGet metodin.
	 * 
	 * @author Team Yellow
	 * @version 0.1
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
