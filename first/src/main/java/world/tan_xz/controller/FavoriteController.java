package world.tan_xz.controller;


import world.tan_xz.entity.Favorite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import world.tan_xz.service.FavoriteServiceimpl;

import java.util.List;

@RestController
@RequestMapping("/favorites")
public class FavoriteController extends BaseController<Favorite>{

    @Autowired
    private FavoriteServiceimpl favoriteService;

    @PostMapping("/issue/{issueId}")
    public Favorite addFavorite(@PathVariable Integer issueId, @RequestBody Favorite favorite) {
        favorite.setIssueId(issueId);
        favorite.setUserId(loginUser.getId());
        return favoriteService.save(favorite);
    }
    @GetMapping("/user/{userId}")
    public List<Favorite> getFavoritesByUserId(@PathVariable Integer userId) {
        return favoriteService.getFavoritesByUserId(userId);
    }
}