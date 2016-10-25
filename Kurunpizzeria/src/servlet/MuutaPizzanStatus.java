package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MuutaPizzaStatusDAO;

/**
 * Servlet implementation class MuutaPizzanStatus
 */
@WebServlet("/MuutaPizzanStatus")
public class MuutaPizzanStatus extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
     * @see HttpServlet#HttpServlet()
     * 
     * <code>MuutaPizzanStatus</code> m‰‰ritt‰‰ metodit, jotka servletin t‰ytyy toteuttaa.
     * 
     * @author Team Yellow
     * @version 0.1
     */
    public MuutaPizzanStatus() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * 
	 * <code>doGet</code> ei t‰ss‰ yhteydess‰ tee mit‰‰n.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * 
	 * <code>doPost</code> metodi palauttaa tiedon siit‰, onnistuiko tilauksen statuksen muutos ja v‰litt‰‰
	 * sen edelleen henkilokunnanServletin kautta henkilokunta.jsp:lle.
	 * 
	 * @author Team Yellow
	 * @version 0.1
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sArvo = request.getParameter("tilaus_id");
		int intArvo = Integer.parseInt(sArvo);
		
		MuutaPizzaStatusDAO mps = new MuutaPizzaStatusDAO();
		boolean paluu = mps.MuutaPizzaStatus(intArvo);
		
		if(paluu == true){
			System.out.println("Statuksen muutos onnistui");
		}else{
			System.out.println("Statuksen muutos ep‰onnistui");
		}
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/henkilokunnanServlet");
		dispatcher.forward(request, response);
		
		
		
		
	}

}
