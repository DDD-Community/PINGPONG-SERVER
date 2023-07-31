package com.pingpong.quoteBakery.app.persistence;

import com.pingpong.quoteBakery.app.domain.Scrap;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScrapRepository extends JpaRepository<Scrap, Long> {
    List<Scrap> findAllByUser_Id(Long userId);
}

