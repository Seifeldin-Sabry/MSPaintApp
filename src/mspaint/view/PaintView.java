package mspaint.view;

import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import mspaint.model.ColorTrack;

import java.util.Arrays;

import static mspaint.model.ColorTrack.ColorPicker.*;

public class PaintView extends BorderPane {
    private MenuBar menuBar;
    private Menu menu;
    private MenuItem saveOption;
    private MenuItem loadOption;
    private ToolBar toolBar;
    private VBox brushContainer;
    private Text currentBrushSize;
    private Slider brushField;
    private CheckBox eraser;
    private ComboBox<ColorTrack.ColorPicker> colors;
    private Button clearButton;
    private Canvas drawingCanvas;
//    private ColorPicker colorPicker;
    private double HEIGHT;
    private double WIDTH;

    public PaintView() {
        initialiseNodes();
        layoutNodes();
    }

    private void initialiseNodes() {
        menuBar = new MenuBar();
        menu = new Menu("File");
        saveOption = new MenuItem("Save");
        loadOption = new MenuItem("Load");
        menuBar.getMenus().add(menu);
        menu.getItems().addAll(saveOption,loadOption);
        toolBar = new ToolBar();
        currentBrushSize = new Text();
        currentBrushSize.setTextAlignment(TextAlignment.CENTER);
        brushField = new Slider();
        brushField.setOrientation(Orientation.HORIZONTAL);
        brushField.setMin(5);
        brushField.setValue(5);
        brushField.setMax(100);
        brushField.setPrefWidth(200);
        brushField.setShowTickLabels(true);
        brushField.setShowTickMarks(true);
        eraser = new CheckBox("Eraser");
        clearButton = new Button("Clear Canvas");
        colors = new ComboBox<>();
        colors.getItems().addAll(Arrays.asList(values()));
        colors.getItems().remove(WHITE);
        colors.getSelectionModel().select(BLACK);
        brushContainer = new VBox(currentBrushSize,brushField);
        brushContainer.setSpacing(5);

//        colorPicker = new ColorPicker(Color.BLACK);
//        toolBar.getItems().addAll(brushField,colorPicker,eraser,clearButton,colors);
        toolBar.getItems().addAll(brushContainer,eraser,colors,clearButton);

        HEIGHT = 1280;
        WIDTH = 720;
        drawingCanvas = new Canvas(HEIGHT, WIDTH);

    }

    private void layoutNodes() {
        setTop(menuBar);
        setCenter(drawingCanvas);
        setAlignment(drawingCanvas,Pos.CENTER);
        setBottom(toolBar);
        setAlignment(toolBar, Pos.CENTER);
    }

     Canvas getDrawingCanvas() {
        return drawingCanvas;
    }

//     ColorPicker getColorPicker() {
//        return colorPicker;
//    }

     Slider getBrushField() {
        return brushField;
    }

     CheckBox getEraser() {
        return eraser;
    }

     Button getClearButton() {
        return clearButton;
    }

     double getHEIGHT() {
        return HEIGHT;
    }

     double getWIDTH() {
        return WIDTH;
    }

     ComboBox<ColorTrack.ColorPicker> getColors() {
        return colors;
    }

    public Menu getMenu() {
        return menu;
    }

    Text getCurrentBrushSize() {
        return currentBrushSize;
    }
}
