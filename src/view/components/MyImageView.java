package view.components;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class MyImageView extends ImageView {
    public MyImageView(String url, double side) {
        this.setImage(new Image(url));
        this.setFitHeight(side);
        this.setFitWidth(side);
    }
}
