package metube.service;

import metube.domain.entities.Tube;
import metube.domain.models.service.TubeServiceModel;
import metube.repository.TubeRepository;
import util.ModelMapper;
import util.ValidationUtil;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TubeServiceImpl implements TubeService {
    private final TubeRepository tubeRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    @Inject
    public TubeServiceImpl(TubeRepository tubeRepository, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.tubeRepository = tubeRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;

    }

    @Override
    public void saveTube(TubeServiceModel tubeServiceModel) {
        if(!this.validationUtil.isValid(tubeServiceModel)){
            throw new IllegalArgumentException();
        }
        this.tubeRepository.save(this.modelMapper.map(tubeServiceModel, Tube.class));

    }

    @Override
    public TubeServiceModel findByName(String name) {
        Tube tube = this.tubeRepository.findByName(name).orElse(null);
        return this.modelMapper.map(tube, TubeServiceModel.class);

    }

    @Override
    public List<TubeServiceModel> findAll() {
        return Arrays.stream(this.modelMapper.map(this.tubeRepository.findAll(), TubeServiceModel[].class)).collect(Collectors.toList());
    }
}
