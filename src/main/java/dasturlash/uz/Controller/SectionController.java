package dasturlash.uz.Controller;

import dasturlash.uz.DTO.SectionCreateDTO;
import dasturlash.uz.DTO.SectionDTO;
import dasturlash.uz.DTO.SectionLangDTO;
import dasturlash.uz.DTO.SectionUpdateDTO;
import dasturlash.uz.Service.SectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/section")
public class SectionController {

    @Autowired
    private final SectionService sectionService;

    @Autowired
    public SectionController(SectionService sectionService) {
        this.sectionService = sectionService;
    }

    @PostMapping("/adm")
    public ResponseEntity<?> create(@RequestBody SectionCreateDTO dto) {
        sectionService.create(dto);
        return ResponseEntity.ok("Created");
    }

    @PutMapping("/adm/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody SectionUpdateDTO dto) {
        sectionService.update(id, dto);
        return ResponseEntity.ok("Updated");
    }

    @DeleteMapping("/adm/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        sectionService.delete(id);
        return ResponseEntity.ok("Deleted");
    }

    @GetMapping("/adm")
    public ResponseEntity<Page<SectionDTO>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(sectionService.getAll(page, size));
    }

}
