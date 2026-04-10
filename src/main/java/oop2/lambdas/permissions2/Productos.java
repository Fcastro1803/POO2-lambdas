package oop2.lambdas.permissions2;

import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

public class Productos {
    public static final String SIN_PERMISOS = "No tiene los permisos necesarios";
    private List<Producto> productos;
    private SecuritySubSystem security;

    public Productos(List<Producto> productos, SecuritySubSystem security) {
        this.security = security;
        this.productos = productos;
    }

    // Metodo genérico para operaciones con diferentes permisos
    private <T> T ejecutarConPermiso(Supplier<Boolean> verificacionPermiso, Supplier<T> operacion, String mensajeError) {
        if (!verificacionPermiso.get()) {
            throw new RuntimeException(SIN_PERMISOS);
        }
        try {
            return operacion.get();
        } catch (Exception e) {
            throw new RuntimeException(mensajeError, e);
        }
    }

    public boolean addProducto(String userId, Producto producto) {
        ejecutarConPermiso(
                () -> this.security.checkAddPermission(userId),  // Permiso específico para add
                () -> this.productos.add(producto),
                "Error al agregar producto"
        );
        return false;
    }

    public boolean removeProducto(String userId, Producto producto) {
        ejecutarConPermiso(
                () -> this.security.checkRemovePermission(userId),  // Permiso específico para remove
                () -> this.productos.remove(producto),
                "Error al eliminar producto"
        );
        return false;
    }

    public List<Producto> listAll(String userId) {
        return ejecutarConPermiso(
                () -> this.security.checkListPermission(userId),  // Permiso específico para list
                () -> Collections.unmodifiableList(this.productos),
                "Error al listar productos"
        );
    }

    int cantidad() {
        return this.productos.size();
    }

    boolean contiene(Producto unProducto) {
        return this.productos.contains(unProducto);
    }
}