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
        velocidadNave = 1.5f;
        batallon=new Batallon();
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
        //disparo yo?
            
        //disparan los enemigos?

        //me han dado
        hanDadoNaveAmiga(batallon, naveAmiga);

        // he matado a alguien?
        List<DisparoAmi> disparoAmis = naveAmiga.getMisDisparos();
        hematado(batallon, disparoAmis);

        //me han tocado los aliens?
        meHanTocado(batallon, naveAmiga);

        //me muevo?
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
        batch.draw(galeriaImagenes.get(naveAmiga.getTextura()),naveAmiga.getX(),naveAmiga.getY(),naveAmiga.getWidth(),naveAmiga.getHeight());

        //en un futuro se implementa el pintar de batallón cuando esté programado.
        //batallon.pintar(batch);
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

    public void hanDadoNaveAmiga(Batallon batallon, NaveAmi naveAmiga){
        Escuadron[] escuadrones = batallon.getEscuadrones();
        for (Escuadron escuadron: escuadrones){
            NaveEne[] navesEnemigas = escuadron.getNavesEnemigas();
            for (NaveEne naveEne: navesEnemigas){
                if (naveEne.estaVivo()){
                    List<DisparoEne> disparoEnes = naveEne.getMisDisparos();
                    for (DisparoEne disparoEne: disparoEnes){
                        if (disparoEne.getY()<=naveAmiga.getY()+naveAmiga.getHeight()){
                            disparoEne.comprobarColision(naveAmiga);
                        }
                    }
                }
            }
        }
    }

    public  void hematado(Batallon batallon, List<DisparoAmi> disparoAmis){
        for (DisparoAmi disparoAmi: disparoAmis){
            if (disparoAmi.getY()+disparoAmi.getHeight()>=batallon.getY()){
                Escuadron[] escuadrones = batallon.getEscuadrones();
                for (Escuadron escuadron: escuadrones){
                    NaveEne[] navesEnemigas = escuadron.getNavesEnemigas();
                    disparoAmi.comprobarColision(navesEnemigas);
                }
            }
        }

    }

    public void meHanTocado(Batallon batallon, NaveAmi naveAmiga) {
        if (batallon.getY() <= naveAmiga.getY() + naveAmiga.getHeight()) {
            Escuadron[] escuadrones = batallon.getEscuadrones();
            for (Escuadron escuadron : escuadrones) {
                NaveEne[] navesEnemigas = escuadron.getNavesEnemigas();
                for (NaveEne naveEne : navesEnemigas) {
                    if (naveEne.estaVivo() && naveEne.colision(naveAmiga)) {
                        naveEne.setEstado(Ovni.Estado.MUERTO);
                        naveAmiga.setVidas(naveAmiga.getVidas() - 1);
                    }
                }
            }
        }


    }
}
