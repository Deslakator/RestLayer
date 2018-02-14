package ru.desl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Import;
import ru.desl.conf.PersistenceJPAConfig;
import ru.desl.domain.Domain;

@SpringBootApplication()
@Import(PersistenceJPAConfig.class)
public class Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
        context.getBean(Domain.class).start();
    }
}
