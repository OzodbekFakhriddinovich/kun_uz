package dasturlash.uz.Service;

import dasturlash.uz.DTO.RegionDTO;
import dasturlash.uz.DTO.RegionLangDTO;

import java.util.List;

public interface RegionService {
    RegionDTO create(RegionDTO dto);
    RegionDTO update(Integer id, RegionDTO dto);
    boolean delete(Integer id);
    List<RegionDTO> getAll();
    List<RegionLangDTO> getByLang(String lang);
}