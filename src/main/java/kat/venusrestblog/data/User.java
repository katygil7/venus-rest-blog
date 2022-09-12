package kat.venusrestblog.data;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;


@Getter
@Setter
@NoArgsConstructor
@ToString
public class User {
    private long id;
    private String userName;
    private String email;
    private String password;
    private LocalDate createdAt;
    private UserRole role;
}
