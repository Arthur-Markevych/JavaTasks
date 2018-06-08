package entity;

public class User { // users

	private int id;

	private String login;

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

	@Override
	public String toString() {
		return login;
	}
	// @Override
	// public String toString() {
	// return "User [id=" + id + ", login=" + login "]";
	// }

	public static User createUser(String string) {
		User user = new User();
		user.setLogin(string);
		return user;
	}

}
