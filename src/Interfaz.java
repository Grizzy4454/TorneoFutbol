import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class Interfaz extends JFrame {
    private JButton crearButton;
    private JComboBox comboBoxNumEquipos;
    private JComboBox comboBoxEquip;
    private JButton asignarButton;
    private JComboBox comboBoxEncuentro;
    private JButton comenzarTorneoButton;
    private JButton quemarDatosButton;
    private JTextField textFieldEquip1;
    private JTextField textFieldEquip2;
    private JTextArea textArea1;
    private JPanel Jpanel;
    private JTextField textFieldNomEquipo;
    private JButton finalizarButton;
    private JLabel Equipo1;
    private JLabel Equipo2;
    Torneo torneo;
    Partido p;

    public Interfaz(){
    setContentPane(Jpanel);


        crearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int numEquipos = Integer.parseInt(comboBoxNumEquipos.getSelectedItem().toString());
                torneo = new Torneo(numEquipos);
                actualizarEquiposBox(numEquipos);
                p = null;
                iniciarBotones(true);
                mostrarTorneo();

            }
        });
        asignarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!textFieldNomEquipo.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, torneo.asignarNombreEquipo(comboBoxEquip.getSelectedItem().toString(), textFieldNomEquipo.getText()));
                    mostrarTorneo();
                } else {
                    JOptionPane.showMessageDialog(null, "Error. El nombre no debe ser nulo");
                }
            }
        });
        comenzarTorneoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p = torneo.obtenerPartidoPorId(Integer.parseInt(comboBoxEncuentro.getSelectedItem().toString()));
                if (p.getEquipo1().getNombre() == null || p.getEquipo2().getNombre() == null) {
                    finalizarButton.setEnabled(false);
                    textFieldEquip1.setEnabled(false);
                    textFieldEquip2.setEnabled(false);

                    textFieldEquip1.setText("");
                    textFieldEquip2.setText("");

                    Equipo1.setText("Equipo: ");
                    Equipo2.setText("Equipo: ");
                    JOptionPane.showMessageDialog(null, "Error. El partido no puede empezar ya que por lo menos hay un equipo que no esta asignado al partido.");
                } else {
                    Equipo1.setText("Equipo: " + p.getEquipo1().getNombre());
                    Equipo2.setText("Equipo: " + p.getEquipo2().getNombre());
                    finalizarButton.setEnabled(true);
                    textFieldEquip1.setEnabled(true);
                    textFieldEquip2.setEnabled(true);
                }
            }
        });
        finalizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!textFieldEquip1.getText().isEmpty()) {
                    if (!textFieldEquip2.getText().isEmpty()) {
                        if (Integer.parseInt(textFieldEquip1.getText()) == Integer.parseInt(textFieldEquip2.getText())) {
                            JOptionPane.showMessageDialog(null, "Error. El partido no puede quedar empate");
                        } else {
                            if (Integer.parseInt(textFieldEquip1.getText()) > Integer.parseInt(textFieldEquip2.getText())) {
                                JOptionPane.showMessageDialog(null, torneo.jugarPartido(Integer.parseInt(comboBoxEncuentro.getSelectedItem().toString()), p.getEquipo1().getNombre(), textFieldEquip1.getText(), textFieldEquip2.getText()));
                            } else {
                                JOptionPane.showMessageDialog(null,torneo.jugarPartido(Integer.parseInt(comboBoxEncuentro.getSelectedItem().toString()), p.getEquipo2().getNombre(), textFieldEquip1.getText(), textFieldEquip2.getText()));
                            }
                            finalizarButton.setEnabled(false);
                            textFieldEquip1.setEnabled(false);
                            textFieldEquip2.setEnabled(false);

                            textFieldEquip1.setText("");
                            textFieldEquip2.setText("");

                            Equipo1.setText("Equipo: ");
                            Equipo2.setText("Equipo: ");

                            mostrarTorneo();
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Error. No se han ingresado el resultado del equipo 2.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Error. No se han ingresado el resultado del equipo 1.");
                }
            }
        });


        textFieldEquip1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c) || textFieldEquip1.getText().length() >= 3) {
                    e.consume();  // Ignorar el evento de la tecla
                }
            }
        });

        textFieldEquip2.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c) || textFieldEquip2.getText().length() >= 3) {
                    e.consume();  // Ignorar el evento de la tecla
                }
            }
        });

        quemarDatosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)   {
                quemarDatos();
            }


        });
    }

    public void actualizarEquiposBox(int numEquipos) {
        comboBoxEquip.removeAllItems(); // limpia el comboBox para evitar duplicados
        comboBoxEncuentro.removeAllItems();

        // crea los equipos y los agrega al comboBox
        for (int i = 1; i <= numEquipos; i++) {
            comboBoxEquip.addItem("Equipo " + i);

            if (i < numEquipos) {
                comboBoxEncuentro.addItem(i);
            }
        }
    }

    public void iniciarBotones(boolean bool) {
        quemarDatosButton.setEnabled(bool);
        asignarButton.setEnabled(bool);
        comenzarTorneoButton.setEnabled(bool);
        textFieldNomEquipo.setEnabled(bool);
        textFieldNomEquipo.setText("");
        textFieldEquip1.setText("");
        textFieldEquip2.setText("");
    }

   public void quemarDatos(){

    Random rand = new Random();
    String[] nombresEquipos = {
            "Real Madrid", "Barcelona", "Atletico Madrid", "Sevilla", "Manchester United",
            "Manchester City", "Chelsea", "Liverpool", "Arsenal", "Tottenham", "Juventus",
            "Inter Milan", "AC Milan", "Roma", "Napoli", "Paris Saint-Germain", "Lyon",
            "Monaco", "Bayern Munich", "Borussia Dortmund", "Schalke 04", "Bayer Leverkusen",
            "Ajax", "PSV Eindhoven", "Feyenoord", "Benfica", "Porto", "Sporting Lisbon",
            "Shakhtar Donetsk", "Dynamo Kiev", "River Plate", "Boca Juniors", "Club America",
            "Monterrey", "Flamengo", "Palmeiras", "Sao Paulo", "Corinthians", "Celtic",
            "Rangers", "Zenit St. Petersburg", "CSKA Moscow", "Al Ahly", "Esperance de Tunis",
            "Kaizer Chiefs", "Mamelodi Sundowns", "Guangzhou Evergrande", "Beijing Guoan",
            "Urawa Red Diamonds", "Kashima Antlers", "Jeonbuk Hyundai Motors", "Al Hilal",
            "Al Nassr", "Persepolis", "Esteghlal", "Sydney FC", "Melbourne Victory",
            "Los Angeles FC", "Seattle Sounders", "New York City FC", "Toronto FC",
            "Club Brugge", "Anderlecht", "Galatasaray", "Fenerbahce", "Besiktas",
            "Olympiacos", "PAOK", "Copenhagen", "Malmo FF"
    };

    int numEquipos = Integer.parseInt(comboBoxNumEquipos.getSelectedItem().toString());

                for (int i = 0; i < numEquipos; i++) {
        torneo.asignarNombreEquipo("Equipo " + (i + 1), nombresEquipos[i]);
    }

    mostrarTorneo();

                for(int i = 0; i < numEquipos-1; i++){
        Partido p = torneo.obtenerPartidoPorId(torneo.obtenerNodosHojaYPadres().get(i));
        int res1 = rand.nextInt(5);  //Genera un número aleatorio entre 0 y 4 para el equipo 1
        int res2;

        do {
            res2 = rand.nextInt(5);  //Genera un número aleatorio entre 0 y 4 para el equipo 2
        } while (res1 == res2);  //Si los resultados son iguales, genera un nuevo resultado para el equipo 2

        String ganador = res1 > res2 ? p.getEquipo1().getNombre() : p.getEquipo2().getNombre();
        JOptionPane.showMessageDialog(null, torneo.jugarPartido(torneo.obtenerNodosHojaYPadres().get(i), ganador, String.valueOf(res1), String.valueOf(res2)));
        mostrarTorneo();
    }

                quemarDatosButton.setEnabled(false);
   }


    public void mostrarTorneo() {
        textArea1.setText(torneo.representarArbol());
    }
}


