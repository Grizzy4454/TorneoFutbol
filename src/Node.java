public class Node {
    private Partido partido;
    private Node izquierdo;
    private Node derecho;

    public Node(Partido partido) {
        this.partido = partido;
    }

    public Partido getPartido() {
        return partido;
    }

    public void setIzquierdo(Node izquierdo) {
        this.izquierdo = izquierdo;
    }

    public void setDerecho(Node derecho) {
        this.derecho = derecho;
    }

    public Node getIzquierdo() {
        return izquierdo;
    }

    public Node getDerecho() {
        return derecho;
    }
}

