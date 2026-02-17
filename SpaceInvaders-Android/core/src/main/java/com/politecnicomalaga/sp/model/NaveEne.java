package com.politecnicomalaga.sp.model;

import java.util.Random;

public class NaveEne extends Nave{

    private int probabilidadDisparo; //Entre 0 y 100
    private Random r;

    public NaveEne(float x, float y, float width, float height, Estado estado, Direccion dir, String textura, int vidas, float cadencia, int probabilidadDisparo, Random r) {
        super(x, y, width, height, estado, dir, textura, vidas, cadencia);
        this.probabilidadDisparo = probabilidadDisparo;
        this.r = r;
    }

    public int getprobabilidadDisparo() {
        return probabilidadDisparo;
    }

    public void setprobabilidadDisparo(int probabilidadDisparo) {
        this.probabilidadDisparo = probabilidadDisparo;
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
            contadorCiclos++; //Cada fps suma 1

            //Si cadencia es 60, esperará 60 frames (1 segundo)
            if (contadorCiclos >= getCadencia()) {
                contadorCiclos = 0; //Reinicializamos

                if (r.nextInt(100) < probabilidadDisparo) { //Calculamos probabilidad para que no sea siempre todos disparando a la vez
                    // X es el centro.
                    // Y menos mitadHeight para que salga justo en el borde inferior.
                    new DisparoEne(getX(), getY() - getMitadHeight());
                }
            }
        }
    }
    //Mover

    //Comprobar colision

    //Eliminarse si no esta en pantalla
}
