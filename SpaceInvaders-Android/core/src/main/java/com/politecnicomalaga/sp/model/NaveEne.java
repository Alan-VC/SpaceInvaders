package com.politecnicomalaga.sp.model;

import java.util.Random;

public class NaveEne extends Nave{

    private int probailidadDisparo;
    private Random r;

    public NaveEne(float x, float y, float width, float height, Estado estado, Direccion dir, String textura, int vidas, float cadencia, int probailidadDisparo, Random r) {
        super(x, y, width, height, estado, dir, textura, vidas, cadencia);
        this.probailidadDisparo = probailidadDisparo;
        this.r = r;
    }

    public int getProbailidadDisparo() {
        return probailidadDisparo;
    }

    public void setProbailidadDisparo(int probailidadDisparo) {
        this.probailidadDisparo = probailidadDisparo;
    }

    public Random getR() {
        return r;
    }

    public void setR(Random r) {
        this.r = r;
    }

    //Create
    @Override
    public void disparar() {
        if (estaVivo()) { //comprobación basica si esta vivo antes de realizar calculos

            //Se realiza un random de 1000 cada frame (60 veces por segundo)
            //Si el resultado es menor a la cadencia disparamos
            //Por ejemplo una cadencia de 5 es un 0,5% probabilidad de disparo en cada frame, x 60 un 30% cada segundo
            //Eso es aproximadamente un disparo cada 3 segundos.
            //La cadencia minima es 1, 6% probabilidad por segundo, aproximadamente cada 16 segundos

            if (r.nextInt(1000) < (int)getCadencia()){
                float x = getX() + getMitadWidth(); //De la propia nave su X mas la mitad para que se genere en el centro
                float y = getY();
                DisparoEne disparoEne = new DisparoEne(x,y);

                disparoEne.setX(disparoEne.getX() - disparoEne.getMitadWidth()); //para centrar con respecto a la nave
            }
        }
    }

    //Mover

    //Comprobar colision

    //Eliminarse si no esta en pantalla
}
