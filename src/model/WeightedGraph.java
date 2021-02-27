package model;

import java.util.Map;

import java.util.*;

public class WeightedGraph<Type> implements Graph<Type> {
    private final Map<Type, Map<Type, Integer>> map = new HashMap<>();
    private final boolean isDirectional;

    public WeightedGraph(boolean isDirectional) {
        this.isDirectional = isDirectional;
    }

    public void add(Type vertex) {
        map.put(vertex, new HashMap<>());
    }

    public void add(Type source, Type destination) {

        if (!map.containsKey(source))
            add(source);

        if (!map.containsKey(destination))
            add(destination);

        map.get(source).put(destination, null);
        if (isDirectional) {
            map.get(destination).put(source, null);
        }
    }

    public void remove(Type vertex) {
        for (Type neighbor : map.get(vertex).keySet()) {
            map.get(neighbor).remove(vertex);
        }
        map.remove(vertex);
    }

    public void remove(Type source, Type destination) {
        map.get(source).remove(destination);
        if (isDirectional) {
            map.get(destination).remove(source);
        }
    }

    public int getVertexCount() {
        return map.keySet().size();
    }

    public int getEdgesCount() {
        int edgesCount = 0;
        for (Type vertex : map.keySet()) {
            edgesCount += map.get(vertex).size();
        }
        if (isDirectional) edgesCount = edgesCount / 2;
        return edgesCount;
    }

    public boolean hasVertex(Type vertex) {
        return map.containsKey(vertex);
    }

    public boolean hasEdge(Type source, Type destination) {
        return map.get(source).containsKey(destination);
    }

    public Set<Type> getVertices() {
        return map.keySet();
    }

    public void setWeight(Type source, Type destination, int weight) {
        map.get(source).put(destination, weight);
        if (isDirectional) {
            map.get(destination).put(source, weight);
        }
    }
}

