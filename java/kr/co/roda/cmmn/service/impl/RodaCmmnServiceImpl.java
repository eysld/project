/*
 * Copyright 2008-2009 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package kr.co.roda.cmmn.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import kr.co.roda.cmmn.service.RodaCmmnService;

@Service("cmmnService")
public class RodaCmmnServiceImpl extends EgovAbstractServiceImpl implements RodaCmmnService {

	private static final Logger LOGGER = LoggerFactory.getLogger(RodaCmmnServiceImpl.class);

	/** SampleDAO */
	// TODO ibatis 사용
	@Resource(name = "cmmnDAO")
	private RodaCmmnDAO cmmnDAO;
	
	
	@Override
	public void insertMap(String _strQryId, HashMap<String, Object> _paramMap) throws Exception {
		cmmnDAO.insertMap(_strQryId, _paramMap);		
	}

	
	@Override
	public int updateMap(String _strQryId, HashMap<String, Object> _paramMap) throws Exception {
		return cmmnDAO.updateMap(_strQryId, _paramMap);
	}

	
	@Override
	public int deleteMap(String _strQryId, HashMap<String, Object> _paramMap) throws Exception {
		return cmmnDAO.deleteMap(_strQryId, _paramMap);
	}

	
	@Override
	public HashMap<String, Object> selectMap(String _strQryId, HashMap<String, Object> _paramMap) throws Exception {
		HashMap<String, Object> resultMap = cmmnDAO.selectMap(_strQryId, _paramMap);

		return resultMap;
	}

	
	@Override
	public List<?> selectList(String _strQryId, HashMap<String, Object> _paramMap) throws Exception {
		return cmmnDAO.selectList(_strQryId, _paramMap);
	}

	
	@Override
	public int selectListTotCnt(String _strQryId, HashMap<String, Object> _paramMap) {
		return cmmnDAO.selectListTotCnt(_strQryId, _paramMap);
	}

	@Override
	public List<HashMap<String, Object>> selectHashList(String _strQryId, HashMap<String, Object> _paramMap) throws Exception {
		return cmmnDAO.selectHashList(_strQryId, _paramMap);
	}

}
