package dasturlash.uz.Service;

import dasturlash.uz.DTO.profile.ProfileDTO;
import dasturlash.uz.DTO.profile.ProfileUpdateDTO;
import dasturlash.uz.EXP.AppBadException;
import dasturlash.uz.Entity.ProfileEntity;
import dasturlash.uz.Repository.ProfileRepository;
import dasturlash.uz.enums.ProfileStatus;
import dasturlash.uz.enums.ProfileStatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfileService {
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private ProfileRoleService profileRoleService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public ProfileDTO create(ProfileDTO profile) {
        Optional<ProfileEntity> optional = profileRepository.findByUsernameAndVisibleIsTrue(profile.getUsername());
        if (optional.isPresent()) {
            throw new AppBadException("User exists");
        }
        ProfileEntity entity = new ProfileEntity();
        entity.setName(profile.getName());
        entity.setSurname(profile.getSurname());
        entity.setPassword(bCryptPasswordEncoder.encode(profile.getPassword())); // TODO MD5/ByCript
        entity.setUsername(profile.getUsername());
        entity.setStatus(ProfileStatusEnum.ACTIVE.ACTIVE);
        entity.setVisible(Boolean.TRUE);
        profileRepository.save(entity); // save
        // role_save
        profileRoleService.merge(entity.getId(), profile.getRoleList()); // TODO understand
        // result
        ProfileDTO response = toDTO(entity);
        response.setRoleList(profile.getRoleList());
        return response;
    }


    public ProfileDTO update(Integer id, ProfileUpdateDTO dto) {
        ProfileEntity entity = get(id);
        // check username exists
        Optional<ProfileEntity> usernameOptional = profileRepository.findByUsernameAndVisibleIsTrue(dto.getUsername());
        if (usernameOptional.isPresent() && !usernameOptional.get().getId().equals(id)) {
            throw new AppBadException("Username exists");
        }
        //
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setUsername(dto.getUsername());
        profileRepository.save(entity); // update
        // role_save
        profileRoleService.merge(entity.getId(), dto.getRoleList());
        // result
        ProfileDTO response = toDTO(entity);
        response.setRoleList(dto.getRoleList());
        return response;
    }

    public ProfileDTO toDTO(ProfileEntity entity) {
        ProfileDTO dto = new ProfileDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setSurname(entity.getSurname());
        dto.setUsername(entity.getUsername());
        dto.setCreatedDate(entity.getCreatedDate());
        return dto;
    }

    public ProfileEntity get(Integer id) {
        return profileRepository.findByIdAndVisibleIsTrue(id).orElseThrow(() -> {
            throw new AppBadException("Profile not found");
        });
        /*Optional<ProfileEntity> optional = profileRepository.findByIdAndVisibleIsTrue(id);
        if (optional.isEmpty()) {
            throw new AppBadException("Profile not found");
        }
        return optional.get();*/
    }

}

