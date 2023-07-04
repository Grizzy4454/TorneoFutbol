public class Partido {
    private int idPartido;
    private Equipo equipo1;
    private Equipo equipo2;
    private Equipo ganador;
    private String marcadorEquipo1;
    private String marcadorEquipo2;

    public Partido(Equipo equipo1, Equipo equipo2, int idPartido) {
        this.idPartido = idPartido;
        this.equipo1 = equipo1;
        this.equipo2 = equipo2;
        this.marcadorEquipo1 = null;
        this.marcadorEquipo2 = null;
    }

    public int getIdPartido() {
        return idPartido;
    }

    public String getMarcadorEquipo1() {
        return marcadorEquipo1;
    }

    public void setMarcadorEquipo1(String marcadorEquipo1) {
        this.marcadorEquipo1 = marcadorEquipo1;
    }

    public String getMarcadorEquipo2() {
        return marcadorEquipo2;
    }

    public void setMarcadorEquipo2(String marcadorEquipo2) {
        this.marcadorEquipo2 = marcadorEquipo2;
    }

    public void setEquipo1(Equipo equipo1) {
        this.equipo1 = equipo1;
    }

    public void setEquipo2(Equipo equipo2) {
        this.equipo2 = equipo2;
    }

    public Equipo getEquipo1() {
        return equipo1;
    }

    public Equipo getEquipo2() {
        return equipo2;
    }

    public Equipo getGanador() {
        return ganador;
    }

    public String ingresarResultado(String nombreGanador) {
        if (nombreGanador.equals(equipo1.getNombre())) {
            ganador = equipo1;
        } else if (nombreGanador.equals(equipo2.getNombre())) {
            ganador = equipo2;
        } else {
            return "El nombre del equipo ganador no coincide con los equipos que participaron en este partido.";
        }
        return "Resultado ingresado exitosamente.";
    }

    public String getResultado() {
        if (ganador == null) {
            return "El partido aún no se ha disputado.";
        }
        return "El ganador del partido es: " + ganador.getNombre();
    }
}
