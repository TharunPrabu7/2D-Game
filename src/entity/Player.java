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

    private final int screenX;
    private final int screenY;

    private int worldX;
    private int worldY;
    private int speed;
    private int spriteCounter;
    private int spriteNum;

    public Player(GamePanel gp, KeyHandler keyHandler){
        this.gp = gp;
        this.keyHandler = keyHandler;

        screenX = gp.getScreenWidth() / 2 - (gp.getTileSize() / 2);
        screenY = gp.getScreenHeight() / 2 - (gp.getTileSize() / 2);

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        worldX = gp.getTileSize() * 23;
        worldY = gp.getTileSize() * 21;
        speed = 4;
        direction = "down";
        spriteNum = 1;
        spriteCounter = 0;
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
        if (keyHandler.upPressed || keyHandler.downPressed ||
                keyHandler.leftPressed || keyHandler.rightPressed
        ) {

            if (keyHandler.upPressed) {
                direction = "up";
                worldY -= speed;
            } else if (keyHandler.downPressed) {
                direction = "down";
                worldY += speed;
            } else if (keyHandler.leftPressed) {
                direction = "left";
                worldX -= speed;
            } else if (keyHandler.rightPressed) {
                direction = "right";
                worldX += speed;
            }
            spriteCounter++;
            if (spriteCounter > 12) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
    }

    public void draw(Graphics2D g2){

        BufferedImage image = down1;     // stand‑still fallback

        switch (direction) {
            case "up"    -> image = (spriteNum == 1 ? up1   : up2);
            case "down"  -> image = (spriteNum == 1 ? down1 : down2);
            case "left"  -> image = (spriteNum == 1 ? left1 : left2);
            case "right" -> image = (spriteNum == 1 ? right1: right2);
        }
        g2.drawImage(image, screenX, screenY, gp.getTileSize(), gp.getTileSize(), null);


        g2.drawImage(image, screenX, screenY, gp.getTileSize(), gp.getTileSize(), null);
    }

    public GamePanel getGp() {
        return gp;
    }

    public void setGp(GamePanel gp) {
        this.gp = gp;
    }

    public KeyHandler getKeyHandler() {
        return keyHandler;
    }

    public void setKeyHandler(KeyHandler keyHandler) {
        this.keyHandler = keyHandler;
    }

    public int getScreenX() {
        return screenX;
    }

    public int getScreenY() {
        return screenY;
    }

    @Override
    public int getWorldX() {
        return worldX;
    }

    @Override
    public void setWorldX(int worldX) {
        this.worldX = worldX;
    }

    @Override
    public int getWorldY() {
        return worldY;
    }

    @Override
    public void setWorldY(int worldY) {
        this.worldY = worldY;
    }

    @Override
    public int getSpeed() {
        return speed;
    }

    @Override
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    @Override
    public int getSpriteCounter() {
        return spriteCounter;
    }

    @Override
    public void setSpriteCounter(int spriteCounter) {
        this.spriteCounter = spriteCounter;
    }

    @Override
    public int getSpriteNum() {
        return spriteNum;
    }

    @Override
    public void setSpriteNum(int spriteNum) {
        this.spriteNum = spriteNum;
    }
}
