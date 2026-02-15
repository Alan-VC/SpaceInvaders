package com.politecnicomalaga.sp.model;

import java.util.ArrayList;

public abstract class Disparo extends Ovni {



    public Disparo( float x, float y, float width, float height){
        super(x, y, width, height);
    }

    //Metodos de Disparo

    public abstract boolean desaparecer();
    public abstract boolean morir();

}
