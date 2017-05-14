package com.example.a1.exampleadapter;

import java.util.ArrayList;

import ru.yandex.speechkit.Error;
import ru.yandex.speechkit.Synthesis;
import ru.yandex.speechkit.Vocalizer;
import ru.yandex.speechkit.VocalizerListener;

public class ThreadPlayVoice implements Runnable ,VocalizerListener {
    Vocalizer vocalizer;
    static final String API_KEY_YANDEX = "670655db-edd8-4ee5-b3b7-e9d47ec78ed8";
    ArrayList<Product> ap = new ArrayList<Product>();

    ThreadPlayVoice(ArrayList<Product> ap) {
        this.ap = ap;
    }

    @Override
    public void onSynthesisBegin(Vocalizer vocalizer) {

    }

    @Override
    public void onSynthesisDone(Vocalizer vocalizer, Synthesis synthesis) {

    }

    @Override
    public void onPlayingBegin(Vocalizer vocalizer) {

    }

    @Override
    public void onPlayingDone(Vocalizer vocalizer) {

    }

    @Override
    public void onVocalizerError(Vocalizer vocalizer, Error error) {

    }

    public void playvoice(String s1) {
        resetVocalizer();
        vocalizer = Vocalizer.createVocalizer(Vocalizer.Language.RUSSIAN, s1, true, Vocalizer.Voice.ERMIL);
        vocalizer.setListener(this);
        vocalizer.start();
    }

    private void resetVocalizer() {
        if (vocalizer != null) {
            vocalizer.cancel();
            vocalizer = null;
        }
    }

    @Override
    public void run() {
//        int i = 0;
//        while (b) {
//            if (i != ap.size()) playvoice(ap.get(i).name);//
//            else b = false;
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
        for (int i = 0; i < ap.size(); i++) {
            playvoice(ap.get(i).name);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            playvoice(Integer.toString(i));
        }
    }
}
