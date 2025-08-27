package com.hayden.record.service;

import com.hayden.record.domain.User;
import com.hayden.record.repository.MemoryUserRepository;
import com.hayden.record.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * 회원가입
     */
    public Long join(User user) {
        validateDuplicateUser(user);
        userRepository.save(user);
        return user.getUserId();
    }

    /**
     * 이메일 중복 확인
     */
    public void validateDuplicateUser(User user) {
        if(userRepository.existsByEmail(user.getUserEmail())){
            throw new IllegalStateException("이미 존재하는 이메일입니다.");
        }
    }

    /**
     * 전체 회원 조회
     */
    public List<User> findUsers() {
        return userRepository.findAll();
    }

    /**
     * Id로 회원 조회
     */
    public Optional<User> findOne(Long userId) {
        return userRepository.findById(userId);
    }

}
