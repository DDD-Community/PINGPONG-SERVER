package com.pingpong.quoteBakery.app.persistence;

import com.pingpong.quoteBakery.app.domain.Like;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like, Long> {
    List<Like> findAllByUser_Id(Long userId);
}

