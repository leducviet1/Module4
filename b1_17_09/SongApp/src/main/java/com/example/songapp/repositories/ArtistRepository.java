//package com.example.songapp.repositories;
//
//import com.example.songapp.models.Artist;
//import jakarta.persistence.EntityManager;
//import jakarta.persistence.PersistenceContext;
//import jakarta.transaction.Transactional;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
//@Repository
//public class ArtistRepository implements IArtistRepository {
//    @PersistenceContext
//    private EntityManager entityManager;
//
//    @Override
//    public List<Artist> getAll() {
//        return entityManager.createQuery("from Artist", Artist.class).getResultList();
//    }
//
//    @Override
//    public Artist getById(int id) {
//        return entityManager.find(Artist.class, id);
//    }
//
//    @Override
//    @Transactional
//    public Artist create(Artist artist) {
//        entityManager.persist(artist); //yêu cầu transaction
//        return  artist;
//    }
//
//    @Override
//    @Transactional
//    public Artist update(int id, Artist artist) {
//        return entityManager.merge(artist);
//    }
//
//    @Override
//    public void delete(int id) {
//        Artist artist = getById(id);
//        entityManager.remove(artist);
//    }
//}
