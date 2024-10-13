import java.util.Arrays;
import java.util.Scanner;

public class ticTacToe {
    public static void main(String[] args) {

        char[][] gameBoard = {{' ', '|', ' ', '|', ' '},
                {'_', '+', '_', '+', '_'},
                {' ', '|', ' ', '|', ' '},
                {'_', '+', '_', '+', '_'},
                {' ', '|', ' ', '|', ' '}};

        printGameBoard(gameBoard);

        Scanner scan = new Scanner(System.in);
        System.out.println("Enter a space on the game board (1-9):");
        int pos = scan.nextInt(); //this takes in the input of the user

        System.out.println(pos);


    }

    public static void printGameBoard(char[][] gameBoard) {
        for(char[] row : gameBoard){
            for(char column : row){
                System.out.print(column); //we don't print every column line by line
            }
            System.out.println(); //here we print a line after printing each column in the row
        }
    }
}
