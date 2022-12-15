package com.kit.meta_chat.repository;

import com.kit.meta_chat.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Integer> {
}