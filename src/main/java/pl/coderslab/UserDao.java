package pl.coderslab;

import java.sql.*;
import java.util.Arrays;

public class UserDao {
    private static final String SQL_CREATE_USER = "INSERT INTO users (email, username, password) VALUES (?, ?, ?)";

    public User create(User user) {
        try (Connection conn = DbUtil.connect()) {
            PreparedStatement statement =
                    conn.prepareStatement(SQL_CREATE_USER, Statement.RETURN_GENERATED_KEYS);
            statement.setString(2, user.getUsername());
            statement.setString(1, user.getEmail());
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
        String query = "select * FROM users WHERE id=?";
        try (Connection conn = DbUtil.connect();
             PreparedStatement statement = conn.prepareStatement(query)
        ) {
            statement.setInt(1, userId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Long id = resultSet.getLong("id");
                    String username = resultSet.getString("username");
                    String email = resultSet.getString("email");
                    String password = resultSet.getString("password");
                    return new User(id, email, username, password);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean update(User user) {
        String query = "UPDATE users SET email = ?, username = ?, password = ? WHERE id = ?";
        try (Connection conn = DbUtil.connect();
             PreparedStatement statement = conn.prepareStatement(query)
        ) {
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getUsername());
            statement.setString(3, user.getPassword());
            statement.setLong(4, user.getId());
            int userUpdate = statement.executeUpdate();
            return userUpdate == 1;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void delete(int userID) {
        String query = "DELETE FROM users WHERE id = ?";

        try (Connection conn = DbUtil.connect();
             PreparedStatement statement = conn.prepareStatement(query)
        ) {
            statement.setLong(1, userID);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Nie ma takiego użytkownika");
        }
    }

    private User[] addToArray(User u, User[] users) {
        User[] tmpUsers = Arrays.copyOf(users, users.length + 1); // Tworzymy kopię tablicy powiększoną o 1.
        tmpUsers[users.length] = u; // Dodajemy obiekt na ostatniej pozycji.
        return tmpUsers; // Zwracamy nową tablicę.
    }


    public User[] findAll() {
        String query = "SELECT * FROM users";
        User[] allUsers = new User[0];
        try (Connection conn = DbUtil.connect();
             PreparedStatement statement = conn.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()
        ) {

            while (resultSet.next()) {
                User user = new User();

                user.setId(resultSet.getLong("id"));
                user.setEmail(resultSet.getString("email"));
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));

                allUsers = addToArray(user, allUsers);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allUsers;
    }
}

