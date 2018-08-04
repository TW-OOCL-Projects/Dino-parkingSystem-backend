package com.oocl.dino_parking_system.services;

import com.oocl.dino_parking_system.entities.User;
import com.oocl.dino_parking_system.repositorys.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;

import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.AdditionalMatchers.not;
import static org.mockito.ArgumentMatchers.isNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    UserRepository userRepository;


    @Test
    public void should_return_the_right_user_with_the_username_is_haha(){
        //given
        User haha = mock(User.class);
        UserService service = new UserService(userRepository);
        when(userRepository.findByUsername("haha")).thenReturn(haha);
        //when
        UserDetails me = service.loadUserByUsername("haha");
        //then
        assertNotNull(me);
    }
    @Test
    void loadUserByUsername() {
    }

    @Test
    void validate() {
    }

    @Test
    void createUser() {
    }

    @Test
    void getAllUser() {
    }

    @Test
    void changeUserStatus() {
    }

    @Test
    void getUserById() {
    }

    @Test
    void updateUser() {
    }

    @Test
    void save() {
    }

    @Test
    void delete() {
    }

    @Test
    void edit() {
    }

    @Test
    void exits() {
    }
}