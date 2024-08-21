package com.lcwd.electronic.store.repositories;

import com.lcwd.electronic.store.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,String> {

    //search
    Page<Product> findByTitleContaining(String subTitle, Pageable pageable);

    //check product is live or not
    Page<Product> findByLiveTrue(Pageable pageable);

    //Custom finder methods
    //query method
}
