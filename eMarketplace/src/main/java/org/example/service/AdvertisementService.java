package org.example.service;

import org.example.entity.Advertisement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface AdvertisementService {
    Page<Advertisement> findAllSorted(String sortBy, Pageable pageable);
    Optional<Advertisement> findById(String id);
    Advertisement save(Advertisement advertisement);
}
