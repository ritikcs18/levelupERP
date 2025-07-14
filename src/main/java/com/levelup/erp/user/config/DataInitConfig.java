package com.levelup.erp.user.config;

import com.levelup.erp.user.model.Role;
import com.levelup.erp.user.model.User;
import com.levelup.erp.user.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitConfig {

    @Bean
    public CommandLineRunner initData(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (userRepository.count() == 0) {

                User admin = new User();
                admin.setUsername("admin");
                admin.setPassword(passwordEncoder.encode("admin123"));
                admin.setRole(Role.ADMIN);
                userRepository.save(admin);
                System.out.println("✅ Admin user created in H2");

                User sales = new User();
                sales.setUsername("sales");
                sales.setPassword(passwordEncoder.encode("sales123"));
                sales.setRole(Role.SALES);
                userRepository.save(sales);

                System.out.println("✅ Sales user created in H2");

                User ops = new User();
                ops.setUsername("ops");
                ops.setPassword(passwordEncoder.encode("ops123"));
                ops.setRole(Role.OPS);
                userRepository.save(ops);


                System.out.println("✅ ops user created in H2");
            }
        };
    }
}
