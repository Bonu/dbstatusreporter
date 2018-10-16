package com.report.database.dbstatusreporter;

import com.report.database.dbstatusreporter.domain.Tables;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static java.sql.DriverManager.getConnection;

public class JDBCPostgresql {

    public static void main( String args[] ) throws SQLException {

        String jdbcUrl = "jdbc:postgresql://localhost:5432/bookdepot";
        String user = "bookdepot";
        String pwd = "bookdepot";

        List<Tables> tables = new ArrayList<>();
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String allTablesQuery = "SELECT * FROM pg_catalog.pg_tables  pt where pt.schemaname = 'bookdepotschema';";

        try (Connection c = getConnection(jdbcUrl, user, pwd);
             Statement stmt = c.createStatement();
             ResultSet rs = stmt.executeQuery( allTablesQuery );
        ){
            while ( rs.next() ) {
                String  schemaname = rs.getString("schemaname");
                String  tablename = rs.getString("tablename");
                String  tableowner = rs.getString("tableowner");
                String  tablespace = rs.getString("tablespace");
                Tables  table = new Tables(schemaname, tablename, tableowner, tablespace);
                tables.add(table);
            }
            rs.close();
            stmt.close();

        } catch ( Exception e ) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        } finally{

        }

        try(
            Connection c = getConnection(jdbcUrl, user, pwd);
            ){

            for (Tables table: tables) {
                try( Statement stmt = c.createStatement();
                     ResultSet rs = stmt.executeQuery( "SELECT count(*) as ROW_COUNT FROM "+table.getSchemaname()+"."+table.getTablename() );){

                while ( rs.next() ) {
                    String  rowCount = rs.getString("ROW_COUNT");
                    System.out.println( table.getSchemaname()+"."+table.getTablename()+" -> " + rowCount );
                }
                } catch ( Exception e ) {
                    System.err.println( e.getClass().getName()+": "+ e.getMessage() );
                    System.exit(0);
                }

            }

        } catch ( Exception e ) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        } finally {

        }


    }
}