package dasturlash.uz.Service;

import dasturlash.uz.DTO.CategoryCreateDTO;
import dasturlash.uz.DTO.CategoryDTO;
import dasturlash.uz.DTO.CategoryLangDTO;
import dasturlash.uz.DTO.CategoryUpdateDTO;

import java.util.List;

public interface CategoryService {
    void create(CategoryCreateDTO dto);
    void update(Integer id, CategoryUpdateDTO dto);
    void delete(Integer id);
    List<CategoryDTO> getAll();
    List<CategoryLangDTO> getByLang(String lang);
}

