package com.example.myfirststringmvc.services;

import com.example.myfirststringmvc.models.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;

import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Service;


import java.util.List;

@Service  //tạo bean --> IOC
public class DBStudentService implements IStudentService {
    private static EntityManager entityManager;
    private static  SessionFactory sessionFactory;
    static {
        try{
            sessionFactory = new Configuration()
                    .configure("hibernate.conf.xml")
                    .buildSessionFactory();
            entityManager = sessionFactory.createEntityManager();

        }catch (HibernateException e){
            throw new RuntimeException(e);
        }
    }
    @Override
    public List<Student> findAll(String q, String  sort, String dir) {
        String query = "SELECT s FROM Student s";
        TypedQuery<Student> typedQuery = entityManager.createQuery(query, Student.class);
        return typedQuery.getResultList();
    }

    @Override
    public void save(Student student) {

    }

    @Override
    public Student getStudentById(String id) {
        String query = "SELECT s FROM Student s WHERE s.code = :id";
        TypedQuery<Student> typedQuery = entityManager.createQuery(query, Student.class);
        typedQuery.setParameter("id", id);
        return typedQuery.getSingleResult();
    }
}
