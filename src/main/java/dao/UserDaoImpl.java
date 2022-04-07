package dao;

import api.UserDao;
import entity.User;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class UserDaoImpl implements UserDao {
    private final static UserDao instance = new UserDaoImpl();

    private Connection connection;
    private final String databaseName = "manegment";
    private final String tableName = "users";

    private UserDaoImpl() {
        init();
    }

    private void init() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/manegment", "root", "mati2009");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static UserDao getInstance() {
        return UserDaoImpl.instance;
    }

    public void saveUser(User user) {
        PreparedStatement statement;
        try {
            String query = "insert into " + tableName + " (login,password) values (?, ?)";
            statement = connection.prepareStatement(query);

            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());

            statement.execute();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(Long userId){
        PreparedStatement statement;
        try {
            String query = "delete from " + tableName + " where id =?";
            statement = connection.prepareStatement(query);

            statement.setLong(1, userId);

            statement.execute();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserByLogin(String login){
        PreparedStatement statement;
        try {
            String query = "delete from " + tableName + " where login =?";
            statement = connection.prepareStatement(query);

            statement.setString(1, login);

            statement.execute();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new LinkedList<User>();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            String query = "select * from " + tableName;
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String login = resultSet.getString("login");
                String password = resultSet.getString("password");

                User user = new User(id,login,password);
                users.add(user);
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    public void updateUser(User user) {
        PreparedStatement statement;
        try {
            String query = "update " + tableName + " set login = ?, password = ? where id = ?";
            statement = connection.prepareStatement(query);

            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setLong(3, user.getId());

            statement.execute();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}