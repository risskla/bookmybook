package beans;
import java.io.Serializable;


public class MatchBook implements Serializable{
	private int id; 
	private int userSourceId;
	private int livreSuggereId; 
	
	
	public MatchBook(int id, int userSourceId, int livreSuggereId,
			int evaluationId) {
		super();
		this.id = id;
		this.userSourceId = userSourceId;
		this.livreSuggereId = livreSuggereId;
		this.evaluationId = evaluationId;
	}
	public MatchBook() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserSourceId() {
		return userSourceId;
	}
	public void setUserSourceId(int userSourceId) {
		this.userSourceId = userSourceId;
	}
	public int getLivreSuggereId() {
		return livreSuggereId;
	}
	public void setLivreSuggereId(int livreSuggereId) {
		this.livreSuggereId = livreSuggereId;
	}
	public int getEvaluationId() {
		return evaluationId;
	}
	public void setEvaluationId(int evaluationId) {
		this.evaluationId = evaluationId;
	}
	private int evaluationId;
	

}