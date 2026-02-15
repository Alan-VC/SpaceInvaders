package com.politecnicomalaga.sp.model;

import java.util.ArrayList;

public abstract class Disparo extends Ovni {



    public Disparo( float x, float y, float width, float height, Estado estado, Direccion dir, String textura){
        super(x, y, width, height, estado, dir, textura);
    }

    //Metodos de Disparo
    public abstract boolean desaparecer();


}
