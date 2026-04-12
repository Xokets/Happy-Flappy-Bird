package ru.innovationcampus.vsu26.xokets.happy_flappy_bird.components;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

import ru.innovationcampus.vsu26.xokets.happy_flappy_bird.GameElement;

public abstract class Button implements GameElement {
    public static final String BUTTON_TEXTURE_PATH = "Buttons/";
    private Texture texture;
    private float x, y;
    private float width, height;

    public Button(float x, float y, String textureName) {
        this.x = x;
        this.y = y;

        texture = new Texture(BUTTON_TEXTURE_PATH + textureName);
        width = texture.getWidth();
        height = texture.getHeight();
    }

    public boolean isTouch(int tx, int ty) {
        return tx >= x && tx <= x + width && ty >= y && ty <= y + height;
    }
    @Override
    public void draw(Batch batch) {
        batch.draw(texture, x, y, width, height);
    }
    @Override
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
