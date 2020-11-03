package homework1.service;

import homework1.dao.Audit;

import java.sql.SQLException;

public interface AuditService {

void createAudit(Audit audit) throws SQLException, ClassNotFoundException;


}

