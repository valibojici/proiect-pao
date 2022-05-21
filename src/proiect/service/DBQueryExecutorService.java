package proiect.service;

import proiect.mapper.RowMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBQueryExecutorService {
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "org.h2.Driver";
    static final String DB_URL = "jdbc:h2:~/proiect";

    //  Database credentials
    static final String USER = "vali";
    static final String PASS = "vali";

    public static List<Object> executeReadQuery(String sql, RowMapper<?> rowMapper){
        Connection conn = null;
        Statement stmt = null;
        List<Object> result = new ArrayList<>();
        try {
            // STEP 1: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            // STEP 2: Open a connection
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            // STEP 3: Execute a query
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            // STEP 4: Extract data from result set
            while(rs.next()) {
                // Retrieve by column name
                result.add(rowMapper.mapRow(rs));
            }

            // STEP 5: Clean-up environment
            rs.close();
        } catch(Exception se) {
            // Handle errors for JDBC
            se.printStackTrace();
        }
        finally {
            // finally block used to close resources
            try {
                if(stmt!=null) stmt.close();
            } catch(SQLException ignored) {}
            try {
                if(conn!=null) conn.close();
            } catch(SQLException se) {
                se.printStackTrace();
            }
        }
        return result;
    }

    /**
     * Used for INSERT, UPDATE, DELETE sql queries
     */
    public static Integer executeUpdateQuery(String sql) {
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();

            stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = stmt.getGeneratedKeys();
            if(rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception se) {
            se.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException ignored) {
            } // nothing we can do
            try {
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return null;
    }
}
