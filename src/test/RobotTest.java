package test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertEquals;
import main.Robot;


public class RobotTest {
    private Robot robot;

    @BeforeEach
    public void setUp() {
        robot = new Robot();
    }

    @Test
    public void testPlace() {
        robot.place(0, 0, "NORTH");
        assertEquals("0,0,NORTH", robot.report());

        robot.place(4, 4, "EAST");
        assertEquals("4,4,EAST", robot.report());

        robot.place(5, 5, "SOUTH"); 
        assertEquals("4,4,EAST", robot.report());
    }

    @Test
    public void testMove() {
        robot.place(0, 0, "NORTH");
        robot.move();
        assertEquals("0,1,NORTH", robot.report());

        robot.place(4, 4, "SOUTH");
        robot.move();
        assertEquals("4,3,SOUTH", robot.report());

        robot.place(4, 4, "WEST");
        robot.move();

        assertEquals("3,4,WEST", robot.report());
    }

    @Test
    public void testTurnLeft() {
        robot.place(0, 0, "NORTH");
        robot.turnLeft();
        assertEquals("0,0,WEST", robot.report());
        robot.turnLeft();

        assertEquals("0,0,SOUTH", robot.report());
        robot.turnLeft();
        assertEquals("0,0,EAST", robot.report());
        robot.turnLeft();
        assertEquals("0,0,NORTH", robot.report());
    }

    @Test
    public void testTurnRight() {
        robot.place(0, 0, "NORTH");
        robot.turnRight();
        assertEquals("0,0,EAST", robot.report());
        robot.turnRight();
        assertEquals("0,0,SOUTH", robot.report());
        robot.turnRight();
        assertEquals("0,0,WEST", robot.report());
        robot.turnRight();
        assertEquals("0,0,NORTH", robot.report());
    }

    @Test
    public void testExecute() {
        robot.execute("PLACE 0,0,NORTH");
        assertEquals("0,0,NORTH", robot.report());
        robot.execute("MOVE");
        assertEquals("0,1,NORTH", robot.report());
        robot.execute("LEFT");
        assertEquals("0,1,WEST", robot.report());
        robot.execute("RIGHT");
        assertEquals("0,1,NORTH", robot.report());
    }
}