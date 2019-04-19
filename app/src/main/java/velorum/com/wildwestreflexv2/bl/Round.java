package velorum.com.wildwestreflexv2.bl;

public class Round {

    private float startTime;
    private float t1;
    private float t2;

    public Round() {
        this.startTime = 0f;
        this.t1 = 0f;
        this.t2 = 0f;
    }

    public float getStartTime() {
        return startTime;
    }

    public void setStartTime(float startTime) {
        this.startTime = startTime;
    }

    public float getT1() {
        return t1;
    }

    public void setT1(float t1) {
        this.t1 = t1;
    }

    public float getT2() {
        return t2;
    }

    public void setT2(float t2) {
        this.t2 = t2;
    }

    public Player handleRoundWinner(Player p1, Player p2) {
        if (p1.getCurrentTime() > p2.getCurrentTime())
            return p1;
        else
            return p2;
    }
}
