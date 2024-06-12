package cool;

import java.sql.*;
import org.duckdb.DuckDBConnection;

public class DuckDbExplorations {
    final static String CREATE_TABLE = "Create table items (item varchar, value decimal(10,2), count INTEGER)";
    final static String INSERT_INTO_TABLE = "insert into items values ('jeans', 20.0, 1), ('hammer', 42.2, 2)";
    final static String SELECT = "select * from items";

    public static void main(String[] args) throws SQLException {

        Connection conn = DriverManager.getConnection("jdbc:duckdb:");

        DuckDBConnection ddbConn = (DuckDBConnection)conn;

        Statement stmt = conn.createStatement();
        stmt.execute(CREATE_TABLE);
        stmt.execute(INSERT_INTO_TABLE);

        try(ResultSet rs = stmt.executeQuery(SELECT)) {
            while(rs.next()) {
                System.out.println(rs.getString(1) + "  " + rs.getInt(3));
            }
        }
        stmt.close();
    }
}
