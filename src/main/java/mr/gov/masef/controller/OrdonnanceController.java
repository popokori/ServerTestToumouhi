package mr.gov.masef.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import mr.gov.masef.entites.Ordonnance;
import mr.gov.masef.service.OrdonnanceService;

import java.util.List;

@RestController
@RequestMapping("/api/ordonnances")
public class OrdonnanceController {

    @Autowired
    private OrdonnanceService ordonnanceService;

    @PostMapping
    public Ordonnance createOrdonnance(@RequestBody Ordonnance ordonnance) {
        return ordonnanceService.saveOrdonnance(ordonnance);
    }

    @GetMapping
    public List<Ordonnance> getAllOrdonnances() {
        return ordonnanceService.getAllOrdonnances();
    }

    @GetMapping("/{id}")
    public Ordonnance getOrdonnanceById(@PathVariable Long id) {
        return ordonnanceService.getOrdonnanceById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteOrdonnance(@PathVariable Long id) {
        ordonnanceService.deleteOrdonnance(id);
    }
}
