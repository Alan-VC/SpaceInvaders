package com.politecnicomalaga.sp.model;

import java.util.ArrayList;

public class NaveAmi extends Nave {
    private float anchoBala; //anchoBala --> Se saca perfe con el texture de la bala texturaBala.getWidth() y .getHeight() para la altoBala
    // Y se hace los calculos respecto a la imagen que le pasemos para que sea literalmente lo que mide la imagen
    private float altoBala;
    private float velocidad;

    private ArrayList<DisparoAmi> misDisparos; //Para el crud de disparos

    public NaveAmi(float x, float y, float width, float height, Estado estado, Direccion dir, String textura, int vidas, float cadencia, float anchoBala, float altoBala, float velocidad) {
        super(x, y, width, height, estado, dir, textura, vidas, cadencia);
        this.misDisparos = new ArrayList<>();
        this.anchoBala = anchoBala;
        this.altoBala = altoBala;
        this.velocidad = velocidad;
    }

    public float getAnchoBala() {
        return anchoBala;
    }

    public void setAnchoBala(float anchoBala) {
        this.anchoBala = anchoBala;
    }

    public float getAltoBala() {
        return altoBala;
    }

    public void setAltoBala(float altoBala) {
        this.altoBala = altoBala;
    }

    public void setMisDisparos(ArrayList<DisparoAmi> misDisparos) {
        this.misDisparos = misDisparos;
    }

    public ArrayList<DisparoAmi> getMisDisparos() {
        return misDisparos;
    }

    public float getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(float velocidad) {
        this.velocidad = velocidad;
    }

    //Crear los disparos(Create)
    @Override
    public void disparar() { //Aquí no tenemos random no tiene sentido usarlo, Definimos la cadencia que tendra nuestra nave en el controlador
        if (estaVivo()) {
            // Cálculo de posición centrada (Igual que en NaveEne pero hacia ARRIBA)
            float posX = (getX() + getMitadWidth()) - (this.anchoBala / 2f);
            float posY = getY() + getMitadHeight();

            // Creamos el disparo y lo añadimos a la lista
            DisparoAmi nuevoDisparo = new DisparoAmi(posX, posY, this.anchoBala, this.altoBala, Estado.VIVO, Direccion.ARRIBA, "disparoAmi.png");
            misDisparos.add(nuevoDisparo);

        }
    }

    //Gestionar la salida de la pantalla de los disparos (Delete)
    public void gestionarMisDisparos() {
        for (int i = misDisparos.size() - 1 ; i >= 0; i--){//Recorremos el array al reves para no liarla con los indices
            DisparoAmi d = misDisparos.get(i);

            //Si el disparo esta vivo es decir no ha colisionado lo movemos
            if (d.getEstado() == Estado.VIVO){
                d.mover(Direccion.ARRIBA, velocidad);
                d.desaparecer(); //Preguntamos si se ha salido de la pantalla y este setea el estado a muerto
            }
            if (d.getEstado() == Estado.MUERTO) misDisparos.remove(i); //Lo eliminamos si la comprobación de desaparecer de la pantalla ya nos da que esta Muerto
        }
    }

}
