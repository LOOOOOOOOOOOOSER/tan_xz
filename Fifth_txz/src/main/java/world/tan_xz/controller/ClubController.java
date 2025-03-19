package world.tan_xz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import world.tan_xz.entity.Club;
import world.tan_xz.service.ClubService;

import java.util.List;

/**
 * @author 谭轩钊
 * version 1.0
 */
@RestController
@RequestMapping("/clubs")
public class ClubController {

    @Autowired
    private ClubService clubService;

    @GetMapping
    public List<Club> getAllClubs() {
        return clubService.getAllClubs();
    }

    @GetMapping("/{id}")
    public Club getClub(@PathVariable Integer id) {
        return clubService.getClubById(id);
    }

    @PostMapping
    public Club saveClub(@RequestBody Club club) {
        return clubService.saveClub(club);
    }

    @DeleteMapping("/{id}")
    public void deleteClub(@PathVariable Integer id) {
        clubService.deleteClub(id);
    }

    @GetMapping("/type/{type}")
    public List<Club> getClubsByType(@PathVariable Integer type) {
        return clubService.getClubsByType(type);
    }


}