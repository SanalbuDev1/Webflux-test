package webflux.estudio.web.domain.pruebas.functionalInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import webflux.estudio.web.domain.model.dto.Persona;

public class FunctionalInterface {

    public static void main(String[] args) {

        Function<String, String> function1 = (x) -> "primer " + x.toUpperCase();
        Function<String, String> function2 = (x) -> "segundo " + x + " - " + x.toUpperCase();

        function1.andThen(function2).apply("java 8");

        System.out.println(function1.apply("hola estoy en minuscula pero me trasformaron a mayuscula"));
        System.out.println(function2.apply("hola estoy en minuscula pero me trasformaron a mayuscula"));
        System.out.println(function1.andThen(function2).apply("java 8"));

        // aplicar metodo function

        List<Persona> personas = new ArrayList<>();
        personas.add(new Persona("Juan", "Perez", 15, "Calle 1"));
        personas.add(new Persona("Maria", "Gomez", 20, "Calle 2"));
        personas.add(new Persona("Pedro", "Garcia", 25, "Calle 3"));
        personas.add(new Persona("Luis", "Gonzalez", 30, "Calle 4"));
        
        List<Persona> resultado = aplicarFuncionParaMayoresDe18(personas, (x) -> {
            x.setNombre(x.getNombre().toUpperCase());
            return x;
            
        });

        System.out.println(resultado);

    }

    public static List<Persona> aplicarFuncionParaMayoresDe18(List<Persona> personas, Function<Persona, Persona> function){

        List<Persona> personasMayoresDe18 = new ArrayList<>();

        for (Persona persona : personas) {
            if(persona.getEdad() >= 18){
                personasMayoresDe18.add(function.apply(persona));
            }else{
                personasMayoresDe18.add(function.apply(persona));
            }
        }
        return personasMayoresDe18;

    }
    
}
