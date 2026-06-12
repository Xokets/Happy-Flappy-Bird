package ru.innovationcampus.vsu26.xokets.happy_flappy_bird.components;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;

public class PointCounter implements Disposable {
    private float x;
    private float y;
    private BitmapFont font;

    public PointCounter(float x, float y) {
        this.x = x;
        this.y = y;
        font = new BitmapFont();
        font.getData().setScale(5f);
        font.setColor(Color.WHITE);
    }
    public void draw(SpriteBatch batch, int point) {
        font.draw(batch, String.format("Points: %s", point), x, y);
    }

    public void dispose() {
        font.dispose();
    }

}
