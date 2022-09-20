package kat.venusrestblog.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UserFetchDTO {
    private Long id;
    private String userName;
    private String email;
}
