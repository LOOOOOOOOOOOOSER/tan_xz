package world.tan_xz.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import world.tan_xz.entity.Comment;
import world.tan_xz.entity.HotIssue;
import world.tan_xz.service.CommentServiceimpl;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController extends BaseController<Comment> {

    @Autowired
    private CommentServiceimpl commentService;
    private static final Logger logger = LoggerFactory.getLogger(CommentController.class);

    @PostMapping("/issue/{issueId}")
    public Comment addComment(@PathVariable Integer issueId, @RequestBody Comment comment) {
        try {
            comment.setIssueId(issueId);
            comment.setUserId(loginUser.getId());
            return commentService.save(comment);
        } catch (Exception e) {
            logger.error("Error adding comment: ", e);
            throw new RuntimeException("Failed to add comment", e);
        }
    }
    @GetMapping
    public List<Comment> getAllHotIssues() {
        return commentService.all();
    }
    @GetMapping("/issue/{issueId}")
    public List<Comment> getCommentsByIssueId(@PathVariable Integer issueId) {
        try {
            List<Comment> comments = commentService.getCommentsByIssueId(issueId);
            if (comments.isEmpty()) {
                logger.warn("No comments found for issueId: {}", issueId);
            }
            return comments;
        } catch (Exception e) {
            logger.error("Error getting comments by issueId {}: ", issueId, e);
            throw new RuntimeException("Failed to get comments", e);
        }
    }
}