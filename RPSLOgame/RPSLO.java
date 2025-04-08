package RPSLOgame;

import java.util.Scanner;

// A simple console game of Rock, Paper, Scissors, Lizard, Spock.
// R = Rock, P = Paper, S = Scissors, L = Lizard, O = Spock

public class RPSLO {

    public static int winner(String computer, String player) {
        if (player.equals("S") && (computer.equals("P") || computer.equals("L"))) return 0; // Scissors wins
        if (computer.equals("S") && (player.equals("P") || player.equals("L"))) return 1;

        if (player.equals("P") && (computer.equals("R") || computer.equals("O"))) return 0; // Paper wins
        if (computer.equals("P") && (player.equals("R") || player.equals("O"))) return 1;

        if (player.equals("R") && (computer.equals("L") || computer.equals("S"))) return 0; // Rock wins
        if (computer.equals("R") && (player.equals("L") || player.equals("S"))) return 1;

        if (player.equals("O") && (computer.equals("S") || computer.equals("R"))) return 0; // Spock wins
        if (computer.equals("O") && (player.equals("S") || player.equals("R"))) return 1;

        if (player.equals("L") && (computer.equals("O") || computer.equals("P"))) return 0; // Lizard wins
        if (computer.equals("L") && (player.equals("O") || player.equals("P"))) return 1;

        if (player.equals(computer)) return 2; // tie
        return -1; // unexpected input
    }

    public static void main(String[] args) {
        // --  TESTING/USER INTERACTION --
        System.out.println("Enter your play: R, P, S, L, O");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine().toUpperCase();
        scanner.close();

        // Randomly choose for the computer
        double rand = Math.random();
        String computerMove = " ";
        if (input.matches("[RPSLO]")) {
            if (rand <= 0.2) computerMove = "R";
            else if (rand <= 0.4) computerMove = "P";
            else if (rand <= 0.6) computerMove = "S";
            else if (rand <= 0.8) computerMove = "L";
            else computerMove = "O";
        } else {
            System.out.println("Invalid input (valid inputs: R, P, S, L, O)");
            return;
        }

        System.out.println("Computer play is " + computerMove);
        int result = winner(computerMove, input);

        // -- RESULT OUTPUT --
        if (result == 1)
            System.out.println("Computer wins!");
        else if (result == 0)
            System.out.println("You win!");
        else if (result == 2)
            System.out.println("It is a tie!");
        else
            System.out.println("Error determining result.");
    }
}

