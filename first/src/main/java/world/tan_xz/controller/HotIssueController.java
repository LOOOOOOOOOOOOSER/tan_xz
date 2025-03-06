package world.tan_xz.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import world.tan_xz.entity.Comment;
import world.tan_xz.entity.Favorite;
import world.tan_xz.entity.HotIssue;
import world.tan_xz.entity.User;
import world.tan_xz.service.CommentServiceimpl;
import world.tan_xz.service.FavoriteServiceimpl;
import world.tan_xz.service.HotIssueServiceimpl;

import java.util.List;

@RestController
@RequestMapping("/hot-issues")
public class HotIssueController extends BaseController<HotIssue> {

    @Autowired
    private HotIssueServiceimpl hotIssueService;

    @Autowired
    private CommentServiceimpl commentService;

    @Autowired
    private FavoriteServiceimpl favoriteService;

    @GetMapping
    public List<HotIssue> getAllHotIssues() {
        return hotIssueService.all();
    }

    @DeleteMapping("/{id}")
    public void deleteHotIssue(@PathVariable Long id) {
        hotIssueService.delete(id);
    }

    @PostMapping("/comments")
    public Comment addComment(@RequestBody Comment comment) {
        return commentService.save(comment);
    }
    @GetMapping("/{id}")
    public HotIssue getHotIssue(@PathVariable Integer id) {
        return hotIssueService.get(id);
    }
    @PostMapping
    public HotIssue addHotIssue(@RequestBody HotIssue hotIssue) {
        // 确保内容字段不为空
        if (hotIssue.getContent() == null || hotIssue.getContent().isEmpty()) {
            throw new IllegalArgumentException("内容不能为空");
        }

        hotIssue.setUserId(loginUser.getId());
        return hotIssueService.save(hotIssue);
    }
    @PostMapping("/favorites")
    public Favorite addFavorite(@RequestBody Favorite favorite) {
        return favoriteService.save(favorite);
    }
    @GetMapping("/comments/{issueId}")
    public List<Comment> getCommentsByIssueId(@PathVariable Integer issueId) {
        return hotIssueService.getCommentsByIssueId(issueId);
    }

    @GetMapping("/favorites/{userId}")
    public List<Favorite> getFavoritesByUserId(@PathVariable Integer userId) {
        return favoriteService.getFavoritesByUserId(userId);
    }
}