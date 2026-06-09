
enum Mark {
    X,
    O,
    EMPTY;

    public Mark getOpposite() {
        if (this == Mark.O) {
            return Mark.X;
        }
        if (this == Mark.X) {
            return Mark.O;
        }
        return Mark.EMPTY;
    }
}