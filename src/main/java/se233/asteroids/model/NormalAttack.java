package se233.asteroids.model;

import javafx.geometry.Bounds;
import javafx.scene.shape.Rectangle;

import java.util.List;

public class NormalAttack extends Projectile {
    private static final String FIREBALL_SPRITE = "/se233/asteroids/assets/projectile/Fireball.png";
    private final double MAX_LIFE_TIME = 0.75;
    private double timeAlive;
    private boolean isMarkForRemove;

    public Rectangle outline;

    public NormalAttack(double x, double y, double rotation, double initialSpeed, double maxSpeed, double acceleration, double friction, double width, double height) {
        super(FIREBALL_SPRITE, 5, 5, 1, 64, 32, 40, 20, x, y, rotation, initialSpeed, maxSpeed, acceleration, friction, width, height);
        this.timeAlive = 0;
        this.isMarkForRemove = false;
    }

    public void markForRemoval() {
        this.isMarkForRemove = true;
    }

    public boolean isMarkForRemove() {
        return isMarkForRemove;
    }

    public boolean checkCharacterCollision(Character character) {
        Bounds projectileBounds = this.outline.getBoundsInParent();
        Bounds characterBounds = character.getImageView().getBoundsInParent();

        return projectileBounds.intersects(characterBounds);
    }

    public boolean checkCollision(List<? extends Character> characters) {
       for (Character character : characters) {
           if (checkCharacterCollision(character)) {
               character.collided();
               return true;
           }
       }

       return false;
    }

    public void update() {
        super.update();
        timeAlive += 0.016;

        if (timeAlive >= MAX_LIFE_TIME) {
            markForRemoval();
        }
    }
}
