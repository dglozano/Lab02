package gruporebechi_garcialozano.dam.isi.frsf.lab02.modelo;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by esteb on 7/9/2017.
 */

public class Tarjeta implements Serializable{
    private String nombre;
    private Integer numero;
    private Integer seguridad;
    private Date fechaVencimiento;

    @Override
    public String toString() {
        return "Tarjeta{" +
                "nombre='" + nombre + '\'' +
                '}';
    }

    public Tarjeta(String nombre, Integer numero, Integer seguridad, Date fechaVencimiento) {
        this.nombre = nombre;
        this.numero = numero;
        this.seguridad = seguridad;
        this.fechaVencimiento = fechaVencimiento;
    }

    public String getNombre() {

        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Integer getSeguridad() {
        return seguridad;
    }

    public void setSeguridad(Integer seguridad) {
        this.seguridad = seguridad;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }
}
