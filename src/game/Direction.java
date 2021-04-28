package game;

public class Direction {

    private int dx, dy;

    public Direction(int dx, int dy){
        this.dx = dx;
        this.dy = dy;
    }

    public int getDx() {
        return dx;
    }

    public void setDx(int dx) {
        this.dx = dx;
    }

    public int getDy() {
        return dy;
    }

    public void setDy(int dy) {
        this.dy = dy;
    }

    @Override
    public String toString(){
        return "(" + getDx() + ", " + getDy() + ")";
    }
}
