package velorum.com.wildwestreflexv2.bl;

import java.util.ArrayList;

public class Game {
    private Player p1;
    private Player p2;
    private int rounds;
    private ArrayList<Round> roundList;

    public Game(Player p1, Player p2, int rounds) {
        this.p1 = p1;
        this.p2 = p2;
        this.rounds = rounds;
        this.roundList = new ArrayList<>();
    }

    public Player getP1() {
        return p1;
    }

    public void setP1(Player p1) {
        this.p1 = p1;
    }

    public Player getP2() {
        return p2;
    }

    public void setP2(Player p2) {
        this.p2 = p2;
    }

    public int getRounds() {
        return rounds;
    }

    public void setRounds(int rounds) {
        this.rounds = rounds;
    }

    public ArrayList<Round> getRoundList() {
        return roundList;
    }

    public void setRoundList(ArrayList<Round> roundList) {
        this.roundList = roundList;
    }
}
