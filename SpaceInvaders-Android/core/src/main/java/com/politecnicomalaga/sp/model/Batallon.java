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
        if (escuadrones.isEmpty()) return; //Si por lo que sea no se ha inicializado todavía no hacemos nada

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
    //Invertir la dirección y bajar el batallón
    private void cambiarDireccionYBajarse(float cuantoBaja) {
        //Invertimos la dirección
        direccionActual = (direccionActual == Ovni.Direccion.DERECHA) ? Ovni.Direccion.IZQUIERDA : Ovni.Direccion.DERECHA;
        //Bajar el batallón usando el método de la clase escuadron
        for (Escuadron esc : escuadrones) {
            esc.bajar(cuantoBaja);
        }
    }

    //El batallón dice disparar y los escuadrones ya se encargan de gestionar lo suyo.
    public void disparar() {
        for (Escuadron esc : escuadrones) {
            esc.disparar();
        }
    }

    //Gestionamos los disparos de los enemigos, batallón se lo pasa a escuadron y escuadron a nave Enemiga que se encarga del CRUD
    public void gestionarDisparos(float limiteMuerte) {
        for (Escuadron esc : escuadrones) {
            esc.gestionarDisparosEnemigos(limiteMuerte);
        }
    }
    //Por si queremos en un futuro seguir añadiendo escuadrones al batallón, añadiendo dificultad
    public void agregarEscuadron(Escuadron esc) {
        this.escuadrones.add(esc);
    }
    //Comprobamos si quedan tropas, si no quedan terminamos el juego más fácil para el controlador.
    public boolean tieneTropas() {
        if (escuadrones.isEmpty()) return false; //Comprobamos si está vacío en ese caso obviamente no hay tropas

        //El problema es que cuando mueren las naves siguen existiendo péro su estado está en Muerto
        //Recorremos los escuadrones y preguntamos si tienen naves vivas
        for (Escuadron esc : escuadrones) {
            if (esc.tieneNavesVivas()) {
                return true; // En cuanto uno tenga una nave viva, el batallón sigue activo
            }
        }
        return false; // Si revisa todos y nadie tiene naves vivas, se acabó el juego, hemos ganado
    }
}
