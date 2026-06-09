import java.util.ArrayList;

// IMPORTANT: Il ne faut pas changer la signature des méthodes
// de cette classe, ni le nom de la classe.
// Vous pouvez par contre ajouter d'autres méthodes (ça devrait 
// être le cas)
class Board
{
    private Mark[][] board;

    // Ne pas changer la signature de cette méthode
    public Board() {
        board = new Mark[3][3];
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                board[i][j] = Mark.EMPTY;
            }
        }
    }

    // Place la pièce 'mark' sur le plateau, à la
    // position spécifiée dans Move
    //
    // Ne pas changer la signature de cette méthode
    public void play(Move m, Mark mark){
        board[m.getRow()][m.getCol()] = mark;
    }

    public void undo(Move m) {
        board[m.getRow()][m.getCol()] = Mark.EMPTY;
    }

    public Mark getMark(int col, int row) {
        return board[row][col];
    }

    public ArrayList<Move> getPossibleMoves() {
        ArrayList<Move> possibleMoves = new ArrayList<>();

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (board[row][col] == Mark.EMPTY) {
                    possibleMoves.add(new Move(row, col));
                }
            }
        }

        return possibleMoves;
    }

    public boolean hasWon(Mark mark) {
        for (int row = 0; row < 3; row++) {
            if (board[row][0] == mark
                    && board[row][1] == mark
                    && board[row][2] == mark) {
                return true;
            }
        }

        for (int col = 0; col < 3; col++) {
            if (board[0][col] == mark
                    && board[1][col] == mark
                    && board[2][col] == mark) {
                return true;
            }
        }

        if (board[0][0] == mark
                && board[1][1] == mark
                && board[2][2] == mark) {
            return true;
        }

        return board[0][2] == mark
                && board[1][1] == mark
                && board[2][0] == mark;
    }

    public boolean isGameOver() {
        return hasWon(Mark.X)
                || hasWon(Mark.O)
                || getPossibleMoves().isEmpty();
    }

    // retourne  100 pour une victoire
    //          -100 pour une défaite
    //           0   pour un match nul
    // Ne pas changer la signature de cette méthode
    public int evaluate(Mark mark){
        Mark opponent;

        if (mark == Mark.X) {
            opponent = Mark.O;
        } else {
            opponent = Mark.X;
        }

        if (hasWon(mark)) {
            return 100;
        }

        if (hasWon(opponent)) {
            return -100;
        }

        return 0;
    }
}
