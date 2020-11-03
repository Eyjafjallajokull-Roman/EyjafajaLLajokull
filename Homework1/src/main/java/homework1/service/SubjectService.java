package homework1.service;

import homework1.dao.Subject;
import homework1.exception.NoSuchSubjectException;

import java.sql.SQLException;
import java.util.List;

public interface SubjectService {
    List<Subject> getAll() throws SQLException, ClassNotFoundException;
    Subject getById(int id) throws SQLException, ClassNotFoundException, NoSuchSubjectException;
    boolean checkSubjectById(int id) throws SQLException, ClassNotFoundException;
    void updateSubjectName(String subjectName, String newName) throws SQLException, ClassNotFoundException;
    void deleteSubjectById(int id) throws SQLException, ClassNotFoundException;
}
