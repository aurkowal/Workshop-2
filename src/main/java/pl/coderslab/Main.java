package pl.coderslab;

import java.util.Arrays;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        UserDao userDao = new UserDao();
        User user = new User();

        //Dodawanie uzytkownika
//        user.setUsername("Wojt454ek");
//        user.setEmail("wojt3333ek@gmail.com");
//        user.setPassword("wo22jtek");
//        user = userDao.create(user);

        //wyswietlanie
        User userInfo = userDao.read(8);
        System.out.println(userInfo);

        //edytowanie danych
        User user2 = userDao.read(21);
        user2.setUsername("Ola3535123");
        user2.setEmail("ola5454123@gmail.com");
        user2.setPassword("ola123");
        userDao.update(user2);


        //usuwanie
 //      userDao.delete(16);

        String usersArrayToString = Arrays.toString(userDao.findAll());
        System.out.println(usersArrayToString);

    }
}