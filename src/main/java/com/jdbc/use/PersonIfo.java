package com.jdbc.use;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PersonIfo {
    private Long id;
    private String name;
    private Integer age;
}
