package gruporebechi_garcialozano.dam.isi.frsf.lab02.modelo;

import java.io.Serializable;

/**
 * Created by esteb on 7/9/2017.
 */

public class Pedido implements Serializable{
    private String nombreCliente;
    private String email;
    private String nombre;
    private Double costo;
    private Boolean esDelivery;
    private String horaEntrega;
    private Utils.ElementoMenu bebida;
    private Utils.ElementoMenu plato;
    private Utils.ElementoMenu postre;

    @Override
    public String toString() {
        return "Pedido{" +
                "nombreCliente='" + nombreCliente + '\'' +
                '}';
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getCosto() {
        return costo;
    }

    public void setCosto(Double costo) {
        this.costo = costo;
    }

    public Boolean getEsDelivery() {
        return esDelivery;
    }

    public void setEsDelivery(Boolean esDelivery) {
        this.esDelivery = esDelivery;
    }

    public String getHoraEntrega() {
        return horaEntrega;
    }

    public void setHoraEntrega(String horaEntrega) {
        this.horaEntrega = horaEntrega;
    }

    public Utils.ElementoMenu getBebida() {
        return bebida;
    }

    public void setBebida(Utils.ElementoMenu bebida) {
        this.bebida = bebida;
    }

    public Utils.ElementoMenu getPlato() {
        return plato;
    }

    public void setPlato(Utils.ElementoMenu plato) {
        this.plato = plato;
    }

    public Utils.ElementoMenu getPostre() {
        return postre;
    }

    public void setPostre(Utils.ElementoMenu postre) {
        this.postre = postre;
    }

    public Pedido() {
    }

    public Pedido(String nombreCliente, String email, String nombre, Double costo, Boolean esDelivery, String horaEntrega, Utils.ElementoMenu bebida, Utils.ElementoMenu plato, Utils.ElementoMenu postre) {

        this.nombreCliente = nombreCliente;
        this.email = email;
        this.nombre = nombre;
        this.costo = costo;
        this.esDelivery = esDelivery;
        this.horaEntrega = horaEntrega;
        this.bebida = bebida;
        this.plato = plato;
        this.postre = postre;
    }
}
