package com.assignment.shoppingApp.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.assignment.shoppingApp.dto.ProductDTO;
import com.assignment.shoppingApp.dto.UserFavouritItemDTO;

public interface UserFavouritItemrepository extends JpaRepository<UserFavouritItemDTO, Integer>{

	@Query(" from ProductDTO p INNER JOIN UserFavouritItemDTO uf on uf.product.id = p.id where uf.user.id= :userId")
	List<ProductDTO> getProductListByUserId(@Param("userId")int userId);

	@Transactional
	@Modifying
	@Query(" delete from UserFavouritItemDTO uf where uf.product.id= :productId and uf.user.id= :userId")
	void deleteFavouritByProductId(@Param("productId")int productId,@Param("userId")int userId);

}
