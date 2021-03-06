package tresbits.springbootbackendapirest.services;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

public class HomeServiceImpl {
    public <T> void merge(T source, T target) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setSkipNullEnabled(true).setMatchingStrategy(MatchingStrategies.STRICT);
        modelMapper.map(source, target);
    }
}
