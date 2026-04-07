package ru.innovationcampus.vsu26.xokets.happy_flappy_bird.components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TextButton {
    private BitmapFont font;

    private String text;
    private Texture texture;
    private float x, y;
    private float textX, textY;
    private float width, height;
    private float textWidth, textHeight;

    private static final String TEXTURE_PATH = "Buttons/";

    public TextButton(float x, float y, String text) {
        this.text = text;
        this.x = x;
        this.y = y;

        font = new BitmapFont();
        font.getData().setScale(5f);
        font.setColor(Color.WHITE);

        GlyphLayout gl = new GlyphLayout(font, text);
        textWidth = gl.width;
        textHeight = gl.height;

        texture = new Texture(TEXTURE_PATH + "Text_Button_Texture.png");
        width = texture.getWidth();
        height = texture.getHeight();

        textX = x + (width - textWidth) / 2;
        textY = y + (height + textHeight) / 2;
    }
    public TextButton(String text) {
        this(0, 0, text);
    }

    public boolean isTouch(int tx, int ty) {
        return tx >= x && tx <= x + width && ty >= y && ty <= y + height;
    }
    public void draw(SpriteBatch batch) {
        batch.draw(texture, x, y, width, height);
        font.draw(batch, text, textX, textY);
    }
    public void dispose() {
        texture.dispose();
        font.dispose();
    }
    public String getText() {
        return text;
    }
    public float getX() {
        return x;
    }
    public float getY() {
        return y;
    }
    public float getWidth() {
        return width;
    }
    public float getHeight() {
        return height;
    }
}