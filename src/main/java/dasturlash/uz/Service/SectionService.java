package dasturlash.uz.Service;

import dasturlash.uz.DTO.SectionCreateDTO;
import dasturlash.uz.DTO.SectionDTO;
import dasturlash.uz.DTO.SectionLangDTO;
import dasturlash.uz.DTO.SectionUpdateDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface SectionService {
    void create(SectionCreateDTO dto);
    void update(Integer id, SectionUpdateDTO dto);
    void delete(Integer id);
    Page<SectionDTO> getAll(int page, int size);
    List<SectionLangDTO> getByLang(String lang);

}
