package dasturlash.uz.Controller;

import dasturlash.uz.DTO.CategoryCreateDTO;
import dasturlash.uz.DTO.CategoryDTO;
import dasturlash.uz.DTO.CategoryLangDTO;
import dasturlash.uz.DTO.CategoryUpdateDTO;
import dasturlash.uz.Service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {


    private final CategoryService categoryService;

    @PostMapping("/adm")
    public ResponseEntity<?> create(@RequestBody CategoryCreateDTO dto) {
        categoryService.create(dto);
        return ResponseEntity.ok("Created");
    }

    @PutMapping("/adm/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody CategoryUpdateDTO dto) {
        categoryService.update(id, dto);
        return ResponseEntity.ok("Updated");
    }

    @DeleteMapping("/adm/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        categoryService.delete(id);
        return ResponseEntity.ok("Deleted");
    }

    @GetMapping("/adm")
    public ResponseEntity<List<CategoryDTO>> getAll() {
        return ResponseEntity.ok(categoryService.getAll());
    }

    @GetMapping("/lang")
    public ResponseEntity<List<CategoryLangDTO>> getByLang(@RequestParam String lang) {
        return ResponseEntity.ok(categoryService.getByLang(lang));
    }

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
}

