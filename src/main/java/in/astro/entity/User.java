package in.astro.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@Table(name = "users")
@NoArgsConstructor
@Getter
@Setter
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String email;
    private String password;
    private String about;
    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Post> posts = new ArrayList<>();
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
    joinColumns= @JoinColumn(name = "user",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role",referencedColumnName = "id"))
    private Set<Role> role = new HashSet<>();


//    Security
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> grantedAuthorities = this.role.stream().
                map((role1 -> new SimpleGrantedAuthority(role1.getName()))).collect(Collectors.toList());
        return grantedAuthorities;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
