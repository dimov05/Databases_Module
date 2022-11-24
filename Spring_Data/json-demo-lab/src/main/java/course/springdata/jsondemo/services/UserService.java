package course.springdata.jsondemo.services;

import course.springdata.jsondemo.entities.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    User getUserById(Long id);

    User addUser(User user);

    User updateUser(User user);

    User deleteUser(User user);

    long getUsersCount();
}
