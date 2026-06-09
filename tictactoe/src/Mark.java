
enum Mark{
        X,
        O,
        EMPTY;

        public Mark getOpposite(){
            switch (this) {
                case X:
                    return O;
                case O:
                    return X;
                default:
                    return EMPTY;
            }
        }
    }

