package ru.job4j.accstorage;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AccountStorageTest {



    @Test
    void add() {
        AccountStorage storage = new AccountStorage();
        storage.add(new Account(1, 100));
        var firstAcc = storage.getById(1)
                .orElseThrow(() -> new IllegalStateException("not found acc by = 1"));
        assertThat(firstAcc.getAmount()).isEqualTo(100);
    }

    @Test
    void update() {
        AccountStorage storage = new AccountStorage();
        storage.add(new Account(1, 100));
        storage.update(new Account(1, 200));
        var firstAcc = storage.getById(1)
                .orElseThrow(() -> new IllegalStateException("not found acc by = 1"));
        assertThat(firstAcc.getAmount()).isEqualTo(200);
    }

    @Test
    void delete() {
        AccountStorage storage = new AccountStorage();
        storage.add(new Account(1, 100));
        storage.delete(1);
        assertThat(storage.getById(1)).isEmpty();
    }


    @Test
    void transfer() {
        AccountStorage storage = new AccountStorage();
        storage.add(new Account(1, 1000));
        storage.add(new Account(2, 500));
        storage.transfer(1, 2, 500);
        var firstAcc = storage.getById(1)
                .orElseThrow(() -> new IllegalStateException("not found acc by = 1"));
        var secondAcc = storage.getById(2)
                .orElseThrow(() -> new IllegalStateException("not found acc by = 2"));
        assertThat(firstAcc.getAmount()).isEqualTo(500);
        assertThat(secondAcc.getAmount()).isEqualTo(1000);
    }

    @Test
    void whenNotTransfer() {
        AccountStorage storage = new AccountStorage();
        storage.add(new Account(1, 500));
        storage.add(new Account(2, 100));
        storage.transfer(1, 2, 1000);
        var firstAcc = storage.getById(1)
                .orElseThrow(() -> new IllegalStateException("not found acc by = 1"));
        var secondAcc = storage.getById(2)
                .orElseThrow(() -> new IllegalStateException("not found acc by = 2"));
        assertThat(firstAcc.getAmount()).isEqualTo(500);
        assertThat(secondAcc.getAmount()).isEqualTo(100);
    }
}