package ru.mipt.bit.platformer.gameEntity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Rectangle;

import static ru.mipt.bit.platformer.util.GdxGameUtils.createBoundingRectangle;

public abstract class GameEntity {

    private final Texture texture;
    private final TextureRegion graphics;
    protected Rectangle rectangle;
    protected GridPoint2 destinationCoordinates;
    protected GridPoint2 currentCoordinates;
    float rotation = 0f;
    public GameEntity(
            String filePathTexture,
            GridPoint2 startCoordinates
    ) {
        texture = new Texture(filePathTexture);
        graphics = new TextureRegion(texture);
        rectangle = createBoundingRectangle(graphics);
        destinationCoordinates = startCoordinates;
        currentCoordinates = new GridPoint2(startCoordinates);
    }

    public void setDestinationCoordinates(GridPoint2 destinationCoordinates) {
        this.destinationCoordinates.set(destinationCoordinates);
    }

    public void setRotation(float rotation) {
        this.rotation = rotation;
    }

    public float getRotation() {
        return rotation;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public GridPoint2 getDestinationCoordinates() {
        return destinationCoordinates;
    }
    public void setCurrentCoordinates(GridPoint2 currentCoordinates) {
        this.currentCoordinates.set(currentCoordinates);
    }
    public GridPoint2 getCurrentCoordinates() {
        return currentCoordinates;
    }

    public TextureRegion getGraphics() {
        return graphics;
    }

    public void setRectangle(Rectangle rectangle) {
        this.rectangle.set(rectangle);
    }


    public void textureDispose(){
        texture.dispose();
    }
}
