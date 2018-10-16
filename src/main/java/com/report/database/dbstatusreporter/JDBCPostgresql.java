package com.report.database.dbstatusreporter;

import com.report.database.dbstatusreporter.domain.Tables;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static java.sql.DriverManager.getConnection;

public class JDBCPostgresql {

    public static void main( String args[] ) throws SQLException {
        Connection c = null;
        Statement stmt = null;

        List<Tables> tables = new ArrayList<>();

        try {
            Class.forName("org.postgresql.Driver");
            c = getConnection("jdbc:postgresql://localhost:5432/bookdepot",
                            "bookdepot", "bookdepot");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM pg_catalog.pg_tables  pt where pt.schemaname = 'bookdepotschema';" );



            while ( rs.next() ) {


                String  schemaname = rs.getString("schemaname");
                String  tablename = rs.getString("tablename");
                String  tableowner = rs.getString("tableowner");
                String  tablespace = rs.getString("tablespace");
                Tables  table = new Tables(schemaname, tablename, tableowner, tablespace);
//                System.out.println( "ID = " + schemaname );
//                System.out.println( "NAME = " + tablename );
//                System.out.println( "AGE = " + tableowner );
//                System.out.println( "ADDRESS = " + tablespace );
//                System.out.println();
            tables.add(table);

            }
            rs.close();
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }

        try{
            Class.forName("org.postgresql.Driver");
            c = getConnection("jdbc:postgresql://localhost:5432/bookdepot",
                    "bookdepot", "bookdepot");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            for (Tables table: tables) {
                stmt = c.createStatement();
                ResultSet rs = stmt.executeQuery( "SELECT count(*) as ROW_COUNT FROM "+table.getSchemaname()+"."+table.getTablename() );
                while ( rs.next() ) {
                    String  rowCount = rs.getString("ROW_COUNT");
                    System.out.println( table.getSchemaname()+"."+table.getTablename()+" -> " + rowCount );
                }
                rs.close();
                stmt.close();
            }

        } catch ( Exception e ) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        } finally {
            c.close();
        }


        System.out.println("Operation done successfully");
    }
}