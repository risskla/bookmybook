package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import beans.Book;
import beans.Evaluation;
import beans.MatchBook; 

public class MatchBookDao {

	public static int insert(MatchBook m) {
		int res = 0;
				
		Connection cnx=null;
		try {
			cnx = ConnexionBDD.getInstance().getCnx();
			
			//Requete
			String sql = "INSERT INTO MatchBook(userSourceId,livreSuggereId,evaluationId) " +
					"VALUES(?,?,?)";
			PreparedStatement ps = cnx.prepareStatement(sql);
			ps.setInt(1, m.getUserSourceId());
			ps.setInt(2, m.getLivreSuggereId());
			ps.setInt(3, m.getEvaluationId());

			
			//Execution et traitement de la réponse
			res = ps.executeUpdate();
			ConnexionBDD.getInstance().closeCnx();	
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return res;
	}

	
	public static int update(MatchBook m) {
		int res = 0;
		
		Connection cnx=null;
		
		try {
			// chargement du driver
			cnx = ConnexionBDD.getInstance().getCnx();
			
			//Requete
			String sql = "UPDATE MatchBook SET userSourceId=?,livreSuggereId=?,evaluationId=? WHERE id=?";
			PreparedStatement ps = cnx.prepareStatement(sql);
			ps.setInt(1, m.getUserSourceId());
			ps.setInt(2, m.getLivreSuggereId());
			ps.setInt(3, m.getEvaluationId());
			ps.setInt(4, m.getId());
			
			//Execution et traitement de la réponse
			res = ps.executeUpdate();
			
			ConnexionBDD.getInstance().closeCnx();			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return res;
	}
	
	public static int deleteByEval(int id) {
		int res = 0;
		Connection cnx=null;
		try {
			cnx = ConnexionBDD.getInstance().getCnx();
			// ou Class.forName(com.mysql.jdbc.Driver.class.getName());
				
			//Requete
			String sql = "DELETE FROM MatchBook WHERE evaluationId=?";
			PreparedStatement ps = cnx.prepareStatement(sql);
			ps.setInt(1,id);
			
			//Execution et traitement de la réponse
			res = ps.executeUpdate();
			
			ConnexionBDD.getInstance().closeCnx();			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return res;
	}
	
	public static int deleteByBook(int id) {
		int res = 0;
		Connection cnx=null;
		try {
			cnx = ConnexionBDD.getInstance().getCnx();
			// ou Class.forName(com.mysql.jdbc.Driver.class.getName());
				
			//Requete
			String sql = "DELETE FROM MatchBook WHERE livreSuggereId=?";
			PreparedStatement ps = cnx.prepareStatement(sql);
			ps.setInt(1,id);
			
			//Execution et traitement de la réponse
			res = ps.executeUpdate();
			
			ConnexionBDD.getInstance().closeCnx();			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return res;
	}
	
	
	
	
	public static List<MatchBook> findAll() {
		List<MatchBook> lb = new ArrayList<MatchBook>();
		Connection cnx=null;
		try {
			cnx = ConnexionBDD.getInstance().getCnx();
			// ou Class.forName(com.mysql.jdbc.Driver.class.getName());

			
			//Requete
			String sql = "SELECT id,userSourceId,livreSuggereId,evaluationId resume FROM MatchBook";
			PreparedStatement ps = cnx.prepareStatement(sql);
			
			//Execution et traitement de la réponse
			ResultSet res = ps.executeQuery();
			
			while(res.next()){
				lb.add(new MatchBook(res.getInt("id"),
						res.getInt("userSourceId"),
						res.getInt("livreSuggereId"),
						res.getInt("evaluationId")
						));
			}
			
			res.close();
			ConnexionBDD.getInstance().closeCnx();			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		//

		return lb;
	}
	

	public static MatchBook find(int id) {

		MatchBook m = null;
		
		Connection cnx=null;
		try {
			cnx = ConnexionBDD.getInstance().getCnx();
			// ou Class.forName(com.mysql.jdbc.Driver.class.getName());

		
			//Requete
			String sql = "SELECT id,userSourceId,livreSuggereId,evaluationId FROM MatchBook WHERE id=?";
			PreparedStatement ps = cnx.prepareStatement(sql);
			ps.setInt(1, id);
			
			
			//Execution et traitement de la réponse
			ResultSet res = ps.executeQuery();
			
			while(res.next()){
				m = new MatchBook(res.getInt("id"),
						res.getInt("userSourceId"),
						res.getInt("livreSuggereId"),
						res.getInt("evaluationId")
						);
				break;
			}
			
			res.close();
			ConnexionBDD.getInstance().closeCnx();			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		//

		return m;
	}
	
	public static MatchBook findByEval(int e) {
		MatchBook m=null; 
		Connection cnx=null;

		System.out.println("dans find by eval MatchBookDao"); 
		
		try {
			cnx = ConnexionBDD.getInstance().getCnx();
			// ou Class.forName(com.mysql.jdbc.Driver.class.getName());

			//Requete
			String sql = "SELECT id,userSourceId,livreSuggereId,evaluationId FROM MatchBook WHERE evaluationId=? ";
			PreparedStatement ps = cnx.prepareStatement(sql);
			ps.setInt(1, e);
            
			//Execution et traitement de la réponse
			ResultSet res = ps.executeQuery();
			while(res.next()){
				//System.out.println("il y a un resultat dans la boucle while matchbookdao"); 
				m=new MatchBook(res.getInt("id"),
						res.getInt("userSourceId"),
						res.getInt("livreSuggereId"),
						res.getInt("evaluationId")
					);
			}
			
			res.close();
			ConnexionBDD.getInstance().closeCnx();			
		} catch (SQLException e2) {
			e2.printStackTrace();
		}

        //System.out.println("match book renvoie : "+m); 
		return m;
	}
	
	
	
	public static MatchBook findByUserAndEval(int u, int e) {
		MatchBook m = new MatchBook();
		
		Connection cnx=null;
		try {
			cnx = ConnexionBDD.getInstance().getCnx();
			// ou Class.forName(com.mysql.jdbc.Driver.class.getName());

		
			//Requete
			String sql = "SELECT id,userSourceId,livreSuggereId,evaluationId FROM MatchBook WHERE userSourceId=? AND evaluationId=?";
			PreparedStatement ps = cnx.prepareStatement(sql);
			ps.setInt(1, u);
			ps.setInt(2, e);
			
			
			//Execution et traitement de la réponse
			ResultSet res = ps.executeQuery();
			
			while(res.next()){
				m=new MatchBook(res.getInt("id"),
						res.getInt("userSourceId"),
						res.getInt("livreSuggereId"),
						res.getInt("evaluationId")
						);
			}
			
			res.close();
			ConnexionBDD.getInstance().closeCnx();			
		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		//

		return m;
	}
	
	public static int countMatchBooks(){
		
		int counter = 0;
		Connection cnx=null;
		try {
			cnx = ConnexionBDD.getInstance().getCnx();
		
			String sql = "SELECT COUNT(*) FROM MatchBook";
			PreparedStatement ps = cnx.prepareStatement(sql);
			ResultSet res = ps.executeQuery();
			
			while(res.next()){
			 counter = res.getInt("COUNT(*)");
			 break;
				
			}
			
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return counter;
	}
	
public static int countMatchBooksByUser(int u){
		
		int counter = 0;
		Connection cnx=null;
		try {
			cnx = ConnexionBDD.getInstance().getCnx();
		
			String sql = "SELECT COUNT(*) FROM MatchBook WHERE userSourceId=?";
			PreparedStatement ps = cnx.prepareStatement(sql);
			ps.setInt(1,u);
			
			ResultSet res = ps.executeQuery();
			
			while(res.next()){
			 counter = res.getInt("COUNT(*)");
			 break;
				
			}
			
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return counter;
	}

public static MatchBook calculMatchBook1(int userSourceId, int evalId){
	
	MatchBook m=null; 
	Book b = null;
	Book b2 = null;
	Connection cnx=null;
	ResultSet res=null; 
	Evaluation e=EvaluationDao.find(evalId); 
	int bookId=e.getLivreId(); 
	b=BooksDao.find(bookId); 
	if (BooksDao.countBooks()<2) return null; 
	//CALCUL A COMPLETER
	
	try {
		cnx = ConnexionBDD.getInstance().getCnx();
		
		//-si la rubrique "lire un autre livre du meme auteur" est cochee, et s'il existe d'autres livres du meme auteur en base, alors on prend le premier qui vient ;
		if (e.getSouhaitAuteur()==1) {
			String sql = "SELECT * FROM Book WHERE auteur = ? and id <> ? limit 1 "; 
			PreparedStatement ps = cnx.prepareStatement(sql);
			ps.setString(1,b.getAuteur());
			ps.setInt(2,bookId);
			res = ps.executeQuery();
			while(res.next()){
				b2 = new Book(res.getInt("id"),
						res.getString("titre"),
						res.getString("auteur"),
						res.getString("editeur"),
						res.getLong("isbn"),
						res.getString("pays"),
						res.getString("genre"),
						res.getInt("anneePubli"),
						res.getString("resume"));
				break;
			}
			if (b2!=null) m=new MatchBook(0, userSourceId, b2.getId(), evalId); 
		}
			
		//-si cette rubrique n'est pas cochee ou s'il n'existe pas de livres du meme auteur, et s'il a aime le genre (3,4) alors on lui propose le premier livre du meme genre et avec un auteur different ;
		if (b2==null) {
			if (e.getInteret()>=3) {
				String sql = "SELECT * FROM Book WHERE genre = ? and id <> ? limit 1"; 
				PreparedStatement ps = cnx.prepareStatement(sql);
				ps.setString(1,b.getGenre());
				ps.setInt(2,bookId);
				res = ps.executeQuery();
				while(res.next()){
					b2 = new Book(res.getInt("id"),
							res.getString("titre"),
							res.getString("auteur"),
							res.getString("editeur"),
							res.getLong("isbn"),
							res.getString("pays"),
							res.getString("genre"),
							res.getInt("anneePubli"),
							res.getString("resume"));
					break;
				}
				if (b2!=null) m=new MatchBook(0, userSourceId, b2.getId(), evalId); 
			}
			if (b2==null) {
				//livre au hasard
				return calculMatchBook2(userSourceId, evalId); 
			}
		}
		
		
		res.close();
		ConnexionBDD.getInstance().closeCnx();			
	} catch (SQLException e2) {
		e2.printStackTrace();
	}
	
	System.out.println("calcul fini : livre numero : "+m.getLivreSuggereId()); 
	
	return m; 
}

public static MatchBook calculMatchBook2(int userSourceId, int evalId){
	
	MatchBook m=null; 
	Book b = null;
	Connection cnx=null;
	Evaluation e=EvaluationDao.find(evalId);
	if (BooksDao.countBooks()<2) return null; 
	
	try {
		
		System.out.println("dans le matchbook2"); 
		cnx = ConnexionBDD.getInstance().getCnx(); 
		//On prend un livre au hasard  
		int bookId=e.getLivreId(); 
		String sql = "SELECT * FROM Book WHERE id NOT IN ("
				+ "SELECT livreId FROM Evaluation WHERE userId = ? )"
				+ " ORDER BY RAND() "
				+ "LIMIT 1 ";
		
		System.out.println("ici1"); 
		PreparedStatement ps = cnx.prepareStatement(sql); 
		
		System.out.println("ici2 prim"); 
		ps.setInt(1,userSourceId);
		
		//Execution et traitement de la réponse
		ResultSet res = ps.executeQuery();
		System.out.println("ici2"); 
		while(res.next()){
			b = new Book(res.getInt("id"),
					res.getString("titre"),
					res.getString("auteur"),
					res.getString("editeur"),
					res.getLong("isbn"),
					res.getString("pays"),
					res.getString("genre"),
					res.getInt("anneePubli"),
					res.getString("resume"));
			break;
		}
		System.out.println("ici3"); 
		//il peut alors ne plus y avoir de livre si le user a tout lu ! 
		if (b==null) return null; 
		System.out.println("le livre : "+b.getId()); 
		m=new MatchBook(0, userSourceId, b.getId(), evalId); 
		
		res.close();
		ConnexionBDD.getInstance().closeCnx();			
	} catch (SQLException e2) {
		e2.printStackTrace();
	}
	
	System.out.println("calcul fini : livre numero : "+m.getLivreSuggereId()); 
	
	return m; 
}
	
	
}