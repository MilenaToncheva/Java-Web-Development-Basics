package app.service;

import app.domain.entities.User;
import app.domain.models.service.UserServiceModel;
import app.repository.UserRepository;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Inject
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void saveUser(UserServiceModel userServiceModel) {
        User user = this.modelMapper.map(userServiceModel, User.class);
        this.userRepository.save(user);
    }

    @Override
    public UserServiceModel findById(String id) {
        return  this.modelMapper.map(this.userRepository.findById(id), UserServiceModel.class);
    }

    @Override
    public List<UserServiceModel> findAllUsers() {
        return Arrays.stream(this.modelMapper.map(this.userRepository.findAll(), UserServiceModel[].class))
                .collect(Collectors.toList());
    }

    @Override
    public UserServiceModel findByUsernameAndPassword(String username, String password) {
        try{
        return this.modelMapper.map(this.userRepository.findByUsernameAndPassword(username, password), UserServiceModel.class);
    }catch(Exception e){
            return null;
        }
    }

    @Override
    public void update(UserServiceModel userServiceModel) {
        this.userRepository.update(this.modelMapper.map(userServiceModel,User.class));
    }

    @Override
    public void delete(String id) {
        this.userRepository.delete(id);
    }
}
