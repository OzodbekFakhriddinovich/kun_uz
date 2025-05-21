package dasturlash.uz.Controller;

import dasturlash.uz.DTO.SectionDTO;
import dasturlash.uz.DTO.LangResponseDTO;
import dasturlash.uz.Service.SectionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/section")
public class SectionController {

    @Autowired
    private SectionService sectionService;

    // Create (ADMIN)
    @PostMapping("")
    public ResponseEntity<SectionDTO> create(@Valid @RequestBody SectionDTO dto) {
        return ResponseEntity.ok(sectionService.create(dto));
    }

    // Update by ID (ADMIN)
    @PutMapping("/{id}")
    public ResponseEntity<SectionDTO> update(@PathVariable("id") Integer id,
                                             @RequestBody SectionDTO dto) {
        return ResponseEntity.ok(sectionService.update(id, dto));
    }

    // Delete by ID (ADMIN)
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(sectionService.delete(id));
    }

    // Get All (ADMIN)
    @GetMapping("")
    public ResponseEntity<List<SectionDTO>> getAll() {
        return ResponseEntity.ok(sectionService.getAll());
    }

    // Get by Lang (PUBLIC)
    @GetMapping("/lang")
    public ResponseEntity<List<LangResponseDTO>> getByLang(@RequestParam("language") String language) {
        return ResponseEntity.ok(sectionService.getByLang(language));
    }
}
