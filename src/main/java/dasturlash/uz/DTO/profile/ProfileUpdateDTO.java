package dasturlash.uz.DTO.profile;

import dasturlash.uz.enums.ProfileRoleEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;


import java.util.List;


public class ProfileUpdateDTO {

    @NotBlank(message = "Ism bo‘sh bo‘lmasligi kerak")
    private String name;

    @NotBlank(message = "Familiya bo‘sh bo‘lmasligi kerak")
    private String surname;

    @NotBlank(message = "Username  bo‘sh bo‘lmasligi kerak")
    private String username;

    @NotEmpty(message = "Role bo‘sh bo‘lmasligi kerak")
    private List<ProfileRoleEnum> roleList;

    public @NotBlank(message = "Ism bo‘sh bo‘lmasligi kerak") String getName() {
        return name;
    }

    public void setName(@NotBlank(message = "Ism bo‘sh bo‘lmasligi kerak") String name) {
        this.name = name;
    }

    public @NotBlank(message = "Familiya bo‘sh bo‘lmasligi kerak") String getSurname() {
        return surname;
    }

    public void setSurname(@NotBlank(message = "Familiya bo‘sh bo‘lmasligi kerak") String surname) {
        this.surname = surname;
    }

    public @NotBlank(message = "Username  bo‘sh bo‘lmasligi kerak") String getUsername() {
        return username;
    }

    public void setUsername(@NotBlank(message = "Username  bo‘sh bo‘lmasligi kerak") String username) {
        this.username = username;
    }

    public @NotEmpty(message = "Role bo‘sh bo‘lmasligi kerak") List<ProfileRoleEnum> getRoleList() {
        return roleList;
    }

    public void setRoleList(@NotEmpty(message = "Role bo‘sh bo‘lmasligi kerak") List<ProfileRoleEnum> roleList) {
        this.roleList = roleList;
    }
}
