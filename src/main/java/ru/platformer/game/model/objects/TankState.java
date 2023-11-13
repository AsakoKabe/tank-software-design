package ru.platformer.game.model.objects;

public interface TankState {
    void continueMovement(float deltaTime);
    void shoot();

}
