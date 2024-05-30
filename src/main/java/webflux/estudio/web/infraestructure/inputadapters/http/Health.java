package webflux.estudio.web.infraestructure.inputadapters.http;

import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class Health {

    @GetMapping("/health")
    public Flux<String> health() {
        List<String> list =  List.of("Health \n", "\n is", "\n OK");
        return Flux.fromIterable(list)
            .delayElements(Duration.ofSeconds(2));
    }
    
}
