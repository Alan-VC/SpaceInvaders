package com.politecnicomalaga.sp.control;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Map;

public class Controlador {
    private static Controlador miSingle;
    private Controlador() {
    }

    public static Controlador getInstance(){
        if (miSingle == null){
            miSingle= new Controlador();
        }
            return miSingle;
    }
    public void click (float x, float y){

    }
    public void simulaMundo(){
        /*
        he muerto
        cada enemigo: ¿he muerto?
        ¿toca disparar? (jugador)
        cada enemigo: ¿toca disparar?
        ¿hay que mover la nave jugador?
         */


    }

    public void pintar(SpriteBatch batch, Map<String, Texture> galeriaImagenes){

    }



}
