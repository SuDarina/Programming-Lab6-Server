package Collection;

import Exceptions.FieldException;

import java.io.Serializable;

public class Coordinates implements Serializable {
    private Integer x; //Значение поля должно быть больше -329, Поле не может быть null
    private float y; //Значение поля должно быть больше -805

    public void setX(Integer x) throws FieldException {
        this.x = x;
        if (x <= -329 || x == null)
                throw new FieldException("Exception in field : coordinates (x)");

    }

    public void setY(float y) throws FieldException {
        this.y = y;
        if (y <= -805)
                throw new FieldException("Exception in field : coordinates (y)");

    }

    @Override
    public String toString() {
        return "[x:" + x + ", y:" + y+"]";
    }
}
