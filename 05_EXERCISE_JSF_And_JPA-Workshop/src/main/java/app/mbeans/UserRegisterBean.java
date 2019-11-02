package app.web.mbeans;

import app.domain.models.binding.UserRegisterBindingModel;
import app.domain.models.service.UserServiceModel;
import app.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.modelmapper.ModelMapper;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class UserRegisterBean extends BaseBean{
    private  UserRegisterBindingModel userRegisterBindingModel;

    private UserService userService;
    private ModelMapper modelMapper;

    public UserRegisterBean() {
    }
@Inject
    public UserRegisterBean(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }
    @PostConstruct
    public void init(){
        this.userRegisterBindingModel=new UserRegisterBindingModel();
    }

    public void register(){
        if (!this.userRegisterBindingModel.getPassword().equals(this.userRegisterBindingModel.getConfirmPassword())) {
            this.redirect("/register");
            return;
        }
        this.userRegisterBindingModel.setPassword(DigestUtils.sha256Hex(this.userRegisterBindingModel.getPassword()));
        this.userService.saveUser(this.modelMapper.map(userRegisterBindingModel, UserServiceModel.class));
        this.redirect("/login");
    }

    public UserRegisterBindingModel getUserRegisterBindingModel() {
        return userRegisterBindingModel;
    }

    public void setUserRegisterBindingModel(UserRegisterBindingModel userRegisterBindingModel) {
        this.userRegisterBindingModel = userRegisterBindingModel;
    }
}
