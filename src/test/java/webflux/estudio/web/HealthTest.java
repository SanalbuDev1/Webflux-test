package webflux.estudio.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import reactor.test.StepVerifier;
import webflux.estudio.web.infraestructure.inputadapters.http.Health;

@SpringBootTest
public class HealthTest {

    @Autowired
    private Health health;

    @Test
    void contextLoads() {
        StepVerifier.create(health.health())
        .expectNext("Health \n")
        .expectNext("\n is")
        .expectNext("\n OK")
        .verifyComplete();
    }

    
}
