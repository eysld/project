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
package kr.co.roda.cmmn.web;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.rte.fdl.property.EgovPropertyService;
import kr.co.roda.cmmn.service.RodaCmmnService;

@Controller
public class RodaCmmnController {

	@Resource(name = "cmmnService")
	private RodaCmmnService cmmnService;
	
	/** EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;

	/** Validator */
	@Resource(name = "beanValidator")
	protected DefaultBeanValidator beanValidator;
	
	// 테스트 화면
	@RequestMapping(value = "/mapMove.do")
	public String mapMove(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
		
		//해시맵 선언
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		// 테스트값 디비에서 불러오기
		List<?> timeznLst = cmmnService.selectList("CmmnDao.selectTimeznList", null);
		
		// 디비에서 조회해온 데이터 화면단으로 테스트값 보내기
		model.addAttribute("timeznLst", timeznLst);
		
		return "mapInfo/map";
	}
	
	
	@RequestMapping(value = "/searchInfo1.do")
	public String searchInfo1(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
		
		String strStartTimeVal = request.getParameter("startTimeSel").toString();
		//해시맵 선언
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		
		
		List<?> happnsWelfrCntrLst = cmmnService.selectList("CmmnDao.selectHappnsWelfrCntrList", null);
		
		// 디비에서 조회해온 데이터 화면단으로 테스트값 보내기
		model.addAttribute("happnsWelfrCntrLst", happnsWelfrCntrLst);
		
		return "mapInfo/map";
	}
	
	
	 @RequestMapping(value = "/searchInfo.do")
	 @ResponseBody
	 public ModelAndView searchInfo(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
		 String strStartTimeVal = request.getParameter("startTimeSel").toString();
		 String strEndTimeSel 	= request.getParameter("endTimeSel").toString();
		 ModelAndView mv = new ModelAndView("jsonView");
	  

		 List<?> happnsWelfrCntrLst = cmmnService.selectList("CmmnDao.selectHappnsWelfrCntrList", null);
		
		 // 디비에서 조회해온 데이터 화면단으로 테스트값 보내기
		 mv.addObject("happnsWelfrCntrLst", happnsWelfrCntrLst);
		 
		 
		 HashMap<String, Object> paramMap = new HashMap<String, Object>();
		 
		 paramMap.put("startTime", 	strStartTimeVal);
		 paramMap.put("endTime", 	strEndTimeSel);
		 List<?> amdTotalValueLst = cmmnService.selectList("CmmnDao.selectAmdTotalValueList", paramMap);
		 mv.addObject("amdTotalValueLst", amdTotalValueLst);
		 
	
		 //mv.addObject("nUpdateCnt",   nUpdateCnt);

		 return mv;

	 }
	
	
	}
