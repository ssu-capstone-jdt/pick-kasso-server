package com.pickkasso.domain.auth.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pickkasso.domain.auth.domain.GoogleRefreshToken;

@Repository
public interface GoogleRefreshTokenRepository extends CrudRepository<GoogleRefreshToken, Long> {}
