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

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("cmmnDAO")
public class RodaCmmnDAO extends EgovAbstractDAO {
	
	public void insertMap(String _strQryId, HashMap<String, Object> _paramMap) throws Exception {
		insert(_strQryId, _paramMap);
	}
	public int updateMap(String _strQryId, HashMap<String, Object> _paramMap) throws Exception {
		return update(_strQryId, _paramMap);
	}

	public int deleteMap(String _strQryId, HashMap<String, Object> _paramMap) throws Exception {
		return delete(_strQryId, _paramMap);
	}

	public HashMap<String, Object> selectMap(String _strQryId, HashMap<String, Object> _paramMap) throws Exception {
		return (HashMap<String, Object>) select(_strQryId, _paramMap);
	}

	public List<?> selectList(String _strQryId, HashMap<String, Object> _paramMap) throws Exception {
		return list(_strQryId, _paramMap);
	}

	public int selectListTotCnt(String _strQryId, HashMap<String, Object> _paramMap) {
		return (Integer) select(_strQryId, _paramMap);
	}
	public List<?> selectExcelDownloadList(String _strQryId, HashMap<String, Object> _paramMap) {
		return list(_strQryId, _paramMap);
	}
	
	public List<HashMap<String, Object>> selectHashList(String _strQryId,HashMap<String, Object> _paramMap) throws Exception {
		return (List<HashMap<String, Object>>) list(_strQryId,_paramMap);
	}

}
