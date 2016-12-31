package com.yyh.dr.repository;

import com.yyh.dr.domain.LawenforceDepartment;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the LawenforceDepartment entity.
 */
@SuppressWarnings("unused")
public interface LawenforceDepartmentRepository extends JpaRepository<LawenforceDepartment,Long> {

}
