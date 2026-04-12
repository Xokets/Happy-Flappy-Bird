package ru.innovationcampus.vsu26.xokets.happy_flappy_bird;

import com.badlogic.gdx.graphics.g2d.Batch;

public interface GameElement {
    void draw(Batch batch);
    void dispose();
}
