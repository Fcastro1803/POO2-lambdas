package oop2.ejercicios.filtros;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Personas {

    
    private List<Persona> filtrarPersonas(List<Persona> personas, Predicate<Persona> criterio) {
        List<Persona> resultado = new ArrayList<>();
        for (Persona persona : personas) {
            if (criterio.test(persona)) {
                resultado.add(persona);
            }
        }
        return resultado;
    }

    // Filtra las personas cuyo nombre comienza con E
    public List<Persona> nombresQueEmpiezanConE(List<Persona> p) {
        return filtrarPersonas(p, persona -> persona.nombre().startsWith("E"));
    }

    // Filtra las personas cuyo nombre tiene cantidad de letras par
    public List<Persona> nombresCuyaCantidadDeLetrasEsPar(List<Persona> p) {
        return filtrarPersonas(p, persona -> persona.nombre().length() % 2 == 0);
    }
}
