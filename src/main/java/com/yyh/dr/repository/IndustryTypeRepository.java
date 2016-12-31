package com.yyh.dr.repository;

import com.yyh.dr.domain.IndustryType;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the IndustryType entity.
 */
@SuppressWarnings("unused")
public interface IndustryTypeRepository extends JpaRepository<IndustryType,Long> {

}
