package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        Car car1 = new Car("Lada Vesta", 1357);
        Car car2 = new Car("Ford Focus", 5678);
        Car car3 = new Car("BMW X5", 1234);
        Car car4 = new Car("Toyota Camry", 7890);

        User user1 = new User("Наталия", "Иванова", "nata@mail.ru", car1);
        User user2 = new User("Данила", "Онищенко", "o.danila@mail.ru", car2);
        User user3 = new User("Егор", "Федунков", "Egoric@mail.ru", car3);
        User user4 = new User("Василиса", "Лапкина", "Vasa@mail.ru", car4);

        userService.add(user1);
        userService.add(user2);
        userService.add(user3);
        userService.add(user4);

        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println("Model = " + user.getCarModel());
            System.out.println("Series = " + user.getCarSeries());
            System.out.println();
        }

        List<User> userList = userService.getOwnerCar("Lada Vesta", 1357);

        for (User user : userList) {
            System.out.println("Id : " + user.getId());
            System.out.println("First Name : " + user.getFirstName());
            System.out.println("Last Name : " + user.getLastName());
            System.out.println("Email : " + user.getEmail());
            System.out.println("Car Model : " + user.getCarModel());
            System.out.println("Car Series : " + user.getCarSeries());
            System.out.println();
        }

        context.close();
    }
}
