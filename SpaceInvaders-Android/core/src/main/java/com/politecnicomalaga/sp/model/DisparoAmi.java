package com.politecnicomalaga.sp.model;

import com.badlogic.gdx.Gdx;

public class DisparoAmi extends Disparo{
    public DisparoAmi(float x, float y, float width, float height) {
        super(x, y, width, height);
    }
    @Override
    public boolean desaparecer() {
        if(getY()> Gdx.graphics.getHeight() ) return true;
        return false;
    }
    @Override
    public boolean morir() {
        return false;
    }
}



