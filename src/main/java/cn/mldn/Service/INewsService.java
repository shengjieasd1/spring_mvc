package cn.mldn.Service;

import cn.mldn.vo.News;

public interface INewsService {
    public boolean add(News vo);
    public News get(Long nid);
}
