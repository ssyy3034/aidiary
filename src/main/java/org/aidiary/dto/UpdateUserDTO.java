package org.aidiary.dto;

import jakarta.validation.constraints.NotBlank;
import jdk.jfr.Name;
import lombok.Data;

@Data
public class UpdateUserDTO {

    @NotBlank(message = "이름은 비워둘 수 없습니다.")
    private String name;
}
