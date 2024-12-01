package pieces;

import board.ChessBoard;

public class King extends ChessPiece {

    public King(String color) {
        super(color);
    }

    @Override
    public String getColor() {
        return this.color;
    }

    @Override
    public String getSymbol() {
        return "K";
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        if (!isPositionOnBoard(line, column) || !isPositionOnBoard(toLine, toColumn)) {
            return false;
        }

        if (line == toLine && column == toColumn) {
            return false;
        }

        int diffLine = Math.abs(line - toLine);
        int diffColumn = Math.abs(column - toColumn);
        if (diffLine > 1 || diffColumn > 1) {
            return false;
        }

        if (isUnderAttack(chessBoard, toLine, toColumn)) {
            return false;
        }

        ChessPiece targetPiece = chessBoard.board[toLine][toColumn];

        if (targetPiece == null || !targetPiece.getColor().equals(this.color)) {
            return true;
        }

        return false;
    }

    private boolean isPositionOnBoard(int line, int column) {
        return line >= 0 && line < 8 && column >= 0 && column < 8;
    }

    public boolean isUnderAttack(ChessBoard chessBoard, int line, int column) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                ChessPiece piece = chessBoard.board[i][j];
                if (piece != null && !piece.getColor().equals(this.color)) {
                    if (piece.canMoveToPosition(chessBoard, i, j, line, column)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
