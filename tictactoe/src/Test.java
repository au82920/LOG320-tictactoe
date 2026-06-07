import java.util.ArrayList;

public class Test {

    public static void main(String[] args) {
        System.out.println("=== TEST TIC TAC TOE ===");

        testBoardEvaluate();
        testCPUPlayerMinMax();
        testCPUPlayerAlphaBeta();

        System.out.println("=== FIN DES TESTS ===");
    }

    private static void testBoardEvaluate() {
        System.out.println("\n--- Test Board.play() et Board.evaluate() ---");

        Board board = new Board();

        board.play(new Move(0, 0), Mark.X);
        board.play(new Move(0, 1), Mark.X);
        board.play(new Move(0, 2), Mark.X);

        int scoreX = board.evaluate(Mark.X);
        int scoreO = board.evaluate(Mark.O);

        System.out.println("Évaluation pour X : " + scoreX + " attendu: 100");
        System.out.println("Évaluation pour O : " + scoreO + " attendu: -100");
    }

    private static void testCPUPlayerMinMax() {
        System.out.println("\n--- Test CPUPlayer MinMax ---");

        Board board = new Board();

        board.play(new Move(0, 0), Mark.X);
        board.play(new Move(0, 1), Mark.X);
        board.play(new Move(1, 0), Mark.O);

        CPUPlayer cpu = new CPUPlayer(Mark.X);

        ArrayList<Move> moves = cpu.getNextMoveMinMax(board);

        System.out.println("Coups recommandés par MinMax :");
        printMoves(moves);

        System.out.println("Nombre de noeuds explorés : " + cpu.getNumOfExploredNodes());
        System.out.println("Le meilleur coup devrait inclure (0, 2), car X peut gagner.");
    }

    private static void testCPUPlayerAlphaBeta() {
        System.out.println("\n--- Test CPUPlayer Alpha-Beta ---");

        Board board = new Board();

        board.play(new Move(0, 0), Mark.X);
        board.play(new Move(0, 1), Mark.X);
        board.play(new Move(1, 0), Mark.O);

        CPUPlayer cpu = new CPUPlayer(Mark.X);

        ArrayList<Move> moves = cpu.getNextMoveAB(board);

        System.out.println("Coups recommandés par Alpha-Beta :");
        printMoves(moves);

        System.out.println("Nombre de noeuds explorés : " + cpu.getNumOfExploredNodes());
        System.out.println("Le meilleur coup devrait inclure (0, 2), car X peut gagner.");
    }

    private static void printMoves(ArrayList<Move> moves) {
        if (moves == null || moves.isEmpty()) {
            System.out.println("Aucun coup retourné.");
            return;
        }

        for (Move move : moves) {
            System.out.println("(" + move.getRow() + ", " + move.getCol() + ")");
        }
    }
}