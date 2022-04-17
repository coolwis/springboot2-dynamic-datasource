package org.springboot.sample;

import org.springboot.sample.datasource.DynamicDataSourceRegister;
import org.springboot.sample.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;


//@EnableAspectJAutoProxy
@SpringBootApplication
@Import(DynamicDataSourceRegister.class) //  Register dynamic multiple data sources
public class SpringBootSampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootSampleApplication.class, args);
    }


    @Autowired
    private Environment environment;


    @Bean
    public CommandLineRunner run(StudentService userRepository) throws Exception {
//        return (String[] args) -> {
//            User user1 = new User("John", "john@domain.com");
//            User user2 = new User("Julie", "julie@domain.com");
//            userRepository.save(user1);
//            userRepository.save(user2);
//            userRepository.findAll().forEach(user -> System.out.println(user));
//        };

        return null;
    }

}
