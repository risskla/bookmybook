package beans;
import java.io.Serializable;


public class Book implements Serializable, Comparable<Book>{

	    private int id;
		private String titre;
		private String auteur;
		private String editeur;
		private long isbn;
		private String pays;
		private String genre;
		private int anneePubli;
		private String resume;
		
		public Book() {
			super();
			// TODO Auto-generated constructor stub
		}
		
			
		public Book(int id, String titre, String auteur, String editeur,
				long isbn, String pays, String genre, int anneePubli,
				String resume) {
			super();
			this.id = id;
			this.titre = titre;
			this.auteur = auteur;
			this.editeur = editeur;
			this.isbn = isbn;
			this.pays = pays;
			this.genre = genre;
			this.anneePubli = anneePubli;
			this.resume = resume;
		}


		

		public int getId() {
			return id;
		}


		public void setId(int id) {
			this.id = id;
		}


		public String getTitre() {
			return titre;
		}


		public void setTitre(String titre) {
			this.titre = titre;
		}


		public String getAuteur() {
			return auteur;
		}


		public void setAuteur(String auteur) {
			this.auteur = auteur;
		}


		public String getEditeur() {
			return editeur;
		}


		public void setEditeur(String editeur) {
			this.editeur = editeur;
		}


		public long getIsbn() {
			return isbn;
		}


		public void setIsbn(long isbn) {
			this.isbn = isbn;
		}


		public String getPays() {
			return pays;
		}


		public void setPays(String pays) {
			this.pays = pays;
		}


		public String getGenre() {
			return genre;
		}


		public void setGenre(String genre) {
			this.genre = genre;
		}


		public int getAnneePubli() {
			return anneePubli;
		}


		public void setAnneePubli(int anneePubli) {
			this.anneePubli = anneePubli;
		}


		public String getResume() {
			return resume;
		}


		public void setResume(String resume) {
			this.resume = resume;
		}


		public int compareTo(Book o) {
			return Long.toString(this.isbn).compareTo(Long.toString(o.isbn));
		}
		
}