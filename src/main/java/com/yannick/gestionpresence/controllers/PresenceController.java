package com.yannick.gestionpresence.controllers;

import com.yannick.gestionpresence.entities.Presence;
import com.yannick.gestionpresence.service.PresenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/presences")
public class PresenceController {

    @Autowired
    private PresenceService presenceService;

    @GetMapping
    public List<Presence> getAllPresences() {
        return presenceService.getAllPresences();
    }

    @GetMapping("/{id}")
    public Presence getPresenceById(@PathVariable int id) {
        return presenceService.getPresenceById(id);
    }

    @PostMapping
    public Presence addPresence(@RequestBody Presence presence) {
        return presenceService.savePresence(presence);
    }

    @DeleteMapping("/{id}")
    public void deletePresence(@PathVariable int id) {
        presenceService.deletePresence(id);
    }
}
