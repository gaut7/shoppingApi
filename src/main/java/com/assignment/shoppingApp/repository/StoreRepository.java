package com.assignment.shoppingApp.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.assignment.shoppingApp.dto.ProductDTO;
import com.assignment.shoppingApp.dto.StoreDTO;

@Repository
public interface StoreRepository extends JpaRepository<StoreDTO, Integer> {
	
	List<StoreDTO> findAll();

	@Query(" from StoreDTO st where st.id= :storeId")
	List<ProductDTO> findProductListById(@Param("storeId")int storeId);
}
