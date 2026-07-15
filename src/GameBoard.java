class GameBoard {
    private final String[][] board;
    private final int size;

    GameBoard(int size) {
        this.size = size;
        board = new String[size][size];

        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                board[y][x] = VisualKeys.EMPTY.getImage();
            }
        }
    }

    int getSize() {
        return size;
    }

    String getCell(int x, int y) {
        return board[y][x];
    }

    boolean isInside(int x, int y) {
        return x >= 0 && x < size && y >= 0 && y < size;
    }

    void setCell(int x, int y, String image) {
        board[y][x] = image;
    }

    boolean isCellEmpty(int x, int y) {
        return getCell(x, y).equals(VisualKeys.EMPTY.getImage());
    }

    void clearCell(int x, int y) {
        setCell(x, y, VisualKeys.EMPTY.getImage());
    }

    void output(int live) {
        String leftBlock = "| ";
        String rightBlock = "|";
        String wall = "+ —— + —— + —— + —— + —— +";

        for (String[] raw : board) {
            System.out.println(wall);
            for (String col : raw) {
                System.out.print(leftBlock + col + " ");
            }
            System.out.println(rightBlock);
        }
        System.out.println(wall);
        System.out.println("Количество жизней:\t" + live + "\n");
    }
}
