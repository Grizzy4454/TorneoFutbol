import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        Interfaz interfaz = new Interfaz();
        interfaz.setTitle("Torneo de Futbol MUNDIAL DE CLUBES");
        interfaz.setSize(900, 500);
        interfaz.setVisible(true);
        interfaz.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}