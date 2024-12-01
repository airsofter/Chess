package pieces;

import board.ChessBoard;


public abstract class ChessPiece {
    public String color;
    public boolean check = true;

    public ChessPiece(String color) {
        if (!color.equals("White") && !color.equals("Black")) {
            throw new IllegalArgumentException("Цвет должен быть 'Black' или 'White'");
        }
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public abstract boolean canMoveToPosition(
            ChessBoard chessBoard, int line, int column, int toLine, int toColumn
    );

    public abstract String getSymbol();
}
