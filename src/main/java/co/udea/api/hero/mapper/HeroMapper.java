package co.udea.api.hero.mapper;

import co.udea.api.hero.dto.HeroDTO;
import co.udea.api.hero.model.Hero;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class HeroMapper {

    private final ModelMapper modelMapper;

    public HeroMapper() {
        this.modelMapper = new ModelMapper();
        configureMappings();
    }

    private void configureMappings() {
        modelMapper.createTypeMap(Hero.class, HeroDTO.class)
                .addMapping(Hero::getId, HeroDTO::setId)
                .addMapping(Hero::getName, HeroDTO::setName);
    }

    public HeroDTO toHeroDTO(Hero hero) {
        return modelMapper.map(hero, HeroDTO.class);
    }

    public Hero toHero(HeroDTO heroDTO) {
        return modelMapper.map(heroDTO, Hero.class);
    }
}
