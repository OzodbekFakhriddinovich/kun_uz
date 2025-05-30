package dasturlash.uz.Controller;

import dasturlash.uz.DTO.CategoryDTO;
import dasturlash.uz.DTO.LangResponseDTO;
import dasturlash.uz.Service.CategoryService;
import dasturlash.uz.enums.AppLanguageEnum;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService service;

    @PostMapping("")
    public ResponseEntity<CategoryDTO> create(@Valid @RequestBody CategoryDTO dto){
        return ResponseEntity.ok(service.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDTO> update(@PathVariable("id") Integer id, @RequestBody CategoryDTO newDto){
        return ResponseEntity.ok(service.update(id, newDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Integer id){
        return ResponseEntity.ok(service.delete(id));
    }

    @GetMapping("")
    public ResponseEntity<List<CategoryDTO>> getAllByOrder(){
        return ResponseEntity.ok(service.getAllByOrder());
    }

    @GetMapping("/lang")
    public ResponseEntity<List<LangResponseDTO>> getByLang(@RequestParam AppLanguageEnum language) {
        return ResponseEntity.ok(service.getAllByLang(language));
    }
}

