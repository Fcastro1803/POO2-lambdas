package oop2.lambdas.permissions1;

import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

public class Productos {
    public static final String SIN_PERMISOS = "No tiene los permisos necesarios";
    private List<Producto> productos;
    private SecuritySubSystem security;

    // Metodo generico para ejecutar operaciones con permisos
    private <T> T ejecutarConPermisos(String userId, Supplier<T> operacion, String mensajeError) {
        if (!this.security.checkPermission(userId)) {
            throw new RuntimeException(SIN_PERMISOS);
        }
        try {
            return operacion.get();
        } catch (Exception e) {
            throw new RuntimeException(mensajeError, e);
        }
    }

    public boolean addProducto(String userId, Producto producto) {
        return ejecutarConPermisos(userId,
                () -> this.productos.add(producto),
                "Error al agregar producto"
        );
    }

    public boolean removeProducto(String userId, Producto producto) {
        return ejecutarConPermisos(userId,
                () -> this.productos.remove(producto),
                "Error al eliminar producto"
        );
    }

    public List<Producto> listAll(String userId) {
        return ejecutarConPermisos(userId,
                () -> Collections.unmodifiableList(this.productos),
                "Error al listar productos"
        );
    }
}
