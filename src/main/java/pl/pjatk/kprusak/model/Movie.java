package pl.pjatk.kprusak.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Movie {
    private Integer id;
    private String name;
    private Category category;
    private boolean isAvailable;
}
