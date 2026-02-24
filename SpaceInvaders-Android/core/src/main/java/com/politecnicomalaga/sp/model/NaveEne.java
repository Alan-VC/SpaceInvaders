package com.politecnicomalaga.sp.model;

import java.util.ArrayList;

public class NaveEne extends Nave{

    private int probabilidadDisparo; //Entre 0 y 100 la seteamos en el controlador
    private ArrayList<DisparoEne> misDisparos; //Lista de disparos
    private float anchoBala;
    private float altoBala;

    public NaveEne(float x, float y, float width, float height, Estado estado, Direccion dir, String textura, int vidas, float cadencia, int probabilidadDisparo, float anchoBala, float altoBala) {
        super(x, y, width, height, estado, dir, textura, vidas, cadencia);
        this.probabilidadDisparo = probabilidadDisparo;
        this.misDisparos = new ArrayList<>();
        this.anchoBala = anchoBala;
        this.altoBala = altoBala;
    }

    public int getProbabilidadDisparo() {
        return probabilidadDisparo;
    }
    public void setProbabilidadDisparo(int probabilidadDisparo) {
        this.probabilidadDisparo = probabilidadDisparo;
    }

    public ArrayList<DisparoEne> getMisDisparos() {
        return misDisparos;
    }

    public void setMisDisparos(ArrayList<DisparoEne> misDisparos) {
        this.misDisparos = misDisparos;
    }

    //Crear los disparos(Create)
    @Override
    public void disparar() {
        if (estaVivo()) {
            if (Math.random() * 100 < getProbabilidadDisparo()) {
                // Cálculo dinámico para centrar la bala perfectamente
                float posX = (getX() + getMitadWidth()) - (this.anchoBala / 2f);
                float posY = getY() - this.altoBala;

                // Creamos el disparo y lo añadimos a la lista
                DisparoEne nuevoDisparo = new DisparoEne(posX, posY, this.anchoBala, this.altoBala, Estado.VIVO, Direccion.ABAJO, "disparoEne.png");
                misDisparos.add(nuevoDisparo);
            }
        }
    }
    //Gestionar la salida de la pantalla de los disparos (Delete)
    public void gestionarMisDisparos() {
        for (int i = misDisparos.size() - 1 ; i >= 0; i--){//Recorremos el array al reves para no liarla con los indices
            DisparoEne d = misDisparos.get(i);

            //Si el disparo esta vivo es decir no ha colisionado lo movemos
            if (d.getEstado() == Estado.VIVO){
                d.mover(Direccion.ABAJO, 5f);
                d.desaparecer(); //Preguntamos si se ha salido de la pantalla y este setea el estado a muerto
            }
            if (d.getEstado() == Estado.MUERTO) misDisparos.remove(i); //Lo eliminamos si la comprobación de desaparecer de la pantalla ya nos da que esta Muerto
        }
    }
}
