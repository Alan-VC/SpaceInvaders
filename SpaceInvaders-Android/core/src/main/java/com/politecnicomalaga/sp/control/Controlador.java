package com.politecnicomalaga.sp.control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.politecnicomalaga.sp.model.NaveAmi;
import com.politecnicomalaga.sp.model.Ovni;

import java.util.Map;

public class Controlador {
    private static Controlador miSingle;
    private NaveAmi naveAmiga;
    public float velocidadNave;

    //CONSTRUCTOR
    private Controlador() {
        naveAmiga = new NaveAmi(300,300,80,70, Ovni.Estado.VIVO, Ovni.Direccion.NOMOVER,"naveJugador.png",1,4,5,7);
        velocidadNave = 1.5f;
    }

    //Otros métodos
    public static Controlador getInstance(){
        if (miSingle == null){
            miSingle= new Controlador();
        }
            return miSingle;
    }
    public void click (float x, float y){
        cambiarSentidoNaveAmiga(x);
    }
    public void simulaMundo(float anchoPantalla, float altoPantalla){
        if (naveAmiga.getX()>anchoPantalla-naveAmiga.getWidth()){
            naveAmiga.setX(anchoPantalla-naveAmiga.getWidth());
            naveAmiga.setDir(Ovni.Direccion.NOMOVER);
        }
        if (naveAmiga.getX()<0){
            naveAmiga.setX(0);
            naveAmiga.setDir(Ovni.Direccion.NOMOVER);
        }
        naveAmiga.mover(naveAmiga.getDir(),velocidadNave);
    }

    public void pintar(SpriteBatch batch, Map<String, Texture> galeriaImagenes){

    }

    public void cambiarSentidoNaveAmiga (float x){
        if (x>naveAmiga.getX() && naveAmiga.getDir()!= Ovni.Direccion.DERECHA){
            naveAmiga.setDir(Ovni.Direccion.DERECHA);
        } else if (x>naveAmiga.getX() && naveAmiga.getDir()== Ovni.Direccion.DERECHA) {
           naveAmiga.setDir(Ovni.Direccion.NOMOVER);
        } else if (x<naveAmiga.getX() && naveAmiga.getDir()!= Ovni.Direccion.IZQUIERDA){
            naveAmiga.setDir(Ovni.Direccion.IZQUIERDA);
        } else if (x<naveAmiga.getX() && naveAmiga.getDir() == Ovni.Direccion.IZQUIERDA){
            naveAmiga.setDir(Ovni.Direccion.NOMOVER);
        }
    }



}
