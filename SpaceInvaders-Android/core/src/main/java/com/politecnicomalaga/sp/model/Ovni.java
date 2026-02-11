package com.politecnicomalaga.sp.model;

public class Ovni {
    private float x,y;
    private int width,height;

    //getters y setters


    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public boolean colision (Ovni otraNave){
        if (colisionW(this.x,this.width,otraNave.getX(),otraNave.getWidth()) && colisionH(this.y,this.height,otraNave.getY(),otraNave.getHeight())){
            return true;
        }
        return false;
    }

    private boolean colisionW(float x1,int w1, float x2,int w2){
        if (x1 >x2+w2) return false;
        if (x1+w1<x2) return false;
        return true;
    }

    private boolean colisionH(float y1,int h1,float y2,int h2){
        if (y1<y2-h2) return false;
        if (y1-h1>y2) return false;
        return true;
    }
}
