package com.pingpong.quoteBakery.app.persistence;

import com.pingpong.quoteBakery.app.domain.UserPreference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserPreferenceRepository extends JpaRepository<UserPreference, Long> {
    UserPreference findByUser_Id(Long userId);

    @Modifying
    @Query("DELETE FROM UserPreference p WHERE p.user.id = :userId")
    void deleteAllByUserId(@Param("userId") Long userId);

}

