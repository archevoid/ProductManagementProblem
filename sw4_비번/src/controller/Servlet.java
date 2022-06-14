package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DeleteDAOImpl;
import model.GroupDAO;
import model.InsertDAOImpl;
import model.SearchDAOImpl;
import model.UpdateDAOImpl;

@WebServlet("*.do")
public class Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Servlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String uri = request.getRequestURI();
		uri = uri.substring(uri.lastIndexOf("/") + 1);
		
		RequestDispatcher rd = null;
		
		setData(request);
		
		switch(uri) {
			case "search.do" :
				rd = request.getRequestDispatcher("search.jsp");
				break;
			case "insert.do" :
				rd = request.getRequestDispatcher("insert.jsp");
				break;
			case "priority.do" :
				SearchDAOImpl priorityDAO = new SearchDAOImpl();
				
				request.setAttribute("list", priorityDAO.getPriority());
				request.setAttribute("otherCol", model.DAO.otherCol);
				request.setAttribute("otherColId", model.DAO.otherColId);
				
				rd = request.getRequestDispatcher("priority.jsp");
				break;
			case "rank.do" :
				SearchDAOImpl rankDAO = new SearchDAOImpl();
				
				request.setAttribute("otherCol", model.DAO.otherCol);
				request.setAttribute("otherColId", model.DAO.otherColId);
				request.setAttribute("list", rankDAO.getRank());
				
				rd = request.getRequestDispatcher("rank.jsp");
				break;
			case "stock.do" :
				SearchDAOImpl stockDAO = new SearchDAOImpl();
				
				request.setAttribute("groupCol", model.DAO.groupCol);
				request.setAttribute("list", stockDAO.getStock());
				
				rd = request.getRequestDispatcher("stock.jsp");
				break;
			default:
				rd = request.getRequestDispatcher("index.html");
		}
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		RequestDispatcher rd;
		
		String uri = request.getRequestURI();
		
		uri = uri.substring(uri.lastIndexOf("/") + 1);
		
		setData(request);
		
		switch(uri) {
			case "search.do" :
				SearchDAOImpl searchDAO = new SearchDAOImpl();
				
				request.setAttribute("list", searchDAO.getByCode(request.getParameter("code")));
				
				rd = request.getRequestDispatcher("search.jsp");
				rd.forward(request, response);
				break;
			case "update.do" :
				UpdateDAOImpl updateDAO = new UpdateDAOImpl();
				
				updateDAO.executeUpdate(request);
				
				response.sendRedirect("search.do");
				break;
			case "insert.do" :
				InsertDAOImpl insertDAO = new InsertDAOImpl();
				
				insertDAO.executeInsert(request);
				
				response.sendRedirect("insert.do");
				break;
			case "delete.do" :
				DeleteDAOImpl deleteDAO = new DeleteDAOImpl();
				
				deleteDAO.deleteByCode(request.getParameter("code"));
				
				response.sendRedirect("search.do");
				break;
		}
	}
	
	private void setData(HttpServletRequest request) {
		GroupDAO groupdao = new GroupDAO();
		
		request.setAttribute("group", groupdao.searchGroup("1=1"));
		request.setAttribute("colId", model.DAO.colId);
		request.setAttribute("col", model.DAO.col);
		request.setAttribute("groupColId", model.DAO.groupColId);
	}
}
