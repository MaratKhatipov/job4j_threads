package ru.job4j.cas.cache;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CacheTest {

    @Test
    void add() {
        Cache cache = new Cache();
        Base base1 = new Base(1, 10);
        base1.setName("Frodo");
        cache.add(base1);
        Assertions.assertThat(cache.getById(1).getName()).isEqualTo("Frodo");
    }

    @Test
    void update() {
        Cache cache = new Cache();
        Base base1 = new Base(1, 10);
        base1.setName("Frodo");
        cache.add(base1);
        Base updateBase1 = new Base(1, 10);
        updateBase1.setName("Frodo Baggins");
        cache.update(updateBase1);
        Assertions.assertThat(cache.getById(1).getName()).isEqualTo("Frodo Baggins");
        Assertions.assertThat(cache.getById(1).getVersion()).isEqualTo(base1.getVersion() + 1);

    }

    @Test
    void delete() {
        Cache cache = new Cache();
        Base base1 = new Base(1, 10);
        base1.setName("Frodo");
        cache.add(base1);
        cache.delete(base1);
        Assertions.assertThat(cache.getById(1)).isEqualTo(null);
    }
}