package beans;
import java.io.Serializable;


public class User implements Serializable, Comparable<User>{

	    private int id;
		private String login;
		private String mdp;
		private String mail;
		private int role;

		//Constructor :
		public User() {
			super();
			// TODO Auto-generated constructor stub
		}
		public User(int id, String login, String mdp, String mail, int role) {
			super();
			this.id = id;
			this.login = login;
			this.mdp = mdp;
			this.mail = mail;
			this.role = role;
		}



		//Getter&Setter :
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getLogin() {
			return login;
		}
		public void setLogin(String login) {
			this.login = login;
		}
		public String getMdp() {
			return mdp;
		}
		public void setMdp(String mdp) {
			this.mdp = mdp;
		}
		public String getMail() {
			return mail;
		}
		public void setMail(String mail) {
			this.mail = mail;
		}
		public int getRole() {
			return role;
		}
		public void setRole(int role) {
			this.role = role;
		}

		public int compareTo(User u) {
			return Integer.toString(this.id).compareTo(Integer.toString(u.id));
		}
		
}