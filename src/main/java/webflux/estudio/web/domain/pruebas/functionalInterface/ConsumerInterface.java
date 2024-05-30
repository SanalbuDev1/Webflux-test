package webflux.estudio.web.domain.pruebas.functionalInterface;

import java.util.function.Consumer;
import java.util.function.Function;

public class ConsumerInterface {
    
    public static void main(String[] args) {
        
        Consumer<String> trasformar = (x) -> System.out.println(x.toUpperCase());
        Consumer<String> trasformar2 = (x) -> System.out.println(x.toLowerCase());
        Consumer<Integer> trasformar3 = (x) -> System.out.println(x * 2);
        Function<Double, Integer> doubleToInt = (x) -> Double.valueOf(x).intValue();

        System.out.println("Transformar a mayuscula");
        trasformar.accept("hola estoy en minuscula pero me trasformaron a mayuscula");
        System.out.println("Transformar a minuscula");
        trasformar2.accept("HOLA ESTOY EN MAYUSCULA PERO ME TRASFORMARON A MINUSCULA");
        System.out.println("Multiplicar por 2");
        trasformar3.accept(5);
        System.out.println("Convertir double a int: " + doubleToInt.apply(5.0));
        

    }

}
