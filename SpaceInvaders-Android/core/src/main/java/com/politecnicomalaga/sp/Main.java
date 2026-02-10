package com.politecnicomalaga.sp;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.HashMap;
import java.util.Map;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {
    private SpriteBatch batch;
    private Texture image;
    Map<String,Texture> galeriaImagenes;
    @Override
    public void create() {
        batch = new SpriteBatch();
        galeriaImagenes = new HashMap<>();



        image = new Texture("enemigo1.png");
        galeriaImagenes.put("enemigo1.png",image);
        image = new Texture("enemigo2.png");
        galeriaImagenes.put("enemigo2.png",image);
        image = new Texture("enemigo3.png");
        galeriaImagenes.put("enemigo3.png",image);
        image = new Texture("enemigo4.png");
        galeriaImagenes.put("enemigo4.png",image);
        image = new Texture("heroe.png");
        galeriaImagenes.put("heroe.png",image);
        image = new Texture("disparoA.png");
        galeriaImagenes.put("disparoA.png",image);
        image = new Texture("disparoE.png");
        galeriaImagenes.put("disparoE.png",image);






    }

    @Override
    public void render() {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);



        //Control de entrada
        if(gdx.input.justTouched()){
                Controlador.getInstance().click();
        }
        //Control de estado
        Controlador.getInstance().simulaMundo();
        //Pintar el mundo
        batch.begin();
        Controlador.getInstance().pintar(batch, galeriaImagenes);
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        for (Texture imagen : galeriaImagenes.values()) {
            imagen.dispose();
        }
    }
}
