package com.ymcatpo.app.topapp.ServiceImplementation.Tpo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ymcatpo.app.topapp.Dao.TPO.TpoDao;
import com.ymcatpo.app.topapp.entity.Tpo.TpoDetails;
import com.ymcatpo.app.topapp.exception.ApiException;
import com.ymcatpo.app.topapp.serviceInterface.Tpo.TpoService;
@Service
public class TpoServiceImpl implements TpoService {
	@Autowired
	TpoDao tpoDao;
	
	@Override
	public TpoDetails getTpo(String tpoId) {
		TpoDetails tpo= tpoDao.findById(tpoId).orElseThrow( ()-> new ApiException("Tpo id is wrong", HttpStatus.NO_CONTENT)   );
		return tpo;
	}
	@Override
	public TpoDetails putTpo(TpoDetails tpo, String tpoId) {
		 tpoDao.findById(tpoId).orElseThrow( ()-> new ApiException("Tpo id is wrong", HttpStatus.NO_CONTENT)  );
		 return tpoDao.save(tpo);
		
	}
	
	public boolean isTpoPresent(String tpoId) {
		tpoDao.findById(tpoId).orElseThrow( ()-> new ApiException("Tpo id is wrong", HttpStatus.NO_CONTENT)  );
		return true;
	}

	@Override
	public void createTpo(TpoDetails tpo) {
	 	tpoDao.save(tpo);
		
		
	}

}
