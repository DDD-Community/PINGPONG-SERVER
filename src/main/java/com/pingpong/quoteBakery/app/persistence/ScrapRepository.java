package com.pingpong.quoteBakery.app.persistence;

import com.pingpong.quoteBakery.app.domain.Scrap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ScrapRepository extends JpaRepository<Scrap, Long> {
    List<Scrap> findAllByUser_Id(Long userId);

    @Modifying
    @Query("DELETE FROM Scrap s WHERE s.user.id = :userId")
    void deleteAllByUserId(@Param("userId") Long userId);
}

