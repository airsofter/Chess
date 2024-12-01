package pieces;

import board.ChessBoard;

public class Queen extends ChessPiece {
    public Queen(String color) {
        super(color);
    }

    @Override
    public String getColor() {
        return this.color;
    }

    @Override
    public String getSymbol() {
        return "Q";
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

        if (column == toColumn) {
            return !isPathBlocked(chessBoard, line, column, toLine, toColumn, false);
        }

        if (line == toLine) {
            return !isPathBlocked(chessBoard, line, column, toLine, toColumn, true);
        }

        if (diffLine == diffColumn) {
            return !isPathBlockedOnDiagonal(chessBoard, line, column, toLine, toColumn);
        }

        return false;
    }

    private boolean isPositionOnBoard(int line, int column) {
        return line >= 0 && line < 8 && column >= 0 && column < 8;
    }

    private boolean isPathBlocked(
            ChessBoard chessBoard, int line, int column, int toLine, int toColumn, boolean isHorizontal
    ) {
        if (isHorizontal) {
            int start = Math.min(column, toColumn) + 1;
            int end = Math.max(column, toColumn);
            for (int i = start; i < end; i++) {
                if (chessBoard.board[line][i] != null) {
                    return true;
                }
            }
        } else {
            int start = Math.min(line, toLine) + 1;
            int end = Math.max(line, toLine);
            for (int i = start; i < end; i++) {
                if (chessBoard.board[i][column] != null) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isPathBlockedOnDiagonal(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        int diffLine = toLine - line;
        int diffColumn = toColumn - column;
        int stepLine = (diffLine > 0) ? 1 : -1;
        int stepColumn = (diffColumn > 0) ? 1 : -1;

        int i = line + stepLine;
        int j = column + stepColumn;
        while (i != toLine && j != toColumn) {
            if (chessBoard.board[i][j] != null) {
                return true;
            }
            i += stepLine;
            j += stepColumn;
        }
        return false;
    }
}
