package org.adrian;

import org.adrian.controller.GestorUsuarios;
import org.adrian.DatabaseConnection;
import org.adrian.model.Usuario;
import org.adrian.view.AppFrame;
import org.adrian.view.LoginPanel;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Clase principal de la aplicación que inicia la interfaz gráfica y gestiona la conexión a la base de datos.
 */
public class App {
    /**
     * Método principal de la aplicación. Inicia la interfaz gráfica y carga los usuarios desde un archivo.
     *algo
     * @param args Argumentos de línea de comandos (no utilizados).
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    DatabaseConnection.createTableIfNotExists(); // Crear la tabla si no existe

                    GestorUsuarios gestorUsuarios = new GestorUsuarios();
                    cargarUsuariosDesdeArchivo("usuarios.txt", gestorUsuarios);

                    // Crear y mostrar el marco de la aplicacion
                    AppFrame frame = new AppFrame();
                    frame.setContentPane(new LoginPanel(frame, gestorUsuarios));
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Carga los usuarios desde un archivo de texto y los agrega al gestor de usuarios.
     * El archivo debe tener el siguiente formato:
     * Cada línea del archivo representa un usuario.
     * @param nombreArchivo     El nombre del archivo que contiene los usuarios.
     * @param gestorUsuarios    El gestor de usuarios al cual se agregarán los usuarios leídos.
     */
    private static void cargarUsuariosDesdeArchivo(String nombreArchivo, GestorUsuarios gestorUsuarios) {
        try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes.length == 3) {
                    String nombreUsuario = partes[0];
                    String contraseña = partes[1];
                    String tipoUsuario = partes[2];
                    gestorUsuarios.agregarUsuario(new Usuario(nombreUsuario, contraseña, tipoUsuario));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
