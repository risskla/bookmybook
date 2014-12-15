package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.Evaluation;

public class EvaluationDao {

	public static int insert(Evaluation e) {
		int res = 0;
				
		Connection cnx=null;
		try {
			cnx = ConnexionBDD.getInstance().getCnx();
			
			//Requete
			String sql = "INSERT INTO evaluation(livreId,userId,note,qualite,interet,lecture,souhaitAuteur,recommand) " +
					"VALUES(?,?,?,?,?,?,?,?)";
			PreparedStatement ps = cnx.prepareStatement(sql);
			ps.setInt(1, e.getLivreId());
			ps.setInt(2, e.getUserId());
			ps.setInt(3, e.getNote());
			ps.setInt(4, e.getQualite());
			ps.setInt(5, e.getInteret());
			ps.setInt(6, e.getLecture());
			ps.setInt(7, e.getSouhaitAuteur());
			ps.setInt(8, e.getRecommand());
			
			
			//Execution et traitement de la réponse
			res = ps.executeUpdate();
			
			ConnexionBDD.getInstance().closeCnx();			
		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		return res;
	}

	
	public static int update(Evaluation e) {
		int res = 0;
		
		Connection cnx=null;
		
		try {
			// chargement du driver
			cnx = ConnexionBDD.getInstance().getCnx(); 
			
			//Requete
			String sql = "UPDATE evaluation SET livreId=?,userId=?,note=?,qualite=?, interet=?, lecture=?, souhaitAuteur=?, recommand=? WHERE id=?";
			PreparedStatement ps = cnx.prepareStatement(sql); 
			ps.setInt(1, e.getLivreId());
			ps.setInt(2, e.getUserId());
			ps.setInt(3, e.getNote());
			ps.setInt(4, e.getQualite());
			ps.setInt(5, e.getInteret());
			ps.setInt(6, e.getLecture());
			ps.setInt(7, e.getSouhaitAuteur());
			ps.setInt(8, e.getRecommand());
			ps.setInt(9, e.getId());
			
			//Execution et traitement de la réponse
			res = ps.executeUpdate();
			
			ConnexionBDD.getInstance().closeCnx();			
		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		return res;
	}
	
	public static int delete(int id) {
		int res = 0;
		Connection cnx=null;
		try {
			cnx = ConnexionBDD.getInstance().getCnx();
			// ou Class.forName(com.mysql.jdbc.Driver.class.getName());

				
			//Requete
			String sql = "DELETE FROM evaluation WHERE id=?";
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
	
	
	public static List<Evaluation> findAll() {
		List<Evaluation> le = new ArrayList<Evaluation>();
		Connection cnx=null;
		try {
			cnx = ConnexionBDD.getInstance().getCnx();
			// ou Class.forName(com.mysql.jdbc.Driver.class.getName());

			
			//Requete
			String sql = "SELECT id, livreId,userId,note,qualite,interet,lecture,souhaitAuteur,recommand FROM evaluation";
			PreparedStatement ps = cnx.prepareStatement(sql);
			
			//Execution et traitement de la réponse
			ResultSet res = ps.executeQuery();
			
			while(res.next()){
				le.add(new Evaluation(res.getInt("id"),
						res.getInt("livreId"),
						res.getInt("userId"),
						res.getInt("note"),
						res.getInt("qualite"),
						res.getInt("interet"),
						res.getInt("lecture"),
						res.getInt("souhaitAuteur"),
						res.getInt("recommand")
						));
			}
			
			res.close();
			ConnexionBDD.getInstance().closeCnx();			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		//

		return le;
	}


	public static Evaluation find(int id) {

		Evaluation e = null;
		
		Connection cnx=null;
		try {
			cnx = ConnexionBDD.getInstance().getCnx();
			// ou Class.forName(com.mysql.jdbc.Driver.class.getName());

		
			//Requete
			String sql = "SELECT id, livreId,userId,note,qualite,interet,lecture,souhaitAuteur,recommand FROM evaluation WHERE id=?";
			PreparedStatement ps = cnx.prepareStatement(sql);
			ps.setInt(1, id);
			
			
			//Execution et traitement de la réponse
			ResultSet res = ps.executeQuery();
			
			while(res.next()){
				e = new Evaluation(res.getInt("id"),
						res.getInt("livreId"),
						res.getInt("userId"),
						res.getInt("note"),
						res.getInt("qualite"),
						res.getInt("interet"),
						res.getInt("lecture"),
						res.getInt("souhaitAuteur"),
						res.getInt("recommand")
						);
				break;
			}
			
			res.close();
			ConnexionBDD.getInstance().closeCnx();			
		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		//

		return e;
	}

	public static List<Evaluation> findbyuser(int uid) {
		List<Evaluation> le = new ArrayList<Evaluation>();
		
		Connection cnx=null;
		try {
			cnx = ConnexionBDD.getInstance().getCnx();
			// ou Class.forName(com.mysql.jdbc.Driver.class.getName());

		
			//Requete
			String sql = "SELECT id, livreId,userId,note,qualite,interet,lecture,souhaitAuteur,recommand FROM evaluation WHERE userId=?";
			PreparedStatement ps = cnx.prepareStatement(sql);
			ps.setInt(1, uid);
			
			
			//Execution et traitement de la réponse
			ResultSet res = ps.executeQuery();
			
			while(res.next()){
				le.add(new Evaluation(res.getInt("id"),
						res.getInt("livreId"),
						res.getInt("userId"),
						res.getInt("note"),
						res.getInt("qualite"),
						res.getInt("interet"),
						res.getInt("lecture"),
						res.getInt("souhaitAuteur"),
						res.getInt("recommand")
						));
			}
			
			res.close();
			ConnexionBDD.getInstance().closeCnx();			
		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		//

		return le;
	}
	
	public static List<Evaluation> findByBook(int bid, int start, int nbElts) {
		List<Evaluation> le = new ArrayList<Evaluation>();
		
		Connection cnx=null;
		try {
			cnx = ConnexionBDD.getInstance().getCnx();
			// ou Class.forName(com.mysql.jdbc.Driver.class.getName());

		
			//Requete
			String sql = "SELECT id, livreId,userId,note,qualite,interet,lecture,souhaitAuteur,recommand FROM evaluation WHERE livreId=? LIMIT ?,?";
			PreparedStatement ps = cnx.prepareStatement(sql);
			ps.setInt(1, bid);
			ps.setInt(2, start);
			ps.setInt(3, nbElts);
			
			
			//Execution et traitement de la réponse
			ResultSet res = ps.executeQuery();
			
			while(res.next()){
				le.add(new Evaluation(res.getInt("id"),
						res.getInt("livreId"),
						res.getInt("userId"),
						res.getInt("note"),
						res.getInt("qualite"),
						res.getInt("interet"),
						res.getInt("lecture"),
						res.getInt("souhaitAuteur"),
						res.getInt("recommand")
						));
			}
			
			res.close();
			ConnexionBDD.getInstance().closeCnx();			
		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		//

		return le;
	}
	public static List<Evaluation> findAll(int start, int nbElts) {
		List<Evaluation> le = new ArrayList<Evaluation>();
		
		Connection cnx=null;
		try {
			cnx = ConnexionBDD.getInstance().getCnx();
			// ou Class.forName(com.mysql.jdbc.Driver.class.getName());

		
			//Requete
			String sql = "SELECT id, livreId,userId,note,qualite,interet,lecture,souhaitAuteur,recommand FROM evaluation LIMIT ?,?";
			PreparedStatement ps = cnx.prepareStatement(sql);
			ps.setInt(1, start);
			ps.setInt(2, nbElts);
			
			
			//Execution et traitement de la réponse
			ResultSet res = ps.executeQuery();
			
			while(res.next()){
				le.add(new Evaluation(res.getInt("id"),
						res.getInt("livreId"),
						res.getInt("userId"),
						res.getInt("note"),
						res.getInt("qualite"),
						res.getInt("interet"),
						res.getInt("lecture"),
						res.getInt("souhaitAuteur"),
						res.getInt("recommand")
						));
			}
			
			res.close();
			ConnexionBDD.getInstance().closeCnx();			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		//

		return le;
	}
	
	public static int countEval(){
		
		int counter = 0;
		Connection cnx=null;
		try {
			cnx = ConnexionBDD.getInstance().getCnx();
		
			String sql = "SELECT COUNT(*) FROM evaluation";
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
	
	public static int countEvalByBook(int idBook){
		
		int counter = 0;
		Connection cnx=null;
		try {
			cnx = ConnexionBDD.getInstance().getCnx();
		
			String sql = "SELECT COUNT(*) FROM evaluation WHERE livreId=?";
			PreparedStatement ps = cnx.prepareStatement(sql);
			ps.setInt(1, idBook);
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
	
}