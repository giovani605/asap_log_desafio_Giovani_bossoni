package com.example.asap_log_deasafio.repository;

import com.example.asap_log_deasafio.entity.Cliente;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ClienteRepository extends MongoRepository<Cliente, String> {

}