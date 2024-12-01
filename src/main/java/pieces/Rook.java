package pieces;

import board.ChessBoard;

public class Rook extends ChessPiece {
    public Rook(String color) {
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

        if (line == toLine || column == toColumn) {
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
        if (line == toLine) {
            int direction = (toColumn > column) ? 1 : -1;
            for (int i = column + direction; i != toColumn; i += direction) {
                if (chessBoard.board[line][i] != null) {
                    return true;
                }
            }
        } else if (column == toColumn) {
            int direction = (toLine > line) ? 1 : -1;
            for (int i = line + direction; i != toLine; i += direction) {
                if (chessBoard.board[i][column] != null) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean isPositionOnBoard(int line, int column) {
        return line >= 0 && line < 8 && column >= 0 && column < 8;
    }

    @Override
    public String getSymbol() {
        return "R";
    }
}
