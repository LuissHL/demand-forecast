package com.demandforecast.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private DataSource dataSource;

    @GetMapping("/db")
    public String testConnection() throws SQLException {
        try (Connection conn = dataSource.getConnection()) {
            return "Conectado ao PostgreSQL com sucesso!";
        }
    }
}
