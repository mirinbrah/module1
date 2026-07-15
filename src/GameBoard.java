public class GameBoard {
    private final String[][] board;
    private final int size;

    GameBoard(int size) {
        this.size = size;
        board = new String[size][size];

        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                board[y][x] = "  ";
            }
        }
    }

    public int getSize() {
        return size;
    }

    public String getCell(int x, int y) {
        return board[y][x];
    }

    public void setCell(int x, int y, String image) {
        board[y][x] = image;
    }

    public boolean isCellEmpty(int x, int y) {
        return getCell(x, y).equals("  ");
    }

    public void clearCell(int x, int y) {
        setCell(x, y, "  ");
    }

    public void output(int live) {
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
