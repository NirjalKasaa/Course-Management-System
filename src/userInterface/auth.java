package userInterface;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dbmanager.DbManager;

public class auth {
	private static PreparedStatement checkEmailStmt;
	private static PreparedStatement retrieveRoleStmt;
	private static PreparedStatement checkEmailExistenceStmt;
	private static PreparedStatement addCredentialStmt;
	private static PreparedStatement addCourseStmt;
	private static PreparedStatement deleteCoureStmt;
	private static PreparedStatement addModuleStmt;
	private static PreparedStatement deleteModuleStmt;
	private static PreparedStatement addTeachStmt;
	private static PreparedStatement deleteTeachStmt;
	private static PreparedStatement addResultsStmt;
	private static PreparedStatement addComboItemsStmt;
	private static PreparedStatement editPasswordStmt;

	public auth(DbManager db) throws SQLException {
		checkEmailStmt = db.getConnection().prepareStatement("SELECT count(*) FROM auth WHERE email=?");
		retrieveRoleStmt = db.getConnection()
				.prepareStatement("SELECT fullName,role FROM auth WHERE email=? AND BINARY password=?");
		checkEmailExistenceStmt = db.getConnection().prepareStatement("SELECT email FROM auth WHERE email=?");
		addCredentialStmt = db.getConnection()
				.prepareStatement("INSERT INTO auth (fullName, email, password, role, phoneNo) VALUES (?,?,?, 'Student',?)");
		addCourseStmt =  db.getConnection().
				prepareStatement("INSERT INTO courses (course_name) VALUES (?)");
		deleteCoureStmt = db.getConnection().
				prepareStatement("DELETE FROM modules WHERE course_id = ?");
		addModuleStmt =  db.getConnection().
				prepareStatement("INSERT INTO modules (module_name ,module_type, course_id) VALUES (?,?,?)");
		deleteModuleStmt = db.getConnection().
				prepareStatement("DELETE FROM modules WHERE module_id = ?");
		addTeachStmt =  db.getConnection().
				prepareStatement("INSERT INTO auth (fullname, email, password, role, phoneNo) VALUES (?,?,?,'Teacher',?)");
		deleteTeachStmt = db.getConnection().prepareStatement("DELETE FROM auth WHERE email = ?");
		addResultsStmt = db.getConnection()
				.prepareStatement("INSERT INTO results(student_id, module_id, marks) VALUES (?,?,?)");
		addComboItemsStmt = db.getConnection()
				.prepareStatement("SELECT course_name FROM courses");
		editPasswordStmt = db.getConnection()
				.prepareStatement("UPDATE `auth` SET `password`=? WHERE `password`=?");
	}

	public static void login(String email, String password) throws Exception {
		try {
			checkEmailStmt.setString(1, email);
			ResultSet rs = checkEmailStmt.executeQuery();
			int count = 0;
			if (rs.next())
				count = rs.getInt(1);
			if (count == 0)
				throw new Exception("No user with this email found!");
			else {
				retrieveRoleStmt.setString(1, email);
				retrieveRoleStmt.setString(2, password);
				rs = retrieveRoleStmt.executeQuery();
				if (rs.next()) {
					switch (rs.getString("role")) {
					case "Student":
						new Dashboard(DbManager.getInstance(),"Student");
						break;
					case "Teacher":
						new Dashboard(DbManager.getInstance(),"Teacher");
						break;
					case "Admin":
						new Dashboard(DbManager.getInstance(),"Admin"); 
						break;
					default:
						throw new Exception("An error occurred while retrieving role!");
					}
				} else
					throw new Exception("Password not valid!");
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new Exception("An error occurred while checking the email and password!");
		}
	}

	public static void addCredential(String name, String email, String password, String phoneNo) throws Exception {
		try {
			checkEmailExistenceStmt.setString(1, email);
			ResultSet rs = checkEmailExistenceStmt.executeQuery();
			if (rs.next()) {
				throw new Exception("Email already in use!");
			} else {
				addCredentialStmt.setString(1, name);
				addCredentialStmt.setString(2, email);
				addCredentialStmt.setString(3, password);
				addCredentialStmt.setString(4, phoneNo);
				addCredentialStmt.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void addCourse( String course_name) throws Exception {
		try {
			addCourseStmt.setString(1, course_name);
			addCourseStmt.executeUpdate();
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new Exception("An error occurred while adding Course!");
		}
	}

	public static void deleteCourse(String course_id) throws Exception {
		try {
			deleteCoureStmt.setString(1,course_id);
			deleteCoureStmt.executeUpdate();
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new Exception("An error occurred while deleting module!");
		}
	}

	public static void addModule(String module_name, String module_type, String course_id) throws Exception {
		try {
			addModuleStmt.setString(1, module_name);
			addModuleStmt.setString(2, module_type);
			addModuleStmt.setString(3, course_id);
			addModuleStmt.executeUpdate();
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new Exception("An error occurred while adding Module!");
		}
	}

	public static void deleteModule(String module_id) throws Exception {
		try {
			deleteModuleStmt.setString(1,module_id);
			deleteModuleStmt.executeUpdate();
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new Exception("An error occurred while deleting module!");

		}
	}

	public static void addTeach(String name, String email, String password, String phoneNo) throws Exception {
		try {
			checkEmailExistenceStmt.setString(1, email);
			ResultSet rs = checkEmailExistenceStmt.executeQuery();
			if (rs.next()) {
				throw new Exception("Email already in use!");
			} else {
				addTeachStmt.setString(1, name);
				addTeachStmt.setString(2, email);
				addTeachStmt.setString(3, password);
				addTeachStmt.setString(4, phoneNo);
				addTeachStmt.executeUpdate();
			}
		}catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new Exception("An error occurred while adding teacher!");
		}
	}

	public static void deleteTeach(String email) throws Exception {
		try {
			deleteTeachStmt.setString(1,email);
			deleteTeachStmt.executeUpdate();
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new Exception("An error occurred while deleting module!");

		}
	}

	public static void addResults(String student_id, String module_id, String marks) throws Exception {
		try {
			addResultsStmt.setString(1, student_id);
			addResultsStmt.setString(2, module_id);
			addResultsStmt.setString(3, marks);
			addResultsStmt.executeUpdate();
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new Exception("An error occurred while deleting module!");

		}
	}

	public PreparedStatement getAddComboItemsStmt() {
		return addComboItemsStmt;
	}

	public static void editPassword(String newpwd, String oldpwd) throws Exception {
		try {
			editPasswordStmt.setString(1, newpwd);
			editPasswordStmt.setString(2, oldpwd);
			editPasswordStmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new Exception("An error occurred while deleting module!");
		}

	}
}
