package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.Book;

public class BooksDao {

	public static int insert(Book b) {
		int res = 0;
				
		Connection cnx=null;
		try {
			cnx = ConnexionBDD.getInstance().getCnx();
			
			//Requete
			String sql = "INSERT INTO book(titre,auteur,editeur,isbn, pays, genre, anneePubli, resume) " +
					"VALUES(?,?,?,?,?,?,?,?)";
			PreparedStatement ps = cnx.prepareStatement(sql);
			ps.setString(1, b.getTitre());
			ps.setString(2, b.getAuteur());
			ps.setString(3, b.getEditeur());
			ps.setInt(4, b.getIsbn());
			ps.setString(5, b.getPays());
			ps.setString(6, b.getGenre());
			ps.setInt(7, b.getAnneePubli());
			ps.setString(8, b.getResume());
			
			
			//Execution et traitement de la réponse
			res = ps.executeUpdate();
			
			ConnexionBDD.getInstance().closeCnx();			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return res;
	}

	
	public static int update(Book b) {
		int res = 0;
		
		Connection cnx=null;
		
		try {
			// chargement du driver
			cnx = ConnexionBDD.getInstance().getCnx();
			System.out.println("modif"); 
			
			System.out.println(b.getTitre()); 
			System.out.println(b.getAuteur());
			System.out.println(b.getEditeur());
			System.out.println(b.getIsbn()); 
			System.out.println(b.getPays()); 
			System.out.println(b.getGenre()); 
			System.out.println(b.getAnneePubli()); 
			System.out.println(b.getResume()); 
			
			//Requete
			String sql = "UPDATE book SET titre=?,auteur=?,editeur=?,isbn=?, pays=?, genre=?, anneePubli=?, resume=? WHERE id=?";
			PreparedStatement ps = cnx.prepareStatement(sql);
			ps.setString(1, b.getTitre());
			ps.setString(2, b.getAuteur());
			ps.setString(3, b.getEditeur());
			ps.setInt(4, b.getIsbn());
			ps.setString(5, b.getPays());
			ps.setString(6, b.getGenre());
			ps.setInt(7, b.getAnneePubli());
			ps.setString(8, b.getResume());
			ps.setInt(9, b.getId());
			
			//Execution et traitement de la réponse
			res = ps.executeUpdate();
			System.out.println("modif2"); 
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
			String sql = "DELETE FROM book WHERE id=?";
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
	
	
	public static List<Book> findAll() {
		List<Book> lb = new ArrayList<Book>();
		Connection cnx=null;
		try {
			cnx = ConnexionBDD.getInstance().getCnx();
			// ou Class.forName(com.mysql.jdbc.Driver.class.getName());

			
			//Requete
			String sql = "SELECT id, titre,auteur,editeur,isbn,pays, genre, anneePubli, resume FROM book";
			PreparedStatement ps = cnx.prepareStatement(sql);
			
			//Execution et traitement de la réponse
			ResultSet res = ps.executeQuery();
			
			while(res.next()){
				lb.add(new Book(res.getInt("id"),
						res.getString("titre"),
						res.getString("auteur"),
						res.getString("editeur"),
						res.getInt("isbn"),
						res.getString("pays"),
						res.getString("genre"),
						res.getInt("anneePubli"),
						res.getString("resume")
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


	public static Book find(int id) {

		Book b = null;
		
		Connection cnx=null;
		try {
			cnx = ConnexionBDD.getInstance().getCnx();
			// ou Class.forName(com.mysql.jdbc.Driver.class.getName());

		
			//Requete
			String sql = "SELECT id,titre, auteur,editeur,isbn,pays,genre,anneePubli,resume FROM book WHERE id=?";
			PreparedStatement ps = cnx.prepareStatement(sql);
			ps.setInt(1, id);
			
			
			//Execution et traitement de la réponse
			ResultSet res = ps.executeQuery();
			
			while(res.next()){
				b = new Book(res.getInt("id"),
						res.getString("titre"),
						res.getString("auteur"),
						res.getString("editeur"),
						res.getInt("isbn"),
						res.getString("pays"),
						res.getString("genre"),
						res.getInt("anneePubli"),
						res.getString("resume"));
				break;
			}
			
			res.close();
			ConnexionBDD.getInstance().closeCnx();			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		//

		return b;
	}
	public static List<Book> findAll(int start, int nbElts) {
		List<Book> lb = new ArrayList<Book>();
		
		Connection cnx=null;
		try {
			cnx = ConnexionBDD.getInstance().getCnx();
			// ou Class.forName(com.mysql.jdbc.Driver.class.getName());

		
			//Requete
			String sql = "SELECT id,titre, auteur,editeur,isbn,pays,genre,anneePubli,resume FROM book LIMIT ?,?";
			PreparedStatement ps = cnx.prepareStatement(sql);
			ps.setInt(1, start);
			ps.setInt(2, nbElts);
			
			
			//Execution et traitement de la réponse
			ResultSet res = ps.executeQuery();
			
			while(res.next()){
				lb.add(new Book(res.getInt("id"),
						res.getString("titre"),
						res.getString("auteur"),
						res.getString("editeur"),
						res.getInt("isbn"),
						res.getString("pays"),
						res.getString("genre"),
						res.getInt("anneePubli"),
						res.getString("resume")));
			}
			
			res.close();
			ConnexionBDD.getInstance().closeCnx();			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		//

		return lb;
	}
	
	public static int countBooks(){
		
		int counter = 0;
		Connection cnx=null;
		try {
			cnx = ConnexionBDD.getInstance().getCnx();
		
			String sql = "SELECT COUNT(*) FROM book";
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
	
}