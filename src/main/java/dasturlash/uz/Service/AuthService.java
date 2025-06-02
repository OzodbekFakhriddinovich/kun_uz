package dasturlash.uz.Service;

import dasturlash.uz.DTO.JwtDTO;
import dasturlash.uz.DTO.ProfileLoginDTO;
import dasturlash.uz.DTO.auth.RegistrationDTO;
import dasturlash.uz.DTO.profile.ProfileUpdateDTO;
import dasturlash.uz.EXP.AppBadException;
import dasturlash.uz.Entity.ProfileEntity;
import dasturlash.uz.Entity.ProfileRoleEntity;
import dasturlash.uz.Repository.ProfileRepository;
import dasturlash.uz.enums.ProfileRoleEnum;
import dasturlash.uz.enums.ProfileStatus;
import dasturlash.uz.enums.ProfileStatusEnum;
import dasturlash.uz.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AuthService {
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private ProfileRoleService profileRoleService;
    @Autowired
    private EmailSenderService emailSenderService;
    @Autowired
    private EmailHistoryService emailHistoryService;




    public String register(RegistrationDTO dto){
        Optional<ProfileEntity> existOpt = profileRepository.findByUsernameAndVisibleIsTrue(dto.getUsername());
        if(existOpt.isPresent()){
            ProfileEntity existProfile = existOpt.get();
            if(existProfile.getStatus().equals(ProfileStatusEnum.NOT_ACTIVE)){
                profileRoleService.deleteRolesByProfileId(existProfile.getId());
                profileRepository.deleteById(existProfile.getId());
            }else {
                throw new AppBadException("Username already in use");
            }
        }
        //create
        ProfileEntity profile = new ProfileEntity();
        profile.setName(dto.getName());
        profile.setSurname(dto.getSurname());
        profile.setUsername(dto.getUsername());
        profile.setPassword(bCryptPasswordEncoder.encode(dto.getPassword()));
        profile.setVisible(true);
        profile.setStatus(ProfileStatusEnum.NOT_ACTIVE);
        profileRepository.save(profile);
        //create profile role
        profileRoleService.create(profile.getId(), ProfileRoleEnum.ROLE_USER);
        //send
        emailSenderService.sendRegistrationEmail(profile.getUsername());
        //response
        return  "Tasdiqlash kodi ketdi";
    }

    public String regEmailVerification(String jwt) {
        JwtDTO jwtDTO = JWTUtil.decode(jwt); // decode qilish
        String username = jwtDTO.getUsername();
        Integer code = jwtDTO.getCode();

        Optional<ProfileEntity> existOptional = profileRepository.findByUsernameAndVisibleIsTrue(username);
        if (existOptional.isEmpty()) {
            throw new AppBadException("Username not found");
        }
        ProfileEntity profile = existOptional.get();
        if (!profile.getStatus().equals(ProfileStatusEnum.NOT_ACTIVE)) {
            throw new AppBadException("Username in wrong status");
        }

        // sms code ni tekshirish
        if (emailHistoryService.isSmsSendToAccount(username, code)) {
            profile.setStatus(ProfileStatusEnum.ACTIVE);
            profileRepository.save(profile);
            return "Verification successfully completed";
        }

        throw new AppBadException("Not completed");
    }



    public ProfileUpdateDTO login(ProfileLoginDTO dto){
        Optional<ProfileEntity> existOpt = profileRepository.findByUsernameAndVisibleIsTrue(dto.getUsername());
        if(existOpt.isEmpty()){
            throw new AppBadException("Username or password is incorrect");
        }
        ProfileEntity existProfile = existOpt.get();
        if (!bCryptPasswordEncoder.matches(dto.getPassword(), existProfile.getPassword())){
            throw new AppBadException("Username or password is incorrect");
        }
        if (existProfile.getStatus().equals(ProfileStatusEnum.BLOCKED)){
            throw new AppBadException("Username is blocked");
        }
        if (existProfile.getStatus().equals(ProfileStatusEnum.NOT_ACTIVE)){
            throw new AppBadException("Activate your profile");
        }
        ProfileUpdateDTO profiledto = new ProfileUpdateDTO();
        profiledto.setName(existProfile.getName());
        profiledto.setSurname(existProfile.getSurname());
        profiledto.setUsername(existProfile.getUsername());
        List<ProfileRoleEntity> roles = new LinkedList<>();
        for (ProfileRoleEntity profileRoleEntity : existProfile.getRoleList()) {
            roles.add(profileRoleEntity);
        }
            return profiledto;
        }
    }



