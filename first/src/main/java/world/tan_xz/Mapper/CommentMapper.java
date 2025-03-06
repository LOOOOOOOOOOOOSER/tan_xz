package world.tan_xz.Mapper;


import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import world.tan_xz.entity.Comment;

import java.util.List;

@Mapper
@Repository
public interface CommentMapper {
    @Insert("INSERT INTO comment (user_id, issue_id, content) VALUES (#{userId}, #{issueId}, #{content})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(Comment comment);

    @Select("SELECT * FROM comment WHERE id = #{id}")
    Comment findById(Integer id);

    @Select("SELECT * FROM comment WHERE issue_id = #{issueId}")
    List<Comment> findByIssueId(Integer issueId);

    @Update("UPDATE comment SET content = #{content} WHERE id = #{id}")
    void update(Comment comment);

    @Delete("DELETE FROM comment WHERE id = #{id}")
    void deleteById(Integer id);
}