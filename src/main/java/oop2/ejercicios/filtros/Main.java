package oop2.ejercicios.filtros;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Persona> personas = new ArrayList<>();
        personas.add(new Persona("Ana", "Garcia"));
        personas.add(new Persona("Elena", "Martinez"));
        personas.add(new Persona("Carlos", "Lopez"));
        personas.add(new Persona("Ernesto", "Rodriguez"));
        personas.add(new Persona("Beatriz", "Sanchez"));
        personas.add(new Persona("Esther", "Gomez"));
        personas.add(new Persona("David", "Fernandez"));
        personas.add(new Persona("Eduardo", "Diaz"));

        // Crear instancia de Personas
        Personas personasService = new Personas();

        // Probar filtros
        System.out.println("=== Personas con nombre que empieza con E ===");
        List<Persona> empiezanConE = personasService.nombresQueEmpiezanConE(personas);
        for (Persona p : empiezanConE) {
            System.out.println(p);
        }

        System.out.println("\n=== Personas con nombre de longitud par ===");
        List<Persona> longitudPar = personasService.nombresCuyaCantidadDeLetrasEsPar(personas);
        for (Persona p : longitudPar) {
            System.out.println(p + " - Longitud: " + p.nombre().length());
        }

        // Mostrar todas las personas
        System.out.println("\n=== Lista completa de personas ===");
        for (Persona p : personas) {
            System.out.println(p);
        }
    }
}
