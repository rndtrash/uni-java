import java.util.Arrays;
import java.util.Random;

public class TicTacToe extends Thread {
    public class TTTPlayer extends Thread {
        final int side;
        final TicTacToe instance;

        public TTTPlayer(TicTacToe instance, int side) {
            this.side = side;
            this.instance = instance;
        }

        @Override
        public void run() {
            super.run();

            while (true) {
                try {
                    synchronized (instance) {
                        instance.notifyAll();
                    }
                    synchronized (this) {
                        wait();
                    }
                } catch (InterruptedException e) {
                    return;
                }

                synchronized (instance) {
                    int availableCells = instance.countAvailableCells();
                    // Если не осталось свободных клеток, или следующий ход
                    if (availableCells == 0)
                        return;

                    int randomCell = new Random().nextInt(availableCells + 1);
                    int x = 0, y = 0;
                    while (randomCell > 0) {
                        if (field[y][x] == TTT_NONE) {
                            randomCell--;
                            if (randomCell == 0) {
                                synchronized (instance.field[y]) {
                                    instance.field[y][x] = side;
                                }
                                break;
                            }
                        }
                        x++;
                        if (x == field[y].length) {
                            x = 0;
                            y++;
                        }
                    }
                }
            }
        }
    }

    public static final int TTT_NONE = 0;
    public static final int TTT_CROSSES = 1;
    public static final int TTT_NOUGHTS = 2;

    public static final int TTT_IN_PROGRESS = 0;
    public static final int TTT_CROSSES_WIN = 1;
    public static final int TTT_NOUGHTS_WIN = 2;
    public static final int TTT_TIE = 3;

    final Thread[] players;

    int[][] field;
    int turn;
    int condition;

    public TicTacToe() {
        field = new int[3][3];
        turn = TTT_CROSSES;
        condition = TTT_IN_PROGRESS;

        players = new Thread[]{
                new TTTPlayer(this, TTT_CROSSES),
                new TTTPlayer(this, TTT_NOUGHTS)
        };
    }

    public boolean canMakeTurn() {
        return Arrays.stream(field).anyMatch((i) -> Arrays.stream(i).anyMatch((j) -> j == TTT_NONE));
    }

    public int countAvailableCells() {
        int c = 0;

        if (condition == TTT_IN_PROGRESS)
            for (int[] row : field) {
                for (int column : row) {
                    if (column == TTT_NONE)
                        c++;
                }
            }

        return c;
    }

    public void declareWin(int side) {
        if (side == TTT_CROSSES)
            condition = TTT_CROSSES_WIN;
        else if (side == TTT_NOUGHTS)
            condition = TTT_NOUGHTS_WIN;
    }

    public void checkGameEnd() {
        int topLeft = field[0][0];
        if (topLeft != TTT_NONE) {
            boolean diagLeftRight = true;
            for (int i = 1; i < field.length; i++) {
                if (topLeft != field[i][i]) {
                    diagLeftRight = false;
                    break;
                }
            }
            if (diagLeftRight) {
                declareWin(topLeft);
                return;
            }
        }

        int topRight = field[0][field[0].length - 1];
        if (topRight != TTT_NONE) {
            boolean diagRightLeft = true;
            for (int i = 1; i < field.length; i++) {
                if (topLeft != field[i][field[0].length - 1 - i]) {
                    diagRightLeft = false;
                    break;
                }
            }
            if (diagRightLeft) {
                declareWin(topLeft);
                return;
            }
        }

        // Горизонтальные линии
        for (int[] row : field) {
            int first = row[0];
            boolean won = true;
            for (int i = 1; i < row.length; i++) {
                if (row[i] != first) {
                    won = false;
                    break;
                }
            }
            if (won) {
                declareWin(first);
                return;
            }
        }

        // Вертикальные линии
        for (int i = 0; i < field.length; i++) {
            int first = field[0][i];
            boolean won = true;
            for (int j = 1; j < field.length; j++) {
                if (first != field[j][i]) {
                    won = false;
                    break;
                }
            }
            if (won) {
                declareWin(first);
                return;
            }
        }

        if (countAvailableCells() == 0)
            condition = TTT_TIE;
    }

    private void printCell(int y, int x) {
        switch (field[y][x]) {
            case TTT_NONE -> System.out.print(' ');
            case TTT_CROSSES -> System.out.print('X');
            case TTT_NOUGHTS -> System.out.print('O');
            default -> throw new RuntimeException("Unknown cell: " + field[y][x]);
        }
    }

    public void printField() {
        for (int i = 0; i < field.length; i++) {
            int[] row = field[i];
            for (int j = 0; j < row.length; j++) {
                printCell(i, j);
                if (j < row.length - 1) {
                    System.out.print('|');
                }
            }
            System.out.println();
            if (i < field.length - 1) {
                System.out.println("-----");
            }
        }
        System.out.println();
    }

    @Override
    public void run() {
        super.run();

        for (Thread t : players) {
            t.start();
        }

        while (condition == TTT_IN_PROGRESS) {
            for (int i = 0; condition == TTT_IN_PROGRESS && i < players.length; i++) {
                synchronized (players[i]) {
                    players[i].notify();
                }
                try {
                    synchronized (this) {
                        wait();
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                printField();
                checkGameEnd();
            }
        }

        switch (condition) {
            case TTT_TIE:
                System.out.println("Tie!");
                break;

            case TTT_CROSSES_WIN:
                System.out.println("Crosses win!");
                break;

            case TTT_NOUGHTS_WIN:
                System.out.println("Noughts win!");
                break;

            default:
                throw new RuntimeException("Unknown condition: " + condition);
        }

        try {
            for (Thread t : players) {
                if (t.isAlive())
                    t.interrupt();

                t.join();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        TicTacToe instance = new TicTacToe();
        instance.start();
    }
}
