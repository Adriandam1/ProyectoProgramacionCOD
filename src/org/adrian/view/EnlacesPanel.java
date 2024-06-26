package org.adrian.view;

import org.adrian.controller.GestorEnlaces;
import org.adrian.controller.GestorUsuarios;
import org.adrian.model.Enlace;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URI;
import java.net.URL;
import java.util.Random;

/**
 * El panel de enlaces es responsable de mostrar los enlaces disponibles para un usuario y permitir la gestión de estos enlaces.
 */
public class EnlacesPanel extends JPanel {
    private GestorEnlaces gestorEnlaces;
    private JTextPane textPane;
    private JLabel userLabel;
    private AppFrame frame;
    private GestorUsuarios gestorUsuarios;
    private static String imagenPrevia = "";

    /**
     * Constructor para el panel de enlaces que inicializa el panel con los enlaces del usuario.
     *
     * @param usuario        El nombre de usuario.
     * @param frame          El marco de la aplicación.
     */
    public EnlacesPanel(String usuario, AppFrame frame, GestorUsuarios gestorUsuarios) {
        this.frame = frame;
        this.gestorUsuarios = gestorUsuarios; // Guarda el GestorUsuarios recibido
        gestorEnlaces = new GestorEnlaces();
        initialize(usuario);
    }

    /**
     * Inicializa los componentes del panel.
     *
     * @param usuario El nombre de usuario.
     */
    private void initialize(String usuario) {
        setLayout(null); // Establece un diseño nulo para posicionar los componentes manualmente

        // Etiqueta para mostrar el usuario que ha iniciado sesión
        userLabel = new JLabel("Usuario: " + usuario);
        userLabel.setBounds(10, 10, 200, 25);
        userLabel.setFont(new Font("Times New Roman", Font.BOLD, 24));
        add(userLabel);

        // Botón para cerrar sesión
        JButton logoutButton = new JButton("Cerrar Sesión");
        logoutButton.setBounds(400, 10, 130, 25);
        add(logoutButton);

        // Configuración del JTextPane para mostrar contenido HTML
        textPane = new JTextPane();
        textPane.setContentType("text/html");
        textPane.setEditable(false); // El texto no es editable por el usuario
        textPane.setBounds(10, 40, 560, 250);
        // Agrega un listener de hipervínculos para abrir enlaces en el navegador
        textPane.addHyperlinkListener(new HyperlinkListener() {
            @Override
            public void hyperlinkUpdate(HyperlinkEvent e) {
                if (HyperlinkEvent.EventType.ACTIVATED.equals(e.getEventType())) {
                    try {// Abre el enlace en el navegador
                        java.awt.Desktop.getDesktop().browse(e.getURL().toURI());
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        JScrollPane scrollPane = new JScrollPane(textPane); // Agrega un JScrollPane alrededor del JTextPane
        scrollPane.setBounds(10, 40, 560, 250);
        add(scrollPane);

        // Boton para añadir enlace
        JButton btnAddNote = new JButton("Añadir enlace");
        btnAddNote.setBounds(10, 300, 130, 25);
        add(btnAddNote);

        // Boton para borrar enlace
        JButton btnDeleteNote = new JButton("Borrar enlace");
        btnDeleteNote.setBounds(150, 300, 120, 25);
        add(btnDeleteNote);

        //--------- Imagen hackeando a la derecha
        JLabel imagenArribaDerecha = new JLabel();
        URL imagenURL = getClass().getResource("/org/adrian/imagenes/hack1.jpg");
        if (imagenURL != null) {
            ImageIcon imageIcon = new ImageIcon(imagenURL);
            imagenArribaDerecha.setIcon(imageIcon);
            imagenArribaDerecha.setBounds(550, 60, imageIcon.getIconWidth(), imageIcon.getIconHeight());
            add(imagenArribaDerecha);
        }
        // Agregar funcionalidad para cargar imagen aleatoria al hacer clic en la imagen
        imagenArribaDerecha.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Lista de rutas de imágenes
                String[] imagePaths = {
                        "/org/adrian/imagenes/hack1.jpg",
                        "/org/adrian/imagenes/hack10.png",
                        "/org/adrian/imagenes/hack4.jpg",
                        "/org/adrian/imagenes/hack3.jpg",
                        "/org/adrian/imagenes/igor.jpg",
                        "/org/adrian/imagenes/hack5.jpg",
                        "/org/adrian/imagenes/hack6.jpg",
                        "/org/adrian/imagenes/hack7.jpg",
                        "/org/adrian/imagenes/hack8.jpg",
                        "/org/adrian/imagenes/hack9.jpg",
                        "/org/adrian/imagenes/hack11.png",
                        "/org/adrian/imagenes/hack12.jpg",
                        "/org/adrian/imagenes/hack13.jpg",
                        "/org/adrian/imagenes/hack14.jpg",
                        "/org/adrian/imagenes/hack15.jpg",
                        "/org/adrian/imagenes/hack16.jpg",
                        "/org/adrian/imagenes/hack2.jpg"
                };

                // Obtener una ruta aleatoria diferente de la ruta actual
                Random random = new Random();
                String imagenAleatoria;
                // Repite el proceso hasta encontrar una imagen que no sea la anterior usada
                do {
                    imagenAleatoria = imagePaths[random.nextInt(imagePaths.length)];
                } while (imagenAleatoria.equals(imagenPrevia));

                imagenPrevia = imagenAleatoria; // Actualiza la imagen previa con la nueva

                // Cargar la imagen aleatoria
                URL randomImageURL = getClass().getResource(imagenAleatoria);
                if (randomImageURL != null) {
                    ImageIcon randomImageIcon = new ImageIcon(randomImageURL);
                    imagenArribaDerecha.setIcon(randomImageIcon); // Actualizar la imagen mostrada
                }
            }
        });

        //------------
        //------------ Imagen esquina inferior derecha

        // Obtener la URL de la imagen
        URL imagenAbajoDerecha = getClass().getResource("/org/adrian/imagenes/DaniCast.png");
        ImageIcon icon2 = new ImageIcon(imagenAbajoDerecha);
        JLabel imageLabel2 = new JLabel(icon2);

        //ImageIcon imageIcon2 = new ImageIcon(imagenAbajoDerecha);
        //imageLabel2.setIcon(imageIcon2);


        // Calcular las coordenadas para la esquina inferior derecha
        int x = frame.getWidth() - icon2.getIconWidth() -17;
        int y = frame.getHeight() - icon2.getIconHeight() -40;
        imageLabel2.setBounds(x, y, icon2.getIconWidth(), icon2.getIconHeight());

        // Crear un borde alrededor de la imagen
        Border border = BorderFactory.createLineBorder(Color.BLACK, 2); // Puedes ajustar el color y el grosor del borde según tus preferencias
        imageLabel2.setBorder(border);

        //mouse listener para cuando se haga click en la imagen
        imageLabel2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    // Abre la página web en el navegador predeterminado
                    Desktop.getDesktop().browse(new URI("https://www.danielcastelao.org/"));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                // Cambia el cursor del ratón cuando pasa sobre la imagen
                imageLabel2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // Cambia el cursor del ratón cuando sale de la imagen
                imageLabel2.setCursor(Cursor.getDefaultCursor());
            }
        });
        add(imageLabel2);
        //------------
        //------------ Imagen esquina inferior izquierda

