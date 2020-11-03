package homework2.service.impl;

import homework2.dao.BucketDao;
import homework2.dao.model.Bucket;
import homework2.exception.NotFoundException;
import homework2.service.BucketService;

import java.sql.SQLException;

public class BucketServiceImpl implements BucketService {
    public BucketServiceImpl(BucketDao bucketDao) {
        this.bucketDao = bucketDao;
    }

    private BucketDao bucketDao;



    @Override
    public Bucket read(int id) throws SQLException, NotFoundException {
       Bucket bucket = bucketDao.read(id);
       if (bucket == null){
           throw new NotFoundException("Exist Bucket");
       }
       return bucket;

    }

    @Override
    public void create(Bucket bucket) throws SQLException {



    }

    @Override
    public void delete(int id) throws SQLException, NotFoundException {
        if (!bucketDao.delete(id)){
            throw new NotFoundException("Bucket doesn`t exist");
        }
       
    }
}
