package cn.mldn.Service.Impl;

import cn.mldn.Dao.INewsDAO;
import cn.mldn.Service.INewsService;
import cn.mldn.vo.News;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.annotation.Resource;

@Service
public class NewsServiceImpl implements INewsService {
    @Resource //可以注入的只是一个接口实例化对象，但是这些对象还需要配置隔离级别、传播属性
    private PlatformTransactionManager transactionManager;
    @Resource
    private INewsDAO newsDAO;
    @Override
    public boolean add(News vo) {
        return this.newsDAO.doCreate(vo);
    }
    @Override
    public News get(Long nid) {
        return this.newsDAO.findById(nid);
    }
}
