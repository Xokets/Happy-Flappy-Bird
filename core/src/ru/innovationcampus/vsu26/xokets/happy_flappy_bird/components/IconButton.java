package ru.innovationcampus.vsu26.xokets.happy_flappy_bird.components;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class IconButton extends Button {
    private Texture icon;
    private Texture notSelectedTexture;
    private Texture selectedTexture;
    private Texture closedMark;
    private float iconX, iconY;
    private float iconWidth, iconHeight;
    private boolean state;
    private Boolean isOpen;

    public IconButton(float x, float y, String notSelectedStateTexturePath, String selectedStateTexturePath, String iconPath) {
        super(x, y, notSelectedStateTexturePath);
        notSelectedTexture = new Texture(notSelectedStateTexturePath);
        selectedTexture = new Texture(selectedStateTexturePath);
        iconWidth = getWidth() / 2;
        iconHeight = getHeight() / 2;
        icon = new Texture(iconPath);
        iconX = x + (getWidth() - iconWidth) / 2;
        iconY = y + (getHeight() - iconHeight) / 2;
        isOpen = false;
        closedMark = new Texture("Buttons/Closed_Mark.png");
    }

    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch);
        batch.draw(icon, iconX, iconY, iconWidth, iconHeight);
        if (!isOpen) {
            batch.draw(closedMark, getX(), getY(), getWidth(), getHeight());
        }
    }

    public Texture getIcon() {
        return icon;
    }

    @Override
    public void setX(float x) {
        super.setX(x);
        iconX = x + (getWidth() - iconWidth) / 2;
    }

    @Override
    public void setY(float y) {
        super.setY(y);
        iconY = y + (getHeight() - iconHeight) / 2;
    }

    @Override
    public void dispose() {
        super.dispose();
        icon.dispose();
        notSelectedTexture.dispose();
        selectedTexture.dispose();
    }

    public void switchState() {
        state = !state;
        if (state) {
            setTexture(selectedTexture);
            return;
        }
        setTexture(notSelectedTexture);
    }

    public boolean getState() {
        return state;
    }

    public void setOpen(Boolean condition) {
        isOpen = condition;
    }


    public boolean isOpen() {
        return isOpen;
    }
}
