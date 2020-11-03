package homework2.service;

import homework2.dao.model.Bucket;
import homework2.exception.NotFoundException;

import java.sql.SQLException;

public interface BucketService {

        Bucket read(int id) throws SQLException, NotFoundException;

        void create(Bucket bucket) throws SQLException;

        void delete(int id) throws SQLException, NotFoundException;
}
