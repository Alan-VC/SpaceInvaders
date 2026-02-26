package com.politecnicomalaga.sp.model;

import java.lang.reflect.Array;

public class Escuadron {
    NaveEne [] navesEnemigas;
    float  espacioEntreNaves;
    public Escuadron(float x, float y, float width, float height, Ovni.Estado estado,
                     Ovni.Direccion dir, String textura, int vidas, float cadencia, float anchoBala, float altoBala,
                     float velocidadBala, int probabilidadDisparo, float espacioEntreNaves){

        navesEnemigas = new NaveEne[8];
        navesEnemigas = loadNaves(navesEnemigas, x, y, width, height, estado, dir, textura, vidas, cadencia,
            anchoBala, altoBala, velocidadBala, probabilidadDisparo, espacioEntreNaves);
    }


    public NaveEne[] loadNaves
        (NaveEne [] navesEnemigas, float x, float y, float width, float height, Ovni.Estado estado,
         Ovni.Direccion dir, String textura, int vidas, float cadencia, float anchoBala, float altoBala,
         float velocidadBala, int probabilidadDisparo, float espacioEntreNaves) {

        for (int i = 0; i < navesEnemigas.length; i++) {
            //Hacemos una X diferente para cada nave para que no se solapen
            float xNave = x + (i * (width + espacioEntreNaves));
            navesEnemigas[i] = new NaveEne(xNave, y, width, height, estado, dir, textura, vidas, cadencia, anchoBala, altoBala, velocidadBala, probabilidadDisparo);
        }

            return navesEnemigas;
    }
    public boolean haTocadoBorde(float anchoPantalla){
        for (NaveEne naveEne : navesEnemigas) {
            if (!naveEne.estaVivo()){
                continue;
            }
            if (naveEne.getX() + naveEne.getWidth() >= anchoPantalla) {
                return true;
            }
            if (naveEne.getX() <= 0) {
                return true;
            }
        }
        return false;
    }
    public void moverLateralmente(Ovni.Direccion direccionActual, float velocidad) {
        for (NaveEne naveEne : navesEnemigas) {
            if (naveEne.estaVivo()) {
                float xActual = naveEne.getX();

                if (direccionActual == Ovni.Direccion.DERECHA) {
                    naveEne.setX(xActual + velocidad);
                } else if (direccionActual == Ovni.Direccion.IZQUIERDA) {
                    naveEne.setX(xActual - velocidad);
                }
            }
        }
    }
    public void bajar(float cuantoBaja) {
        for (NaveEne naveEne :navesEnemigas) {
            //Aqui solo bajan las naves que estén vivas por eso la comprobación
            if (naveEne.estaVivo()) {
                float yActual = naveEne.getY();
                naveEne.setY(yActual - cuantoBaja);
            }
        }
    }
    public void gestionarDisparosEnemigos(float limiteInferior) {
        for (NaveEne naveEne :navesEnemigas) {
            if (naveEne.estaVivo()) {
                naveEne.gestionarMisDisparos(limiteInferior);
            }
        }
    }
}
