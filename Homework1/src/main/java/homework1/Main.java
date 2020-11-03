package homework1;

import homework1.exception.NoSuchSubjectException;
import homework1.service.AuditService;
import homework1.service.SubjectService;
import homework1.service.impl.AuditServiceImpl;
import homework1.service.impl.SubjectServiceImpl;

import java.sql.SQLException;

public class Main {
    private static AuditService auditService= new AuditServiceImpl();
    private  static SubjectService subjectService= new SubjectServiceImpl();

    public static void main(String[] args) throws SQLException, ClassNotFoundException, NoSuchSubjectException {
//        auditService.createAudit(new Audit(11,"356","32"));
//        subjectService.checkSubjectById(9);
        subjectService.checkSubjectById(11);
//        subjectService.getById(8);
    }
}
