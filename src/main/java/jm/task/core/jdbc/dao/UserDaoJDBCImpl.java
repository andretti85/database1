package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
   // private Connection dbconnection;
   /* public UserDaoJDBCImpl() {
        dbconnection = Util.getMySQLConnection();
    }*/

    public void createUsersTable() {
        String sql = "CREATE TABLE IF NOT EXISTS USERS (id BIGINT AUTO_INCREMENT, name VARCHAR(20), lastName VARCHAR(20), age TINYINT, PRIMARY KEY (id));";

        try (Statement stmt = Util.getMySQLConnection().createStatement()) {

            stmt.executeUpdate(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }



    public void dropUsersTable() {
        String sql = "DROP TABLE USERS";

        try (Connection dbconnection = Util.getMySQLConnection()) {
            Statement stmt = dbconnection.createStatement();
            stmt.executeUpdate(sql);
        } catch (Exception e) {
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String sql = "INSERT INTO USERS (name, lastName, age) Values (?, ?, ?);";

        try (PreparedStatement pstmt =  Util.getMySQLConnection().prepareStatement(sql)) {
            //PreparedStatement pstmt = dbconnection.prepareStatement(sql);

           // pstmt.setLong(1, 0);
            pstmt.setString(1, name);
            pstmt.setString(2, lastName);
            pstmt.setByte(3, age);
          //  System.out.println(sql);
            pstmt.execute();
          //  System.out.println(pstmt.executeUpdate(sql));
           // System.out.println(sql);
        } catch (Exception e) {
        }

    }


    public void removeUserById(long id) {
        try (Connection dbconnection = Util.getMySQLConnection()) {
            String sql = "DELETE FROM USERS (id) WHERE Values (?)";
            PreparedStatement pstmt = dbconnection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setLong(1, id);
            pstmt.execute();
        } catch (Exception e) {
        }
    }

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        String query = "SELECT * FROM USERS";
        try (Statement stmt = Util.getMySQLConnection().createStatement()) {
          //  Statement stmt = dbconnection.createStatement();

            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                long id = rs.getLong("id");
                String firstName = rs.getString("name");
                String lastName = rs.getString("lastName");
                byte age = rs.getByte("age");

                User user = new User(firstName, lastName, age);
                user.setId(id);
                list.add(user);

            }
        } catch (Exception e) {
        }
        return list;
    }

    public void cleanUsersTable() {
        String sql = "DELETE FROM USERS";

        try (Connection dbconnection = Util.getMySQLConnection()) {
            PreparedStatement pstmt = dbconnection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.execute();

        } catch (Exception e) {
        }

    }
}
