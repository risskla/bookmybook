package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.AdminParameters;

public class AdminParametersDao {

	public static int insert(AdminParameters a) {
		int res = 0;
				
		Connection cnx=null;
		try {
			cnx = ConnexionBDD.getInstance().getCnx();
			
			//Requete
			String sql = "INSERT INTO AdminParameters(id,algoMatchBook,algoMatchReader,dateSaisie) " +
					"VALUES(?,?,?,?)";
			PreparedStatement ps = cnx.prepareStatement(sql);
			ps.setInt(1, a.getId());
			ps.setInt(2, a.getAlgoMatchBook());
			ps.setInt(3, a.getAlgoMatchReader());
			ps.setDate(4, (Date) a.getDateSaisie());
			
			//Execution et traitement de la réponse
			res = ps.executeUpdate();
			
			ConnexionBDD.getInstance().closeCnx();			
		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		return res;
	}

	
	public static int update(AdminParameters a) {
		int res = 0;
		
		Connection cnx=null;
		
		try {
			// chargement du driver
			cnx = ConnexionBDD.getInstance().getCnx(); 
			
			//Requete
			String sql = "UPDATE AdminParameters SET id=?,algoMatchBook=?,algoMatchReader=?,dateSaisie=? WHERE id=?";
			PreparedStatement ps = cnx.prepareStatement(sql); 
			ps.setInt(1, a.getId());
			ps.setInt(2, a.getAlgoMatchBook());
			ps.setInt(3, a.getAlgoMatchReader());
			ps.setDate(4, (Date) a.getDateSaisie());
			ps.setInt(5, a.getId());
			
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
			String sql = "DELETE FROM AdminParameters WHERE id=?";
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
	
	
	public static List<AdminParameters> findAll() {
		List<AdminParameters> le = new ArrayList<AdminParameters>();
		Connection cnx=null;
		try {
			cnx = ConnexionBDD.getInstance().getCnx();
			// ou Class.forName(com.mysql.jdbc.Driver.class.getName());

			
			//Requete
			String sql = "SELECT id, algoMatchBook, algoMatchReader, dateSaisie FROM AdminParameters";
			PreparedStatement ps = cnx.prepareStatement(sql);
			
			//Execution et traitement de la réponse
			ResultSet res = ps.executeQuery();
			
			while(res.next()){
				le.add(new AdminParameters(res.getInt("id"),
						res.getInt("algoMatchBook"),
						res.getInt("algoMatchReader"),
						res.getDate("dateSaisie")
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


	public static AdminParameters find(int id) {

		AdminParameters a = null;
		
		Connection cnx=null;
		try {
			cnx = ConnexionBDD.getInstance().getCnx();
			// ou Class.forName(com.mysql.jdbc.Driver.class.getName());

		
			//Requete
			String sql = "SELECT id, algoMatchBook, algoMatchReader, dateSaisie FROM AdminParameters WHERE id=?";
			PreparedStatement ps = cnx.prepareStatement(sql);
			ps.setInt(1, id);
			
			
			//Execution et traitement de la réponse
			ResultSet res = ps.executeQuery();
			
			while(res.next()){
				a = new AdminParameters(res.getInt("id"),
						res.getInt("algoMatchBook"),
						res.getInt("algoMatchReader"),
						res.getDate("dateSaisie")
						);
				break;
			}
			
			res.close();
			ConnexionBDD.getInstance().closeCnx();			
		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		//

		return a;
	}

	
	public static List<AdminParameters> findAll(int start, int nbElts) {
		List<AdminParameters> la = new ArrayList<AdminParameters>();
		
		Connection cnx=null;
		try {
			cnx = ConnexionBDD.getInstance().getCnx();
			// ou Class.forName(com.mysql.jdbc.Driver.class.getName());

		
			//Requete
			String sql = "SELECT id, algoMatchBook, algoMatchReader, dateSaisie FROM AdminParameters LIMIT ?,?";
			PreparedStatement ps = cnx.prepareStatement(sql);
			ps.setInt(1, start);
			ps.setInt(2, nbElts);
			
			
			//Execution et traitement de la réponse
			ResultSet res = ps.executeQuery();
			
			while(res.next()){
				la.add(new AdminParameters(res.getInt("id"),
						res.getInt("algoMatchBook"),
						res.getInt("algoMatchReader"),
						res.getDate("dateSaisie")
						));
			}
			
			res.close();
			ConnexionBDD.getInstance().closeCnx();			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		//

		return la;
	}
	
	public static int countAdminParameters(){
		
		int counter = 0;
		Connection cnx=null;
		try {
			cnx = ConnexionBDD.getInstance().getCnx();
		
			String sql = "SELECT COUNT(*) FROM AdminParameters";
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
	
public static int getLastParameters(){
	int max = 0;
	Connection cnx=null;
	try {
		cnx = ConnexionBDD.getInstance().getCnx();
	
		String sql = "SELECT MAX(id) FROM AdminParameters";
		PreparedStatement ps = cnx.prepareStatement(sql);
		ResultSet res = ps.executeQuery();
		
		while(res.next()){
		 max = res.getInt("MAX(id)");
		 break;	
		}
		
		ConnexionBDD.getInstance().closeCnx();	
	}catch (SQLException e) {
		e.printStackTrace();
	}
	return max;
}
	
	
	
}