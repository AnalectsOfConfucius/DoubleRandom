package com.yyh.dr.repository;

import com.yyh.dr.domain.LawenforceArea;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the LawenforceArea entity.
 */
@SuppressWarnings("unused")
public interface LawenforceAreaRepository extends JpaRepository<LawenforceArea,Long> {

}
