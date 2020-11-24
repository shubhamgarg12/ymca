package com.ymcatpo.app.topapp.Dao.TPO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ymcatpo.app.topapp.entity.Tpo.Company;

@Repository
public interface CompanyDao extends JpaRepository<Company, Long> {

}
