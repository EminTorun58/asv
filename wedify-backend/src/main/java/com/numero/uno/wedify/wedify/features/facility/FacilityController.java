package com.numero.uno.wedify.wedify.features.facility;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
public class FacilityController {

    private final IFacilityService facilityService;

    public FacilityController(IFacilityService facilityService) {
        this.facilityService = facilityService;

    }

    @GetMapping(path = "/facilities/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Facility getFacilityById(@PathVariable Long id) {

        return facilityService.getFacilityById(id);
    }

    @GetMapping(path = "/facilities")
    @ResponseStatus(HttpStatus.OK)
    public List<Facility> getAllFacilities() {

        return facilityService.getAllFacilities();
    }

    @GetMapping(path = "/companies/{id}/facilities")
    @ResponseStatus(HttpStatus.OK)
    public List<Facility> getFacilitiesByCompany(@PathVariable Long id) {

        return facilityService.getFacilitiesByCompany(id);
    }

    @DeleteMapping(path = "/facilities/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("isAuthenticated()")
    public void deleteFacility(@PathVariable Long id) {
        facilityService.deleteFacility(id);
    }


    @GetMapping(path = "/facility/{id}/images")
    @ResponseStatus(HttpStatus.OK)
    public String getImages(@PathVariable Long id) {
        return facilityService.getImages(id);
    }


    @PostMapping(path = "/facility/{id}/images")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("isAuthenticated()")
    public void uploadImage(@PathVariable Long id, @RequestParam("file") MultipartFile file) {
        facilityService.uploadImage(file, id);
    }

    @DeleteMapping(path = "/facility/{id}/images")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("isAuthenticated()")
    public void removeImage(@RequestParam("filename") String filename, @PathVariable Long id) {
        facilityService.removeImage(filename, id);
    }


}
