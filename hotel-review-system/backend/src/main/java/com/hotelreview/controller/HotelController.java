package com.hotelreview.controller;

import com.hotelreview.model.Hotel;
import com.hotelreview.repository.HotelRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/hotels")
public class HotelController {

    private final HotelRepository hotelRepository;

    public HotelController(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    // GET all hotels
    @GetMapping
    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }

    // GET single hotel
    @GetMapping("/{id}")
    public ResponseEntity<Hotel> getHotelById(@PathVariable Long id) {
        return hotelRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Search hotels by name or location
    @GetMapping("/search")
    public List<Hotel> searchHotels(@RequestParam String query) {
        return hotelRepository.findByNameContainingIgnoreCaseOrLocationContainingIgnoreCase(query, query);
    }

    // CREATE hotel
    @PostMapping
    public ResponseEntity<Hotel> createHotel(@Valid @RequestBody Hotel hotel) {
        Hotel saved = hotelRepository.save(hotel);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    // UPDATE hotel
    @PutMapping("/{id}")
    public ResponseEntity<Hotel> updateHotel(@PathVariable Long id, @Valid @RequestBody Hotel updated) {
        return hotelRepository.findById(id)
                .map(hotel -> {
                    hotel.setName(updated.getName());
                    hotel.setLocation(updated.getLocation());
                    hotel.setDescription(updated.getDescription());
                    hotel.setImageUrl(updated.getImageUrl());
                    return ResponseEntity.ok(hotelRepository.save(hotel));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE hotel
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteHotel(@PathVariable Long id) {
        if (!hotelRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        hotelRepository.deleteById(id);
        return ResponseEntity.ok(Map.of("message", "Hotel deleted successfully"));
    }
}
