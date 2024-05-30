package webflux.estudio.web.domain.pruebas.functionalInterface;

import java.util.function.Predicate;

public class PredicateInterface {

    public static void main(String[] args) {

        Predicate<String> compararNombres = (x) -> x.equals("Juan");
        Predicate<String> compararEdad = (x) -> Integer.parseInt(x) > 18;

        System.out.println(compararNombres.test("Juan"));
        System.out.println(compararEdad.test("20"));
        
    }
    
}
