package ma.enset.ensetjpa;

import ma.enset.ensetjpa.entities.Role;
import ma.enset.ensetjpa.entities.User;
import ma.enset.ensetjpa.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.stream.Stream;

@SpringBootApplication
public class EnsetJpaApplication {

    public static void main(String[] args) {

        SpringApplication.run(EnsetJpaApplication.class, args);
    }
    @Bean
    CommandLineRunner start(UserService userService){
        return args -> {
            User user=new User();
            user.setUserName("User1");
            user.setPassword("1234567");
            userService.addNewUser(user);

            User user1=new User();
            user1.setUserName("Admin");
            user1.setPassword("123456");
            userService.addNewUser(user1);

            Stream.of("Student","User","ADMIN").forEach(r->{
                Role role=new Role();
                role.setRoleName(r);
                userService.addNewRole(role);
            });

           userService.addRoleToUser("User1","Student");
           userService.addRoleToUser("User1","User");
           userService.addRoleToUser("Admin","User");
           userService.addRoleToUser("Admin","ADMIN");

           try {
             User user2=userService.authentificate("User1","1234567");
               System.out.println(user2.getUserId());
               System.out.println(user2.getUserName());
               System.out.println("Roles :");
               user2.getRoles().forEach(r->{
                   System.out.println("Role : "+r);
               });
           }catch (Exception e){
                  e.printStackTrace();
           }


        };
    }

}
