package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BooksDao;
import beans.Book;

/**
 * Servlet implementation class SearchBooks
 */
@WebServlet("/SearchBooks")
public class SearchBooks extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchBooks() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//éxécuté lors de modif/suppr parce qu'on passe par des url 
		String keyword=(String) request.getParameter("keyword"); 
		/*gestion pagination*/
		int page = 1;
        int recordsPerPage = 5;
        if(request.getParameter("page") != null)
            page = Integer.parseInt(request.getParameter("page")); //page actuelle
        List<Book> listeB = BooksDao.findByKeyword(keyword,(page-1)*recordsPerPage, recordsPerPage); //de page actuelle au max : de 0 à 5
        int noOfRecords = BooksDao.countBooksByKeyword(keyword); //nb total d'enregistrement
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage); //nb total de pages possible 
        System.out.println("into GET le mot cle est : " + keyword + " page actuelle " + page + " " + noOfRecords +" "+ noOfPages); 
        System.out.println(listeB); 
        /*fin bloc gestion pagination*/

		// recuperer une liste d'utilisateurs
		request.setAttribute("listeB", listeB);
        request.setAttribute("noOfPages", noOfPages);
        request.setAttribute("currentPage", page);
        request.setAttribute("keyword", keyword);

		// rediriger vers une page : on retourne sur la page davant 
		request.getRequestDispatcher("searchBook.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String keyword=request.getParameter("keyword"); 
		
		/*gestion pagination*/
		int page = 1;
        int recordsPerPage = 5;
        if(request.getParameter("page") != null)
            page = Integer.parseInt(request.getParameter("page")); //page actuelle
        List<Book> listeB = BooksDao.findByKeyword(keyword, (page-1)*recordsPerPage, recordsPerPage); //de page actuelle au max : de 0 à 5
        System.out.println(listeB); 
        int noOfRecords = BooksDao.countBooksByKeyword(keyword); //nb total d'enregistrement
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage); //nb total de pages possible 
        
        /*fin bloc gestion pagination*/
		 
		request.setAttribute("listeB", listeB);
		System.out.println("into POST" + " page actuelle " + page + " " + noOfRecords +" "+ noOfPages); 
        request.setAttribute("noOfPages", noOfPages);
        request.setAttribute("currentPage", page);
        request.setAttribute("keyword", keyword);
		request.getRequestDispatcher("searchBook.jsp").forward(request, response);  
	}

}
