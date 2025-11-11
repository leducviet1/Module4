//package com.example.songapp.repositories;
//
//import com.example.songapp.models.Song;
//import jakarta.persistence.EntityManager;
//import jakarta.persistence.PersistenceContext;
//import jakarta.persistence.TypedQuery;
//import jakarta.transaction.Transactional;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//@Repository
//public class SongRepository implements ISongRepository {
//    @PersistenceContext
//    private  EntityManager entityManager;
//
//    @Override
//    public List<Song> getAll() {
//        String query = "SELECT s FROM Song s ORDER BY s.id DESC";
//        TypedQuery<Song> query1 = entityManager.createQuery(query, Song.class);
//        return query1.getResultList();
//    }
//
//    @Override
//    public Song getById(int id) {
//        String query = "SELECT s FROM Song s WHERE s.id = :id";
//        TypedQuery<Song> typedQuery = entityManager.createQuery(query, Song.class);
//        typedQuery.setParameter("id", id);
//        return typedQuery.getSingleResult();
//    }
//
//    //transaction
////    @Override
////    public Song create(Song song) {
////        Transaction tx = null;
////        Session session = sessionFactory.openSession();
////        tx = session.beginTransaction();
////        Song savedSong =  new Song();
////        savedSong.setTitle(song.getTitle());
////        savedSong.setArtist(song.getArtist());
////        savedSong.setGenre(song.getGenre());
////        savedSong.setFilePath(song.getFilePath());
////        session.persist(savedSong);
////        tx.commit();
////        session.close();
////        return savedSong;
////    }
//    @Override
//    @Transactional
//    public Song create(Song song) {
//        entityManager.persist(song);
//        return song;
//    }
//
//    @Override
//    @Transactional
//    public Song update(int id, Song song) {
//        Song savedSong = getById(id);
//        savedSong.setTitle(song.getTitle());
//        savedSong.setArtist(song.getArtist());
//        savedSong.setGenre(song.getGenre());
//        savedSong.setFilePath(song.getFilePath());
//        savedSong = entityManager.merge(savedSong);
//        return savedSong;
//    }
//
//    //    @Override
////    public void delete(int id) {
////        Song savedSong = getById(id);
////        Transaction tx = null;
////        Session session = sessionFactory.openSession();
////        tx = session.beginTransaction();
////        session.remove(savedSong);
////        tx.commit();
////        session.close();
////    }
//    @Override
//    public void delete(int id) {
//        entityManager.remove(getById(id));
//    }
//}
