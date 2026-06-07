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
    private boolean lkjps = true;

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

        if (board.isFull())
            return moves;

        ArrayList<Move> possibleMoves = board.possibleMoves();
        int best = Integer.MIN_VALUE;

        for (Move move : possibleMoves) {
            this.numExploredNodes++;
            board.play(move, this.mark);
            int score = board.evaluate(mark);

            if (score == 100) {
                moves.clear();
                moves.add(move);
                break;
            }
            if (score == -1)
                score = minMax(board, this.mark.getOpposite());

            board.removeMove(move);
            if (score > best) {
                moves.clear();
                moves.add(move);
                best = score;
            } else if (best == score) {
                moves.add(move);
            }
        }
        return moves;
    }

    private int minMax(Board board, Mark currentMark) {
        int score = board.evaluate(this.mark);
        if (score != -1) {
            return score;
        }
        this.numExploredNodes++;

        ArrayList<Move> possibleMoves = board.possibleMoves();

        if (currentMark == this.mark) {
            int best = Integer.MIN_VALUE;
            for (Move move : possibleMoves) {
                board.play(move, currentMark);
                score = minMax(board, this.mark.getOpposite());
                board.removeMove(move);
                if (score > best)
                    best = score;
            }
            return best;
        } else {
            int best = Integer.MAX_VALUE;
            for (Move move : possibleMoves) {

                board.play(move, currentMark);
                score = minMax(board, this.mark);
                board.removeMove(move);
                if (score < best)
                    best = score;
            }
            return best;
        }
    }

    // Retourne la liste des coups possibles. Cette liste contient
    // plusieurs coups possibles si et seuleument si plusieurs coups
    // ont le même score.
    public ArrayList<Move> getNextMoveAB(Board board) {
        ArrayList<Move> moves = new ArrayList<>();
        numExploredNodes = 0;

        if (board.isFull())
            return moves;

        ArrayList<Move> possibleMoves = board.possibleMoves();
        int best = Integer.MIN_VALUE;
        int alpha = Integer.MIN_VALUE;
        int beta = Integer.MAX_VALUE;

        for (Move move : possibleMoves) {
            this.numExploredNodes++;
            board.play(move, this.mark);
            int score = board.evaluate(mark);

            if (score == 100) {
                moves.clear();
                moves.add(move);
                break;
            }

            if (score == -1)
                score = alphaBeta(board, this.mark.getOpposite(), alpha, beta);
            board.removeMove(move);
            if (score > best) {
                moves.clear();
                moves.add(move);
                best = score;
            } else if (best == score) {
                moves.add(move);
            }

        }
        return moves;
    }

    private int alphaBeta(Board board, Mark currentMark, int alpha, int beta) {
        int score = board.evaluate(this.mark);
        if (score != -1) {
            return score;
        }
        this.numExploredNodes++;

        ArrayList<Move> possibleMoves = board.possibleMoves();

        if (currentMark == this.mark) {
            int best = Integer.MIN_VALUE;
            for (Move move : possibleMoves) {
                board.play(move, currentMark);
                score = alphaBeta(board, this.mark.getOpposite(), alpha, beta);
                board.removeMove(move);
                best = Math.max(best, score);
                alpha = Math.max(alpha, best);
                if (alpha >= beta)
                    break;
            }
            return best;
        } else {
            int best = Integer.MAX_VALUE;
            for (Move move : possibleMoves) {
                board.play(move, currentMark);
                score = alphaBeta(board, this.mark, alpha, beta);
                board.removeMove(move);
                best = Math.min(score, best);
                beta = Math.min(beta, best);
                if (alpha >= beta)
                    break;
            }
            return best;
        }
    }

    public int testEvaluateNextMove(Board board, Mark mark) {
        return minMax(board, mark);
    }

}
