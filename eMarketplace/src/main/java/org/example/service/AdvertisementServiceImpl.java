package org.example.service;

import org.example.entity.Advertisement;
import org.example.repository.AdvertisementRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdvertisementServiceImpl implements AdvertisementService {

    private final AdvertisementRepository repository;

    public AdvertisementServiceImpl(AdvertisementRepository repository) {
        this.repository = repository;
    }

    @Override
    public Page<Advertisement> findAllSorted(String sortBy, Pageable pageable) {
        Sort sort;
        switch (sortBy) {
            case "date_asc":
                sort = Sort.by("submissionTime").ascending();
                break;
            case "price_asc":
                sort = Sort.by("price").ascending();
                break;
            case "price_desc":
                sort = Sort.by("price").descending();
                break;
            case "date_desc":
            default:
                sort = Sort.by("submissionTime").descending();
                break;
        }
        Pageable sortedPageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);
        return repository.findAll(sortedPageable);
    }

    @Override
    public Optional<Advertisement> findById(String id) {
        return repository.findById(id);
    }

    @Override
    public Advertisement save(Advertisement advertisement) {
        return repository.save(advertisement);
    }
}
