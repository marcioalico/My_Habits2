package ies.tsds.myhabits.modelo;

public class ToDo {

    private String tarea;
    private String dias;

    public ToDo() {
    }

    public ToDo(String tarea, String dias) {
        this.tarea = tarea;
        this.dias = dias;
    }

    public String getTarea() {
        return tarea;
    }

    public void setTarea(String tarea) {
        this.tarea = tarea;
    }

    public String getDias() {
        return dias;
    }

    public void setDias(String dias) {
        this.dias = dias;
    }
}
