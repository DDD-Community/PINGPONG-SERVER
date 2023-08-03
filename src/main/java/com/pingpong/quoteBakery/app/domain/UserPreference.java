package com.pingpong.quoteBakery.app.domain;

import com.pingpong.quoteBakery.app.dto.UserPrefDto;
import com.pingpong.quoteBakery.com.entity.BaseEntity;
import com.pingpong.quoteBakery.sys.domain.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 사용자별 명언 취향 Entity
 */
@Entity
@Table(name = "user_preferences")
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserPreference extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_generator")
    @SequenceGenerator(name = "sequence_generator", sequenceName = "user_pref_sequence", allocationSize = 50)
    @Column(name = "user_pref_id")
    private Long userPrefId;

    @Column(name = "flavor", length = 100) // 명언 맛
    private String flavor;


    @Column(name = "source", length = 100) // 명언 출처
    private String source;

    @OneToOne
    @JoinColumn(name = "user_id") // 사용자ID
    private User user;


    /*
    * 생성자 대체
    * 엔티티 객체 생성시 Builder로 직접 빌드하지 않고 toEntity만을 호출
    * 관리 포인트를 줄이기 위함.
    * */
    public static UserPreference toEntity(UserPrefDto dto, User user){
        return UserPreference.builder()
            .flavor(dto.getFlavor())
            .source(dto.getSource())
            .user(user)
            .build();
    }
    
    /*
    * 객체 수정시 사용
    * */
    public void update(UserPrefDto dto){
        this.flavor = dto.getFlavor();
        this.source = dto.getSource();
    }

}