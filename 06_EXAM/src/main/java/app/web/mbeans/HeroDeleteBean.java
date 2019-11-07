package app.web.mbeans;

import app.domain.entities.BaseEntity;
import app.domain.models.service.HeroServiceModel;
import app.domain.models.view.HeroDeleteViewModel;
import app.service.HeroService;
import org.modelmapper.ModelMapper;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@Named
@RequestScoped
public class HeroDeleteBean extends BaseBean {
    private HeroDeleteViewModel heroDeleteViewModel;

    private HeroService heroService;
    private ModelMapper modelMapper;

    public HeroDeleteBean() {
    }
@Inject
    public HeroDeleteBean(HeroService heroService, ModelMapper modelMapper) {
        this.heroService = heroService;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    public void init(){
        HttpServletRequest req= (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String id=req.getParameter("id");
        HeroServiceModel heroServiceModel=this.heroService.findById(id);
        this.heroDeleteViewModel=this.modelMapper.map(heroServiceModel,HeroDeleteViewModel.class);
        this.heroDeleteViewModel.setAclass(heroServiceModel.getAclass().name());
    }
public void delete(){
    this.heroService.delete(this.heroDeleteViewModel.getId());
    this.redirect("/home");
}
    public HeroDeleteViewModel getHeroDeleteViewModel() {
        return heroDeleteViewModel;
    }

    public void setHeroDeleteViewModel(HeroDeleteViewModel heroDeleteViewModel) {
        this.heroDeleteViewModel = heroDeleteViewModel;
    }
}
