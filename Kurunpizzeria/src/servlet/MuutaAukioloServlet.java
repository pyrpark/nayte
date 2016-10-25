package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.aukioloDAO;
import dao.muutaAukioloDAO;
import beans.Aukiolo;



/**
 * Servlet implementation class MuutaAukioloServlet
 */
@WebServlet("/lisaa_aukiolo")
public class MuutaAukioloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     * 
     * <code>MuutaAukioloServlet</code> m‰‰ritt‰‰ metodit, jotka servletin t‰ytyy toteuttaa.
     * 
     * @author Team Yellow
     * @version 0.1
     */
    public MuutaAukioloServlet() {
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
	 * <code>doPost</code> metodi palauttaa parametrit ma_pe, la_su sek‰ lounas 
	 * riippuen mit‰ niist‰ k‰ytt‰j‰ on halunnut muuttaa.
	 * 
	 * @author Team Yellow
	 * @version 0.1
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//requestin parametri "etunimi" java-muuttujaan
				String ma_pe = request.getParameter("ma_pe");
				String la_su = request.getParameter("la_su");
				String lounas = request.getParameter("lounas");
				
				if (ma_pe != ""){
					int id = 1;
					muutaAukioloDAO aukiolodao = new muutaAukioloDAO();
					boolean onnistui = aukiolodao.muutaAukiolo(ma_pe, id);
					request.setAttribute("ilmoitus", "Ma-Pe aukioloajat p‰ivitetty!");
					request.setAttribute("vari","#009933");
					System.out.println(onnistui);
				}
				
				if (la_su != ""){
					int id = 2;
					muutaAukioloDAO aukiolodao = new muutaAukioloDAO();
					boolean onnistui = aukiolodao.muutaAukiolo(la_su, id);
					System.out.println(onnistui);
					 request.setAttribute("lasuUpdate", "La-Su aukioloajat p‰ivitetty!");
				}
				
				if (lounas != ""){
					int id = 3;
					muutaAukioloDAO aukiolodao = new muutaAukioloDAO();
					boolean onnistui = aukiolodao.muutaAukiolo(lounas, id);
					System.out.println(onnistui);
					request.setAttribute("lounasUpdate", "Lounas ajankohta p‰ivitetty!");
				}
				
				
				new aukioloDAO();
				List<Aukiolo> aukiolo = aukioloDAO.haeKaikki();
				
				// requestiin talteen
				request.setAttribute("aukiolo", aukiolo);
				
				// jsp hoitaa muotoilun
				request.getRequestDispatcher("henkilokunta.jsp").forward(request, response);
	}

}
