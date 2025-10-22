package com.example.CRMforDelivery.repository;

import com.example.CRMforDelivery.entity.Customer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository
        extends JpaRepository<Customer, Long> {

    @EntityGraph(value = "test")
    @Query("SELECT c FROM Customer c ")
    List<Customer> findAllWithOrdersUsingGraphEntity();

    //если у пользоваетля нет заказов, то пользователи не подргузятся
    @Query("SELECT c FROM Customer c JOIN FETCH c.orders")
    List<Customer> findAllWithOrdersUsingJoinFetch();


    @Query("SELECT c FROM Customer c LEFT JOIN FETCH c.orders")
    List<Customer> findAllWithOrdersUsingLeftJoinFetch();


}
