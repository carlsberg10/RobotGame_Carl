package main;
import java.util.Set;

public class Robot {
    private int x;
    private int y;
    private String direction;
    private boolean isPlaced = false;
    private static final int TABLE_SIZE = 5; // Storleken på spelplanen
    private static final String[] DIRECTIONS = { "NORTH", "EAST", "SOUTH", "WEST" };// De fyra riktningarna roboten kan

    public boolean valid_directions(String direction) {
        return Set.of(DIRECTIONS).contains(direction);
    }
    

    public void place(int x, int y, String direction) { // Metod för att placera roboten på spelplanen
        if (x < 0 || x >= TABLE_SIZE || y < 0 || y >= TABLE_SIZE) { // Om positionen är utanför spelplanen så returneras den
            System.out.println("Invalid position: (" + x + "," + y + ")");
            return; 
        }
    
        String dir = direction.toUpperCase();
        if (!valid_directions(dir)) { // Om riktningen inte är en av de fyra riktningarna så returneras den
            System.out.println("Invalid direction: " + direction);
            return;
        }
    
        this.x = x;
        this.y = y;
        this.direction = dir;
        isPlaced = true;
    }
    

    public void move() { // Metod för att flytta roboten
        if (!isPlaced)
            return; // om den inte placeras så returneras den
        switch (direction) {// Beroende på vilket håll roboten är riktad så flyttas den i den riktningen
            case "NORTH" -> {
                if (y < TABLE_SIZE - 1)
                    y++;
            }
            case "EAST" -> {
                if (x < TABLE_SIZE - 1)
                    x++;
            }
            case "SOUTH" -> {
                if (y > 0)
                    y--;
            }
            case "WEST" -> {
                if (x > 0)
                    x--;
            }
        }
    }

    public void turnLeft() {// Metod för att rotera roboten 90 grader åt vänster
        if (!isPlaced) {
            return;
        } else {
            int index = getDirectionIndex();
            direction = DIRECTIONS[(index + 3) % 4];
        }
    }

    public void turnRight() { // Metod för att rotera roboten 90 grader åt höger
        if (!isPlaced) {
            return;
        } else {
            int index = getDirectionIndex();
            direction = DIRECTIONS[(index + 1) % 4];
        }
    }

    public String report() {// Metod för att rapportera robotens position
        if (!isPlaced) {
            return "Robot not placed yet!";
        } else {
            return x + "," + y + "," + direction;
        }
    }

    public void execute(String command) { // Metod för att exekvera robotens rörelser 
        command = command.trim();
        if (command.toUpperCase().startsWith("PLACE")) {
            String[] parts = command.substring(6).split("\\s*,\\s*"); // Delar upp PLACE-kommandot i delar
            if (parts.length == 3) {
                try {
                    int newX = Integer.parseInt(parts[0]);
                    int newY = Integer.parseInt(parts[1]);
                    String newDirection = parts[2].trim();
                    place(newX, newY, newDirection);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid PLACE command: " + command);
                }
            }
        } else if (isPlaced) {
            switch (command.toUpperCase()) {
                case "MOVE" -> move();
                case "LEFT" -> turnLeft();
                case "RIGHT" -> turnRight();
                case "REPORT" -> {
                    System.out.println(report());
                    displayTable();
                }
                default -> System.out.println("Invalid command: " + command);
            }
        } else {
            System.out.println("Robot is not placed yet. Ignoring command: " + command);
        }
    }

    private int getDirectionIndex() {// Metod för att få indexet för riktningen
        return java.util.Arrays.asList(DIRECTIONS).indexOf(direction);
    }
    

    public void displayTable() {
        String symbol = ""; // Dessa symboler används för att visa vilket håll roboten är riktad
        switch (direction) { // Beroende på vilket håll roboten är riktad så visas en symbol
            case "NORTH" -> symbol = "^";
            case "EAST" -> symbol = ">";
            case "SOUTH" -> symbol = "v";
            case "WEST" -> symbol = "<";
        }
        System.out.println("\n     0  1  2  3  4");
        System.out.println("     -----------------");

        for (int row = TABLE_SIZE - 1; row >= 0; row--) {
            System.out.print("  " + row + " | ");
            for (int col = 0; col < TABLE_SIZE; col++) {
                if (isPlaced && x == col && y == row) {
                    System.out.print(symbol + "  "); 
                } else {
                    System.out.print(".  ");
                }
            }
            System.out.println();
        }
        System.out.println("     -----------------");
        System.out.println("       0  1  2  3  4");
    }

}

