package se.lexicon.springbootrest.model.dto;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class RoleDto {

    //@NotEmpty(message = "name should not be empty")
    //@Size(min = 2, max = 40, message = "name length should be between 2-40")

    @NotEmpty
    @Size(min = 2, max = 40)
    private String name;
}
