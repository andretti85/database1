package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        User user1 = new User("John", "Carmack", (byte) 44);
        User user2 = new User("Kira", "Nightly", (byte) 37);
        User user3 = new User("Donald", "Trump", (byte) 80);
        User user4 = new User("Napoleon", "Francev", (byte) 99);

        UserServiceImpl usi = new UserServiceImpl();
        usi.createUsersTable();

        usi.saveUser(user1.getName(), user1.getLastName(), user1.getAge());
        System.out.println(user1.toString() + "was added");
        usi.saveUser(user2.getName(), user2.getLastName(), user2.getAge());
        System.out.println(user2.toString() + "was added");
        usi.saveUser(user3.getName(), user3.getLastName(), user3.getAge());
        System.out.println(user3.toString() + "was added");
        usi.saveUser(user4.getName(), user4.getLastName(), user4.getAge());
        System.out.println(user4.toString() + "was added");

        List<User> l = usi.getAllUsers();

        for (User u : l) {
            System.out.println(u.toString() + "HHH");
        }
        System.out.println("HHH");
       // usi.cleanUsersTable();
      //  usi.dropUsersTable();
    }
}
