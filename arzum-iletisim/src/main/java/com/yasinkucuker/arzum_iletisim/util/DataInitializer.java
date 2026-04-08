package com.yasinkucuker.arzum_iletisim.util;


import com.yasinkucuker.arzum_iletisim.model.User;
import com.yasinkucuker.arzum_iletisim.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initUsers(UserRepository repo, PasswordEncoder encoder) {
        return args -> {
            // Admin kullanıcı zaten varsa ekleme
            if (repo.findByUsername("admin").isEmpty()) {
                User admin = new User();
                admin.setUsername("admin");
                admin.setPassword(encoder.encode("123456")); // Buradaki şifreyi istediğin gibi değiştir
                admin.setRole("ADMIN");

                repo.save(admin);
                System.out.println("Admin kullanıcı oluşturuldu.");
            } else {
                System.out.println("Admin kullanıcı zaten mevcut.");
            }
        };
    }
}
