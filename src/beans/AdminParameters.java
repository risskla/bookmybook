package beans;
import java.io.Serializable;
import java.util.Date;


public class AdminParameters implements Serializable {
	private int id; 
	private int algoMatchBook; 
	private int algoMatchReader;
	private Date dateSaisie;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getAlgoMatchBook() {
		return algoMatchBook;
	}
	public void setAlgoMatchBook(int algoMatchBook) {
		this.algoMatchBook = algoMatchBook;
	}
	public int getAlgoMatchReader() {
		return algoMatchReader;
	}
	public void setAlgoMatchReader(int algoMatchReader) {
		this.algoMatchReader = algoMatchReader;
	}
	public Date getDateSaisie() {
		return dateSaisie;
	}
	public void setDateSaisie(Date dateSaisie) {
		this.dateSaisie = dateSaisie;
	}
	public AdminParameters(int id, int algoMatchBook, int algoMatchReader,
			Date dateSaisie) {
		super();
		this.id = id;
		this.algoMatchBook = algoMatchBook;
		this.algoMatchReader = algoMatchReader;
		this.dateSaisie = dateSaisie;
	}
	public AdminParameters() {
		super();
		// TODO Auto-generated constructor stub
	} 
	
	
}