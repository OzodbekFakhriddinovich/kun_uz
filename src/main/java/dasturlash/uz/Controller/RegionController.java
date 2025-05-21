package dasturlash.uz.Controller;

import dasturlash.uz.DTO.RegionDTO;
import dasturlash.uz.DTO.RegionLangDTO;
import dasturlash.uz.Service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/region")
public class RegionController {

    @Autowired
    private RegionService regionService;

    @PostMapping("/create")
    public RegionDTO create(@RequestBody RegionDTO dto) {
        return regionService.create(dto);
    }

    @PutMapping("/update/{id}")
    public RegionDTO update(@PathVariable Integer id, @RequestBody RegionDTO dto) {
        return regionService.update(id, dto);
    }

    @DeleteMapping("/delete/{id}")
    public boolean delete(@PathVariable Integer id) {
        return regionService.delete(id);
    }

    @GetMapping("/list")
    public List<RegionDTO> getAll() {
        return regionService.getAll();
    }

    @GetMapping("/lang/{lang}")
    public List<RegionLangDTO> getByLang(@PathVariable String lang) {
        return regionService.getByLang(lang);
    }
}
