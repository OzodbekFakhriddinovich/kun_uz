package dasturlash.uz.Service;

import dasturlash.uz.DTO.RegionDTO;
import dasturlash.uz.DTO.RegionLangDTO;
import dasturlash.uz.Entity.RegionEntity;
import dasturlash.uz.Repository.RegionRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RegionServiceImpl implements RegionService {

    private final RegionRepository regionRepository;

    @Autowired
    public RegionServiceImpl(RegionRepository regionRepository) {
        this.regionRepository = regionRepository;
    }
    @Override
    public RegionDTO create(RegionDTO dto) {
        RegionEntity entity = new RegionEntity();
        entity.setOrderNumber(dto.getOrderNumber());
        entity.setKey(dto.getKey());
        entity.setNameUz(dto.getNameUz());
        entity.setNameRu(dto.getNameRu());
        entity.setNameEn(dto.getNameEn());

        regionRepository.save(entity);

        dto.setId(entity.getId());
        return dto;
    }

    @Override
    public RegionDTO update(Integer id, RegionDTO dto) {
        Optional<RegionEntity> optional = regionRepository.findById(id);
        if (optional.isEmpty()) {
            throw new RuntimeException("Region not found");
        }

        RegionEntity entity = optional.get();
        entity.setOrderNumber(dto.getOrderNumber());
        entity.setKey(dto.getKey());
        entity.setNameUz(dto.getNameUz());
        entity.setNameRu(dto.getNameRu());
        entity.setNameEn(dto.getNameEn());

        regionRepository.save(entity);
        return dto;
    }

    @Override
    public boolean delete(Integer id) {
        Optional<RegionEntity> optional = regionRepository.findById(id);
        if (optional.isPresent()) {
            RegionEntity entity = optional.get();
            entity.setVisible(false);
            regionRepository.save(entity);
            return true;
        }
        return false;
    }

    @Override
    public List<RegionDTO> getAll() {
        return regionRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<RegionLangDTO> getByLang(String lang) {
        return regionRepository.findAllByVisibleTrueOrderByOrderNumberAsc().stream()
                .map(region -> {
                    String name = switch (lang.toLowerCase()) {
                        case "uz" -> region.getNameUz();
                        case "ru" -> region.getNameRu();
                        case "en" -> region.getNameEn();
                        default -> region.getNameEn();
                    };
                    return new RegionLangDTO(region.getId(), region.getKey(), name);
                }).collect(Collectors.toList());
    }

    private RegionDTO toDTO(RegionEntity entity) {
        RegionDTO dto = new RegionDTO();
        dto.setId(entity.getId());
        dto.setOrderNumber(entity.getOrderNumber());
        dto.setNameUz(entity.getNameUz());
        dto.setNameRu(entity.getNameRu());
        dto.setNameEn(entity.getNameEn());
        dto.setKey(entity.getKey());
        return dto;
    }
}
