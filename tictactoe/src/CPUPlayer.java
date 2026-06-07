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
        if (board.isFull()) {
            return moves;
        }
        ArrayList<Move> possibleMoves = board.possibleMoves();
        int best = Integer.MIN_VALUE;
        for (Move move : possibleMoves) {
            board.play(move, this.mark);
            int score = board.evaluate(mark);
            System.err.println(score);
            if (score == 100) {
                moves.clear();
                moves.add(move);
                break;
            }
            if (score == -1) {
                score = evaluateNextMove(board, this.mark.getOpposite());
            }
            if (score > best) {
                moves.clear();
                moves.add(move);
                best = score;
            } else if (best == score) {
                moves.add(move);
            }
            board.removeMove(move);
            System.err.println(move.toString() + " - score: " + score);

        }
        System.err.println(moves.get(0));
        return moves;
    }

    private int evaluateNextMove(Board board, Mark currentMark) {
        this.numExploredNodes++;
        int score = board.evaluate(this.mark);
        if (score != -1) {
            return score;
        }

        ArrayList<Move> possibleMoves = board.possibleMoves();

        if (currentMark == this.mark) {
            int best = Integer.MIN_VALUE;
            for (Move move : possibleMoves) {
                board.play(move, currentMark);
                score = evaluateNextMove(board, this.mark.getOpposite());
                if (score > best) {
                    best = score;
                }
                board.removeMove(move);
            }
            return best;
        } else {
            int best = Integer.MAX_VALUE;
            for (Move move : possibleMoves) {

                board.play(move, currentMark);
                score = evaluateNextMove(board, this.mark);
                if (score < best) {
                    best = score;
                }
                board.removeMove(move);
            }
            return best;
        }
    }

    // Retourne la liste des coups possibles. Cette liste contient
    // plusieurs coups possibles si et seuleument si plusieurs coups
    // ont le même score.
    public ArrayList<Move> getNextMoveAB(Board board) {
        numExploredNodes = 0;
        ArrayList<Move> moves = new ArrayList<>();
        return moves;
    }

    public int testEvaluateNextMove(Board board, Mark mark) {
        return evaluateNextMove(board, mark);
    }

}
