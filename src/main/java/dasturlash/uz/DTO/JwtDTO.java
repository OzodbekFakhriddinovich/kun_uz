package dasturlash.uz.DTO;

public class JwtDTO {
    private String username;
    private Integer code;

    public JwtDTO(String username, Integer code) {
        this.username = username;
        this.code = code;
    }

    public String getUsername() {
        return username;
    }


    public Integer getCode() {
        return code;
    }

}
