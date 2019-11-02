package app.service;

import app.domain.models.service.UserServiceModel;

import java.util.List;

public interface UserService {
    void saveUser(UserServiceModel userServiceModel);

    UserServiceModel findById(String id);

    List<UserServiceModel> findAllUsers();

    UserServiceModel findByUsernameAndPassword(String username, String password);

    void update(UserServiceModel userServiceModel);

void delete(String id);
}
