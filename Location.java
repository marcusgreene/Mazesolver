/*
this class defines the location of the path in x and y coordinates
 */

class Location {

    private int x;
    private int y;

    //class object begins with x and y of 0
    Location() {
        x = y = 0;
    }

    Location(int x, int y) {
        this.x = x;
        this.y = y;
    }

    int getX() {
        return x;
    }

    int getY() {
        return y;
    }

    @Override
    public String toString() {
        return "(" + this.x + "," + this.y + ")";
    }

    @Override
    public boolean equals(Object obj) {
        Location other = (Location) obj;
        return this.x == other.getX() && this.y == other.getY();
    }
}
