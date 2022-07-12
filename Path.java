

import java.awt.*;
import java.util.*;

class Path implements Iterable<Location>, Drawable {

    private Maze maze;
    private LinkedList<Location> data;

    Path(final Maze maze) {
        this.maze = maze;
        this.data = new LinkedList<>();
    }

    // check if the path linked list contains the passed location
    boolean contains(final Location location) {
        return data.contains(location);
    }

    // adds location to the iterable
    void add(final Location location) {
        data.add(location);
    }

    // gets last item of the linked list
    Location getLast() {
        return data.getLast();
    }

    // checks to see if the location is at the bottom right corner
    boolean isAtEnd() {
        if (data.getLast().getX() == maze.getSize()-1 && data.getLast().getY() == maze.getSize()-1) {
            return true;
        }
        return false;
    }

    boolean canMoveUp() {
        Location location = getLast();
        if (location == null)
            return false;
        Location newLocation = new Location(location.getX(), location.getY() - 1);
        return !this.maze.isWall(newLocation);
    }

    boolean canMoveDown() {
        Location location = getLast();
        if (location == null)
            return false;
        Location newLocation = new Location(location.getX(), location.getY() + 1);
        return !this.maze.isWall(newLocation);
    }

    boolean canMoveRight() {
        Location location = getLast();
        if (location == null)
            return false;
        Location newLocation = new Location(location.getX() + 1, location.getY());
        return !this.maze.isWall(newLocation);
    }

    boolean canMoveLeft() {
        Location location = getLast();
        if (location == null)
            return false;
        Location newLocation = new Location(location.getX() - 1, location.getY());
        return !this.maze.isWall(newLocation);
    }

    @Override
    public String toString() {
        return this.data.toString();
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Path clone = new Path(this.maze);
        Iterator<Location> it = this.data.iterator();
        while (it.hasNext())
            clone.add(it.next());
        return clone;
    }

    @Override
    public Iterator<Location> iterator() {
        return this.data.iterator();
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.YELLOW);
        int cellWidth = MazeGUI.WIDTH / this.maze.getSize();
        int cellHeight = MazeGUI.HEIGHT / this.maze.getSize();
        Iterator it = iterator();
        while (it.hasNext()) {
            Location location = (Location) it.next();
            g.fillRect(location.getX() * cellWidth + 1, location.getY() * cellHeight + 1, cellWidth - 2, cellHeight - 2);
        }
    }
}
