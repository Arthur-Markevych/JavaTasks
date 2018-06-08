package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entity.Group;
import entity.User;

public class DBManager {

	private static final String URL = "jdbc:mysql://localhost/epam" + "?user=root&password=123456";

	///////////////////
	// queries

	private static final String SQL_FIND_USER_BY_LOGIN = "SELECT * FROM users WHERE login = ?";

	private static final String SQL_CREATE_NEW_USER = "INSERT INTO users VALUES (DEFAULT, ?)";

	private static final String SQL_FIND_ALL_USERS = "SELECT * FROM users";

	private static final String SQL_DELETE_USER = "DELETE FROM users WHERE id=?";

	private static final String SQL_UPDATE_USER = "UPDATE users SET login=?, WHERE id=?";

	private static final String SQL_CREATE_NEW_GROUP = "INSERT INTO groups VALUES (DEFAULT, ?)";

	private static final String SQL_FIND_ALL_GROUPS = "SELECT * FROM groups";

	private static final String SQL_FIND_GROUP_BY_NAME = "SELECT * FROM groups WHERE name = ?";

	private static final String SQL_DELETE_GROUP = "DELETE FROM groups WHERE id = ? ";

	private static final String SQL_UPDATE_GROUP = "UPDATE groups SET name=? WHERE id=?";

	private static final String SQL_SET_GROUPS_FOR_USER = "INSERT into users_groups VALUES (?, ?)";

	private static final String SQL_FIND_ALL_USER_GROUPS = "SELECT G.*, U.*  FROM groups as G "
			+ "LEFT JOIN users_groups as UG on G.id = UG.group_id "
			+ "LEFT JOIN Users as U on UG.user_id = U.id WHERE U.id = ?";

	///////////////////////////

	private static DBManager instance;

	public static synchronized DBManager getInstance() {
		if (instance == null) {
			instance = new DBManager();
		}
		return instance;
	}

	private DBManager() {
		// nothing to do
	}

	/**
	 * Clear database data before start application.
	 */
	public void clearDataBase() {
		Connection conn = null;
		Statement pstm = null;
		String sql = "delete from users where id > 1;";
		String sql2 = "delete from groups where id > 1;";
		String sql3 = "delete from users_groups where user_id > 1;";
		try {
			conn = getConnection();
			pstm = conn.createStatement();
			pstm.executeUpdate(sql);
			pstm.executeUpdate(sql2);
			pstm.executeUpdate(sql3);
			System.out.println("cleared!");
		} catch (SQLException e) {
			System.out.println("Cannot delete all: " + e.getMessage());
		}

	}

	/////////////////////////

	public Connection getConnection() throws SQLException {
		Connection con = DriverManager.getConnection(URL);
		return con;
	}

	////////////////////////////

