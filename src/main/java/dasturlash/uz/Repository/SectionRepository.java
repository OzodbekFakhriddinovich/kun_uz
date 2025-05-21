package dasturlash.uz.Repository;

import dasturlash.uz.Entity.SectionEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

public interface SectionRepository extends JpaRepository<SectionEntity, Long> {
    Page<SectionEntity> findAllByVisibleTrue(Pageable pageable);
    List<SectionEntity> findAllByVisibleTrue();
    Page<SectionEntity> findAll(Pageable pageable);  // to'g'ri variant
    List<SectionEntity> findAll();                  // to'g'ri variant
}
