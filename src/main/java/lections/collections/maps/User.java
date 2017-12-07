package lections.collections.maps;


import java.util.HashMap;
import java.util.Map;

/**
 * Created by admin on 18.07.2017.
 */
public class User {
    String name;

    public User(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return name != null ? name.equals(user.name) : user.name == null;
    }

    @Override
    public int hashCode() {
        return name != null ? name.length() : 0;
    }

    @Override
    public String toString() {
        return name;
    }
}
