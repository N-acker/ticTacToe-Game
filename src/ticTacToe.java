import java.util.Arrays;
import java.util.Scanner;

public class ticTacToe {
    public static void main(String[] args) {

        char[][] gameBoard = {{' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '}};

        printGameBoard(gameBoard);

        Scanner scan = new Scanner(System.in);
        System.out.println("Enter a space on the game board (1-9):");
        int pos = scan.nextInt(); //this takes in the input of the user

        System.out.println(pos);

        printGameBoard(gameBoard);
    }

    public static void printGameBoard(char[][] gameBoard) {
        for(char[] row : gameBoard){
            for(char column : row){
                System.out.print(column); //we don't print every column line by line
            }
            System.out.println(); //here we print a line after printing each column in the row
        }
    }

    public static void placePiece(char [][] gameBoard, int pos, String user){

        char symbol = ' ';// the space is the default symbol

        if(user.equals("player")){
            symbol = 'X';
        }else if(user.equals("cpu")){
            symbol = '0';
        }
        switch(pos){
            case 1:
                gameBoard[0][0] = symbol;
                break;
            case 2:
                gameBoard[0][2] = symbol;
                break;
            case 3:
                gameBoard[0][4] = symbol;
                break;
            case 4:
                gameBoard[2][0] = symbol;
                break;
            case 5:
                gameBoard[2][2] = symbol;
                break;
            case 6:
                gameBoard[2][4] = symbol;
                break;
            case 7:
                gameBoard[4][0] = symbol;
                break;
            case 8:
                gameBoard[4][2] = symbol;
                break;
            case 9:
                gameBoard[4][4] = symbol;
                break;
            default:
                break;
        }
    }
}
