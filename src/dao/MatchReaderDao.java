package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.Book;
import beans.Evaluation;
import beans.MatchBook;
import beans.MatchReader;

public class MatchReaderDao {
	
	public static int update(MatchReader m) {
		int res = 0;
		
		Connection cnx=null;
		
		try {
			// chargement du driver
			cnx = ConnexionBDD.getInstance().getCnx();
			
			//Requete
			String sql = "UPDATE MatchReader SET userSourceId=?,userPlusProcheId=?,userPlusLoinId=?,evaluationId=? WHERE id=?";
			PreparedStatement ps = cnx.prepareStatement(sql);
			ps.setInt(1, m.getUserSourceId());
			ps.setInt(2, m.getUserPlusProcheId());
			ps.setInt(3, m.getUserPlusLoinId());
			ps.setInt(4, m.getEvaluationId());
			ps.setInt(5, m.getId());
			
			//Execution et traitement de la réponse
			res = ps.executeUpdate();
			
			ConnexionBDD.getInstance().closeCnx();			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return res;
	}

	public static int insert(MatchReader m) {
		int res = 0;
				
		Connection cnx=null;
		try {
			cnx = ConnexionBDD.getInstance().getCnx();
			
			//Requete
			String sql = "INSERT INTO MatchReader(userSourceId,userPlusProcheId,userPlusLoinId,evaluationId) " +
					"VALUES(?,?,?,?)";
			PreparedStatement ps = cnx.prepareStatement(sql);
			ps.setInt(1, m.getUserSourceId());
			ps.setInt(2, m.getUserPlusProcheId());
			ps.setInt(3, m.getUserPlusLoinId());
			ps.setInt(4, m.getEvaluationId());

			
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
			String sql = "DELETE FROM MatchReader WHERE evaluationId=?";
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
	
	public static int deleteByUser(int id) {
		int res = 0;
		Connection cnx=null;
		try {
			cnx = ConnexionBDD.getInstance().getCnx();
			// ou Class.forName(com.mysql.jdbc.Driver.class.getName());
				
			//Requete
			String sql = "DELETE FROM MatchReader WHERE userPlusProcheId=? OR userPlusLoinId=?";
			PreparedStatement ps = cnx.prepareStatement(sql);
			ps.setInt(1,id);
			ps.setInt(2,id);
			
			//Execution et traitement de la réponse
			res = ps.executeUpdate();
			
			ConnexionBDD.getInstance().closeCnx();			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return res;
	}
	
	public static int deleteTable1() {
		int res = 0;
		Connection cnx=null;
		try {
			cnx = ConnexionBDD.getInstance().getCnx();
			//Requete
			String sql = "DELETE FROM calculMatchUser1";
			PreparedStatement ps = cnx.prepareStatement(sql);
			//Execution et traitement de la réponse
			res = ps.executeUpdate();
			
			ConnexionBDD.getInstance().closeCnx();			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return res;
	}
	
	public static int deleteTable2() {
		int res = 0;
		Connection cnx=null;
		try {
			cnx = ConnexionBDD.getInstance().getCnx();
			//Requete
			String sql = "DELETE FROM calculMatchUser2";
			PreparedStatement ps = cnx.prepareStatement(sql);
			//Execution et traitement de la réponse
			res = ps.executeUpdate();
			
			ConnexionBDD.getInstance().closeCnx();			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return res;
	}
	
	public static int countUsersTable1() {
		
		Connection cnx=null;
		int nb=0; 
		try {
			cnx = ConnexionBDD.getInstance().getCnx();
			String sql = "SELECT COUNT(DISTINCT user) as c FROM calculMatchUser1";
			PreparedStatement ps = cnx.prepareStatement(sql);
			ResultSet res = ps.executeQuery();
			
			while(res.next())nb=res.getInt("c"); 

			ConnexionBDD.getInstance().closeCnx();			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return nb;
	}
	
	public static int insertTable1(int userSourceId, int evalId) {
		int res = 0;
		Connection cnx=null;
		Evaluation e=EvaluationDao.find(evalId); 
		int noteGlobaleSource=e.getNote(); 
		System.out.println("dans insertTable1"); 
		
		try {
			cnx = ConnexionBDD.getInstance().getCnx();
			//Requete : pour l'evaluation des autres users qui ont lu les memes livres, on calcule l'écart par rapport à la noteGlobaleSource
			String sql = "INSERT INTO calculMatchUser1(user, book, ecartNoteGLobale) "
					+ "SELECT userId, livreId, note from evaluation where userId<>? "
					+ "and livreId in ("
					+ "select livreid from evaluation where userid=?)"; 
			
			
			PreparedStatement ps = cnx.prepareStatement(sql);
			ps.setInt(1,userSourceId);
			ps.setInt(2,userSourceId);
			//Execution et traitement de la réponse
			res = ps.executeUpdate();
			System.out.println("execution1"); 
			//maintenant on calcule la différence avec la valeur absolue
			sql = "UPDATE calculMatchUser1 SET ecartNoteGlobale=abs(ecartNoteGlobale-?)"; 
			ps = cnx.prepareStatement(sql);
			ps.setInt(1,noteGlobaleSource);
			res = ps.executeUpdate();
			System.out.println("execution2");
			
			ConnexionBDD.getInstance().closeCnx();			
		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		return res;
	}
	
	
public static int insertTable2() {
		int res = 0;
		Connection cnx=null;
		
		
		try {
			cnx = ConnexionBDD.getInstance().getCnx();
			//Requete
			String sql = "INSERT INTO calculMatchUser2 (SELECT user, avg(EcartNoteGlobale) FROM calculMatchUser1 group by user) "; 
			PreparedStatement ps = cnx.prepareStatement(sql);
			//Execution et traitement de la réponse
			res = ps.executeUpdate();
			
			ConnexionBDD.getInstance().closeCnx();			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return res;
	}
	
	public static List<MatchReader> findAll() {
		List<MatchReader> lb = new ArrayList<MatchReader>();
		Connection cnx=null;
		try {
			cnx = ConnexionBDD.getInstance().getCnx();
			// ou Class.forName(com.mysql.jdbc.Driver.class.getName());

			
			//Requete
			String sql = "SELECT id,userSourceId,userPlusProcheId,userPlusLoinId,evaluationId resume FROM MatchBook";
			PreparedStatement ps = cnx.prepareStatement(sql);
			
			//Execution et traitement de la réponse
			ResultSet res = ps.executeQuery();
			
			while(res.next()){
				lb.add(new MatchReader(res.getInt("id"),
						res.getInt("userSourceId"),
						res.getInt("userPlusProcheId"),
						res.getInt("userPlusLoinId"),
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
	

	public static MatchReader find(int id) {

		MatchReader m = null;
		
		Connection cnx=null;
		try {
			cnx = ConnexionBDD.getInstance().getCnx();
			// ou Class.forName(com.mysql.jdbc.Driver.class.getName());

		
			//Requete
			String sql = "SELECT id,userSourceId,userPlusProcheId,userPlusLoinId,evaluationId WHERE id=?";
			PreparedStatement ps = cnx.prepareStatement(sql);
			ps.setInt(1, id);
			
			
			//Execution et traitement de la réponse
			ResultSet res = ps.executeQuery();
			
			while(res.next()){
				m = new MatchReader(res.getInt("id"),
						res.getInt("userSourceId"),
						res.getInt("userPlusProcheId"),
						res.getInt("userPlusLoinId"),
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
	
	public static MatchReader findByEval(int e) {
		MatchReader m=null; 
		Connection cnx=null;

		System.out.println("dans find by eval Match reader Dao"); 

		
		try {
			cnx = ConnexionBDD.getInstance().getCnx();
			
			//Requete
			String sql = "SELECT id,userSourceId,userPlusProcheId,userPlusLoinId,evaluationId FROM matchReader WHERE evaluationId=? ";
			PreparedStatement ps = cnx.prepareStatement(sql);
			ps.setInt(1, e);
            
			//Execution et traitement de la réponse
			ResultSet res = ps.executeQuery();
			
			while(res.next()){
				m=new MatchReader(res.getInt("id"),
						res.getInt("userSourceId"),
						res.getInt("userPlusProcheId"),
						res.getInt("userPlusLoinId"),
						res.getInt("evaluationId")
					);
			}
			
			res.close();
			ConnexionBDD.getInstance().closeCnx();			
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
        
		return m;
	}
	
	
	public static List<MatchReader> findAll(int start, int nbElts) {
		List<MatchReader> lb = new ArrayList<MatchReader>();
		
		Connection cnx=null;
		try {
			cnx = ConnexionBDD.getInstance().getCnx();
			// ou Class.forName(com.mysql.jdbc.Driver.class.getName());

		
			//Requete
			String sql = "SELECT  id,userSourceId,userPlusProcheId,userPlusLoinId,evaluationId LIMIT ?,?";
			PreparedStatement ps = cnx.prepareStatement(sql);
			ps.setInt(1, start);
			ps.setInt(2, nbElts);
			
			
			//Execution et traitement de la réponse
			ResultSet res = ps.executeQuery();
			
			while(res.next()){
				lb.add(new MatchReader(res.getInt("id"),
						res.getInt("userSourceId"),
						res.getInt("userPlusProcheId"),
						res.getInt("userPlusLoinId"),
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
	
	public static List<MatchReader> findByUser(int u, int start, int nbElts) {
		List<MatchReader> lb = new ArrayList<MatchReader>();
		
		Connection cnx=null;
		try {
			cnx = ConnexionBDD.getInstance().getCnx();
			// ou Class.forName(com.mysql.jdbc.Driver.class.getName());

		
			//Requete
			String sql = "SELECT id,userSourceId,userPlusProcheId,userPlusLoinId,evaluationId FROM MatchReader WHERE userSourceId= ? LIMIT ?,?";
			PreparedStatement ps = cnx.prepareStatement(sql);
			ps.setInt(1, u);
			ps.setInt(2, start);
			ps.setInt(3, nbElts);
			
			
			//Execution et traitement de la réponse
			ResultSet res = ps.executeQuery();
			
			while(res.next()){
				lb.add(new MatchReader(res.getInt("id"),
						res.getInt("userSourceId"),
						res.getInt("userPlusProcheId"),
						res.getInt("userPlusLoinId"),
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
	
	public static MatchReader findByUserAndEval(int u, int e) {
		MatchReader m = new MatchReader();
		
		Connection cnx=null;
		try {
			cnx = ConnexionBDD.getInstance().getCnx();
			// ou Class.forName(com.mysql.jdbc.Driver.class.getName());

		
			//Requete
			String sql = "SELECT id,userSourceId,userPlusProcheId,userPlusLoinId,evaluationId FROM MatchReader WHERE userSourceId=? AND evaluationId=?";
			PreparedStatement ps = cnx.prepareStatement(sql);
			ps.setInt(1, u);
			ps.setInt(2, e);
			
			
			//Execution et traitement de la réponse
			ResultSet res = ps.executeQuery();
			
			while(res.next()){
				m=new MatchReader(res.getInt("id"),
						res.getInt("userSourceId"),
						res.getInt("userPlusProcheId"),
						res.getInt("userPlusLoinId"),
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
	
	public static int countMatchReaders(){
		
		int counter = 0;
		Connection cnx=null;
		try {
			cnx = ConnexionBDD.getInstance().getCnx();
		
			String sql = "SELECT COUNT(*) FROM MatchReader";
			PreparedStatement ps = cnx.prepareStatement(sql);
			ResultSet res = ps.executeQuery();
			
			while(res.next()){
			 counter = res.getInt("COUNT(*)");
			 break;
				
			}
			
			ConnexionBDD.getInstance().closeCnx();	
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return counter;
	}
	
public static int countMatchReadersByUser(int u){
		
		int counter = 0;
		Connection cnx=null;
		try {
			cnx = ConnexionBDD.getInstance().getCnx();
		
			String sql = "SELECT COUNT(*) FROM MatchReader WHERE userSourceId=?";
			PreparedStatement ps = cnx.prepareStatement(sql);
			ps.setInt(1,u);
			
			ResultSet res = ps.executeQuery();
			
			while(res.next()){
			 counter = res.getInt("COUNT(*)");
			 break;
				
			}
			ConnexionBDD.getInstance().closeCnx();	
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return counter;
	}
public static MatchReader calculMatchReader1(int userSourceId, int evalId){
	
	MatchReader m=null; 
	Book b = null;
	Connection cnx=null;
	Evaluation e=EvaluationDao.find(evalId);
	int userPlusProche=0; 
	int userPlusLoin=0; 
	if (UserDao.countUser()<3) return null; 
	
	try {
		
		//effacer la 1ere table de calcul
		deleteTable1(); 
		//Stocker dans une table1 : User, livre, EcartNoteGlobale
		insertTable1(userSourceId, evalId); 
		System.out.println("apres le remplissage de la premiere table"); 
		//les eval en base peuvent ne pas suffir pour cette methode de calcul : on teste l'autre
		if (countUsersTable1()<2) return calculMatchReader2(userSourceId, evalId); 
		
		
		//effacer la 2e table de calcul
		deleteTable2(); 
		//calculer ensuite dans une table2 : User, MoyenneEcart 
		insertTable2(); 
		System.out.println("apres le remplissage de la 2e table"); 

		//user le plus loin
		cnx = ConnexionBDD.getInstance().getCnx(); 
		String sql = "SELECT user FROM calculMatchUser2 WHERE moyenneEcart IN"
				+ "(SELECT max(moyenneEcart) FROM calculMatchUser2)"
				+ "LIMIT 1"; //en cas d'égalité on prend le premier
		PreparedStatement ps = cnx.prepareStatement(sql); 
		ResultSet res = ps.executeQuery();
		while(res.next()){ 
			userPlusLoin = res.getInt("user");
		}
		
		//user le plus proche
		sql = "SELECT user FROM calculMatchUser2 WHERE moyenneEcart IN"
				+ "(SELECT min(moyenneEcart) FROM calculMatchUser2)"
				+ "LIMIT 1"; //en cas d'égalité on prend le premier
		ps = cnx.prepareStatement(sql); 
		res = ps.executeQuery();
		while(res.next()){ 
			userPlusProche = res.getInt("user");
		}
		
		System.out.println("le plus loin : "+userPlusLoin + " et le plus proche : "+userPlusProche); 
		
		//on ne gere pas les cas d'egalite pour l'instant
		
		System.out.println("ici2"); 
		
		m = new MatchReader(0, userSourceId, userPlusProche, userPlusLoin, evalId); 
		
		res.close();
		ConnexionBDD.getInstance().closeCnx();		
		
	} catch (SQLException e2) {
		e2.printStackTrace();
	}
	
	return m; 
}

public static MatchReader calculMatchReader2(int userSourceId, int evalId){
	
	MatchReader m=null; 
	Book b = null;
	Connection cnx=null;
	int userPlusProche=0; 
	int userPlusLoin=0; 
	System.out.println("nb de users en base : "+UserDao.countUser()); 
	if (UserDao.countUser()<3) return null; 
	
	try {
		cnx = ConnexionBDD.getInstance().getCnx(); 

		//On prend 2 users au hasard
		
		//il faut un nb de users au moins egal à trois !
		
		//requete pour le user le plus proche : nimporte lequel different du user actuel
		String sql = "SELECT id FROM User WHERE id NOT IN ("
				+ "SELECT id FROM User WHERE id = ? )"
				+ " ORDER BY RAND() "
				+ "LIMIT 1 ";
		
		System.out.println("ici1"); 
		PreparedStatement ps = cnx.prepareStatement(sql); 
		
		System.out.println("ici2 prim"); 
		ps.setInt(1,userSourceId);
		
		//Execution et traitement de la réponse
		ResultSet res = ps.executeQuery();
		System.out.println("ici2 avant resultat"); 
		System.out.println(res); 
		while(res.next()){ userPlusProche = res.getInt("id");
		System.out.println("userPlusProche : "+userPlusProche); 
		}
		
		//user le plus loin : n'importe lequel different du user actuel et du user plus proche 
		sql = "SELECT id FROM User WHERE id NOT IN ("
				+ "SELECT id FROM User WHERE id = ? OR id=? )"
				+ " ORDER BY RAND() "
				+ "LIMIT 1 ";
		
		ps = cnx.prepareStatement(sql);
		ps.setInt(1,userSourceId);
		ps.setInt(2,userPlusProche);
		res = ps.executeQuery();
		
		while(res.next()){ userPlusLoin = res.getInt("id"); 
		System.out.println("userPlusLoin : "+userPlusLoin);
		}
		
		
		m = new MatchReader(0, userSourceId, userPlusProche, userPlusLoin, evalId); 
		
		res.close();
		ConnexionBDD.getInstance().closeCnx();			
	} catch (SQLException e2) {
		e2.printStackTrace();
	}
	
	System.out.println("calcul fini : plus proche : "+m.getUserPlusProcheId()+" et user plus loin : " +m.getUserPlusLoinId()); 
	
	return m; 
}
	
	
}