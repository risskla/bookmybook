package beans;
import java.io.Serializable;


public class User implements Serializable, Comparable<User>{

	    private int id;
		private String login;
		private String mdp;
		private String mail;
		private int role;
		private String nom;
		private String prenom;
		private int age;
		private String sexe;
		private String adresse;
		private int codepostale;
		private String ville;
		private String telephone;
		
		//Constructors :
		
		public User() {
			super();
		}
		
		public User(int id, String login, String mdp, String mail, int role,
				String nom, String prenom, int age, String sexe,
				String adresse, int codepostale, String ville, String telephone) {
			super();
			this.id = id;
			this.login = login;
			this.mdp = mdp;
			this.mail = mail;
			this.role = role;
			this.nom = nom;
			this.prenom = prenom;
			this.age = age;
			this.sexe = sexe;
			this.adresse = adresse;
			this.codepostale = codepostale;
			this.ville = ville;
			this.telephone = telephone;
		}

		//Getters and setters :
		
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

		public String getNom() {
			return nom;
		}

		public void setNom(String nom) {
			this.nom = nom;
		}

		public String getPrenom() {
			return prenom;
		}

		public void setPrenom(String prenom) {
			this.prenom = prenom;
		}

		public int getAge() {
			return age;
		}

		public void setAge(int age) {
			this.age = age;
		}

		public String getSexe() {
			return sexe;
		}

		public void setSexe(String sexe) {
			this.sexe = sexe;
		}

		public String getAdresse() {
			return adresse;
		}

		public void setAdresse(String adresse) {
			this.adresse = adresse;
		}

		public int getCodepostale() {
			return codepostale;
		}

		public void setCodepostale(int codepostale) {
			this.codepostale = codepostale;
		}

		public String getVille() {
			return ville;
		}

		public void setVille(String ville) {
			this.ville = ville;
		}

		public String getTelephone() {
			return telephone;
		}

		public void setTelephone(String telephone) {
			this.telephone = telephone;
		}
		
		//Compare Function :
		




		public int compareTo(User u) {
			return Integer.toString(this.id).compareTo(Integer.toString(u.id));
		}
		
}