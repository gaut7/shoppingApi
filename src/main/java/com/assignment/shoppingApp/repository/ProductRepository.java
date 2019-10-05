package com.assignment.shoppingApp.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.assignment.shoppingApp.dto.ProductDTO;


@Repository
public interface ProductRepository extends JpaRepository<ProductDTO, Integer> {

	@Query(value = "from ProductDTO p where p.store.id= :storeId")
	 List<ProductDTO> getProductByStoreId(@Param("storeId")int storeId);

	 List<ProductDTO> findByName(String name); 

}
