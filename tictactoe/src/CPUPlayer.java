import java.util.ArrayList;

// IMPORTANT: Il ne faut pas changer la signature des méthodes
// de cette classe, ni le nom de la classe.
// Vous pouvez par contre ajouter d'autres méthodes (ça devrait 
// être le cas)
class CPUPlayer {

    // Contient le nombre de noeuds visités (le nombre
    // d'appel à la fonction MinMax ou Alpha Beta)
    // Normalement, la variable devrait être incrémentée
    // au début de votre MinMax ou Alpha Beta.
    private int numExploredNodes;
    private Mark mark;

    // Le constructeur reçoit en paramètre le
    // joueur MAX (X ou O)
    public CPUPlayer(Mark cpu) {
        this.mark = cpu;
    }

    // Ne pas changer cette méthode
    public int getNumOfExploredNodes() {
        return numExploredNodes;
    }

    // Retourne la liste des coups possibles. Cette liste contient
    // plusieurs coups possibles si et seuleument si plusieurs coups
    // ont le même score.
    public ArrayList<Move> getNextMoveMinMax(Board board) {
        ArrayList<Move> moves = new ArrayList<>();
        numExploredNodes = 0;

        int bestScore = Integer.MIN_VALUE;

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {

                if (board.isEmpty(row, col)) {
                    Move move = new Move(row, col);

                    board.play(move, mark);

                    int score = minMax(board, mark.getOpposite());

                    board.remove(row, col, Mark.EMPTY);

                    if (score > bestScore) {
                        bestScore = score;
                        moves.clear();
                        moves.add(move);
                    } else if (score == bestScore) {
                        moves.add(move);
                    }
                }
            }
        }

        return moves;
    }

    // Retourne la liste des coups possibles. Cette liste contient
    // plusieurs coups possibles si et seuleument si plusieurs coups
    // ont le même score.
    public ArrayList<Move> getNextMoveAB(Board board) {
        ArrayList<Move> moves = new ArrayList<>();
        numExploredNodes = 0;

        int bestScore = Integer.MIN_VALUE;
        int alpha = Integer.MIN_VALUE;
        int beta = Integer.MAX_VALUE;

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {

                if (board.isEmpty(row, col)) {
                    Move move = new Move(row, col);

                    board.play(move, mark);

                    int score = alphaBeta(board, mark.getOpposite(), alpha, beta);

                    board.remove(row, col, Mark.EMPTY);

                    if (score > bestScore) {
                        bestScore = score;
                        moves.clear();
                        moves.add(move);
                    } else if (score == bestScore) {
                        moves.add(move);
                    }

                    alpha = Math.max(alpha, bestScore);
                }
            }
        }

        return moves;
    }

    private int minMax(Board board, Mark currentMark) {
        numExploredNodes++;

        int score = board.evaluate(mark);

        if (score != -1) {
            return score;
        }

        if (currentMark == mark) {
            int bestScore = Integer.MIN_VALUE;

            for (int row = 0; row < 3; row++) {
                for (int col = 0; col < 3; col++) {

                    if (board.isEmpty(row, col)) {
                        Move move = new Move(row, col);

                        board.play(move, currentMark);

                        int moveScore = minMax(board, currentMark.getOpposite());

                        board.remove(row, col, Mark.EMPTY);

                        bestScore = Math.max(bestScore, moveScore);
                    }
                }
            }

            return bestScore;
        } else {
            int bestScore = Integer.MAX_VALUE;

            for (int row = 0; row < 3; row++) {
                for (int col = 0; col < 3; col++) {

                    if (board.isEmpty(row, col)) {
                        Move move = new Move(row, col);

                        board.play(move, currentMark);

                        int moveScore = minMax(board, currentMark.getOpposite());

                        board.remove(row, col, Mark.EMPTY);

                        bestScore = Math.min(bestScore, moveScore);
                    }
                }
            }

            return bestScore;
        }
    }

    private int alphaBeta(Board board, Mark currentMark, int alpha, int beta) {
        numExploredNodes++;

        int score = board.evaluate(mark);

        if (score != -1) {
            return score;
        }

        if (currentMark == mark) {
            int bestScore = Integer.MIN_VALUE;

            for (int row = 0; row < 3; row++) {
                for (int col = 0; col < 3; col++) {

                    if (board.isEmpty(row, col)) {
                        Move move = new Move(row, col);

                        board.play(move, currentMark);

                        int moveScore = alphaBeta(
                                board,
                                currentMark.getOpposite(),
                                alpha,
                                beta);

                        board.remove(row, col, Mark.EMPTY);

                        bestScore = Math.max(bestScore, moveScore);
                        alpha = Math.max(alpha, bestScore);

                        if (alpha >= beta) {
                            return bestScore;
                        }
                    }
                }
            }

            return bestScore;
        } else {
            int bestScore = Integer.MAX_VALUE;

            for (int row = 0; row < 3; row++) {
                for (int col = 0; col < 3; col++) {

                    if (board.isEmpty(row, col)) {
                        Move move = new Move(row, col);

                        board.play(move, currentMark);

                        int moveScore = alphaBeta(
                                board,
                                currentMark.getOpposite(),
                                alpha,
                                beta);

                        board.remove(row, col, Mark.EMPTY);

                        bestScore = Math.min(bestScore, moveScore);
                        beta = Math.min(beta, bestScore);

                        if (alpha >= beta) {
                            return bestScore;
                        }
                    }
                }
            }

            return bestScore;
        }
    }

}