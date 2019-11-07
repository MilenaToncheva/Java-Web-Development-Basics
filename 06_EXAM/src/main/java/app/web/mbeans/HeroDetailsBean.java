package app.web.mbeans;

import app.domain.models.service.HeroServiceModel;
import app.domain.models.view.HeroDetailsViewModel;
import app.service.HeroService;
import org.modelmapper.ModelMapper;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

@Named
@RequestScoped
public class HeroDetailsBean {
    private HeroDetailsViewModel heroDetailsViewModel;

    private HeroService heroService;
    private ModelMapper modelMapper;

    public HeroDetailsBean() {
    }
@Inject
    public HeroDetailsBean(HeroService heroService, ModelMapper modelMapper) {
        this.heroService = heroService;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    public void init(){
        HttpServletRequest req= (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String id=req.getParameter("id");
        HeroServiceModel heroServiceModel=this.heroService.findById(id);
        this.heroDetailsViewModel=this.modelMapper.map(heroServiceModel,HeroDetailsViewModel.class);
        this.heroDetailsViewModel.setAclass(heroServiceModel.getAclass().name());
    }

    public HeroDetailsViewModel getHeroDetailsViewModel() {
        return heroDetailsViewModel;
    }

    public void setHeroDetailsViewModel(HeroDetailsViewModel heroDetailsViewModel) {
        this.heroDetailsViewModel = heroDetailsViewModel;
    }
}
