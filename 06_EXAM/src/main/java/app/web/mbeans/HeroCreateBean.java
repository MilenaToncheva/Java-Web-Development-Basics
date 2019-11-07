package app.web.mbeans;

import app.domain.entities.Class;
import app.domain.models.binding.HeroCreateBindingModel;
import app.domain.models.service.HeroServiceModel;
import app.service.HeroService;
import org.modelmapper.ModelMapper;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class HeroCreateBean extends BaseBean {
    private HeroCreateBindingModel heroCreateBindingModel;

    private HeroService heroService;
    private ModelMapper modelMapper;

    public HeroCreateBean() {
    }
@Inject
    public HeroCreateBean(ModelMapper modelMapper,HeroService heroService) {
        this.modelMapper = modelMapper;
        this.heroService=heroService;
    }
@PostConstruct
    public void init(){
        this.heroCreateBindingModel=new HeroCreateBindingModel();
}
public void create(){
        HeroServiceModel heroServiceModel=this.modelMapper.map(heroCreateBindingModel,HeroServiceModel.class);
        heroServiceModel.setAclass(Class.valueOf(heroCreateBindingModel.getAclass().toUpperCase()));

        this.heroService.createHero(heroServiceModel);
        this.redirect("/home");
}

    public HeroCreateBindingModel getHeroCreateBindingModel() {
        return heroCreateBindingModel;
    }

    public void setHeroCreateBindingModel(HeroCreateBindingModel heroCreateBindingModel) {
        this.heroCreateBindingModel = heroCreateBindingModel;
    }
}
