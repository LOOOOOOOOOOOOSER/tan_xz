package world.tan_xz.controller;


import org.springframework.http.ResponseEntity;
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
    @DeleteMapping("/{id}")
    public void deleteFavorite(@PathVariable Integer id) {

        favoriteService.delete(id);
    }
    @DeleteMapping("/favorites/{favoriteId}")
    public ResponseEntity<Void> removeFavorite(@PathVariable Integer favoriteId) {
        // 实现删除收藏的逻辑
        // 例如：favoriteService.removeFavorite(favoriteId);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/user/{userId}")
    public List<Favorite> getFavoritesByUserId(@PathVariable Integer userId) {
        return favoriteService.getFavoritesByUserId(userId);
    }
}