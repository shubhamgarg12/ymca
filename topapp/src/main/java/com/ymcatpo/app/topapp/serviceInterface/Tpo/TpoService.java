package com.ymcatpo.app.topapp.serviceInterface.Tpo;

import com.ymcatpo.app.topapp.entity.Tpo.TpoDetails;

public interface TpoService {
	
	TpoDetails getTpo(String tpoId);
	void createTpo(TpoDetails tpo);
	TpoDetails putTpo(TpoDetails tpo,String tpoId);

}
