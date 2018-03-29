package ru.tinkoff.testTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ru.tinkoff.testTask.model.Application;
import ru.tinkoff.testTask.model.ApplicationRepository;
import ru.tinkoff.testTask.model.Contact;
import ru.tinkoff.testTask.model.ContactRepository;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.*;

@SpringBootApplication
public class TestTaskApplication {

    private static final Logger log = LoggerFactory.getLogger(TestTaskApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(TestTaskApplication.class, args);
    }

    @Bean
    public CommandLineRunner init(ContactRepository contactRepository, ApplicationRepository applicationRepository) {
        return (args) -> Arrays.asList("Ваня,Петя,Женя,Вася,Дима,Валя".split(","))
                .forEach(name -> {
                    Contact contact = contactRepository.save(new Contact(name));
                    List<String> productName = Arrays.asList("Кредит,Вклад,Страховка,Инвестиции".split(","));
                    long date = (long) (Math.random() * 400_000_000_000L + 600_000_000_000L);
                    Random random = new Random();
                    applicationRepository.save(new Application(new Date(date), productName.get(random.nextInt(productName.size())), contact));
                    applicationRepository.save(new Application(new Date(date), productName.get(random.nextInt(productName.size())), contact));
                    applicationRepository.save(new Application(new Date(date), productName.get(random.nextInt(productName.size())), contact));
                });
    }
}
