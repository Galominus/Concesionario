package modelo;

public class Pedido {
    private int id;
    private String modelo;
    private String motor;
    private String color;
    private int ruedas;
    private boolean pilotoAuto;

    public Pedido(int id,String modelo, String motor, String color, int ruedas, boolean pilotoAuto) {
        this.id = id;
        this.modelo = modelo;
        this.motor = motor;
        this.color = color;
        this.ruedas = ruedas;
        this.pilotoAuto = pilotoAuto;
    }

    public Pedido(String modelo, String motor, String color, int ruedas, boolean pilotoAuto) {
        this.modelo = modelo;
        this.motor = motor;
        this.color = color;
        this.ruedas = ruedas;
        this.pilotoAuto = pilotoAuto;
    }

    public int getId() {
        return id;
    }

    public String getModelo() {
        return modelo;
    }

    public String getMotor() {
        return motor;
    }

    public String getColor() {
        return color;
    }

    public int getRuedas() {
        return ruedas;
    }

    public boolean isPilotoAuto() {
        return pilotoAuto;
    }

    @Override
    public String toString() {
        return id + " "+ modelo + " " + motor + " " + color + " " + ruedas + " " + pilotoAuto;
    }
}
