package app.web.mbeans;

import app.domain.models.binding.UserLoginBindingModel;
import app.domain.models.service.UserServiceModel;
import app.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.modelmapper.ModelMapper;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Map;

@Named
@RequestScoped
public class UserLoginBean extends BaseBean{
    private UserLoginBindingModel userLoginBindingModel;

    private UserService userService;
    private ModelMapper modelMapper;

    public UserLoginBean() {
    }
@Inject
    public UserLoginBean(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    public void init(){
        this.userLoginBindingModel=new UserLoginBindingModel();
    }
    public void login() {
        UserServiceModel userServiceModel =
                this.userService.
                        findByUsernameAndPassword(this.userLoginBindingModel.getUsername(),
                                DigestUtils.sha256Hex(this.userLoginBindingModel.getPassword()));
        if (userServiceModel == null) {
            this.redirect("/login");
            return;
        }
        Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        sessionMap.put("username", userServiceModel.getUsername());
        sessionMap.put("userId", userServiceModel.getId());
        this.redirect("/home");
    }
    public UserLoginBindingModel getUserLoginBindingModel() {
        return userLoginBindingModel;
    }

    public void setUserLoginBindingModel(UserLoginBindingModel userLoginBindingModel) {
        this.userLoginBindingModel = userLoginBindingModel;
    }
}
