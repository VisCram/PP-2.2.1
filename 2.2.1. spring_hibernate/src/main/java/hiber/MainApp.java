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
        userService.add(new User("User1", "LastName1", "user1.@mail.ru",
                new Car("Model name1", 123)));
        userService.add(new User("User2", "LastName2", "user2.@mail.ru",
                new Car("Model name2", 456)));
        userService.add(new User("User3", "LastName3", "user3.@mail.ru",
                new Car("Model name3", 789)));

        List<User> users = userService.getListUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println("Car = " + user.getCar());
            System.out.println();
        }

        User user = userService.getUserByCar("Model name1", 123);
        System.out.println(user);
        context.close();
    }
}
