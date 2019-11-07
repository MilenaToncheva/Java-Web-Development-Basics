package app.service;

import app.domain.models.service.HeroServiceModel;

import java.util.List;

public interface HeroService {
    void createHero(HeroServiceModel hero);
    HeroServiceModel findById(String id);
    List<HeroServiceModel> findAll();
   void delete(String id);

}
