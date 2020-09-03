package cn.mldn.Dao;

import cn.mldn.vo.News;

public interface INewsDAO {
    public boolean doCreate(News vo);
    public News findById(Long nid);
}
