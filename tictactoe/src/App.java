import java.util.ArrayList;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        boolean play = true;

        System.out.println("WELCOME TO TICTACTOE");
        while (play) {
            System.out.println("CHOOSE YOUR MARK (X/O)");
            Mark playerMark = Mark.EMPTY;
            while (playerMark == Mark.EMPTY) {
                String markString = scanner.nextLine();
                if (markString.toLowerCase().equals("x")) {
                    playerMark = Mark.X;
                } else if (markString.toLowerCase().equals("o")) {
                    playerMark = Mark.O;
                } else {
                    System.out.println("PLEASE ENTER A VALID MARK (X/O)");
                }
            }
            System.out.println("CHOOSE THE CPU ALGORITHM");
            System.out.println("MM : MINMAX");
            System.out.println("AB : ALPHA-BETA");
            String cpuAlgo = "";
            while (cpuAlgo.equals("")) {
                cpuAlgo = scanner.nextLine().toLowerCase();
                if (!cpuAlgo.equals("mm") && !cpuAlgo.equals("ab")) {
                    System.out.println("PLEASE ENTER A VALID ALGORITHM (MM/AB)");
                    cpuAlgo = "";
                }
            }
            System.out.println("OK LET'S START!");
            Board board = new Board();
            CPUPlayer cpu = new CPUPlayer(playerMark.getOpposite());
            int result = -1;
            Mark turn = Mark.X;
            Thread.sleep(2000);
            generateGrid(board);
            while (result == -1) {
                turn = turn.getOpposite();
                if (turn == playerMark) {
                    boolean moveValid = false;
                    System.out.println("WHAT'S YOUR MOVE? (ex. a-1)");
                    while (!moveValid) {
                        String moveString = scanner.nextLine();
                        if (moveString.matches("[abc]-[123]")) {
                            String[] move = moveString.split("-");
                            int col = move[0].charAt(0) - 'a';
                            int row = Integer.parseInt(move[1]) - 1;
                            if (board.getMark(col, row) == Mark.EMPTY) {
                                moveValid = true;
                                board.play(new Move(row, col), playerMark);
                            } else {
                                System.out.println("PLEASE ENTER A POSITION NOT ALREADY TAKEN");
                            }
                        } else {
                            System.out.println("PLEASE ENTER A VALID POSITION (ex. a-1)");
                        }
                    }
                } else {
                    ArrayList<Move> possibleMoves;
                    if(cpuAlgo.equals("mm")){
                        possibleMoves = cpu.getNextMoveMinMax(board);
                    }else{
                        possibleMoves = cpu.getNextMoveAB(board);
                    }
                    int move = (int) (Math.random() * (possibleMoves.size() - 1));
                    Move cpuMove = possibleMoves.get(move);
                    board.play(possibleMoves.get(move), turn);
                    char col = (char) ('a' + (cpuMove.getCol()));
                    int row = cpuMove.getRow() + 1;
                    System.out.println("NUM OF EXPLORED NODES : " + cpu.getNumOfExploredNodes());
                    System.out.println("THE CPU PLAYS " + col + "-" + row);
                }

                result = board.evaluate(turn);
                generateGrid(board);
            }
            if (result == 0) {
                System.out.println("IT'S A TIE!");
            } else {
                if (turn == playerMark) {
                    System.out.println("YOU WON!");
                } else {
                    System.out.println("YOU LOST...");
                }
            }
            Thread.sleep(2000);
            System.out.println("DO YOU WANT TO PLAY AGAIN? (Y/N)");
            String ansString = scanner.nextLine().toLowerCase();
            if(ansString.charAt(0) != 'y'){
                System.out.println("THANK YOU FOR PLAYING!");
                play = false;
            }
        }
    }

    private static void generateGrid(Board board) {
        System.out.println("     a     b     c   ");
        System.out.println("1   " + getMarkString(board.getMark(0, 0)) + " | " + getMarkString(board.getMark(1, 0))
                + " | " + getMarkString(board.getMark(2, 0)) + " ");
        System.out.println("   ----------------- ");
        System.out.println("2   " + getMarkString(board.getMark(0, 1)) + " | " + getMarkString(board.getMark(1, 1))
                + " | " + getMarkString(board.getMark(2, 1)) + " ");
        System.out.println("   ----------------- ");
        System.out.println("3   " + getMarkString(board.getMark(0, 2)) + " | " + getMarkString(board.getMark(1, 2))
                + " | " + getMarkString(board.getMark(2, 2)) + " ");
    }

    private static String getMarkString(Mark mark) {
        switch (mark) {
            case Mark.X:
                return " X ";
            case Mark.O:
                return " O ";
            default:
                return "   ";
        }
    }
}
