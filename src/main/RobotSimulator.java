package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class RobotSimulator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Do you want to play the robot game? (Y/N)");
        boolean playing = true;
        String play = scanner.nextLine().trim().toUpperCase();
        if (!play.equals("Y")) {
            playing = false;
        }

        while (playing) {
            try {
                System.out.print("Enter the file path: ");
                String filePath = scanner.nextLine().replace("\"", "");

                File file = new File(filePath);
                if (!file.exists()) {
                    System.out.println("Error: File not found! Check the path.");
                    continue;
                }

                BufferedReader reader = new BufferedReader(new FileReader(file));
                Robot robot = new Robot();
                String line;

                while ((line = reader.readLine()) != null) {
                    line = line.trim();
                    if (!line.isEmpty()) {
                        robot.execute(line);
                    }
                }
                reader.close();

            } catch (IOException e) {
                System.out.println("Error reading the file: " + e.getMessage());
            }

            System.out.print("\nDo you want to play again? (Y/N): ");
            String playAgain = scanner.nextLine().trim().toUpperCase();

            if (!playAgain.equals("Y")) {
                playing = false; 
            }
        }
        scanner.close();
    }
}