        URL imageURL3 = getClass().getResource("/org/adrian/imagenes/github.png");
        ImageIcon icon3 = new ImageIcon(imageURL3);
        JLabel imageLabel3 = new JLabel(icon3);

        if (imageURL3 != null) {
            ImageIcon imageIcon3 = new ImageIcon(imageURL3);
            imageLabel3.setIcon(imageIcon3);
            // Calcular las coordenadas para la esquina inferior izquierda
            int x2 = 10;
            int y2 = frame.getHeight() - icon3.getIconHeight() - 40;
            imageLabel3.setBounds(x2, y2, icon3.getIconWidth(), icon3.getIconHeight());

            // Crear un borde alrededor de la imagen
            Border border2 = BorderFactory.createLineBorder(Color.BLACK, 2);
            imageLabel3.setBorder(border2);

            // mouse listener para cuando se haga click en la imagen
            imageLabel3.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    try {
                        // Abre la pgina web en el navegador
                        Desktop.getDesktop().browse(new URI("https://github.com/"));
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    // Cambia el cursor del ratón cuando pasa sobre la imagen
                    imageLabel3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    // Cambia el cursor del ratón cuando sale de la imagen
                    imageLabel3.setCursor(Cursor.getDefaultCursor());
                }
            });
            add(imageLabel3);
        }

        //------------ Imagen entre las dos esquinas inferiores

        URL URLYouTube = getClass().getResource("/org/adrian/imagenes/youtube.png");
        ImageIcon iconYouTube = new ImageIcon(URLYouTube);
        JLabel imageLabelYouTube = new JLabel(iconYouTube);

        if (URLYouTube != null) {
            ImageIcon imageIcon4 = new ImageIcon(URLYouTube);
            imageLabelYouTube.setIcon(imageIcon4);
            // Calcular las coordenadas para el centro inferior
            int x3 = (frame.getWidth() - iconYouTube.getIconWidth()) / 2 - 155;
            int y4 = frame.getHeight() - iconYouTube.getIconHeight() - 40;
            imageLabelYouTube.setBounds(x3, y4, iconYouTube.getIconWidth(), iconYouTube.getIconHeight());

            // Crear un borde alrededor de la imagen
            Border border3 = BorderFactory.createLineBorder(Color.BLACK, 2);
            imageLabelYouTube.setBorder(border3);

            // mouse listener para cuando se haga click en la imagen
            imageLabelYouTube.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    try {
                        // Abre la página web en el navegador predeterminado
                        Desktop.getDesktop().browse(new URI("https://www.youtube.com/"));
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    // Cambia el cursor del ratón cuando pasa sobre la imagen
                    imageLabelYouTube.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    // Cambia el cursor del ratón cuando sale de la imagen
                    imageLabelYouTube.setCursor(Cursor.getDefaultCursor());
                }
            });
            add(imageLabelYouTube);
        }

        // Listener boton para añadir un nuevo enlace
        btnAddNote.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Solicita nombre unico para el enlace
                String nombre;
                boolean nombreUnico = false;
                do {
                    nombre = JOptionPane.showInputDialog("Pon nombre al enlace:");
                    if (!nombre.contains(" ")) {
                        if (!nombre.isEmpty()) {
                            if (!gestorEnlaces.existeEnlaceConNombre(nombre)) {
                                nombreUnico = true;
                            } else {
                                JOptionPane.showMessageDialog(EnlacesPanel.this, "El nombre de enlace ya está en uso. Por favor, elija otro.", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        } else {
                            JOptionPane.showMessageDialog(EnlacesPanel.this, "El nombre de enlace no puede estar vacío.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(EnlacesPanel.this, "El nombre de enlace no puede contener espacios en blanco.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } while (!nombreUnico || nombre.contains(" "));

                // Solicitar URL
                String url = JOptionPane.showInputDialog("Escribe la URL:");
                // Verificar si se ha cancelado la entrada
                if (url == null) {
                    url = null; // Asignar null si se cancela la entrada
                }
                String descripcion = JOptionPane.showInputDialog("Escribe algún comentario:");
                // Agregar el nuevo enlace usando el gestor de enlaces
                gestorEnlaces.agregarEnlace(new Enlace(nombre, url, descripcion));
                updateNoteList(); // Actualizar la lista de enlaces mostrados en el panel
            }
        });
        // Listener oton para eliminar un enlace existente
        btnDeleteNote.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Solicitar el nombre del enlace a eliminar
                String nombre;
                    nombre = JOptionPane.showInputDialog("Ingrese el nombre del enlace a eliminar:");
                    if (nombre != null && !nombre.isEmpty()) {
                        if (gestorEnlaces.existeEnlaceConNombre(nombre)) {
                        } else {
                            JOptionPane.showMessageDialog(EnlacesPanel.this, "El enlace '" + nombre + "' no existe.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(EnlacesPanel.this, "Por favor, ingrese un nombre válido de enlace.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                // Si se proporciona un nombre válido, eliminar el enlace y actualizar la lista
                gestorEnlaces.eliminarEnlace(nombre);
                updateNoteList(); // Actualizar la lista de enlaces mostrados en el panel
            }
        });

        // Listener boton para cerrar sesión
        logoutButton.addActionListener(new ActionListener() {
            /**
             * Maneja el evento de clic en el botón de cierre de sesión.
             *
             * @param e El evento de acción.
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                // Mensaje para confirmar el cierre de sesion
                int option = JOptionPane.showConfirmDialog(EnlacesPanel.this, "¿Está seguro que desea cerrar sesión?", "Cerrar Sesión", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    // Cambia el panel de contenido a LoginPanel
                    frame.setContentPane(new LoginPanel(frame, gestorUsuarios)); //
                    frame.revalidate();
                    // Restablecer los campos de texto del panel de inicio de sesion
                    ((LoginPanel)frame.getContentPane()).resetFields();
                }
            }
        });

        updateNoteList(); // Actualizar la lista de enlaces mostrados en el panel
    }

    /**
     * Actualiza la lista de enlaces mostrados en el panel.
     */
    private void updateNoteList() {
        StringBuilder html = new StringBuilder("<html><body>");
        for (Enlace enlace : gestorEnlaces.obtenerTodosLosEnlaces()) {
            html.append("<p>")
                    .append(enlace.getNombre()).append(" - ")
                    .append("<a href='").append(enlace.getUrl()).append("'>").append(enlace.getUrl()).append("</a>")
                    .append(" - ").append(enlace.getComentario())
                    .append("</p>");
        }
        html.append("</body></html>");
        // Establecer el texto HTML en el JTextPane para mostrar la lista de enlaces
        textPane.setText(html.toString());
    }
}

