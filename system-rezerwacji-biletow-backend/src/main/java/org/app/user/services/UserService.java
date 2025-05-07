package org.app.user.services;

import lombok.RequiredArgsConstructor;
import org.app.user.model.Credentials;
import org.app.user.model.LoggedUser;
import org.app.user.model.User;
import org.app.user.model.UserDto;
import org.app.user.repository.UserRepository;
import org.app.user.repository.UserRepositoryImpl;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserService() {
        this.userRepository = new UserRepositoryImpl();
    }

    public LoggedUser login(Credentials credentials){
        String email = credentials.getEmail();
        String password = credentials.getPassword();

        Optional<User> loggedUser = userRepository.findByEmailAndPassword(email,password);

        User foundUser = loggedUser.orElseThrow(() -> new RuntimeException("Nieprawidłowe dane"));
        return buildLoggedUserData(foundUser);
    }

    private LoggedUser buildLoggedUserData(User foundUser) {
        return LoggedUser.builder()
                .userId(foundUser.getUserId())
                .name(foundUser.getName())
                .email(foundUser.getEmail())
                .phoneNumber(foundUser.getPhoneNumber())
                .isLogged(true)
                .build();
    }

    public User register(UserDto userDto){
        User userToRegister = buildUser(userDto);
        return userRepository.save(userToRegister);
    }

    private User buildUser(UserDto userDto) {
        return User.builder()
                .name(userDto.getName())
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .phoneNumber(userDto.getPhoneNumber())
                .build();
    }

    public User findById(UUID userId) {
        return userRepository.findById(userId).orElseThrow(
                () -> new RuntimeException("Nie znaleziono użytkownika!")
        );
    }
}
