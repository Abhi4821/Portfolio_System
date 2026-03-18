//package com.abhishekyadav.portfolio_backend;
//import io.github.cdimascio.dotenv.Dotenv;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//@SpringBootApplication
//public class PortfolioBackendApplication {
//	public static void main(String[] args) {
//        Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();
//        dotenv.entries().forEach(entry -> System.setProperty(entry.getKey(), entry.getValue()));
//        SpringApplication.run(PortfolioBackendApplication.class, args);
//	}
//}

package com.abhishekyadav.portfolio_backend;

import io.github.cdimascio.dotenv.Dotenv; // Yeh import zaruri hai
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PortfolioBackendApplication {

    public static void main(String[] args) {
        // STEP 1: .env file load karo
        Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();

        // STEP 2: Saare environment variables ko System properties mein daalo
        dotenv.entries().forEach(entry -> {
            System.setProperty(entry.getKey(), entry.getValue());
        });

        SpringApplication.run(PortfolioBackendApplication.class, args);
    }
}
