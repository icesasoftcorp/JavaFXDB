package com.project.dbhandler;

import java.sql.Connection;
import java.sql.DriverManager;

public class PostgresConnect {
    private final String HOST = "localhost";
    private final String PORT = "5432";
    private final String DB = "javafxdb";
    private final String USER = "admin";
    private final String PASSWORD = "puceTec1234";

        /**
         * Parameters to establish connection to PostgreSQL
         * @return
         */
        public Connection getConnection() {
        Connection connection = null;

        try{
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://"+ HOST + ":" + PORT + "/" + DB;
            connection = DriverManager.getConnection(url, USER, PASSWORD);
            System.out.println("Conexión Establecida");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return connection;
    }
}
