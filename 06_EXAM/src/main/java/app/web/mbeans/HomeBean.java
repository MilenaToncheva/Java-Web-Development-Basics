package app.web.mbeans;

import app.domain.models.service.HeroServiceModel;
import app.domain.models.view.HeroViewModel;
import app.service.HeroService;
import org.modelmapper.ModelMapper;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Named
@RequestScoped
public class HomeBean {
    private List<HeroViewModel> heroes;

    private HeroService heroService;
    private ModelMapper modelMapper;

    public HomeBean() {
    }

    @Inject
    public HomeBean(HeroService heroService, ModelMapper modelMapper) {
        this.heroService = heroService;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    public void init() {
        this.heroes = this.heroService.findAll().stream().map(hero -> {
            HeroViewModel model = this.modelMapper.map(hero, HeroViewModel.class);
            model.setAclass(hero.getAclass().name().toLowerCase());
            return model;
        }).collect(Collectors.toList());
    }



    public List<HeroViewModel> getHeroes() {
        return heroes;
    }

    public void setHeroes(List<HeroViewModel> heroes) {
        this.heroes = heroes;
    }
}
