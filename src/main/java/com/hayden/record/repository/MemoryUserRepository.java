package com.hayden.record.repository;

import com.hayden.record.domain.User;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

public class MemoryUserRepository implements UserRepository{

    private static final Map<Long, User> store = new HashMap<>();
    private static final AtomicLong sequence = new AtomicLong(0);

    @Override
    public User save(User user) {
        Long id = sequence.incrementAndGet();
        user.setUserId(id);
        store.put(id, user);
        return user;
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<User> findByName(String name) {
        return store.values().stream()
                .filter(user -> user.getNickname().equals(name))
                .findAny();
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public boolean existsByEmail(String email) {
        return store.values().stream()
                .anyMatch(user -> user.getUserEmail().equals(email));
    }

    // 테스트에서 사용
    public void clearStore() {
        store.clear();
    }
}
