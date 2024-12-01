package pieces;

import board.ChessBoard;

public class Horse extends ChessPiece {

    public Horse(String color) {
        super(color);
    }

    @Override
    public String getColor() {
        return this.color;
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        if (!isPositionOnBoard(line, column) || !isPositionOnBoard(toLine, toColumn)) {
            return false;
        }

        if ((Math.abs(line - toLine) == 2 && Math.abs(column - toColumn) == 1)
                || (Math.abs(line - toLine) == 1 && Math.abs(column - toColumn) == 2)) {

            ChessPiece targetPiece = chessBoard.board[toLine][toColumn];

            if (targetPiece == null || !targetPiece.getColor().equals(this.color)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public String getSymbol() {
        return "H";
    }

    private boolean isPositionOnBoard(int line, int column) {
        return line >= 0 && line < 8 && column >= 0 && column < 8;
    }
}
