package dasturlash.uz.Service;

import dasturlash.uz.DTO.CategoryCreateDTO;
import dasturlash.uz.DTO.CategoryDTO;
import dasturlash.uz.DTO.CategoryLangDTO;
import dasturlash.uz.DTO.CategoryUpdateDTO;
import dasturlash.uz.Entity.CategoryEntity;
import dasturlash.uz.Repository.CategoryRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private  CategoryRepository categoryRepository;

    @Override
    public void create(CategoryCreateDTO dto) {
        CategoryEntity entity = new CategoryEntity();
        BeanUtils.copyProperties(dto, entity);
        categoryRepository.save(entity);
    }

    @Override
    public void update(Integer id, CategoryUpdateDTO dto) {
        CategoryEntity entity = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        BeanUtils.copyProperties(dto, entity);
        categoryRepository.save(entity);
    }

    @Override
    public void delete(Integer id) {
        CategoryEntity entity = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        entity.setVisible(false);
        categoryRepository.save(entity);
    }

    @Override
    public List<CategoryDTO> getAll() {
        return categoryRepository.findAll(Sort.by("orderNumber")).stream()
                .map(entity -> {
                    CategoryDTO dto = new CategoryDTO();
                    BeanUtils.copyProperties(entity, dto);
                    return dto;
                }).collect(Collectors.toList());
    }

    @Override
    public List<CategoryLangDTO> getByLang(String lang) {
        return categoryRepository.findAllByVisibleTrueOrderByOrderNumber().stream()
                .map(entity -> {
                    String name = switch (lang.toLowerCase()) {
                        case "uz" -> entity.getNameUz();
                        case "ru" -> entity.getNameRu();
                        case "en" -> entity.getNameEn();
                        default -> entity.getNameUz();
                    };
                    return new CategoryLangDTO(entity.getId(), entity.getOrderNumber(), entity.getKey(), name);
                }).collect(Collectors.toList());
    }
}

