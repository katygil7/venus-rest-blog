package kat.venusrestblog.data;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.Collection;


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

    private Collection<Post> posts;
}
