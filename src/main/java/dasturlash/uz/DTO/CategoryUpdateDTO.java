package dasturlash.uz.DTO;

import lombok.Data;

@Data
public class CategoryUpdateDTO {
    private Integer orderNumber;
    private String nameUz;
    private String nameRu;
    private String nameEn;
    private String key;
}