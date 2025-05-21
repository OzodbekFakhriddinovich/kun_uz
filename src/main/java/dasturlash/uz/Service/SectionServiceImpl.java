package dasturlash.uz.Service;

import dasturlash.uz.DTO.SectionCreateDTO;
import dasturlash.uz.DTO.SectionDTO;
import dasturlash.uz.DTO.SectionLangDTO;
import dasturlash.uz.DTO.SectionUpdateDTO;
import dasturlash.uz.Entity.SectionEntity;
import dasturlash.uz.Repository.SectionRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;
import java.util.stream.Collectors;

@Service

public class SectionServiceImpl implements SectionService {

    private final SectionRepository sectionRepository;

    @Autowired
    public SectionServiceImpl(SectionRepository sectionRepository) {
        this.sectionRepository = sectionRepository;
    }

    @Override
    public void create(SectionCreateDTO dto) {
        SectionEntity entity = new SectionEntity();
        BeanUtils.copyProperties(dto, entity);
        sectionRepository.save(entity);
    }

    @Override
    public void update(Integer id, SectionUpdateDTO dto) {
        // id ni Long ga o‘tkazish kerak
        Long longId = id.longValue();

        SectionEntity entity = sectionRepository.findById(longId)
                .orElseThrow(() -> new RuntimeException("Section not found"));
        BeanUtils.copyProperties(dto, entity);
        sectionRepository.save(entity);
    }

    @Override
    public void delete(Integer id) {
        Long longId = id.longValue();

        SectionEntity entity = sectionRepository.findById(longId)
                .orElseThrow(() -> new RuntimeException("Section not found"));
        entity.setVisible(false);
        sectionRepository.save(entity);
    }

    @Override
    public Page<SectionDTO> getAll(int page, int size) {
        Pageable pageable = (Pageable) PageRequest.of(page, size, Sort.by("orderNumber"));

        Page<SectionEntity> sectionPage = sectionRepository.findAll(pageable);

        Page<SectionDTO> dtoPage = sectionPage.map(entity -> {
            SectionDTO dto = new SectionDTO();
            BeanUtils.copyProperties(entity, dto);
            return dto;
        });

        return dtoPage;
    }



    @Override
    public List<SectionLangDTO> getByLang(String lang) {
        return sectionRepository.findAllByVisibleTrue().stream()
                .map(entity -> {
                    String name = switch (lang.toLowerCase()) {
                        case "uz" -> entity.getNameUz();
                        case "ru" -> entity.getNameRu();
                        case "en" -> entity.getNameEn();
                        default -> entity.getNameUz();
                    };
                    return new SectionLangDTO(entity.getId(), entity.getKey(), name);
                }).collect(Collectors.toList());
    }
}

