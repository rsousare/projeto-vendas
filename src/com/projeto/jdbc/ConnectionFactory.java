package com.projeto.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {

    public Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/bdvendas", "usuariocurso", "123");
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
