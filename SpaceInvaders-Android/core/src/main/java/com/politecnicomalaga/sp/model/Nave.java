package com.politecnicomalaga.sp.model;

public abstract class Nave extends Ovni{

    //Atributos
    private int vidas;
    private float cadencia;

    // Atributos compartidos para el disparo
    private float anchoBala;
    private float altoBala;
    private float velocidadBala;

    public Nave(float x, float y, float width, float height, Estado estado, Direccion dir, String textura, int vidas, float cadencia, float anchoBala, float altoBala, float velocidadBala) {
        super(x, y, width, height, estado, dir, textura);
        this.vidas = vidas;
        this.cadencia = cadencia;
        this.anchoBala = anchoBala;
        this.altoBala = altoBala;
        this.velocidadBala = velocidadBala;
    }

    public int getVidas() {
        return vidas;
    }

    public void setVidas(int vidas) {
        this.vidas = vidas;
    }

    public float getCadencia() {
        return cadencia;
    }

    public void setCadencia(float cadencia) {
        this.cadencia = cadencia;
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

    public float getVelocidadBala() {
        return velocidadBala;
    }

    public void setVelocidadBala(float velocidadBala) {
        this.velocidadBala = velocidadBala;
    }

    //Metodos
    //Si recibimos un disparo perdemos una vida, si llega a 0 morimos seteamos a muerto.
    public void recibirDisparo() {
        if (estaVivo()) { // NUEVO: Solo procesamos el daño si no está muerto
            this.vidas--;
            if (this.vidas <= 0) {
                this.vidas = 0; // NUEVO: Evitamos vidas negativas que puedan romper algo en el controlador
                this.setEstado(Estado.MUERTO);
            }
        }
    }

    //Para verificar si esta vivo mas como para usarlo en el controlador
    public boolean estaVivo() {
        return this.getEstado() == Estado.VIVO;
    }

    //Ambas naves disparan y gestionan sus disparos pero lo hacen de manera diferente, metodo abstracto
    public abstract void disparar();
    public abstract void gestionarMisDisparos();
}
