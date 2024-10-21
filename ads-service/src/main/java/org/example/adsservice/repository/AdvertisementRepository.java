package org.example.adsservice.repository;

import org.example.adsservice.domain.Advertisement;
import org.example.adsservice.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdvertisementRepository extends JpaRepository<Advertisement,Long> {
    List<Advertisement> getAdvertisementsByUser(User user);
}
