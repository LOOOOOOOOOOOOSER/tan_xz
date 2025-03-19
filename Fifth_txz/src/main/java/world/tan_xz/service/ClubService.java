package world.tan_xz.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import world.tan_xz.dao.ClubDao;
import world.tan_xz.entity.Club;
import world.tan_xz.utils.Assert;

import java.io.Serializable;
import java.util.List;

/**
 * @author 谭轩钊
 * version 1.0
 */
@Service
public class ClubService {

    @Autowired
    private ClubDao clubDao;

    public List<Club> getAllClubs() {
        return clubDao.selectList(null);
    }

    public Club getClubById(Integer id) {
        return clubDao.selectById(id);
    }

    public Club saveClub(Club club) {
        if (club.getId() == null) {
            clubDao.insert(club);
        } else {
            clubDao.updateById(club);
        }
        return clubDao.selectById(club.getId());
    }

    public void deleteClub(Integer id) {
        clubDao.deleteById(id);
    }

    public List<Club> getClubsByType(Integer type) {
        return clubDao.selectList(new QueryWrapper<Club>().eq("type", type));
    }
}

