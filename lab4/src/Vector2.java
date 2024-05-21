public final class Vector2 {
    public final double X;
    public final double Y;

    public Vector2(double x, double y) {
        X = x;
        Y = y;
    }

    public Vector2(Vector2I v) {
        X = v.X;
        Y = v.Y;
    }

    public double Length() {
        return Math.sqrt(X * X + Y * Y);
    }

    public static Vector2 add(Vector2 a, Vector2 b) {
        return new Vector2(a.X + b.X, a.Y + b.Y);
    }

    public static Vector2 subtract(Vector2 a, Vector2 b) {
        return new Vector2(a.X - b.X, a.Y - b.Y);
    }

    public static Vector2 multiply(Vector2 a, double c) {
        return new Vector2(a.X * c, a.Y * c);
    }

    public static Vector2 divide(Vector2 a, double c) {
        return new Vector2(a.X / c, a.Y / c);
    }

    public static Vector2 normal(Vector2 a) {
        return divide(a, a.Length());
    }

    public static double distance(Vector2 a, Vector2 b) {
        return subtract(a, b).Length();
    }

    public static Vector2 direction(Vector2 from, Vector2 to) {
        return normal(subtract(to, from));
    }

    public static double angle(Vector2 a) {
        return Math.atan2(a.Y, a.X);
    }

    public static Vector2 fromAngle(double angle) {
        return new Vector2(Math.cos(angle), Math.sin(angle));
    }
}
