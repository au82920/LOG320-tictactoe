// de cette classe, ni le nom de la classe.
// Vous pouvez par contre ajouter d'autres méthodes (ça devrait 
// être le cas)

import java.util.ArrayList;

class Board {
    private Mark[][] board;

    // Ne pas changer la signature de cette méthode
    public Board() {
        this.board = new Mark[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = Mark.EMPTY;
            }
        }
    }

    // Place la pièce 'mark' sur le plateau, à la
    // position spécifiée dans Move
    //
    // Ne pas changer la signature de cette méthode
    public void play(Move m, Mark mark) {
        board[m.getCol()][m.getRow()] = mark;
    }

    // retourne 100 pour une victoire
    // -100 pour une défaite
    // 0 pour un match nul
    // Ne pas changer la signature de cette méthode
    public int evaluate(Mark mark) {
        Mark opposite = mark.getOpposite();
        for (int i = 0; i < 3; i++) {

            if ((board[i][0] == mark && board[i][1] == mark && board[i][2] == mark) ||
                    (board[0][i] == mark && board[1][i] == mark && board[2][i] == mark)) {
                return 100;
            } else if ((board[i][0] == opposite && board[i][1] == opposite && board[i][2] == opposite) ||
                    (board[0][i] == opposite && board[1][i] == opposite && board[2][i] == opposite)) {
                return -100;
            }

        }
        if ((board[0][0] == mark && board[1][1] == mark && board[2][2] == mark) ||
                (board[0][2] == mark && board[1][1] == mark && board[2][0] == mark)) {
            return 100;
        } else if ((board[0][0] == opposite && board[1][1] == opposite && board[2][2] == opposite) ||
                (board[0][2] == opposite && board[1][1] == opposite && board[2][0] == opposite)) {
            return -100;
        }
        if (this.isFull()) {
            return 0;
        } else {
            return -1;
        }
    }

    public boolean isFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == Mark.EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    public ArrayList<Move> possibleMoves() {
        ArrayList<Move> moves = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == Mark.EMPTY) {
                    moves.add(new Move(j, i));
                }
            }
        }
        return moves;
    }

    public void removeMove(Move move) {
        this.board[move.getCol()][move.getRow()] = Mark.EMPTY;
    }

    public Mark getMark(int col, int row) {
        return board[col][row];
    }
}
