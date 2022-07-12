
import java.util.*;

class MazeSolver {

    private Maze maze;
    private MazeGUI gui;

    MazeSolver(int size) {
        this.maze = new Maze(size);
        this.gui = new MazeGUI(maze);
    }

    void repaint(Path path) {
        gui.setPath(path);
        gui.repaint();
        try {
            Thread.sleep(50);
        } catch (Exception ex) {

        }
    }

    private Path solve() throws CloneNotSupportedException {

        // path starts with a location 
        Location origin = new Location(0,0);
        Path path = new Path(maze);
        path.add(origin);
        Location location;

        // stack which contains multiple paths in order to backtrack
        Stack<Path> stack = new Stack<Path>();
        stack.push(path);

        // backtracking algorithm
        while(!stack.isEmpty()) {
            path = stack.pop();
            repaint(path);
            if(path.isAtEnd()) {
                return path;
            }

            if(path.canMoveLeft()) {
                Path newPath = (Path) path.clone();
                location = path.getLast();
                Location newLocation = new Location(location.getX() - 1, location.getY() );
                if(!path.contains(newLocation)) {
                    newPath.add(newLocation);
                    stack.push(newPath);
                }
            }

            if(path.canMoveUp()) {
                Path newPath = (Path) path.clone();
                location = path.getLast();
                Location newLocation = new Location(location.getX(), location.getY() - 1);
                if(!path.contains(newLocation)) {
                    newPath.add(newLocation);
                    stack.push(newPath);
                }
            }

            if(path.canMoveDown()) {
                Path newPath = (Path) path.clone();
                location = path.getLast();
                Location newLocation = new Location(location.getX(), location.getY()+1);
                if(!path.contains(newLocation)) {
                    newPath.add(newLocation);
                    stack.push(newPath);
                }
            }

            if(path.canMoveRight()) {
                Path newPath = (Path) path.clone();
                location = path.getLast();
                Location newLocation = new Location(location.getX() + 1, location.getY());
                if(!path.contains(newLocation)) {
                    newPath.add(newLocation);
                    stack.push(newPath);
                }
            }




        }
        return null;
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        MazeSolver mazeSolver = new MazeSolver(50);
        Path path = mazeSolver.solve();

        if (path != null) {
            System.out.println("Found a solution!");
            System.out.println(path);
        }
        else
            System.out.println("A solution does not exist!");
    }
}