	public boolean updateUser(User user) throws DBException {
		boolean res = false;

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = getConnection();
			pstmt = con.prepareStatement(SQL_UPDATE_USER);

			int k = 1;
			pstmt.setString(k++, user.getLogin());
			pstmt.setInt(k++, user.getId());

			res = pstmt.executeUpdate() > 0;
		} catch (SQLException ex) {
			// (1) write to log
			ex.printStackTrace();

			// (2)
			throw new DBException("Cannot update a user:" + user, ex);
		} finally {
			close(con);
		}
		return res;

	}

	public boolean updateGroup(Group group) throws DBException {
		boolean res = false;

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = getConnection();
			pstmt = con.prepareStatement(SQL_UPDATE_GROUP);

			int k = 1;
			pstmt.setString(k++, group.getName());
			pstmt.setInt(k++, group.getId());

			res = pstmt.executeUpdate() > 0;
		} catch (SQLException ex) {
			// (1) write to log
			ex.printStackTrace();

			// (2)
			throw new DBException("Cannot update a group:" + group, ex);
		} finally {
			close(con);
		}
		return res;

	}

	public boolean deleteUser(int userId) throws DBException {
		boolean res = false;

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = getConnection();
			pstmt = con.prepareStatement(SQL_DELETE_USER);

			int k = 1;
			pstmt.setInt(k++, userId);

			res = pstmt.executeUpdate() > 0;
		} catch (SQLException ex) {
			// (1) write to log
			ex.printStackTrace();

			// (2)
			throw new DBException("Cannot delete a user with id:" + userId, ex);
		} finally {
			close(con);
		}
		return res;

	}

	public boolean deleteGroup(Group group) throws DBException {
		boolean res = false;
		int groupId = group.getId();

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = getConnection();
			pstmt = con.prepareStatement(SQL_DELETE_GROUP);

			int k = 1;
			pstmt.setInt(k++, groupId);

			res = pstmt.executeUpdate() > 0;
		} catch (SQLException ex) {
			// (1) write to log
			ex.printStackTrace();

			// (2)
			throw new DBException("Cannot delete a group with id:" + groupId, ex);
		} finally {
			close(con);
		}
		return res;

	}

	public boolean insertUser(User user) throws DBException {
		boolean res = false;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = getConnection();
			pstmt = con.prepareStatement(SQL_CREATE_NEW_USER, Statement.RETURN_GENERATED_KEYS);

			int k = 1;
			pstmt.setString(k++, user.getLogin());

			if (pstmt.executeUpdate() > 0) {
				rs = pstmt.getGeneratedKeys();
				if (rs.next()) {
					user.setId(rs.getInt(1));
					res = true;
				}
			}
		} catch (SQLException ex) {
			// (1) write to log
			ex.printStackTrace();

			// (2)
			throw new DBException("Cannot create a user:" + user, ex);
		} finally {
			close(con);
		}
		return res;
	}

	public boolean insertGroup(Group group) throws DBException {
		boolean res = false;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = getConnection();
			pstmt = con.prepareStatement(SQL_CREATE_NEW_GROUP, Statement.RETURN_GENERATED_KEYS);

			int k = 1;
			pstmt.setString(k++, group.getName());

			if (pstmt.executeUpdate() > 0) {
				rs = pstmt.getGeneratedKeys();
				if (rs.next()) {
					group.setId(rs.getInt(1));
					res = true;
				}
			}
		} catch (SQLException ex) {
			// (1) write to log
			ex.printStackTrace();

			// (2)
			throw new DBException("Cannot create a group:" + group, ex);
		} finally {
			close(con);
		}
		return res;
	}

	public Group getGroup(String name) throws DBException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Group group = null;

		try {
			con = getConnection();
			pstmt = con.prepareStatement(SQL_FIND_GROUP_BY_NAME);

			int k = 1;
			pstmt.setString(k++, name);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				group = extractGroup(rs);
			}
		} catch (SQLException ex) {
			// (1) write to log
			ex.printStackTrace();

			// (2)
			throw new DBException("Cannot obtain a user by login", ex);
		} finally {
			close(rs);
			close(pstmt);
			close(con);
		}
		return group;
	}

	public User getUser(String login) throws DBException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		User user = null;

		try {
			con = getConnection();
			pstmt = con.prepareStatement(SQL_FIND_USER_BY_LOGIN);

			int k = 1;
			pstmt.setString(k++, login);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				user = extractUser(rs);
			}
		} catch (SQLException ex) {
			// (1) write to log
			ex.printStackTrace();
			// log.error("Cannot obtain a user by login", ex);

			// (2)
			throw new DBException("Cannot obtain a user by login", ex);
		} finally {
			close(rs);
			close(pstmt);
			close(con);
		}
		return user;
	}

	public List<User> findAllUsers() throws DBException {
		List<User> users = new ArrayList<>();

		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			con = getConnection();
			stmt = con.createStatement();

			rs = stmt.executeQuery(SQL_FIND_ALL_USERS);

			while (rs.next()) {
				users.add(extractUser(rs));
			}
		} catch (SQLException ex) {
			// (1) write to log
			ex.printStackTrace();
			// log.error("Cannot obtain a user by login", ex);

			// (2)
			throw new DBException("Cannot obtain a user by login", ex);
		} finally {
			close(con);
		}
		return users;
	}

	public List<Group> findAllGroups() throws DBException {
		List<Group> groups = new ArrayList<>();
		try {
			Connection con = getConnection();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(SQL_FIND_ALL_GROUPS);
			while (rs.next()) {
				Group group = extractGroup(rs);
				groups.add(group);
			}
			return groups;
		} catch (SQLException e) {
			throw new DBException("Cannot obtain groups", e);
		}
	}

	public List<Group> getUserGroups(User user) throws DBException {
		List<Group> groups = new ArrayList<>();

		Connection con;
		try {
			con = getConnection();
			PreparedStatement stmt = con.prepareStatement(SQL_FIND_ALL_USER_GROUPS);
			stmt.setInt(1, user.getId());
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Group group = extractGroup(rs);
				groups.add(group);
			}
		} catch (SQLException e) {
			throw new DBException("Cannot find groups of " + user + ": ", e);
		}
		return groups;
	}

	public void setGroupsForUser(User user, Group... groups) throws DBException {
		int userId = user.getId();

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(SQL_SET_GROUPS_FOR_USER);
			for (Group group : groups) {
				int k = 1;
				pstmt.setInt(k++, userId);
				pstmt.setInt(k++, group.getId());
				pstmt.addBatch();
			}
			pstmt.executeBatch();

			conn.commit();
			// pstmt.clearBatch();
			pstmt.close();
		} catch (SQLException ex) {
			// (1) Rollback
			try {
				conn.rollback();
			} catch (SQLException e) {
				throw new DBException("Rollback error: " + user, e);
			}
			// (2) write to log
			ex.printStackTrace();

			// (3)
			throw new DBException("Cannot set groups for user: " + user, ex);
		} finally {
			close(conn);
		}

	}

	//////////////////////
	// utils

	private User extractUser(ResultSet rs) throws SQLException {
		User user = new User();
		user.setId(rs.getInt("id"));
		user.setLogin(rs.getString("login"));
		return user;
	}

	private Group extractGroup(ResultSet rs) throws SQLException {
		Group group = new Group();
		group.setId(rs.getInt("id"));
		group.setName(rs.getString("name"));
		return group;
	}

	private void close(AutoCloseable ac) {
		if (ac != null) {
			try {
				ac.close();
			} catch (Exception ex) {
				throw new IllegalStateException("Cannot close " + ac);
			}
		}
	}

}
