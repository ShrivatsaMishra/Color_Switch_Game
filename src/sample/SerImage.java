package sample;

import javafx.scene.image.Image;

import java.io.Serializable;

public class SerImage extends Image implements Serializable {
    public SerImage(String url) {
        super(url);
    }
}
