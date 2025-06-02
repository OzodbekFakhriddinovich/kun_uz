package dasturlash.uz.Service;

import dasturlash.uz.DTO.ArticleDTO;
import dasturlash.uz.DTO.ArticleInfoDTO;
import dasturlash.uz.EXP.NotFoundException;
import dasturlash.uz.Entity.ArticleEntity;
import dasturlash.uz.Repository.ArticleRepository;
import dasturlash.uz.enums.ArticleStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ArticleService {

    @Autowired
    private ArticleRepository repository;

    public ArticleInfoDTO create(ArticleDTO dto) {
        ArticleEntity entity = new ArticleEntity();
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setContent(dto.getContent());
        entity.setImageId(dto.getImageId());
        entity.setRegionId(dto.getRegionId());
        entity.setCategoryList(dto.getCategoryList());
        entity.setSectionList(dto.getSectionList());

        repository.save(entity);
        return toDTO(entity);
    }

    public ArticleInfoDTO update(Integer id, ArticleDTO dto) {
        Optional<ArticleEntity> optional = repository.findById(id);
        if (optional.isEmpty()) throw new NotFoundException("Article not found");

        ArticleEntity entity = optional.get();
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setContent(dto.getContent());
        entity.setImageId(dto.getImageId());
        entity.setRegionId(dto.getRegionId());
        entity.setCategoryList(dto.getCategoryList());
        entity.setSectionList(dto.getSectionList());
        entity.setStatus(ArticleStatus.NOT_PUBLISHED);

        repository.save(entity);
        return toDTO(entity);
    }

    public void delete(Integer id) {
        if (!repository.existsById(id)) {
            throw new NotFoundException("Article not found");
        }
        repository.deleteById(id);
    }

    public void changeStatus(Integer id, ArticleStatus status) {
        ArticleEntity entity = repository.findById(id).orElseThrow(() -> new NotFoundException("Article not found"));
        entity.setStatus(status);
        repository.save(entity);
    }

    private ArticleInfoDTO toDTO(ArticleEntity entity) {
        ArticleInfoDTO dto = new ArticleInfoDTO();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setDescription(entity.getDescription());
        dto.setContent(entity.getContent());
        dto.setImageId(entity.getImageId());
        dto.setRegionId(entity.getRegionId());
        dto.setCategoryList(entity.getCategoryList());
        dto.setSectionList(entity.getSectionList());
        dto.setStatus(entity.getStatus());
        dto.setCreatedDate(entity.getCreatedDate());
        return dto;
    }
}