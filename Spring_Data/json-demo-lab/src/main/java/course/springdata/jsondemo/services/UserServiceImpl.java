package course.springdata.jsondemo.services;

import course.springdata.jsondemo.entities.User;
import course.springdata.jsondemo.exceptions.NonExistingEntityException;
import course.springdata.jsondemo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepo;

    @Autowired
    public UserServiceImpl(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public User getUserById(Long id) {
        return userRepo.findById(id).orElseThrow(() ->
                new NonExistingEntityException(String.format("User with ID=%s does not exist.",
                        id)));
    }

    @Transactional
    @Override
    public User addUser(User user) {
        user.setId(null);
        return userRepo.save(user);
    }

    @Transactional
    @Override
    public User updateUser(User user) {
        getUserById(user.getId());
        return userRepo.save(user);
    }

    @Transactional
    @Override
    public User deleteUser(User user) {
        User removed = getUserById(user.getId());
        userRepo.deleteById(user.getId());
        return removed;
    }

    @Override
    public long getUsersCount() {
        return userRepo.count();
    }
}
