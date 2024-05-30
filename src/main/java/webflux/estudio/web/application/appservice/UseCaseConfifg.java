package webflux.estudio.web.application.appservice;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import webflux.estudio.web.domain.model.todo.gateway.EntityRepository;
import webflux.estudio.web.domain.usecase.ProductoRepositoryUseCase;

@Configuration
public class UseCaseConfifg {
    
    @Bean
    public ProductoRepositoryUseCase productoRepositoryy(EntityRepository entityRepository) {
        return new ProductoRepositoryUseCase(entityRepository);
    }

}
