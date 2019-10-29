package org.TFG.project;

import java.sql.*;

public class DBControler {
    private static final String DB_URL = "jdbc:h2:/C:/Users/jek19/IdeaProjects/Servlet/lab3/db/accountDB";
    private static final String DB_Driver = "org.h2.Driver";
    private static Connection connection;

    public static void OpenDataBase() {
        if (connection == null){
            try {
                Class.forName(DB_Driver); //Проверяем наличие JDBC драйвера для работы с БД
                connection = DriverManager.getConnection(DB_URL);//соединениесБД
                System.out.println("Соединение с СУБД выполнено.");
            } catch (ClassNotFoundException e) {
                e.printStackTrace(); // обработка ошибки  Class.forName
                System.out.println("JDBC драйвер для СУБД не найден!");
            } catch (SQLException e) {
                e.printStackTrace(); // обработка ошибок  DriverManager.getConnection
                System.out.println("Ошибка SQL !");
            }
        }

    }

    public static void CreateTable() {
        String CreateSQLQuery = "CREATE TABLE IF NOT EXISTS USERS2(login varchar(256) primary key, password varchar(256), email varchar(256))";
        try {
            Statement statement = connection.createStatement();

            statement.execute(CreateSQLQuery);
            statement.close();
        } catch (Exception ex) {
            System.out.println("Ошыбка при создании таблицы");
        }

        System.out.println("Таблица успешно создана");
    }


    public static void ExecuteCommand(String command) {
        try {
            Statement statement = connection.createStatement();

            statement.execute(command);
            statement.close();
        } catch (Exception ex) {
            System.out.println("Ошыбка при выполнении запроса");
        }
        System.out.println("Запрос успешно выполнен");
    }

    public static ResultSet ExecuteCommandAndReturn(String command) {
        ResultSet resultSet;
        Statement statement = null;

        try {
            statement = connection.createStatement();

            resultSet = statement.executeQuery(command);
            return resultSet;
        } catch (Exception ex) {
            System.out.println("Ошыбка при выполнении запроса");
        }
        return null;
    }

    public static void CloseDataBase() throws SQLException {
        connection.close();
    }
}
