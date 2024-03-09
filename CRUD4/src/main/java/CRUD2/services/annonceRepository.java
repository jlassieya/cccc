package CRUD2.services;

import org.springframework.data.jpa.repository.JpaRepository;

import CRUD2.model.annonce;

public interface annonceRepository extends JpaRepository<annonce,Integer> {

}