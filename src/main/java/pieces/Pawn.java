package pieces;

import board.ChessBoard;

public class Pawn extends ChessPiece {

    public Pawn(String color) {
        super(color);
    }

    @Override
    public String getColor() {
        return this.color;
    }

    @Override
    public String getSymbol() {
        return "P";
    }

    private boolean isPositionOnBoard(int line, int column) {
        return line >= 0 && line < 8 && column >= 0 && column < 8;
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        if (!isPositionOnBoard(line, column) || !isPositionOnBoard(toLine, toColumn)) {
            return false;
        }
        if (line == toLine && column == toColumn) {
            return false;
        }

        int direction = this.color.equals("White") ? 1 : -1;
        ChessPiece targetPiece = chessBoard.board[toLine][toColumn];

        if (column == toColumn && targetPiece == null) {
            if (toLine - line == direction) {
                return true;
            }
            if ((this.color.equals("White") && line == 1 || this.color.equals("Black") && line == 6)
                    && toLine - line == 2 * direction
                    && chessBoard.board[line + direction][column] == null) {
                return true;
            }
        }

        if (Math.abs(column - toColumn) == 1 && toLine - line == direction && targetPiece != null
                && !targetPiece.getColor().equals(this.color)) {
            return true;
        }

        return false;
    }
}
