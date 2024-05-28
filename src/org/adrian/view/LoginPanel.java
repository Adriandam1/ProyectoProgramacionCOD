package org.adrian.view;

import org.adrian.controller.GestorUsuarios;

import javax.swing.*;
import java.awt.*;

/**
 * Panel de inicio de sesión que permite a los usuarios autenticarse en la aplicación.
 */
public class LoginPanel extends JPanel {
    private JTextField userField;
    private JPasswordField passField;
    private GestorUsuarios gestorUsuarios;
    private AppFrame frame;

    public LoginPanel(AppFrame frame, GestorUsuarios gestorUsuarios) {
        this.frame = frame;
        this.gestorUsuarios = gestorUsuarios;
        initComponents();
    }
    /**
     * Inicializa y configura los componentes del panel de inicio de sesión.
     */
    private void initComponents() {
        setLayout(null);

        JLabel jLabelInicioSesion = new JLabel("Inicio de Sesion");
        jLabelInicioSesion.setFont(new Font("Times New Roman", Font.BOLD, 36));
        jLabelInicioSesion.setForeground(new Color(102, 102, 255));
        jLabelInicioSesion.setBounds(100, 50, 356, 50);
        add(jLabelInicioSesion);

        JLabel jLabelUsuario = new JLabel("Usuario:");
        jLabelUsuario.setFont(new Font("Times New Roman", Font.BOLD, 24));
        jLabelUsuario.setBounds(50, 150, 100, 25);
        add(jLabelUsuario);

        userField = new JTextField();
        userField.setFont(new Font("Times New Roman", Font.BOLD, 24));
        userField.setBounds(jLabelUsuario.getX() + jLabelUsuario.getWidth() + 10, 145, 300, 30);
        add(userField);

        JLabel jLabel1 = new JLabel("Contraseña:");
        jLabel1.setFont(new Font("Times New Roman", Font.BOLD, 24));
        jLabel1.setBounds(50, 250, 150, 25);
        add(jLabel1);

        passField = new JPasswordField();
        passField.setFont(new Font("Times New Roman", Font.BOLD, 24));
        passField.setBounds(180, 250, 300, 30);
        add(passField);

        JButton jButtonLogin = new JButton("BotonLogin");
        jButtonLogin.setFont(new Font("Times New Roman", Font.BOLD, 24));
        jButtonLogin.setBounds(220, 350, 150, 40);
        jButtonLogin.addActionListener(e -> {
            // Comprueba si el campo usuario esta vacio
            if (userField.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(LoginPanel.this, "El campo usuario está vacío!");
                userField.grabFocus();
                return;
            }
            // comprueba si el campo contraseña esta vacio
            if (new String(passField.getPassword()).trim().isEmpty()) {
                JOptionPane.showMessageDialog(LoginPanel.this, "El campo contraseña está vacío!");
                passField.grabFocus();
                return;
            }
            // En el caso de que el usuario exista, comprobamos contraseña e inicia sesion o no
            boolean usuarioExiste = gestorUsuarios.autenticarUsuario(userField.getText(), new String(passField.getPassword())) != null;
            if (usuarioExiste) {
                JOptionPane.showMessageDialog(LoginPanel.this, "Iniciado usuario: " + userField.getText());
                frame.setContentPane(new EnlacesPanel(userField.getText(), frame, gestorUsuarios));
                frame.revalidate();
            } else {
                JOptionPane.showMessageDialog(LoginPanel.this, "Usuario incorrecto!");
                userField.grabFocus();
            }
        });
        add(jButtonLogin);
    }
    /**
     * Reinicio los campos de usuario y contraseña del panel de inicio de sesión.
     */
    public void resetFields() {
        userField.setText("");
        passField.setText("");
    }
}
