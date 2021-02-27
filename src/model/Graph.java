package model;

import java.util.Collection;

public interface Graph<Type> {
    void add(Type vertex);

    void add(Type source, Type destination);

    void remove(Type vertex);

    void remove(Type source, Type destination);

    Collection<Type> getVertices();
}
