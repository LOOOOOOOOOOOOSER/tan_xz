package world.tan_xz.Mapper;


import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import world.tan_xz.entity.Favorite;

import java.util.List;

@Mapper
@Repository
public interface FavoriteMapper {
    @Insert("INSERT INTO favorite (user_id, issue_id) VALUES (#{userId}, #{issueId})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(Favorite favorite);

    @Select("SELECT * FROM favorite WHERE id = #{id}")
    Favorite findById(Integer id);

    @Select("SELECT * FROM favorite WHERE user_id = #{userId}")
    List<Favorite> findByUserId(Integer userId);

    @Select("SELECT * FROM favorite WHERE issue_id = #{issueId}")
    List<Favorite> findByIssueId(Integer issueId);

    @Delete("DELETE FROM favorite WHERE id = #{id}")
    void deleteById(Integer id);
}