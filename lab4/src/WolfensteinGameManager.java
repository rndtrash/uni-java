public class WolfensteinGameManager {
    public final class TraceResult {
        public final Vector2 StartPosition;
        public final Vector2 EndPosition;
        public final boolean IsHit;

        public TraceResult(Vector2 startPosition, Vector2 endPosition, boolean isHit) {
            StartPosition = startPosition;
            EndPosition = endPosition;
            IsHit = isHit;
        }

        public Vector2 Direction() {
            return Vector2.direction(StartPosition, EndPosition);
        }

        public double Distance() {
            return Vector2.distance(StartPosition, EndPosition);
        }

        public Vector2I CellPosition() {
            return Vector2I.toGrid(Vector2.add(EndPosition, Direction()), CELL_SIZE);
        }
    }

    public static final double DELTA_TIME = 1 / 10d;

    public static final int WORLD_WIDTH = 10;
    public static final int WORLD_HEIGHT = 10;
    public static final int CELL_SIZE = 10;

    public static final int CELL_EMPTY = 0;
    public static final int CELL_WALL = 1;

    public static final int PLAYER_SPEED = 10;
    public static final double PLAYER_ROTATION_SPEED = Math.toRadians(60);

    public static final double PLAYER_FIELD_OF_VIEW = Math.toRadians(90);
    public static final double PLAYER_VIEW_DISTANCE = CELL_SIZE * 5;

    public int[][] World = new int[WORLD_WIDTH][WORLD_HEIGHT];

    public Vector2 PlayerPosition = new Vector2((double) WORLD_WIDTH / 2 * CELL_SIZE, (double) WORLD_HEIGHT / 2 * CELL_SIZE);
    // Угол в радианах
    public double PlayerDirection = 0;

    public WolfensteinGameManager() {
        drawBox(0, 0, WORLD_WIDTH - 1, WORLD_HEIGHT - 1);
        drawBox(3, 3, WORLD_WIDTH - 4, WORLD_HEIGHT - 4);

        World[3][WORLD_HEIGHT / 2] = World[WORLD_WIDTH - 4][WORLD_HEIGHT / 2] = CELL_EMPTY;
    }

    private void drawBox(int x0, int y0, int x1, int y1) {
        for (int i = x0; i <= x1; i++) {
            World[i][y0] = World[i][y1] = CELL_WALL;
        }

        for (int j = y0 + 1; j <= y1 - 1; j++) {
            World[x0][j] = World[x1][j] = CELL_WALL;
        }
    }

    public int[] getColumns(int resolution) {
        int[] columns = new int[resolution];

        for (int i = 0; i < resolution; i++) {
            double angle = PlayerDirection - PLAYER_FIELD_OF_VIEW / 2 + PLAYER_FIELD_OF_VIEW * ((double) i / (resolution - 1));
            TraceResult traceResult = trace(PlayerPosition, Vector2.fromAngle(angle), PLAYER_VIEW_DISTANCE);
            columns[i] = (int) (PLAYER_VIEW_DISTANCE - traceResult.Distance());
        }

        return columns;
    }

    public TraceResult trace(Vector2 from, Vector2 to) {
        return trace(from, Vector2.direction(from, to), Vector2.distance(from, to));
    }

    public TraceResult trace(Vector2 from, Vector2 direction, double distance) {
        // TODO: naive method
//        int dirX = direction.X > 0 ? 1 : -1;
//        int dirY = direction.Y > 0 ? 1 : -1;
//
//        Vector2 endPosition = from;
//        boolean isHit = false;
//        while (!isHit) {
//
//        }
//
//        return new TraceResult(from, endPosition, isHit);

        double step = (double) CELL_SIZE / 2;
        double dist = 0;
        boolean isHit = false;
        for (; !isHit && dist < distance; dist += step) {
            Vector2I position = Vector2I.toGrid(Vector2.add(from, Vector2.multiply(direction, dist)), CELL_SIZE);
            if (position.X < 0 || position.X >= WORLD_WIDTH || position.Y < 0 || position.Y >= WORLD_HEIGHT)
                break;

            if (World[position.X][position.Y] != CELL_EMPTY) {
                isHit = true;
            }
        }

        return new TraceResult(from, Vector2.add(from, Vector2.multiply(direction, dist)), isHit);
    }

    public Vector2 GetDirectionVector() {
        return new Vector2(Math.cos(PlayerDirection), Math.sin(PlayerDirection));
    }

    public void MoveForwards() {
        PlayerPosition = Vector2.add(PlayerPosition, Vector2.multiply(GetDirectionVector(), PLAYER_SPEED * DELTA_TIME));
    }

    public void MoveBackwards() {
        PlayerPosition = Vector2.add(PlayerPosition, Vector2.multiply(GetDirectionVector(), -PLAYER_SPEED * DELTA_TIME));
    }

    public void LookLeft() {
        PlayerDirection += PLAYER_ROTATION_SPEED * DELTA_TIME;
    }

    public void LookRight() {
        PlayerDirection -= PLAYER_ROTATION_SPEED * DELTA_TIME;
    }
}
