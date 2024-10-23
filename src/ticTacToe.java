import java.util.*;

public class ticTacToe {

    static ArrayList<Integer> playerPositions = new ArrayList<Integer>();
    static ArrayList<Integer> cpuPositions = new ArrayList<Integer>();

    public static void main(String[] args) {

        char[][] gameBoard = {{' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '}};

        printGameBoard(gameBoard);

        while(true){
            System.out.println("Enter 1 for single player or 2 for multiplayer");
            Scanner scan = new Scanner(System.in);
            int choice = scan.nextInt();
            if (choice == 1){
                while(true){

                    System.out.println("Enter a space on the game board (1-9):");
                    int playerPos = scan.nextInt(); //this takes in the input of the user
                    while(playerPositions.contains(playerPos) || cpuPositions.contains(playerPos)){
                        System.out.println("Position taken! Enter a different position!");
                        playerPos = scan.nextInt();
                    }

                    placePiece(gameBoard, playerPos, "player");

                    String result = checkWinner(choice);
                    if(result.length() > 0){
                        System.out.println(" ");
                        printGameBoard(gameBoard);
                        System.out.println(result);
                        break;
                    }

//                    Random rand = new Random();
//                    int cpuPos = rand.nextInt(9) + 1;
                    int cpuPos = getBestMove(gameBoard);
//                    while(playerPositions.contains(cpuPos) || cpuPositions.contains(cpuPos)){
//                        cpuPos = rand.nextInt(9) + 1;
//                    }
                    placePiece(gameBoard, cpuPos, "cpu");

                    printGameBoard(gameBoard);

                    result = checkWinner(choice);
                    if(result.length() > 0){
                        System.out.println(" ");
                        printGameBoard(gameBoard);
                        System.out.println(result);
                        break;
                    }
                    System.out.println(result);
                }
                break;

            } else if(choice == 2){

                while(true){

                    System.out.println("Player X: Enter a space on the game board (1-9):");
                    int playerPos = scan.nextInt(); //this takes in the input of the user
                    while(playerPositions.contains(playerPos) || cpuPositions.contains(playerPos)){
                        System.out.println("Position taken! Enter a different position!");
                        playerPos = scan.nextInt();
                    }

                    placePiece(gameBoard, playerPos, "player");

                    printGameBoard(gameBoard);

                    String result = checkWinner(choice);
                    if(result.length() > 0){
                        System.out.println(" ");
                        printGameBoard(gameBoard);
                        System.out.println(result);
                        break;
                    }

                    System.out.println("Player O: Enter a space on the game board (1-9):");
                    int player2Pos = scan.nextInt();
                    while(playerPositions.contains(player2Pos) || cpuPositions.contains(player2Pos)){
                        System.out.println("Position taken! Enter a different position!");
                        player2Pos = scan.nextInt();
                    }
                    placePiece(gameBoard, player2Pos, "cpu");

                    printGameBoard(gameBoard);

                    result = checkWinner(choice);
                    if(result.length() > 0){
                        System.out.println(" ");
                        printGameBoard(gameBoard);
                        System.out.println(result);
                        break;
                    }
                    System.out.println(result);
                }
                break;

            }else{
                System.out.println("Only enter 1 or 2!");
            }
        }





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
            playerPositions.add(pos);
        }else if(user.equals("cpu")){
            symbol = '0';
            cpuPositions.add(pos);
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

    public static String checkWinner(int choice) {

        List topRow = Arrays.asList(1, 2, 3);
        List midRow = Arrays.asList(4, 5, 6);
        List botRow = Arrays.asList(7, 8, 9);
        List leftCol = Arrays.asList(1, 4, 7);
        List midCol = Arrays.asList(2, 5, 8);
        List rightCol = Arrays.asList(3, 6, 9);
        List diag1 = Arrays.asList(1, 5, 9);
        List diag2 = Arrays.asList(7, 5, 3);

        List<List> winning = new ArrayList<List>();
        winning.add(topRow);
        winning.add(midRow);
        winning.add(botRow);
        winning.add(leftCol);
        winning.add(midCol);
        winning.add(rightCol);
        winning.add(diag1);
        winning.add(diag2);

        for(List l : winning){
            if(playerPositions.containsAll(l)){
                if(choice==1){
                    return "Yay you win!";
                }else{
                    return "X wins!";
                }
            }else if(cpuPositions.containsAll(l)){
                if(choice==1){
                    return "Sorry CPU wins!";
                }else{
                    return "0 wins!";
                }
            }
        }

        if(playerPositions.size() + cpuPositions.size() == 9){
//            System.out.println(" ");
            return "It's a tie!";
        }

        return "";

    }

    public static int getBestMove(char[][] gameBoard) {
        int bestScore = Integer.MIN_VALUE;
        int bestMove = 0;

        for (int i = 1; i <= 9; i++) {
//            is the spot still available?
            if (!playerPositions.contains(i) && !cpuPositions.contains(i)) {
                cpuPositions.add(i);
                int score = minimax(0,false);
                cpuPositions.remove(Integer.valueOf(i));

                if (score > bestScore) {
                    bestScore = score;
                    bestMove = i;
                }
            }
        }
        return bestMove;
    }



    public static int minimax(int depth, boolean isMaximizing) {
        String result = checkWinner(1);
        if (result.equals("Sorry CPU wins!")) {
            return 1;
        } else if (result.equals("Yay you win!")) {
            return -1;
        } else if (result.equals("It's a tie!")) {
            return 0;
        }

//        if it's a maximizing player check all the best spots by calling minimax recursively then return the best spot
        int bestScore;
        if (isMaximizing) {
            bestScore = Integer.MIN_VALUE;
            for (int i = 1; i <= 9; i++) {
//                is the spot available?
                if (!playerPositions.contains(i) && !cpuPositions.contains(i)) {
                    cpuPositions.add(i);
                    int score = minimax(depth+1, false);
                    cpuPositions.remove(Integer.valueOf(i));
                    bestScore = Math.max(score, bestScore);
                }
            }
        } else {
            bestScore = Integer.MAX_VALUE;
            for (int i = 1; i <= 9; i++) {
                if (!playerPositions.contains(i) && !cpuPositions.contains(i)) {
                    playerPositions.add(i);
                    int score = minimax( depth+1, true);
                    playerPositions.remove(Integer.valueOf(i));
                    bestScore = Math.min(score, bestScore);
                }
            }
        }
        return bestScore;

/*  if you return just 1 the algorithm doesn't pick the most optimal move, it just picks the
next available spot on the board so let's say spots 1 and 2 get filled, it chooses 3 */
//        return 1;
    }
}
