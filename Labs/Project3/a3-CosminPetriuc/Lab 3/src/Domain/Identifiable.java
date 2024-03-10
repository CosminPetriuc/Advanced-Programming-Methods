package Domain;

import java.io.Serializable;

public interface Identifiable<T> extends Serializable{
    T getID();
    void setID(T id);

}
