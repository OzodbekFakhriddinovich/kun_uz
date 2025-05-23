package dasturlash.uz.DTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


public class CategoryDTO {

    private Integer id;

    @NotNull(message = "orderNumber required")
    @Min(value = 1, message = "orderNumber have to higher than 0")
    private Integer orderNumber;

    @NotBlank(message = "nameUz required")
    private String nameUz;

    @NotBlank(message = "nameRu required")
    private String nameRu;

    @NotBlank(message = "nameEn required")
    private String nameEn;

    @NotNull(message = "categoryKey required")
    private String categoryKey;

    private LocalDateTime createdDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public @NotNull(message = "orderNumber required") @Min(value = 1, message = "orderNumber have to higher than 0") Integer getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(@NotNull(message = "orderNumber required") @Min(value = 1, message = "orderNumber have to higher than 0") Integer orderNumber) {
        this.orderNumber = orderNumber;
    }

    public @NotBlank(message = "nameUz required") String getNameUz() {
        return nameUz;
    }

    public void setNameUz(@NotBlank(message = "nameUz required") String nameUz) {
        this.nameUz = nameUz;
    }

    public @NotBlank(message = "nameRu required") String getNameRu() {
        return nameRu;
    }

    public void setNameRu(@NotBlank(message = "nameRu required") String nameRu) {
        this.nameRu = nameRu;
    }

    public @NotBlank(message = "nameEn required") String getNameEn() {
        return nameEn;
    }

    public void setNameEn(@NotBlank(message = "nameEn required") String nameEn) {
        this.nameEn = nameEn;
    }

    public @NotNull(message = "categoryKey required") String getCategoryKey() {
        return categoryKey;
    }

    public void setCategoryKey(@NotNull(message = "categoryKey required") String categoryKey) {
        this.categoryKey = categoryKey;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }
}