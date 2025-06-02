package dasturlash.uz.Controller;

import dasturlash.uz.DTO.ArticleDTO;
import dasturlash.uz.DTO.ArticleInfoDTO;
import dasturlash.uz.Service.ArticleService;
import dasturlash.uz.enums.ArticleStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/article")
public class ArticleController {

    @Autowired
    private ArticleService service;

    @PostMapping("")
    public ResponseEntity<ArticleInfoDTO> create(@RequestBody ArticleDTO dto) {
        return ResponseEntity.ok(service.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ArticleInfoDTO> update(@PathVariable Integer id, @RequestBody ArticleDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.ok("Deleted");
    }

    @PutMapping("/status/{id}")
    public ResponseEntity<String> changeStatus(@PathVariable Integer id, @RequestParam ArticleStatus status) {
        service.changeStatus(id, status);
        return ResponseEntity.ok("Status updated");
    }
}
