package se.lexicon.springbootrest.service;

import se.lexicon.springbootrest.model.dto.UserDto;

import java.util.Map;

public interface UserService {
    UserDto register(UserDto dto);

    Map<String, Object> findByUsername(String username);

    void disableUserByUsername(String username);
}
