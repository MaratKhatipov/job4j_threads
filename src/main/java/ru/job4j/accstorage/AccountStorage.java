package ru.job4j.accstorage;

import net.jcip.annotations.GuardedBy;

import java.util.HashMap;
import java.util.Optional;

public class AccountStorage {

    @GuardedBy("accounts")
    private final HashMap<Integer, Account> accounts = new HashMap<>();

    public synchronized boolean add(Account account) {
        return accounts.putIfAbsent(account.getId(), account) == null;
    }

    public synchronized boolean update(Account account) {
        return accounts.replace(account.getId(), account) != null;
    }

    public synchronized boolean delete(int id) {
        return accounts.remove(id) != null;
    }

    public synchronized Optional<Account> getById(int id) {
        return Optional.ofNullable(accounts.get(id));
    }

    public synchronized boolean transfer(int fromId, int toId, int amount) {
        boolean result = false;
        Optional<Account> fromAcc = getById(fromId);
        Optional<Account> toAcc = getById(toId);
        boolean condition = fromAcc.isPresent() && toAcc.isPresent() && amount <= fromAcc.get().getAmount();
        if (condition) {
            fromAcc.get().setAmount(fromAcc.get().getAmount() - amount);
            toAcc.get().setAmount(toAcc.get().getAmount() + amount);
            result = true;
        }
        return result;
    }
}
