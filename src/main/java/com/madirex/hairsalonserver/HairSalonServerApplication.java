package com.madirex.hairsalonserver;

import com.madirex.hairsalonserver.repository.AppointmentRepository;
import com.madirex.hairsalonserver.repository.LoginRepository;
import com.madirex.hairsalonserver.repository.ServiceRepository;
import com.madirex.hairsalonserver.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HairSalonServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(HairSalonServerApplication.class, args);
    }

    @Bean
    public CommandLineRunner selectAll(LoginRepository loginRepo, ServiceRepository serviceRepo, AppointmentRepository appointmentRepo, UserRepository userRepo) {
        return args -> {
            loginRepo.findAll().forEach(System.out::println);
            appointmentRepo.findAll().forEach(System.out::println);
            serviceRepo.findAll().forEach(System.out::println);
            userRepo.findAll().forEach(System.out::println);
        };
    }
}