package com.politecnicomalaga.sp.control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.politecnicomalaga.sp.model.Batallon;
import com.politecnicomalaga.sp.model.DisparoAmi;
import com.politecnicomalaga.sp.model.DisparoEne;
import com.politecnicomalaga.sp.model.Escuadron;
import com.politecnicomalaga.sp.model.NaveAmi;
import com.politecnicomalaga.sp.model.NaveEne;
import com.politecnicomalaga.sp.model.Ovni;

import java.util.List;
import java.util.Map;

public class Controlador {
    private static Controlador miSingle;
    private NaveAmi naveAmiga;
    private Batallon batallon;
    public float velocidadNave;

    //CONSTRUCTOR
    private Controlador() {
        naveAmiga = new NaveAmi(300,0,80,70, Ovni.Estado.VIVO, Ovni.Direccion.NOMOVER,"naveJugador.png",1,4,20,45,10);
        batallon = new Batallon();
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
        //pintar naveAmiga
        batch.draw(galeriaImagenes.get(naveAmiga.getTextura()),naveAmiga.getX(),naveAmiga.getY(),naveAmiga.getWidth(),naveAmiga.getHeight());

        //pintar navesEnemigas y sus disparos
        Escuadron[] escuadrones = batallon.getEscuadrones();
        for (Escuadron esc: escuadrones){
            NaveEne[] naveEnes = esc.getNavesEnemigas();
            for (NaveEne navE: naveEnes){
                if (navE.estaVivo()) {
                    batch.draw(galeriaImagenes.get(navE.getTextura()), navE.getX(), navE.getY(), navE.getWidth(), navE.getHeight());
                    List<DisparoEne> disparosEnemigos = navE.getMisDisparos();
                    for (DisparoEne disEne: disparosEnemigos){
                        batch.draw(galeriaImagenes.get(disEne.getTextura()),disEne.getX(),disEne.getY(),disEne.getWidth(),disEne.getHeight());
                    }
                }
            }
        }
        //Pintar disparosAmigos
        List<DisparoAmi> disparosAmigos = naveAmiga.getMisDisparos();
        for (DisparoAmi dispAmi: disparosAmigos){
            batch.draw(galeriaImagenes.get(dispAmi.getTextura()),dispAmi.getX(),dispAmi.getY(),dispAmi.getWidth(),dispAmi.getHeight());
        }

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
