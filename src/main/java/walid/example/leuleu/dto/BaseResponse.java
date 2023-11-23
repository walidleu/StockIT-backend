package walid.example.leuleu.dto;
import lombok.*;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BaseResponse {
    Boolean success;
    String error;

    public static BaseResponse success() {
        return new BaseResponse(true, null);
    }
}
