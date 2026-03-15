package app.models;
import app.models.validators.Validatable;

/**
 * Класс, описывающий местоположение оператора (координаты X, Y, Z).
 */
public class Location implements Validatable {
    private Float x;
    private int y;
    private int z;

    public Location(Float x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Float getX() { return x; }
    public int getY() { return y; }
    public int getZ() { return z; }

    @Override
    public boolean validate() {
        if (x == null) return false;
        return true;
    }

    @Override
    public String toString() {
        return "Location{x=" + x + ", y=" + y + ", z=" + z + "}";
    }
}