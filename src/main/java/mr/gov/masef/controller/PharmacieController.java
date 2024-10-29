package mr.gov.masef.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import mr.gov.masef.entites.Pharmacie;
import mr.gov.masef.service.PharmacieService;

import java.util.List;

@RestController
@RequestMapping("/api/pharmacies")
public class PharmacieController {

    @Autowired
    private PharmacieService pharmacieService;

    @PostMapping
    public Pharmacie createPharmacie(@RequestBody Pharmacie pharmacie) {
        return pharmacieService.savePharmacie(pharmacie);
    }

    @GetMapping
    public List<Pharmacie> getAllPharmacies() {
        return pharmacieService.getAllPharmacies();
    }

    @GetMapping("/{id}")
    public Pharmacie getPharmacieById(@PathVariable Long id) {
        return pharmacieService.getPharmacieById(id);
    }

    @DeleteMapping("/{id}")
    public void deletePharmacie(@PathVariable Long id) {
        pharmacieService.deletePharmacie(id);
    }
}
