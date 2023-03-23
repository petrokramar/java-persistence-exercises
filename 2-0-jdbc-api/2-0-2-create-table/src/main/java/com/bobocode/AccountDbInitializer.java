package com.bobocode;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * {@link AccountDbInitializer} provides an API that allow to initialize (create) an Account table in the database
 */
public class AccountDbInitializer {
    private DataSource dataSource;

    public AccountDbInitializer(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void init() throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            Statement statement = connection.createStatement();
            statement.execute("CREATE TABLE account(" +
                    "id BIGINT," +
                    " email VARCHAR(255) NOT NULL," +
                    " first_name VARCHAR(255) NOT NULL," +
                    " last_name VARCHAR(255) NOT NULL," +
                    " gender VARCHAR(255) NOT NULL," +
                    " birthday DATE NOT NULL," +
                    " balance DECIMAL(19,4)," +
                    " creation_time TIMESTAMP NOT NULL DEFAULT now()," +
                    " CONSTRAINT account_pk PRIMARY KEY (id)," +
                    " CONSTRAINT account_email_uq UNIQUE (email)" +
                    ");");
        }
    }
}