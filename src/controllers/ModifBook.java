package controllers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Book;
import dao.BooksDao;

/**
 * Servlet implementation class ModifBook
 */
@WebServlet("/ModifBook")
public class ModifBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifBook() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response); 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int modif=Integer.parseInt(request.getParameter("idModif")); 
		System.out.println(modif);	
		
		String titre = request.getParameter("titre");
		String auteur = request.getParameter("auteur");
		String editeur = request.getParameter("editeur");
		String isbn = request.getParameter("isbn"); //à transformer en int
		String pays = request.getParameter("pays");
		String genre = request.getParameter("genre");
		String anneePubli = request.getParameter("anneePubli");
		String resume = request.getParameter("resume");
		
		
		//****************************************************************
		//Gestion des erreurs sur le formulaire d'ajout d'un livre :
		//****************************************************************
		String alert_msg = "";
		int error = 0;
		int ap=-1;
		long intisbn=-1;
		//sur le Titre :
		if(titre==""){
			alert_msg = alert_msg + "Titre vide !<br>";
			error = error + 1;
		}
		//sur le Auteur :
		if(auteur==""){
			alert_msg = alert_msg + "Auteur vide !<br>";
			error = error + 1;
		}
		//sur le editeur :
		if(editeur==""){
			alert_msg = alert_msg + "Editeur vide !<br>";
			error = error + 1;
		}
		//sur le isbn :
		if(isbn==""){
			alert_msg = alert_msg + "ISBN vide !<br>";
			error = error + 1;
		} else {
			if(!isbn.matches("^\\d{10,13}$")){
				alert_msg = alert_msg + "L'ISBN '" + isbn + "' n'est pas correct !<br>";
				error = error + 1;
			}else{
				intisbn=Long.parseLong(isbn); 
			}
		}
		//sur le pays
		if(pays==""){
			alert_msg = alert_msg + "Age vide !<br>";
			error = error + 1;
		}
		//sur le genre :
		if(genre==""){
			alert_msg = alert_msg + "Sexe vide !<br>";
			error = error + 1;
		}
		//sur anneePubli :
		if(anneePubli==""){
			alert_msg = alert_msg + "Adresse vide !<br>";
			error = error + 1;
		} else {
			if(!anneePubli.matches("^\\d{1,4}$")){
				alert_msg = alert_msg + "L'année '" + anneePubli + "' n'est pas correcte !<br>";
				error = error + 1;
			}else{
				ap=Integer.parseInt(anneePubli); 
			}
		}
		
		Book b = new Book(modif, titre, auteur, editeur, intisbn, pays, genre, ap, resume);
		
		if(error!=0){
			request.setAttribute("alert", alert_msg);
			request.setAttribute("bModif", b);
			request.getRequestDispatcher("WEB-INF/ModifBook.jsp").forward(request, response); 
		} else {

			
			Book b2=BooksDao.find(modif);
			if (b2==null){
				if(modif==-1){
					//Il s'agit en réalité d'un addbook on lui renvoie donc :
					request.getRequestDispatcher("AddBook").forward(request, response); 
				}else{
				System.out.println("NULL");
				}
			}
			else { 
				BooksDao.update(b);
				
				request.setAttribute("operation","modifié");
				request.setAttribute("titre",titre);
				request.setAttribute("auteur",auteur);
				request.setAttribute("editeur",editeur);
				request.setAttribute("isbn",isbn);
				request.setAttribute("pays",pays);
				request.setAttribute("genre",genre);
				request.setAttribute("anneePubli",anneePubli);
				request.setAttribute("resume",resume);
				request.getRequestDispatcher("WEB-INF/StatusBookModifAdd.jsp").forward(request, response);  
			}
		}
	}

}
