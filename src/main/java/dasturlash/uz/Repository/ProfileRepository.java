package dasturlash.uz.Repository;

import dasturlash.uz.Entity.ProfileEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ProfileRepository extends CrudRepository<ProfileEntity, Integer> {
    // where username = ? and visible = true
    Optional<ProfileEntity> findByUsernameAndVisibleIsTrue(String username);
    Optional<ProfileEntity> findByIdAndVisibleIsTrue(Integer id);

}

