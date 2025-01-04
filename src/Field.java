public class Field {

    String color;
    String value;
    boolean used;
    Position pos;

    public Field(String color, String value, boolean used, Position pos) {
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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
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
        return getColor() + "," + getValue() + "," + isUsed() + "," + getPos().getX() + "," + getPos().getY();
    }

}
