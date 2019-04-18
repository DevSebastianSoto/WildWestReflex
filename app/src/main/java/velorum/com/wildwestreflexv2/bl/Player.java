package velorum.com.wildwestreflexv2.bl;

import android.widget.ImageView;

public class Player{
    ImageView character;
    String name;
    float currentTime;
    int score;
    Position pos;

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

    public void setCurrentTime(float currentTime) {
        this.currentTime = currentTime;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void die(){
        int rotation = 180;
        rotation *= (this.pos == Position.LEFT)?-1:1;
        character.animate().rotation(rotation).setDuration(500);
    }
}
