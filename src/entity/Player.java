package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player extends Entity{
    GamePanel gp;
    KeyHandler keyHandler;

    private int x = getX();
    private int y = getY();
    private int speed = getSpeed();

    public Player(GamePanel gp, KeyHandler keyHandler){
        this.gp = gp;
        this.keyHandler = keyHandler;
        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        x = 100;
        y = 100;
        speed = 4;
        direction = "down";
    }

    public void getPlayerImage(){
        try {
            up1 = ImageIO.read((Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_up_1.png"))));
            up2 = ImageIO.read((Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_up_2.png"))));
            down1 = ImageIO.read((Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_down_1.png"))));
            down2 = ImageIO.read((Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_down_2.png"))));
            left1 = ImageIO.read((Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_left_1.png"))));
            left2 = ImageIO.read((Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_left_2.png"))));
            right1 = ImageIO.read((Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_right_1.png"))));
            right2 = ImageIO.read((Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_right_2.png"))));
        } catch (IOException e) {
            throw new RuntimeException("could not load player images", e);
        }
    }

    public void update(){
        if (keyHandler.upPressed) {
            direction = "up";
            y -= speed;
        } else if (keyHandler.downPressed) {
            direction = "down";
            y += speed;
        } else if (keyHandler.leftPressed) {
            direction = "left";
            x -= speed;
        } else if (keyHandler.rightPressed) {
            direction = "right";
            x += speed;
        }
    }

    public void draw(Graphics2D g2){
//        g2.setColor(Color.white);
//        g2.fillRect(x, y, gp.getTileSize(), gp.getTileSize());
        BufferedImage image = switch (direction) {
            case "up" -> up1;
            case "down" -> down1;
            case "left" -> left1;
            case "right" -> right1;
            default -> null;
        };
        g2.drawImage(image, x, y, gp.getTileSize(), gp.getTileSize(), null);
    }
}
