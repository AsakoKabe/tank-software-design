package ru.platformer.game.graphics.actions.toggle;

import ru.platformer.game.Action;
import ru.platformer.game.graphics.Toggle;

public class ToggleAction implements Action {
    private final Toggle toggle;

    public ToggleAction(Toggle toggle) {
        this.toggle = toggle;
    }

    @Override
    public void apply() {
        toggle.switchToggle();
    }
}
