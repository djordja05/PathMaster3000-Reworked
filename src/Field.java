import java.awt.*;

public class Field {

    Color color;
    String value;
    boolean used;
    Position pos;

    public Field(Color color, String value, boolean used, Position pos) {
        this.color = color;
        this.value = value;
        this.used = used;
        this.pos = pos;
    }

    public Field(Field field) {
        this.color = field.color;
        this.value = field.value;
        this.used = field.used;
        this.pos = field.pos;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public Position getPos() {
        return pos;
    }

    public void setPos(Position pos) {
        this.pos = pos;
    }

    public String toString() {
        return getColor().getRed() + ";" + getColor().getGreen() + ";" + getColor().getBlue() + "," + getValue() + "," + isUsed() + "," + getPos().getX() + "," + getPos().getY();
    }

}
