package mspaint.view;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.ImagePattern;
import javafx.stage.FileChooser;
import mspaint.model.ColorTrack;

import javax.imageio.ImageIO;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;

public class PaintPresenter {
    PaintView view;
    ColorTrack model;

    public PaintPresenter(PaintView view) {
        this.view = view;
        this.model = new ColorTrack();
        updateView();
        addEventHandlers();
    }

    private void updateView() {
        if(view.getEraser().isSelected()) model.setCurrentColor(ColorTrack.ColorPicker.WHITE);
        else model.setCurrentColor(view.getColors().getValue());
        model.setBrushSize(view.getBrushField().getValue());
        view.getCurrentBrushSize().setText(String.format("%.2f",view.getBrushField().getValue()));
    }

    private void addEventHandlers() {
        view.getDrawingCanvas().setOnMouseDragged(e -> {
            double x = e.getX() - model.getBrushSize() / 2;
            double y = e.getY() - model.getBrushSize() / 2;
//            Paint color = new Color(model.getCurrentColor())
                view.getDrawingCanvas().getGraphicsContext2D().setFill(model.getCurrentColor().getColor());
                view.getDrawingCanvas().getGraphicsContext2D().fillOval(x , y , model.getBrushSize(), model.getBrushSize());
            });
//        view.getColorPicker().setOnAction(actionEvent -> updateView());
        view.getBrushField().setOnMouseDragged(event -> updateView());
        view.getBrushField().setOnMouseClicked(event -> updateView());
        view.getEraser().setOnAction(actionEvent -> updateView());
        view.getClearButton().setOnAction(actionEvent -> {
            view.getDrawingCanvas().getGraphicsContext2D().clearRect(0, 0, view.getHEIGHT(), view.getWIDTH());
        });

        view.getColors().setOnAction(actionEvent -> updateView());

        view.getMenu().getItems().get(0).setOnAction(saveImageAs());
        view.getMenu().getItems().get(1).setOnAction(loadImage());
    }

    private EventHandler<ActionEvent> loadImage() {
        return actionEvent -> {
            FileChooser FC = new FileChooser();
            FC.setTitle("Choose Image");
            FC.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("PNG files", "*.png"));
            File file = FC.showOpenDialog(view.getScene().getWindow());
            if (file != null) {
                view.getDrawingCanvas().getGraphicsContext2D().clearRect(0, 0, view.getWIDTH(), view.getHEIGHT());
                Image img = new Image(file.toURI().toString());
                view.getDrawingCanvas().getGraphicsContext2D().drawImage(img, 0, 0, view.getHEIGHT(), view.getWIDTH());
            }
        };
    }

    private EventHandler<ActionEvent> saveImageAs(){
        return actionEvent -> {
            FileChooser stegoImageSaver = new FileChooser();
            stegoImageSaver.setTitle("Save Image File");
            stegoImageSaver.setInitialDirectory(new File("/home/seif/Desktop"));
            stegoImageSaver.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("PNG Files", "*.png"));
//                    new FileChooser.ExtensionFilter("BMP Files", "*.bmp"),
//                    new FileChooser.ExtensionFilter("GIF Files", "*.gif"));
            File f1 = stegoImageSaver.showSaveDialog(view.getScene().getWindow());
            if (f1 != null) {
                String name = f1.getName();
                String extension = name.substring(1+name.lastIndexOf(".")).toLowerCase();
                try {
                    WritableImage writableImage = new WritableImage((int) view.getHEIGHT(), (int) view.getWIDTH());
                    view.getDrawingCanvas().snapshot(null,writableImage);
                    RenderedImage renderedImage = SwingFXUtils.fromFXImage(writableImage,null);
                    ImageIO.write(renderedImage, extension, f1);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        };
        };




}

