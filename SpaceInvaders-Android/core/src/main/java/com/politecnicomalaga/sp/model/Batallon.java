package com.politecnicomalaga.sp.model;

import java.util.ArrayList;
import java.util.List;

public class Batallon {
    //Atributos
    //Composición de 4 escuadrones
    //Definimos una lista de escuadrones, la dirección actual y la velocidad
    private List<Escuadron> escuadrones;
    private Ovni.Direccion direccionActual;
    private float velocidad;

    //Constructor
    public Batallon(Ovni.Direccion direccionActual, float velocidad) {
        this.escuadrones = new ArrayList<>();
        this.direccionActual = direccionActual;
        this.velocidad = velocidad;
    }
    //Getters y Setters
    public List<Escuadron> getEscuadrones() {
        return escuadrones;
    }
    public void setEscuadrones(List<Escuadron> escuadrones) {
        this.escuadrones = escuadrones;
    }
    public Ovni.Direccion getDireccionActual() {
        return direccionActual;
    }
    public void setDireccionActual(Ovni.Direccion direccionActual) {
        this.direccionActual = direccionActual;
    }
    public float getVelocidad() {
        return velocidad;
    }
    public void setVelocidad(float velocidad) {
        this.velocidad = velocidad;
    }
    //Métodos
    //Mover los escuadrones
    public void mover(float anchoPantalla, float altoPantalla, float cuantoBaja){ //Nos deberán pasar el ancho de la pantalla el alto de la pantalla y cuanto queremos que baje cada vez que llega al borde
        boolean tocarBorde = false;

        for (Escuadron esc : escuadrones) { //Recorremos los escuadrones preguntando si han tocado el borde
            if (esc.haTocadoBorde(anchoPantalla)){
                tocarBorde = true;
                break;
            }
        }
        if (tocarBorde){ //En ese caso cambiamos la dirección y bajamos el batallón, utilizando el método cambiarDireccionYBajarse
            cambiarDireccionYBajarse(cuantoBaja);
            }
        else {
            for (Escuadron esc : escuadrones) { //Caso contrario, movemos los escuadrones lateralmente usando el método implementado en escuadron
                esc.moverLateralmente(direccionActual, velocidad);
            }
        }
    }
    private void cambiarDireccionYBajarse(float cuantoBaja) {
        //Invertimos la dirección
        if (direccionActual == Ovni.Direccion.DERECHA) direccionActual = Ovni.Direccion.IZQUIERDA;
        else direccionActual = Ovni.Direccion.DERECHA;
        //Bajar el batallón usando el método de la clase escuadron
        for (Escuadron esc : escuadrones) {
            esc.bajar(cuantoBaja);
        }
    }
    public void gestionarDisparos(float limiteMuerte) { //Gestionamos los disparos de los enemigos, batallón se lo pasa a escuadron y escuadron a nave Enemiga que se encarga del CRUD
        for (Escuadron esc : escuadrones) {
            esc.gestionarDisparosEnemigos(limiteMuerte);
        }
    }

    public void agregarEscuadron(Escuadron esc) {
        this.escuadrones.add(esc);
    }
}
