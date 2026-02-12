package pl.coderslab;

import java.sql.*;

public class UserDao {
    private static final String SQL_CREATE_USER = "INSERT INTO user (name, password, email) VALUES (?, ?, ?)";

    public User create(User user) {
        try (Connection conn = DbUtil.connect()) {
            PreparedStatement statement =
                    conn.prepareStatement(SQL_CREATE_USER, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());
            statement.executeUpdate();
            //Pobieramy wstawiony do bazy identyfikator, a następnie ustawiamy id obiektu user.
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                user.setId(resultSet.getLong(1));
            }
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public User read(int userId) {
        return null;
    }

    public User[] findAll() {
        return null;
    }
}

