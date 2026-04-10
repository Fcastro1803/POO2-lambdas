package oop2.ejercicios.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

interface OperacionBD {
    void ejecutar(Connection connection) throws SQLException;
}

public class Usuarios {

    private final String jdbcUrl;

    public Usuarios(String jdbcUrl) {
        this.jdbcUrl = jdbcUrl;
    }

    // Metodo para manejar la transaccion de forma genérica, evitando repetir código en cada operación
    private void ejecutarEnTransaccion(OperacionBD operacion, String mensajeError) {
        try (Connection connection = DriverManager.getConnection(this.jdbcUrl)) {
            connection.setAutoCommit(false);
            try {
                operacion.ejecutar(connection);
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                throw new RuntimeException(mensajeError, e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(mensajeError, e);
        }
    }

    public void insertar(String nombre, String email) {
        ejecutarEnTransaccion(connection -> {
            String sql = "INSERT INTO usuarios (nombre, email) VALUES (?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, nombre);
                statement.setString(2, email);
                statement.executeUpdate();
                System.out.println("Usuario insertado");
            }
        }, "Error al insertar usuario");
    }

    public void actualizarEmail(int id, String nuevoEmail) {
        ejecutarEnTransaccion(connection -> {
            String sql = "UPDATE usuarios SET email = ? WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, nuevoEmail);
                statement.setInt(2, id);
                statement.executeUpdate();
                System.out.println("Email actualizado");
            }
        }, "Error al actualizar usuario");
    }
}