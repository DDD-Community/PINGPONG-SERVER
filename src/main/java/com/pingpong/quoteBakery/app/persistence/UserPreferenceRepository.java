package com.pingpong.quoteBakery.app.persistence;

import com.pingpong.quoteBakery.app.domain.UserPreference;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserPreferenceRepository extends JpaRepository<UserPreference, Long> {
}

