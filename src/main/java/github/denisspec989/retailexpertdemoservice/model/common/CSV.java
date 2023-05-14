package github.denisspec989.retailexpertdemoservice.model.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@AllArgsConstructor
public class CSV {
    private final MultipartFile multipartFile;
}
