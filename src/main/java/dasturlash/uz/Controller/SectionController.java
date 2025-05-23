package dasturlash.uz.Controller;

import dasturlash.uz.DTO.SectionDTO;
import dasturlash.uz.DTO.LangResponseDTO;
import dasturlash.uz.Service.SectionService;
import dasturlash.uz.enums.AppLanguageEnum;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/section")

public class SectionController {

    @Autowired
    private SectionService service;

    @PostMapping("")
    public ResponseEntity<SectionDTO> create(@Valid @RequestBody SectionDTO dto){
        return ResponseEntity.ok(service.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SectionDTO> update(@PathVariable Integer id,@RequestBody SectionDTO dto){
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Integer id){
        return ResponseEntity.ok(service.delete(id));
    }

    @GetMapping("")
    public ResponseEntity<List<SectionDTO>> getAllByOrder(){
        return ResponseEntity.ok(service.getAllByOrder());
    }

    @GetMapping("/lang")
    public ResponseEntity<List<LangResponseDTO>> getByLang(@RequestParam AppLanguageEnum language) {
        return ResponseEntity.ok(service.getAllByLang(language));
    }
}