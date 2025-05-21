package dasturlash.uz.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class RegionLangDTO {
    private Integer id;
    private String key;
    private String name;

    public RegionLangDTO(Integer id, String key, String name) {
        this.id = id;
        this.key = key;
        this.name = name;
    }

}
