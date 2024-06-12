package cool;

import java.sql.*;
import org.duckdb.DuckDBConnection;

public class DuckDbExplorations {
    public static void main(String[] args) throws SQLException {

        Connection conn = DriverManager.getConnection("jdbc:duckdb:");

        DuckDBConnection ddbConn = (DuckDBConnection)conn;

        Statement stmt = conn.createStatement();
        stmt.execute("Create table items (item varchar, value decimal(10,2), count INTEGER)");
        stmt.execute("insert into items values ('jeans', 20.0, 1), ('hammer', 42.2, 2)");

        try(ResultSet rs = stmt.executeQuery("select * from items")) {
            while(rs.next()) {
                System.out.println(rs.getString(1) + "  " + rs.getInt(3));
            }
        }
        stmt.close();
    }
}
