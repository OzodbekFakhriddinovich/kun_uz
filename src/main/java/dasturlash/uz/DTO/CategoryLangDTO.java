package dasturlash.uz.DTO;

import lombok.Data;




public class CategoryLangDTO {
    private Integer id;
    private Integer orderNumber;
    private String key;
    private String name;

    public CategoryLangDTO(Integer id, Integer orderNumber, String key, String name) {
        this.id = id;
        this.orderNumber = orderNumber;
        this.key = key;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
