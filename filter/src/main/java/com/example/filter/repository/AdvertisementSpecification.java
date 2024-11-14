package com.example.filter.repository;

import com.example.filter.domain.Advertisement;
import com.example.filter.domain.Category;
import org.springframework.data.jpa.domain.Specification;

public class AdvertisementSpecification {
    public static Specification<Advertisement> build() {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.conjunction());
    }
    public static Specification<Advertisement> findByUsrAdver(Long user_id) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder
        .equal(root.get("user_id").get("id"), user_id));
    }
    public static Specification<Advertisement> findByCategory(Category category) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder
                .equal(root.get("category"), category));
    }
    public static Specification<Advertisement> findByName(String name) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder
                .like(root.get("title"), "%" + name + "%"));
    }
    public static Specification<Advertisement> findByDescription(String description) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder
                .like(root.get("description"), "%" + description + "%"));
    }
    public static Specification<Advertisement> findByLocation(String location) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder
                .equal(root.get("location"), location));
    }
}
