package app.service;

import app.domain.entities.Hero;
import app.domain.models.service.HeroServiceModel;
import app.repository.HeroRepository;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class HeroServiceImpl implements HeroService {
    private final HeroRepository heroRepository;

    private final ModelMapper modelMapper;

    @Inject
    public HeroServiceImpl(HeroRepository heroRepository, ModelMapper modelMapper) {
        this.heroRepository = heroRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void createHero(HeroServiceModel heroServiceModel) {

        Hero hero = this.modelMapper.map(heroServiceModel, Hero.class);
        this.heroRepository.save(hero);

    }

    @Override
    public HeroServiceModel findById(String id) {
        return this.modelMapper.map(this.heroRepository.findById(id), HeroServiceModel.class);
    }

    @Override
    public List<HeroServiceModel> findAll() {
        return Arrays.stream(this.modelMapper.map(this.heroRepository.findAll(), HeroServiceModel[].class)).collect(Collectors.toList());
    }

    @Override
    public void delete(String id) {
        this.heroRepository.delete(id);
    }
}
