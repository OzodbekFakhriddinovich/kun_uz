package dasturlash.uz.Controller;

import dasturlash.uz.DTO.RegionDTO;
import dasturlash.uz.DTO.LangResponseDTO;
import dasturlash.uz.Service.RegionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/region")
public class RegionController {

    @Autowired
    private RegionService service;

    @PostMapping("")
    public ResponseEntity<RegionDTO> create(@Valid @RequestBody RegionDTO dto){
        return ResponseEntity.ok(service.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RegionDTO> update(@PathVariable("id") Integer id, @RequestBody RegionDTO newDto){
        return ResponseEntity.ok(service.update(id, newDto));
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
    public ResponseEntity<List<LangResponseDTO>> getByLang(@RequestParam String language) {
        return ResponseEntity.ok(service.getAllbyLang(language));
    }
}
