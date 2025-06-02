package dasturlash.uz.DTO.profile;

import dasturlash.uz.enums.ProfileRoleEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;


public class ProfileDTO {
    private Integer id;

    @NotBlank(message = "Ism bo‘sh bo‘lmasligi kerak")
    private String name;

    @NotBlank(message = "Familiya bo‘sh bo‘lmasligi kerak")
    private String surname;

    @NotBlank(message = "Username  bo‘sh bo‘lmasligi kerak")
    private String username;

    @NotBlank(message = "Parol bo‘sh bo‘lmasligi kerak")
    private String password;

    @NotEmpty(message = "Role bo‘sh bo‘lmasligi kerak")
    private List<ProfileRoleEnum> roleList;

    private LocalDateTime createdDate;
    private String photoId;

    public void setRoleList(@NotEmpty(message = "Role bo‘sh bo‘lmasligi kerak") List<ProfileRoleEnum> roleList) {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public @NotBlank(message = "Parol bo‘sh bo‘lmasligi kerak") String getPassword() {
        return password;
    }

    public void setPassword(@NotBlank(message = "Parol bo‘sh bo‘lmasligi kerak") String password) {
        this.password = password;
    }

    public @NotEmpty(message = "Role bo‘sh bo‘lmasligi kerak") List<ProfileRoleEnum> getRoleList() {
        return roleList;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public String getPhotoId() {
        return photoId;
    }

    public void setPhotoId(String photoId) {
        this.photoId = photoId;
    }


}
