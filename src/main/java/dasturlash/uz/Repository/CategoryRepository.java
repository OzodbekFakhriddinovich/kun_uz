package dasturlash.uz.Repository;

import dasturlash.uz.Entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Integer> {
    List<CategoryEntity> findAllByVisibleTrueOrderByOrderNumber();
}

