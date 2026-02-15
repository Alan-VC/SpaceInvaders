package com.politecnicomalaga.sp.model;

import com.badlogic.gdx.Gdx;

public class DisparoEne extends Disparo{
    public DisparoEne(float x, float y, float width, float height) {
        super(x, y, width, height);

    }
    @Override
    public boolean desaparecer() {
        if (getY()<0) return true;
        return false;
    }
    @Override
    public boolean morir() {
        return false;
    }
}






