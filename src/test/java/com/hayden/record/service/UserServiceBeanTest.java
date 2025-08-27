package com.hayden.record.service;

import com.hayden.record.domain.User;
import com.hayden.record.repository.MemoryUserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class UserServiceBeanTest {

    @Autowired UserService userService;
    @Autowired MemoryUserRepository userRepository;

    @BeforeEach
    public void beforeEach() {
        userRepository = new MemoryUserRepository();
        userService = new UserService(userRepository);
    }

    @AfterEach
    public void afterEach() {
        userRepository.clearStore();
    }

    @Test
    void 회원가입() {
        // given
        User user = new User();
        user.setUserEmail("villians@heroes.com");
        user.setUserPassword("1234");
        user.setUserCategory("MOVIE");
        user.setNickname("테스트1");

        // when
        Long saveId = userService.join(user);

        // then
        User fineUser = userService.findOne(saveId).get();
        assertThat(user.getUserEmail()).isEqualTo(fineUser.getUserEmail());
    }

    @Test
    void 이메일_중복_예외() {
        // given
        User user1 = new User();
        user1.setUserEmail("villians@heroes.com");
        user1.setUserPassword("1234");
        user1.setUserCategory("MOVIE");
        user1.setNickname("테스트1");

        User user2 = new User();
        user2.setUserEmail("villians@heroes.com");
        user2.setUserPassword("4567");
        user2.setUserCategory("BOOK");
        user2.setNickname("테스트2");

        // when
        userService.join(user1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> userService.join(user2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 이메일입니다.");

        // then
    }

    @Test
    void findUsers() {
        // given
        // when
        // then
    }

    @Test
    void findOne() {
        // given
        // when
        // then
    }

}
