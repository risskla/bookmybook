package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.User;

public class UserDao {

	public static int insert(User u) {
		int res = 0;
				
		Connection cnx=null;
		try {
			cnx = ConnexionBDD.getInstance().getCnx();
			
			//Requete
			String sql = "INSERT INTO user(login,mdp,mail,role,nom,prenom,age,sexe,adresse,codepostale,ville,telephone) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement ps = cnx.prepareStatement(sql);
			ps.setString(1, u.getLogin());
			ps.setString(2, u.getMdp());
			ps.setString(3, u.getMail());
			ps.setInt(4, u.getRole());
			ps.setString(5, u.getNom());
			ps.setString(6, u.getPrenom());
			ps.setInt(7, u.getAge());
			ps.setString(8, u.getSexe());
			ps.setString(9, u.getAdresse());
			ps.setInt(10, u.getCodepostale());
			ps.setString(11, u.getVille());
			ps.setInt(12, u.getTelephone());
			
			//Execution et traitement de la réponse
			res = ps.executeUpdate();
			
			ConnexionBDD.getInstance().closeCnx();			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return res;
	}

	
	public static int update(User u) {
		int res = 0;
		
		Connection cnx=null;
		
		try {
			// chargement du driver
			cnx = ConnexionBDD.getInstance().getCnx();
			
			//Requete
			String sql = "UPDATE User SET login=?,mdp=?,mail=?,role=?,nom=?,prenom=?,age=?,sexe=?,adresse=?,codepostale=?,ville=?,telephone=? WHERE id=?";
			PreparedStatement ps = cnx.prepareStatement(sql);
			ps.setString(1, u.getLogin());
			ps.setString(2, u.getMdp());
			ps.setString(3, u.getMail());
			ps.setInt(4, u.getRole());
			ps.setString(1, u.getLogin());
			ps.setString(2, u.getMdp());
			ps.setString(3, u.getMail());
			ps.setInt(4, u.getRole());
			ps.setString(5, u.getNom());
			ps.setString(6, u.getPrenom());
			ps.setInt(7, u.getAge());
			ps.setString(8, u.getSexe());
			ps.setString(9, u.getAdresse());
			ps.setInt(10, u.getCodepostale());
			ps.setString(11, u.getVille());
			ps.setInt(12, u.getTelephone());
			
			ps.setInt(13, u.getId());
			
			//Execution et traitement de la réponse
			res = ps.executeUpdate();
			System.out.println("modifuser"); 
			System.out.println(res);
			
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
			String sql = "DELETE FROM User WHERE id=?";
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
	
	
	public static List<User> findAll() {
		List<User> lu = new ArrayList<User>();
		Connection cnx=null;
		try {
			cnx = ConnexionBDD.getInstance().getCnx();
			// ou Class.forName(com.mysql.jdbc.Driver.class.getName());
			
			
			//Requete
			String sql = "SELECT id,login,mdp,mail,role,nom,prenom,age,sexe,adresse,codepostale,ville,telephone FROM User";
			PreparedStatement ps = cnx.prepareStatement(sql);
			
			//Execution et traitement de la réponse
			ResultSet res = ps.executeQuery();
			
			while(res.next()){
				lu.add(new User(
						res.getInt("id"),
						res.getString("login"),
						res.getString("mdp"),
						res.getString("mail"),
						res.getInt("role"),
						res.getString("nom"),
						res.getString("prenom"),
						res.getInt("age"),
						res.getString("sexe"),
						res.getString("adresse"),
						res.getInt("codepostale"),
						res.getString("ville"),
						res.getInt("telephone")
						));
			}
			
			res.close();
			ConnexionBDD.getInstance().closeCnx();			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		//

		return lu;
	}
	
	public static List<User> findAll(int start, int nbElts) {
		List<User> lu = new ArrayList<User>();
		
		Connection cnx=null;
		try {
			cnx = ConnexionBDD.getInstance().getCnx();
			// ou Class.forName(com.mysql.jdbc.Driver.class.getName());

		
			//Requete
			System.out.println("dans le dao : debut et fin : " + start + " " + nbElts); 
			String sql = "SELECT id,login,mdp,mail,role,nom,prenom,age,sexe,adresse,codepostale,ville,telephone FROM user LIMIT ?,?";
			PreparedStatement ps = cnx.prepareStatement(sql);
			ps.setInt(1, start);
			ps.setInt(2, nbElts);
			
			
			//Execution et traitement de la réponse
			ResultSet res = ps.executeQuery();
			
			while(res.next()){
				lu.add(new User(
						res.getInt("id"),
						res.getString("login"),
						res.getString("mdp"),
						res.getString("mail"),
						res.getInt("role"),
						res.getString("nom"),
						res.getString("prenom"),
						res.getInt("age"),
						res.getString("sexe"),
						res.getString("adresse"),
						res.getInt("codepostale"),
						res.getString("ville"),
						res.getInt("telephone")
						));
			}
			
			res.close();
			ConnexionBDD.getInstance().closeCnx();			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		//

		return lu;
	}
	


	public static User find(int id) {

		User u = null;
		
		Connection cnx=null;
		try {
			cnx = ConnexionBDD.getInstance().getCnx();
			// ou Class.forName(com.mysql.jdbc.Driver.class.getName());

		
			//Requete
			String sql = "SELECT id,login,mdp,mail,role,nom,prenom,age,sexe,adresse,codepostale,ville,telephone FROM User WHERE id=?";
			PreparedStatement ps = cnx.prepareStatement(sql);
			ps.setInt(1, id);
			
			
			//Execution et traitement de la réponse
			ResultSet res = ps.executeQuery();
			
			while(res.next()){
				u = new User(						
						res.getInt("id"),
						res.getString("login"),
						res.getString("mdp"),
						res.getString("mail"),
						res.getInt("role"),
						res.getString("nom"),
						res.getString("prenom"),
						res.getInt("age"),
						res.getString("sexe"),
						res.getString("adresse"),
						res.getInt("codepostale"),
						res.getString("ville"),
						res.getInt("telephone")
						);
				break;
			}
			
			res.close();
			ConnexionBDD.getInstance().closeCnx();			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		//

		return u;
	}
	
	public static User findByLogin(String login) {

		User u = null;
		
		Connection cnx=null;
		try {
			cnx = ConnexionBDD.getInstance().getCnx();
			// ou Class.forName(com.mysql.jdbc.Driver.class.getName());

		
			//Requete
			String sql = "SELECT id,login,mdp,mail,role,nom,prenom,age,sexe,adresse,codepostale,ville,telephone FROM User WHERE login=?";
			PreparedStatement ps = cnx.prepareStatement(sql);
			ps.setString(1, login);
			
			
			//Execution et traitement de la réponse
			ResultSet res = ps.executeQuery();
			
			while(res.next()){
				u = new User(						
						res.getInt("id"),
						res.getString("login"),
						res.getString("mdp"),
						res.getString("mail"),
						res.getInt("role"),
						res.getString("nom"),
						res.getString("prenom"),
						res.getInt("age"),
						res.getString("sexe"),
						res.getString("adresse"),
						res.getInt("codepostale"),
						res.getString("ville"),
						res.getInt("telephone")
						);
				break;
			}
			
			res.close();
			ConnexionBDD.getInstance().closeCnx();			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		//

		return u;
	}
	
public static int countUser(){
		
		int counter = 0;
		Connection cnx=null;
		try {
			cnx = ConnexionBDD.getInstance().getCnx();
		
			String sql = "SELECT COUNT(*) FROM User";
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
}