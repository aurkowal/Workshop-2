package pl.coderslab;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        UserDao userDao = new UserDao();
        User user = new User();
        user.setName("arek");
        user.setEmail("ark1adi2usz.jozwiak@coderslab.pl");
        user.setPassword("pass");
        user = userDao.create(user);

        System.out.println(user);


    }
}