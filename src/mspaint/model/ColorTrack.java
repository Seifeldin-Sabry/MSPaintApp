package mspaint.model;

import javafx.scene.paint.Color;

public class ColorTrack {
    private double brushSize;
    private ColorPicker currentColor;

    public ColorTrack() {
        brushSize = 5;
        currentColor = ColorPicker.BLACK;
    }

    public void setBrushSize(double brushSize) {
        this.brushSize = brushSize;
    }

    public double getBrushSize() {
        return brushSize;
    }

    public ColorPicker getCurrentColor() {
        return currentColor;
    }

    public void setCurrentColor(ColorPicker currentColor) {
        this.currentColor = currentColor;
    }


    public enum ColorPicker{
        RED(Color.RED),
        GREEN(Color.GREEN),
        BLUE(Color.BLUE),
        BLACK(Color.BLACK),
        YELLOW(Color.YELLOW),
        WHITE(Color.WHITE);

        private Color color;

        ColorPicker(Color color) {
            this.color = color;
        }

        public Color getColor() {
            return color;
        }
    }
}
