package com.github.roknikolic;

import processing.core.*;
import ddf.minim.*;

public class Player extends PApplet {
    Minim minim;
    AudioPlayer player;

    @Override
    public void settings() {
        size(1370, 745);
    }

    @Override
    public void setup() {
        smooth();

        minim = new Minim(this);
        player = minim.loadFile("src/main/media/Phlex - Take Me Home Tonight (feat. Caitlin Gare) [Argofox].mp3");
        player.play();
    }

    @Override
    public void draw() {
        float amount_played = (float) player.position() / player.length();

        float loudness = player.mix.level();
        float size = loudness * 100;
        float screen_width = (amount_played * width * 10) % width;
        float screen_height = (((amount_played * width * 10) / width) + 1) * 70 - 35;

        fill(204, 204, 255);
        ellipse(screen_width, screen_height, size, size);
    }

    @Override
    public void keyPressed() {
        super.keyPressed();
        if (player.isPlaying()) {
            player.pause();
        }
        else {
            player.play();
        }
    }

    @Override
    public void mouseClicked() {
        super.mouseClicked();
        float length_of_song = player.length();
        float margin = (float) mouseX/width;
        float position = length_of_song * margin;
        int position2 = (int) position;
        player.cue(position2);
    }
}

