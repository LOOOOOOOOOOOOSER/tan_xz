package world.tan_xz.Mapper;


import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import world.tan_xz.entity.HotIssue;

import java.util.List;

@Mapper
@Repository
public interface HotIssueMapper {
    @Insert("INSERT INTO hot_issue (user_id, content) VALUES (#{userId}, #{content})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(HotIssue hotIssue);

    @Select("SELECT * FROM hot_issue WHERE id = #{id}")
    HotIssue findById(Integer id);

    @Select("SELECT * FROM hot_issue")
    List<HotIssue> findAll();

    @Update("UPDATE hot_issue SET content = #{content} WHERE id = #{id}")
    void update(HotIssue hotIssue);

    @Delete("DELETE FROM hot_issue WHERE id = #{id}")
    void deleteById(Integer id);
}