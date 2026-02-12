package com.politecnicomalaga.sp.model;

public abstract class Nave extends Ovni{

    //Atributos
    private int vidas;
    private float cadencia;

    //Constructor
    public Nave(float x, float y, float width, float height, Estado estado, Direccion dir, String textura, int vidas, float cadencia) {
        super(x, y, width, height, estado, dir, textura);
        this.vidas = vidas;
        this.cadencia = cadencia;
    }


    //Getters
    public int getVidas() {
        return vidas;
    }

    public void setVidas(int vidas) {
        this.vidas = vidas;
    }

    //Setters
    public float getCadencia() {
        return cadencia;
    }

    public void setCadencia(float cadencia) {
        this.cadencia = cadencia;
    }

    //Metodos
    //Si recibimos un disparo perdemos una vida, si llega a 0 morimos seteamos a muerto.
    public void recibirDisparo(){
        this.vidas--;
        if (this.vidas<=0) this.setEstado(Estado.MUERTO);
    }

    //Para verificar si esta vivo mas como para usarlo en el controlador
    public boolean estaVivo() {
        return this.getEstado() == Estado.VIVO;
    }

    //Una nave puede disparar, es abstracto, cada nave dispara de su manera pero debe implementarlo
    public abstract void disparar();

}
