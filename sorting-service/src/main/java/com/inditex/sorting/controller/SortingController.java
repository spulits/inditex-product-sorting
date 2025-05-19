package com.inditex.sorting.controller;

import com.inditex.sorting.dto.SortRequestDTO;
import com.inditex.sorting.dto.SortResultDTO;
import com.inditex.sorting.service.SortingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/sort")
@RequiredArgsConstructor
public class SortingController {
    
    private final SortingService sortingService;
    
    @PostMapping
    public ResponseEntity<List<SortResultDTO>> sortProducts(@Valid @RequestBody SortRequestDTO sortRequest) {
        List<SortResultDTO> results = sortingService.sortProducts(sortRequest);
        return ResponseEntity.ok(results);
    }
    
    @GetMapping("/criteria")
    public ResponseEntity<List<String>> getAvailableCriteria() {
        return ResponseEntity.ok(sortingService.getAvailableCriteria());
    }
}
