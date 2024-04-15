package com.pickkasso.domain.painting.dao;

import com.pickkasso.domain.painting.domain.Painting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaintingRepository extends JpaRepository<Painting, Long> {
    List<Painting> findByUserId(Long userId);
    List<Painting> findByState(boolean paintingState);
}
