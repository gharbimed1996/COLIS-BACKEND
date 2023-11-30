package com.colis.paypal.query.repositories;



import com.colis.paypal.query.entity.Ad;
import org.springframework.data.jpa.repository.JpaRepository;



public interface AdRepository extends JpaRepository<Ad,String> {

}