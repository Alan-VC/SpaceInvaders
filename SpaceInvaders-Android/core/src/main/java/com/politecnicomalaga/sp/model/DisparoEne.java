package com.politecnicomalaga.sp.model;

public class DisparoEne extends Disparo{
    public DisparoEne(float x, float y, float width, float height, Estado estado, Direccion dir, String textura) {
        super(x, y, width, height, estado, dir, textura);

    }
    @Override
    public void desaparecer(float limiteInferior) {
        if (this.getY()<=limiteInferior) {
            this.setEstado(Estado.MUERTO);
        }
    }

    public boolean comprobarColision(NaveAmi naveAmi) {
        // Si ya impactó (y está pendiente de borrar) evitamos "doblekill"
        // Y evitamos que una bala muerta siga matando mientras espera ser borrada
        if (this.getEstado() == Estado.MUERTO) return false;

        if (naveAmi.estaVivo() && this.colision(naveAmi)) {
            naveAmi.recibirDisparo();
            this.setEstado(Estado.MUERTO);
            return true;
        }
        return false;
    }
}
