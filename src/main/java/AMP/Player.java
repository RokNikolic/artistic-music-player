package AMP;

import processing.core.*;
import ddf.minim.*;
import ddf.minim.analysis.*;

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
        // background(150); // comment za task 5
        float amount_played = (float) player.position() / player.length();

        float loudness = player.mix.level();
        float size = loudness * 100;
        fill(204, 204, 255);
        // Horizontal
        //ellipse(amount_played * width, 500, size, size);
        float screen_width = (amount_played * width * 10) % width;
        float screen_height = (((amount_played * width * 10) / width) + 1) * 70 - 35;
        // Whole screen
        ellipse(screen_width, screen_height, size, size);

        float[] array = player.mix.toArray();
        int len_of_array = array.length;
        float column_width = (float) width / len_of_array;
        for (int i = 0; i < len_of_array; i++) {
            rect(column_width * i, 0, column_width, array[i] * height);
        }

        FFT fft = new FFT(len_of_array, player.sampleRate());
        fft.forward(array);
        float size2 = fft.specSize();
        float column_width2 = (float) width / size2;
        for (int i = 0; i < size2; i++) {
            float value = fft.getBand(i);
           rect(column_width2 * i, 0, column_width, value);
        }

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

