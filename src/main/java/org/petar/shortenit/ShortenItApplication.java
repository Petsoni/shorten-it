package org.petar.shortenit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ShortenItApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShortenItApplication.class, args);
    }

}
