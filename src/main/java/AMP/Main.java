package AMP;

import processing.core.PApplet;

public class Main {
    public static void main (String... args) {
        Player pt = new Player();
        PApplet.runSketch(new String[]{"ArtPlayer"}, pt);
    }
}