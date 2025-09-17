package com.isep.acme.bootstrapper;

import com.isep.acme.repositories.IRepos.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.isep.acme.model.Role;
import com.isep.acme.model.User;
import com.isep.acme.repositories.UserRepository;

@Component
//@Profile("JPA")
public class UserBootstrapper implements CommandLineRunner {

    @Autowired
    private IUserRepository userRepo;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public void run(String... args) throws Exception {
/*
        //admin
        if(userRepo.findByUsername("admin1@mail.com").isEmpty()) {
            User admin1 = new User("admin1@mail.com", encoder.encode("AdminPW1"),
                    "Jose Antonio", "355489123", "Rua Um","43215-789", 123456789L,"2d3d2d2df");
            admin1.addAuthority(new Role(Role.Admin));

            userRepo.save(admin1);
        }

        if(userRepo.findByUsername("admin2@mail.com").isEmpty()) {
            User mod1 = new User("admin2@mail.com", encoder.encode("AdminPW2"),
                    "Antonio Jose", "321984553", "Rua dois","43215-789", 133456789L,"2d3d2dgthrf");
            mod1.addAuthority(new Role(Role.Mod));
            userRepo.save(mod1);
        }
        if(userRepo.findByUsername("user1@mail.com").isEmpty()) {
            User user1 = new User("user1@mail.com", encoder.encode("userPW1"),
                    "Nuno Miguel", "253647883", "Rua tres","43215-789", 123466789L,"2d3d2d2djuyj");
            user1.addAuthority(new Role(Role.RegisteredUser));
            userRepo.save(user1);
        }
        if(userRepo.findByUsername("user2@mail.com").isEmpty()) {
            User user2 = new User("user2@mail.com", encoder.encode("userPW2"),
                    "Miguel Nuno", "253698854", "Rua quatro","43215-789", 123444789L,"2d3djikiuf");
            user2.addAuthority(new Role(Role.RegisteredUser));
            userRepo.save(user2);
        }
        if(userRepo.findByUsername("user3@mail.com").isEmpty()) {
            User user3 = new User("user3@mail.com", encoder.encode("userPW3"),
                    "Antonio Pedro", "254148863", "Rua vinte","43215-789", 123456799L,"2d3467jf");
            user3.addAuthority(new Role(Role.RegisteredUser));
            userRepo.save(user3);
        }

        if(userRepo.findByUsername("user4@mail.com").isEmpty()) {
            User user4 = new User("user4@mail.com", encoder.encode("userPW4"),
                    "Pedro Antonio", "452369871", "Rua cinco","43215-789", 113456789L,"2d3d932fvnf");
            user4.addAuthority(new Role(Role.RegisteredUser));
            userRepo.save(user4);
        }
        if(userRepo.findByUsername("user5@mail.com").isEmpty()) {
            User user5 = new User("user5@mail.com", encoder.encode("userPW5"),
                    "Ricardo Joao", "452858596", "Rua seis","43215-789", 123336789L,"2d3cnmx7f");
            user5.addAuthority(new Role(Role.RegisteredUser));
            userRepo.save(user5);
        }
        if(userRepo.findByUsername("user6@mail.com").isEmpty()) {
            User user6 = new User("user6@mail.com", encoder.encode("userPW6"),
                    "Joao Ricardo", "425364781", "Rua sete","43215-789", 123456777L,"2d3d2csxs");
            user6.addAuthority(new Role(Role.RegisteredUser));
            userRepo.save(user6);
        }
        if(userRepo.findByUsername("user7@mail.com").isEmpty()) {
            User user7 = new User("user7@mail.com", encoder.encode("userPW7"),
                    "Luis Pedro", "526397747", "Rua oito","43215-789", 912345678L,"2d39328vgf");
            user7.addAuthority(new Role(Role.RegisteredUser));
            userRepo.save(user7);
        }
        if(userRepo.findByUsername("user8@mail.com").isEmpty()) {
            User user8 = new User("user8@mail.com", encoder.encode("userPW8"),
                    "Pedro Luis", "523689471", "Rua nove ","43215-789", 123455555L,"2d3puyevc5");
            user8.addAuthority(new Role(Role.RegisteredUser));
            userRepo.save(user8);
        }
        if(userRepo.findByUsername("user9@mail.com").isEmpty()) {
            User user9 = new User("user9@mail.com", encoder.encode("userPW9"),
                    "Marco Antonio", "253148965", "Rua dez","43215-789", 111156789L,"2d3ldhnam43");
            user9.addAuthority(new Role(Role.RegisteredUser));
            userRepo.save(user9);
        }
        if(userRepo.findByUsername("user10@mail.com").isEmpty()) {
            User user10 = new User("user10@mail.com", encoder.encode("userPW10"),
                    "Antonio Marco", "201023056", "Rua onze","43215-789", 987654321L,"frksnm432df");
            user10.addAuthority(new Role(Role.RegisteredUser));
            userRepo.save(user10);
        }
        if(userRepo.findByUsername("user11@mail.com").isEmpty()) {
            User user11 = new User("user11@mail.com", encoder.encode("userPW11"),
                    "Rui Ricardo", "748526326", "Rua doze","43215-789", 981237456L,"2doiqnms418f");
            user11.addAuthority(new Role(Role.RegisteredUser));
            userRepo.save(user11);
        }
*/
    }

}
