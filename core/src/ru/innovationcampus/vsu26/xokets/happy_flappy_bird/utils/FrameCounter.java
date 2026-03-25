package ru.innovationcampus.vsu26.xokets.happy_flappy_bird.utils;

public class FrameCounter {
    private int frame;
    private int maxFrame;
    private int multiplier;
    private int counter;

    public FrameCounter(int value, int maxValue, int multiplier) {
        this.frame = value;
        this.maxFrame = maxValue;
        if (multiplier > 0) {
            this.multiplier = multiplier;
        } else {
            this.multiplier = 1;
        }
        counter = multiplier;
    }

    public FrameCounter(int maxValue) {
        this(0, maxValue, 1);
    }

    public FrameCounter(int maxValue, int multiplier) {
        this(0, maxValue, multiplier);
    }

    public void nextFrame() {
        if (!useCounter()) return;
        if (frame == maxFrame) {
            frame = 0;
            return;
        }
        frame++;
    }

    public void previousFrame() {
        if (!useCounter()) return;
        if (frame == 0) {
            frame = maxFrame;
            return;
        }
        frame--;
    }

    public int getFrame() {
        return frame;
    }

    public int getMaxFrame() {
        return maxFrame;
    }

    public void setMaxFrame(int maxFrame) {
        this.maxFrame = maxFrame;
    }

    public int getMultiplier() {
        return multiplier;
    }

    public void setMultiplier(int multiplier) {
        this.multiplier = multiplier;
    }

    private boolean useCounter() {
        if (counter == 0) {
            counter = multiplier;
            return true;
        } else {
            counter--;
            return false;
        }
    }
}
