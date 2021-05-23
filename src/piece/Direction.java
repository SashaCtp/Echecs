package piece;

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

    @Override
    public boolean equals(Object obj){

        if(obj == null)
            return false;
        if(obj == this)
            return true;
        if(!(obj instanceof Direction))
            return false;

        Direction dir = (Direction) obj;

        return getDx() == dir.getDx() && getDy() == dir.getDy();

    }
}
