package com.example.bulletinboard.repository;

import com.example.bulletinboard.domain.Advertisement;
import com.example.bulletinboard.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdvertisementRepository extends JpaRepository<Advertisement,Long> {
    List<Advertisement> getAdvertisementByUsers(Users users);
}
