package beans;
import java.io.Serializable;


public class Evaluation implements Serializable {

	private int id; 
	private int livreId;
	private int userId;
	private int note;
	private int qualite; 
	private int interet; 
	private int lecture; 
	private int souhaitAuteur; 
	private int recommand;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getLivreId() {
		return livreId;
	}
	public void setLivreId(int livreId) {
		this.livreId = livreId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getNote() {
		return note;
	}
	public void setNote(int note) {
		this.note = note;
	}
	public int getQualite() {
		return qualite;
	}
	public void setQualite(int qualite) {
		this.qualite = qualite;
	}
	public int getInteret() {
		return interet;
	}
	public void setInteret(int interet) {
		this.interet = interet;
	}
	public int getLecture() {
		return lecture;
	}
	public void setLecture(int lecture) {
		this.lecture = lecture;
	}
	public int getSouhaitAuteur() {
		return souhaitAuteur;
	}
	public void setSouhaitAuteur(int souhaitAuteur) {
		this.souhaitAuteur = souhaitAuteur;
	}
	public int getRecommand() {
		return recommand;
	}
	public void setRecommand(int recommand) {
		this.recommand = recommand;
	}
	public Evaluation(int id, int livreId, int userId, int note, int qualite,
			int interet, int lecture, int souhaitAuteur, int recommand) {
		super();
		this.id = id;
		this.livreId = livreId;
		this.userId = userId;
		this.note = note;
		this.qualite = qualite;
		this.interet = interet;
		this.lecture = lecture;
		this.souhaitAuteur = souhaitAuteur;
		this.recommand = recommand;
	}
	public Evaluation() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
}