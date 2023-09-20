package com.programmers.ticketparis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class TicketparisApplication {

    public static void main(String[] args) {
        SpringApplication.run(TicketparisApplication.class, args);
    }

}
