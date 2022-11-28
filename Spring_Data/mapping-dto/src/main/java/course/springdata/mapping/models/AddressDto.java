package course.springdata.mapping.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
public class AddressDto {
    private Long id;
    private String country;
    private String city;
    private String details;
}
