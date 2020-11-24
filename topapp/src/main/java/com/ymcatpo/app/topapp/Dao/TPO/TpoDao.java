package com.ymcatpo.app.topapp.Dao.TPO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ymcatpo.app.topapp.entity.Tpo.TpoDetails;

@Repository
public interface TpoDao extends JpaRepository<TpoDetails, String> {

}
