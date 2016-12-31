package com.yyh.dr.repository;

import com.yyh.dr.domain.CompanyType;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the CompanyType entity.
 */
@SuppressWarnings("unused")
public interface CompanyTypeRepository extends JpaRepository<CompanyType,Long> {

}
