package com.devops.tools.engineer.appClientSales.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devops.tools.engineer.appClientSales.dao.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

}
