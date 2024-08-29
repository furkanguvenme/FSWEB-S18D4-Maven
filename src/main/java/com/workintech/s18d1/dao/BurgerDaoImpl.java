package com.workintech.s18d1.dao;

import com.workintech.s18d1.entity.BreadType;
import com.workintech.s18d1.entity.Burger;
import com.workintech.s18d1.exceptions.BurgerException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Repository
public class BurgerDaoImpl implements BurgerDao{

    @Autowired
    private EntityManager entityManager;

    @Transactional
    @Override
    public Burger save(Burger burger) {
        entityManager.persist(burger);
        return burger;
    }

    @Override
    public Burger findById(Long id) {
        Burger burger = entityManager.find(Burger.class, id);
        if(burger == null){
            throw new BurgerException("Adam gibi id gir" , HttpStatus.BAD_REQUEST);
        }
        return burger;
    }

    @Override
    public List<Burger> findAll() {
        TypedQuery<Burger> burgers = entityManager.createQuery("SELECT b FROM Burger b", Burger.class);
        return burgers.getResultList();
    }

    @Override
    public List<Burger> findByPrice(double price) {
        TypedQuery<Burger> burgers = entityManager.createQuery("SELECT b FROM Burger b WHERE b.price > :price ORDER BY b.price DESC", Burger.class);
        return burgers.getResultList();
    }

    @Override
    public List<Burger> findByBreadType(BreadType breadType) {
        TypedQuery<Burger> burgers = entityManager.createQuery("SELECT b FROM Burger b WHERE b.breadType = :breadType ORDER BY b.name ASC", Burger.class);
        return burgers.getResultList();
    }

    @Override
    public List<Burger> findByContent(String content) {
        TypedQuery<Burger> burgers = entityManager.createQuery("SELECT b FROM Burger b WHERE b.content = :content", Burger.class);
        return burgers.getResultList();
    }

    @Transactional
    @Override
    public Burger update(Burger burger) {
        return entityManager.merge(burger);
    }

    @Transactional
    @Override
    public Burger remove(long id) {
        Burger burger = entityManager.find(Burger.class, id);
        entityManager.remove(burger);
        return burger;
    }
}
