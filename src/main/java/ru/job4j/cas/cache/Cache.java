package ru.job4j.cas.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiFunction;

public class Cache {
    private final Map<Integer, Base> memory = new ConcurrentHashMap<>();

    public boolean add(Base model) {
        return memory.putIfAbsent(model.getId(), model) == null;
    }

    public boolean update(Base model) {
        BiFunction<Integer, Base, Base> updateFunction = (id, base) -> {
            Base stored = memory.get(model.getId());
            if (stored.getVersion() != model.getVersion()) {
                throw new OptimisticException("Versions are not equal");
            }
            Base newModel = new Base(id, model.getVersion() + 1);
            newModel.setName(model.getName());
            return newModel;
        };

        return memory.computeIfPresent(model.getId(), updateFunction) != null;
    }

    public void delete(Base model) {
        memory.remove(model.getId());
    }

    public Base getById(int id) {
        return memory.get(id);
    }
}
