package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import org.h2.Driver;

//import org.h2.jdbcx.JdbcConnectionPool;

public class Conexao {

	private static String URL = "jdbc:postgresql://localhost:5432/ru";
	private static String user = "ruAdmin";
	private static String senha = "admin";

	private static Connection connection;
	protected static PreparedStatement psmt;
	private static ResultSet result;

	public static void initConnection() throws SQLException,
			ClassNotFoundException {
		Class.forName("org.postgresql.Driver");
		connection = DriverManager.getConnection(URL, user, senha);
	}

	public static PreparedStatement prepare(String sql) throws SQLException {

		psmt =  connection.prepareStatement(sql);
		return psmt;
	}

	public static ResultSet executeQuery() throws SQLException
	{
		result = psmt.executeQuery();
		return result;
	}
	public static boolean execute() throws SQLException
	{
		return psmt.execute();
	}
	
	public static void closeConnection() throws SQLException {
		if(!result.isClosed())
			result.close();
		if (!psmt.isClosed())
			psmt.close();
		if(!connection.isClosed())
			connection.close();
	}

}
