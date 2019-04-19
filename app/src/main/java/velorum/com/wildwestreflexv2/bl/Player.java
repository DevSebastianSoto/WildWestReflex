package velorum.com.wildwestreflexv2.bl;

import android.widget.ImageView;

public class Player{
    private ImageView character;
    private String name;
    private float currentTime;
    private int score;
    private Position pos;

    public Player(String name, Position pos) {
        this.name = name;
        this.pos = pos;
        this.currentTime = 0;
        this.score = 0;
        this.character = null;
    }

    public String getName() {
        return name;
    }

    public ImageView getCharacter() {
        return character;
    }

    public void setCharacter(ImageView character) {
        this.character = character;
    }

    public float getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(float currentTime) {
        this.currentTime = currentTime;
    }

    public void addScore(int score) {
        this.score += score;
    }

    public int getScore() {
        return score;
    }

    public void die(){
        int rotation = 180;
        rotation *= (this.pos == Position.LEFT)?-1:1;
        character.animate().rotation(rotation).setDuration(500);
    }
}
