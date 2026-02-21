package com.politecnicomalaga.sp.model;

import java.util.Random;

public class NaveAmi extends Nave{
    private float anchoBala; //anchoBala --> Se saca perfe con el texture de la bala texturaBala.getWidth() y .getHeight() para la altoBala
                            // Y se hace los calculos respecto a la imagen que le pasemos para que sea literalmente lo que mide la imagen
    private float altoBala;
    private Random r;
    public NaveAmi(float x, float y, float width, float height, Estado estado, Direccion dir, String textura, int vidas, float cadencia, float anchoBala, float altoBala){
        super(x, y, width, height, estado, dir, textura, vidas, cadencia);
        this.r = new Random();
    }

    @Override
    public void disparar() {
        if (estaVivo()) {
            if (r.nextInt(1000) < (int)getCadencia()){
                DisparoAmi nuevoDisparo = new DisparoAmi (
                    getX(),
                    getY() + getMitadHeight(),
                    this.anchoBala,
                    this.altoBala,
                    Estado.VIVO,
                    Direccion.ARRIBA,
                    "disparoAmi.png"
                );
                nuevoDisparo.setX(nuevoDisparo.getX() - nuevoDisparo.getMitadWidth());

            }
        }
    }

}
