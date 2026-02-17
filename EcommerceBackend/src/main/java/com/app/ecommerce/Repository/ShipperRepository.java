package com.app.ecommerce.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.ecommerce.model.Shipper;

@Repository
public interface ShipperRepository extends JpaRepository<Shipper, Integer>{

}
