package com.todolist.sea.adapter.out.persistence.repository;

import com.todolist.sea.adapter.out.persistence.entity.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SpringDataClienteRepository extends JpaRepository<ClienteEntity, UUID> {
}
