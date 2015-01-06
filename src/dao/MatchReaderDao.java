package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.Book;
import beans.MatchReader;

public class MatchReaderDao {

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
	
	public static int delete(int id) {
		int res = 0;
		Connection cnx=null;
		try {
			cnx = ConnexionBDD.getInstance().getCnx();
			// ou Class.forName(com.mysql.jdbc.Driver.class.getName());
				
			//Requete
			String sql = "DELETE FROM MatchReader WHERE id=?";
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
			
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return counter;
	}
	
	
}