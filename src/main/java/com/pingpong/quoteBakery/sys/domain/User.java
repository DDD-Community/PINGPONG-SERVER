package com.pingpong.quoteBakery.sys.domain;

import com.pingpong.quoteBakery.app.domain.Like;
import com.pingpong.quoteBakery.sys.dto.UserDto;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "uid", nullable = true, unique = true, length = 2000)
    private String uid;

    @Column(name = "fcm", nullable = true, unique = true, length = 2000)
    private String fcm;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = true) //파베 로그인만 지원하기 때문에 아직 사용하지는 않지만 추후 사용을 위해 만들어둠
    private String password;

    @Column(name = "nickname", nullable = false, unique = true)
    private String nickname;

    @Column(name = "rmk", nullable = false)
    private String rmk;

    @Column(name = "jobCd", nullable = false)
    private String jobCd;

    @OneToMany(mappedBy = "user")
    private List<Like> likes = new ArrayList<>();

    @Builder
    public User(String uid, String fcm, String email, String password, String nickname, String rmk, String jobCd) {
        this.uid = uid;
        this.fcm = fcm;
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.rmk = rmk;
        this.jobCd = jobCd;
    }

    public void updateUserInfo(UserDto userDto) {
        this.nickname = userDto.getNickname();
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("user"));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
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
