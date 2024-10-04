package cloud.meghdo.drizzle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class drizzleApplication {
    public static void main (String[] args) {
        SpringApplication.run(drizzleApplication.class, args);
    }

    @RestController
    public class drizzleController {
        @GetMapping("/isActive")
        public String isActive() {
            return "Welcome to Drizzle @ Meghdo Cloud";
        }
    }


}



