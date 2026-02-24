package com.politecnicomalaga.sp.model;

import com.badlogic.gdx.Gdx;

public class DisparoEne extends Disparo{
    public DisparoEne(float x, float y, float width, float height, Estado estado, Direccion dir, String textura) {
        super(x, y, width, height, estado, dir, textura);

    }
    @Override
    public boolean desaparecer() {
        if (this.getY()<=0) {
            this.setEstado(Estado.MUERTO);
            return true;
        }
        return false;
    }

    public boolean comprobarColision(NaveAmi naveAmi) {
        if (naveAmi.estaVivo() && this.colision(naveAmi)) {
            naveAmi.recibirDisparo();
            this.setEstado(Estado.MUERTO);
            return true;
        }
        return false;
    }
}
