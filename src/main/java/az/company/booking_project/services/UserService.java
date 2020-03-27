package az.company.booking_project.services;


import az.company.booking_project.dao.UserDao;
import az.company.booking_project.entities.User;

import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class UserService {
    private UserDao userDao =new UserDao();



    public Optional<User> getUser(User user) {
        return userDao.get(user);
    }

    public boolean createNewUser(String username, String password) {
        boolean userNameExists = userNameExists(username);
        if (!userNameExists) {
            userDao.create(new User(username, password));
        }
        return !userNameExists;
    }

    private boolean userNameExists(String username) {
        try {
             userDao.getAll().stream()
                    .filter(new Predicate<User>() {
                        @Override
                        public boolean test(User user) {
                            return user.getUsername().equals(username);
                        }
                    }).collect(Collectors.toList()).get(0);
            return true;
        } catch (IndexOutOfBoundsException e) {
            return false;
        }

    }

}
