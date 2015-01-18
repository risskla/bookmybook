package controllers;

import java.io.IOException;


import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.AdminParameters;
import beans.Book;
import beans.Evaluation;
import beans.MatchBook;
import beans.MatchReader;
import beans.User;
import dao.AdminParametersDao;
import dao.BooksDao;
import dao.EvaluationDao;
import dao.MatchBookDao;
import dao.MatchReaderDao;
import dao.UserDao;

/**
 * Servlet implementation class UpdateMatchs
 */
@WebServlet("/UpdateMatchs")
public class UpdateMatchs extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateMatchs() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		List<Evaluation> EvalList = EvaluationDao.findAll();
		
		int change = 0;
		
		AdminParameters a=AdminParametersDao.find(AdminParametersDao.getLastParameters()); 
		MatchBook m=null;
		MatchReader m2=null; 
		

		
		Book b=null;
		User userPlusProche=null;
		User userPlusLoin=null;
		
		for(Evaluation e : EvalList){
		int tmp_change = 0;
		//Current : (to compare)
		MatchBook currentm=MatchBookDao.findByEval(e.getId());
		MatchReader currentm2=MatchReaderDao.findByEval(e.getId()); 
		//CALCUL DU MATCH BOOK
		
		if (a==null || a.getAlgoMatchBook()==1){ m=MatchBookDao.calculMatchBook1(e.getUserId(), e.getId());}
		else{ m=MatchBookDao.calculMatchBook2(e.getUserId(), e.getId()); }
		
		if (m!=null) {
			if(m.getLivreSuggereId()!=currentm.getLivreSuggereId())
			{
			currentm.setLivreSuggereId(m.getLivreSuggereId());
			MatchBookDao.update(currentm); 
			tmp_change++;
			}
		}
		
		//CALCUL DU MATCH USER
		
		if (a==null || a.getAlgoMatchReader()==1){
			m2=MatchReaderDao.calculMatchReader1(e.getUserId(), e.getId());
		}
		else{ m2=MatchReaderDao.calculMatchReader2(e.getUserId(), e.getId());
		}
		
		if (m2!=null)  {
			if(m2.getUserPlusLoinId()!=currentm2.getUserPlusLoinId())
			{
			currentm2.setUserPlusLoinId(m2.getUserPlusLoinId());
			MatchReaderDao.update(currentm2); 
			tmp_change++;
			}
			if(m2.getUserPlusProcheId()!=currentm2.getUserPlusProcheId())
			{
			currentm2.setUserPlusProcheId(m2.getUserPlusProcheId());
			MatchReaderDao.update(currentm2); 
			tmp_change++;
			}
			if(tmp_change!=0)change++;
		}
		
		//recapitulatif de l'eval et du match nouvellement calculé
		}
		String alertmsg = "";
		
		if(change == 0){
			alertmsg="La base était déjà à jour.";
		}else{
			alertmsg="Mise à jour des matchs effecutée : <strong>" + change + " matchs</strong> modifiés !";
		}

		request.setAttribute("alert",alertmsg);
		request.getRequestDispatcher("GestionEval?action=afficher").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
