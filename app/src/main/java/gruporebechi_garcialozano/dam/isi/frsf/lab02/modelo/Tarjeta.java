package gruporebechi_garcialozano.dam.isi.frsf.lab02.modelo;

import java.io.Serializable;
import java.util.Date;

public class Tarjeta implements Serializable{
    private String nombre;
    private String numero;
    private Integer seguridad;
    private String fechaVencimiento;

    @Override
    public String toString() {
        return "Tarjeta{" +
                "nombre='" + nombre + '\'' +
                '}';
    }

    public Tarjeta() {
    }

    public String getNombre() {

        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Integer getSeguridad() {
        return seguridad;
    }

    public void setSeguridad(Integer seguridad) {
        this.seguridad = seguridad;
    }

    public String getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(String fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }
}
