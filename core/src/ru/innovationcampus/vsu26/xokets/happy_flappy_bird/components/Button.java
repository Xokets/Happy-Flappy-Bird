package ru.innovationcampus.vsu26.xokets.happy_flappy_bird.components;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Button {
    private Texture texture;
    private float x, y;
    private float width, height;

    public Button(float x, float y, String texturePath) {
        this.x = x;
        this.y = y;

        texture = new Texture(texturePath);
        width = texture.getWidth();
        height = texture.getHeight();
    }

    public boolean isTouch(int tx, int ty) {
        return tx >= x && tx <= x + width && ty >= y && ty <= y + height;
    }

    public void draw(SpriteBatch batch) {
        batch.draw(texture, x, y, width, height);
    }

    public void dispose() {
        texture.dispose();
    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }
}
