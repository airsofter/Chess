package pieces;

import board.ChessBoard;

public class Bishop extends ChessPiece {
    public Bishop(String color) {
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

        if (Math.abs(line - toLine) == Math.abs(column - toColumn)) {
            if (!isPathBlocked(chessBoard, line, column, toLine, toColumn)) {
                ChessPiece targetPiece = chessBoard.board[toLine][toColumn];

                if (targetPiece == null || !targetPiece.getColor().equals(this.color)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean isPathBlocked(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        int directionX = (toColumn > column) ? 1 : -1;
        int directionY = (toLine > line) ? 1 : -1;

        int x = column + directionX;
        int y = line + directionY;

        while (x != toColumn && y != toLine) {
            if (chessBoard.board[y][x] != null) {
                return true;
            }
            x += directionX;
            y += directionY;
        }

        return false;
    }

    private boolean isPositionOnBoard(int line, int column) {
        return line >= 0 && line < 8 && column >= 0 && column < 8;
    }

    @Override
    public String getSymbol() {
        return "B";
    }
}
