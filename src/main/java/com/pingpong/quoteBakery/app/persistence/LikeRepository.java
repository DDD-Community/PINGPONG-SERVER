package com.pingpong.quoteBakery.app.persistence;

import com.pingpong.quoteBakery.app.domain.Like;
import com.pingpong.quoteBakery.app.domain.Quote;
import com.pingpong.quoteBakery.sys.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LikeRepository extends JpaRepository<Like, Long> {
    List<Like> findAllByUser_Id(Long userId);

    boolean existsByUserAndQuote(User user, Quote quote);

    @Modifying
    @Query("DELETE FROM Like l WHERE l.user.id = :userId")
    void deleteAllByUserId(@Param("userId") Long userId);
}

