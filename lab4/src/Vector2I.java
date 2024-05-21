public final class Vector2I {
    public int X;
    public int Y;

    public Vector2I(int x, int y) {
        X = x;
        Y = y;
    }

    public static Vector2I add(Vector2I a, Vector2I b) {
        return new Vector2I(a.X + b.X, a.Y + b.Y);
    }

    public static Vector2I multiply(Vector2I a, int c) {
        return new Vector2I(a.X * c, a.Y * c);
    }

    public static Vector2I toGrid(Vector2 a, float gridSize) {
        return new Vector2I((int) Math.floor(a.X / gridSize), (int) Math.floor(a.Y / gridSize));
    }
}
