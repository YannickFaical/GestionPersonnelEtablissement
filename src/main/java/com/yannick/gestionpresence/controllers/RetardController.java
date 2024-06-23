package com.yannick.gestionpresence.controllers;

import com.yannick.gestionpresence.entities.Retard;
import com.yannick.gestionpresence.service.RetardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/retards")
public class RetardController {


@Autowired
private RetardService retardService;

@GetMapping
public List<Retard> getAllRetards() {
    return retardService.getAllRetards();
}

@GetMapping("/{id}")
public Retard getRetardById(@PathVariable int id) {
    return retardService.getRetardById(id);
}

@PostMapping
public Retard addRetard(@RequestBody Retard retard) {
    return retardService.saveRetard(retard);
}

@DeleteMapping("/{id}")
public void deleteRetard(@PathVariable int id) {
    retardService.deleteRetard(id);
}
}
