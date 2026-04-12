package ru.innovationcampus.vsu26.xokets.happy_flappy_bird.components;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;

public class TextButton extends Button {
    private BitmapFont font;

    private String text;
    private float textX, textY;
    private float textWidth, textHeight;

    public TextButton(float x, float y, String text) {
        super(x, y, "Text_Button_Texture.png");
        this.text = text;

        font = new BitmapFont();
        font.getData().setScale(5f);
        font.setColor(Color.WHITE);

        GlyphLayout gl = new GlyphLayout(font, text);
        textWidth = gl.width;
        textHeight = gl.height;

        textX = x + (getWidth() - textWidth) / 2;
        textY = y + (getHeight() + textHeight) / 2;
    }
    public TextButton(String text) {
        this(0, 0, text);
    }
    @Override
    public void draw(Batch batch) {
        super.draw(batch);
        font.draw(batch, text, textX, textY);
    }
    @Override
    public void dispose() {
        super.dispose();
        font.dispose();
    }
    public String getText() {
        return text;
    }
}