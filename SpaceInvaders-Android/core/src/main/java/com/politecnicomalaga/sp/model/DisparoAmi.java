package com.politecnicomalaga.sp.model;

import com.badlogic.gdx.Gdx;

import java.util.ArrayList;

public class DisparoAmi extends Disparo{
    public DisparoAmi(float x, float y, float width, float height, Estado estado, Direccion dir, String textura) {
        super(x, y, width, height, estado, dir, textura);
    }
    @Override
    public boolean desaparecer() {
        if(this.getY()> Gdx.graphics.getHeight()){
            this.setEstado(Estado.MUERTO);
            return true;
        }
        return false;
    }
    public boolean comprobarColision(ArrayList<NaveEne> enemigos) {
        // Si ya impactó ( y está pendiente de borrar) evitamos "doblekill"
        // Y evitamos que una bala muerta siga matando mientras espera ser borrada
        if (this.getEstado() == Estado.MUERTO) return false;


        //Recorre todos los enemigos para ver si han tocado el disparo
        for (NaveEne enemigo : enemigos) {
            if (enemigo.estaVivo() && this.colision(enemigo)) {
                enemigo.recibirDisparo(); //Tocar recibir disparos según la vida de la nave enemiga "1"
                this.setEstado(Estado.MUERTO);
                return true;
            }
        }
        return false;

    }
}
