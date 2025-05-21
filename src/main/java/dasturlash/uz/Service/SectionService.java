package dasturlash.uz.Service;

import dasturlash.uz.DTO.LangResponseDTO;
import dasturlash.uz.DTO.SectionDTO;
import dasturlash.uz.EXP.AppBadException;
import dasturlash.uz.EXP.NotFoundException;
import dasturlash.uz.Entity.SectionEntity;
import dasturlash.uz.Repository.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class SectionService {

    @Autowired
    private SectionRepository repository;

    public SectionDTO create(SectionDTO dto) {
        try {
            SectionEntity entity = new SectionEntity();
            entity.setOrderNumber(dto.getOrderNumber());
            entity.setNameUz(dto.getNameUz());
            entity.setNameRu(dto.getNameRu());
            entity.setNameEn(dto.getNameEn());
            entity.setKey(dto.getKey());
            entity.setCreated_date(LocalDateTime.now());
            entity.setVisible(true);

            repository.save(entity);

            dto.setId(entity.getId());
            dto.setCreated_date(entity.getCreated_date());
            return dto;

        } catch (Exception e) {
            if (e.getMessage().contains("unique constraint") || e.getMessage().contains("Duplicate entry")) {
                throw new AppBadException("Section with order number " + dto.getOrderNumber() + " already exists");
            }
            throw e;
        }
    }

    public SectionDTO update(Integer id, SectionDTO dto) {
        Optional<SectionEntity> optional = repository.findById(id);
        if (optional.isEmpty() || !optional.get().getVisible()) {
            throw new NotFoundException("Section not found");
        }

        SectionEntity entity = optional.get();
        entity.setOrderNumber(dto.getOrderNumber());
        entity.setNameUz(dto.getNameUz());
        entity.setNameRu(dto.getNameRu());
        entity.setNameEn(dto.getNameEn());
        entity.setKey(dto.getKey());

        repository.save(entity);

        dto.setId(entity.getId());
        dto.setCreated_date(entity.getCreated_date());
        return dto;
    }

    public Boolean delete(Integer id) {
        Optional<SectionEntity> optional = repository.findById(id);
        if (optional.isEmpty() || !optional.get().getVisible()) {
            throw new NotFoundException("Section not found");
        }

        SectionEntity entity = optional.get();
        entity.setVisible(false);
        repository.save(entity);
        return true;
    }

    public List<SectionDTO> getAll() {
        Iterable<SectionEntity> all = repository.findAllByVisibleTrueOrderByOrderNumberAsc();
        List<SectionDTO> dtoList = new LinkedList<>();
        all.forEach(entity -> dtoList.add(toDto(entity)));
        return dtoList;
    }

    public List<LangResponseDTO> getByLang(String lang) {
        Iterable<SectionEntity> all = repository.findAllByVisibleTrueOrderByOrderNumberAsc();
        List<LangResponseDTO> dtoList = new LinkedList<>();
        all.forEach(entity -> dtoList.add(toLangDto(lang, entity)));
        return dtoList;
    }

    private SectionDTO toDto(SectionEntity entity) {
        SectionDTO dto = new SectionDTO();
        dto.setId(entity.getId());
        dto.setOrderNumber(entity.getOrderNumber());
        dto.setNameUz(entity.getNameUz());
        dto.setNameRu(entity.getNameRu());
        dto.setNameEn(entity.getNameEn());
        dto.setKey(entity.getKey());
        dto.setCreated_date(entity.getCreated_date());
        return dto;
    }

    private LangResponseDTO toLangDto(String lang, SectionEntity entity) {
        LangResponseDTO dto = new LangResponseDTO();
        dto.setId(entity.getId());
        dto.setKey(entity.getKey());

        switch (lang) {
            case "uz" -> dto.setName(entity.getNameUz());
            case "ru" -> dto.setName(entity.getNameRu());
            case "en" -> dto.setName(entity.getNameEn());
        }

        return dto;
    }
}

