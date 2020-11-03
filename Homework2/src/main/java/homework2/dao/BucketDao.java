package homework2.dao;

import homework2.dao.model.Bucket;

import java.sql.SQLException;

public interface BucketDao {
    Bucket read(int id) throws SQLException;

    void create(Bucket bucket) throws SQLException;

    boolean delete(int id) throws SQLException;
}
