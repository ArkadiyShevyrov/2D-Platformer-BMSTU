package ui;

import gamestates.GameState;
import utilz.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;

import static utilz.Constants.TextureConstants.Menu.*;
import static utilz.Constants.UI.Button.*;
import static utilz.Constants.UI.MenuButtons.*;

public class MenuButton extends Button{

    private final GameState state;
    private BufferedImage[] images;

    public MenuButton(int x, int y, int typeButton, GameState state) {
        super(x - BUTTON_WIDTH / 2, y, BUTTON_WIDTH, BUTTON_HEIGHT);
        this.typeButton = typeButton;
        this.state = state;
        loadImages();
    }

    protected void loadImages() {
        images = new BufferedImage[3];
        BufferedImage temp = LoadSave.GetSpriteAtlas(MENU_BUTTONS_PNG);
        for (int i = 0; i < images.length; i++) {
            images[i] = temp.getSubimage(
                    i * BUTTON_WIDTH_DEFAULT, typeButton * BUTTON_HEIGHT_DEFAULT,
                    BUTTON_WIDTH_DEFAULT, BUTTON_HEIGHT_DEFAULT);
        }
    }

    public void update() {
        stateButton = ON;
        if (mouseOver) {
            stateButton = OVER;
        }
        if (mousePressed) {
            stateButton = PRESSED;
        }
    }

    public void draw(Graphics g) {
        g.drawImage(images[stateButton], x, y,
                BUTTON_WIDTH, BUTTON_HEIGHT, null);
    }

    public void applyGameState() {
        GameState.state = state;
    }

}
