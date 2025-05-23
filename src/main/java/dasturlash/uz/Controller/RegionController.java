package dasturlash.uz.Controller;

import dasturlash.uz.DTO.RegionDTO;
import dasturlash.uz.DTO.LangResponseDTO;
import dasturlash.uz.Service.RegionService;
import dasturlash.uz.enums.AppLanguageEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/region")
public class RegionController {

    @Autowired
    private RegionService service;

    @PostMapping("")
    public ResponseEntity<?> createRegion(@RequestBody RegionDTO dto) {
        return ResponseEntity.ok().build();
    }


    @PutMapping("/{id}")
    public ResponseEntity<String> updateRegion(@PathVariable Integer id, @RequestBody RegionDTO dto) {
        return ResponseEntity.ok("Region yangilandi: ID = " + id + ", Name = " + dto.getNameEn());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Integer id){
        return ResponseEntity.ok(service.delete(id));
    }

    @GetMapping("")
    public ResponseEntity<List<RegionDTO>> get() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/lang")
    public ResponseEntity<List<LangResponseDTO>> getByLang(@RequestParam AppLanguageEnum language) {
        return ResponseEntity.ok(service.getAllByLang(language));
    }

}