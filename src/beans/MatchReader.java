package beans;
import java.io.Serializable;


public class MatchReader implements Serializable{
	private int id; 
	private int userSourceId;
	private int userPlusProcheId;
	private int userPlusLoinId;
	private int evaluationId;
	
	
	public MatchReader() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MatchReader(int id, int userSourceId, int userPlusProcheId,
			int userPlusLoinId, int evaluationId) {
		super();
		this.id = id;
		this.userSourceId = userSourceId;
		this.userPlusProcheId = userPlusProcheId;
		this.userPlusLoinId = userPlusLoinId;
		this.evaluationId = evaluationId;
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
	public int getUserPlusProcheId() {
		return userPlusProcheId;
	}
	public void setUserPlusProcheId(int userPlusProcheId) {
		this.userPlusProcheId = userPlusProcheId;
	}
	public int getUserPlusLoinId() {
		return userPlusLoinId;
	}
	public void setUserPlusLoinId(int userPlusLoinId) {
		this.userPlusLoinId = userPlusLoinId;
	}
	public int getEvaluationId() {
		return evaluationId;
	}
	public void setEvaluationId(int evaluationId) {
		this.evaluationId = evaluationId;
	} 
}