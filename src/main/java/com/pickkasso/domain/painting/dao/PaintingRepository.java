// PaintingRepository
package com.pickkasso.domain.painting.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pickkasso.domain.painting.domain.Painting;

@Repository
public interface PaintingRepository extends JpaRepository<Painting, Long> {
    List<Painting> findByMemberId(Long memberId);

    List<Painting> findByPaintingState(boolean paintingState);
}
