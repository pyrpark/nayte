package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.TilattuJuomaBean;
import beans.TilattuPizzaBean;
import beans.TilausBean;
import dao.tilausDAO;

/**
 * Servlet implementation class VanhatTilaukset
 */
@WebServlet("/VanhatTilaukset")
public class VanhatTilaukset extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
     * @see HttpServlet#HttpServlet()
     * 
     * <code>VanhatTilaukset</code> m‰‰ritt‰‰ metodit, jotka servletin t‰ytyy toteuttaa.
     * 
     * @author Team Yellow
     * @version 0.1
     */
    public VanhatTilaukset() {
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
	 * <code>doPost</code> metodi palauttaa vanhat, jo olemassa olevat tilaukset.
	 * 
	 * @author Team Yellow
	 * @version 0.1
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<TilausBean> tilatutTilaukset = tilausDAO.haeTilaus();
		ArrayList<TilattuPizzaBean> tilatutPizzat = tilausDAO.haeTilatutPizzat();
		ArrayList<TilattuJuomaBean> tilatutJuomat= tilausDAO.haeTilatutJuomat();
		
		
		request.setAttribute("tilaukset", tilatutTilaukset);
		request.setAttribute("tilatutPizzat", tilatutPizzat);
		request.setAttribute("tilatutJuomat", tilatutJuomat);
		
		request.getRequestDispatcher("VanhatTilaukset.jsp").forward(request, response);
	}

}
