package org.example.controller;

import org.example.entity.Advertisement;
import org.example.service.AdvertisementService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/market")
public class MarketController {

    private final AdvertisementService advertisementService;

    public MarketController(AdvertisementService advertisementService) {
        this.advertisementService = advertisementService;
    }

    @GetMapping
    public String index(@RequestParam(defaultValue = "0") int page,
                        @RequestParam(defaultValue = "date_desc") String sort,
                        Model model) {
        Page<Advertisement> ads = advertisementService.findAllSorted(sort, PageRequest.of(page, 6));
        model.addAttribute("ads", ads);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", ads.getTotalPages());
        model.addAttribute("sort", sort);
        return "index";
    }

    @GetMapping("/item/{id}")
    public String item(@PathVariable String id, Model model) {
        advertisementService.findById(id).ifPresent(ad -> model.addAttribute("ad", ad));
        return "item";
    }

    @GetMapping("/new")
    public String newItem() {
        return "new-item";
    }

    @PostMapping("/new")
    public String createItem(@ModelAttribute Advertisement ad) {
        ad.setSubmissionTime(LocalDateTime.now());
        advertisementService.save(ad);
        return "redirect:/market";
    }
}
