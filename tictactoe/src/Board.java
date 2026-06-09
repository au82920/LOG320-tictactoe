// de cette classe, ni le nom de la classe.
// Vous pouvez par contre ajouter d'autres méthodes (ça devrait 
// être le cas)
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

            if (isWinningLine(board[i][0], board[i][1], board[i][2], mark))
                return 100;

            if (isWinningLine(board[i][0], board[i][1], board[i][2], opposite))
                return -100;

            if (isWinningLine(board[0][i], board[1][i], board[2][i], mark))
                return 100;

            if (isWinningLine(board[0][i], board[1][i], board[2][i], opposite))
                return -100;

        }
        if (isWinningLine(board[0][0], board[1][1], board[2][2], mark))
            return 100;

        if (isWinningLine(board[0][0], board[1][1], board[2][2], opposite))
            return -100;

        if (isWinningLine(board[0][2], board[1][1], board[2][0], mark))
            return 100;

        if (isWinningLine(board[0][2], board[1][1], board[2][0], opposite))
            return -100;

        return isFull() ? 0 : -1;
    }

    private boolean isWinningLine(Mark a, Mark b, Mark c, Mark mark) {
        return a == mark && b == mark && c == mark;
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

    public boolean isEmpty(int row, int col) {
        return board[col][row] == Mark.EMPTY;
    }

    public void remove(int row, int col, Mark mark) {
        board[col][row] = mark;
    }

    public Mark getMark(int col, int row) {
        return board[col][row];
    }

}