<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>맵화면</title>
<!-- css  -->
<link rel="stylesheet" href="/roda/css/main.css">
<link rel="stylesheet" href="/roda/css/font.css">
<link rel="stylesheet" href="/roda/css/reset.css">
<link rel="stylesheet" href="/roda/css/setting.css">
<link rel="stylesheet" href="/roda/css/load.css">
<style type="text/css">
.divCon {
	margin: 5px 0;
	width: 100% -20px;
	height: 160px;
	overflow-x: hidden;
	overflow-y: auto;
	border: 2px dotted #3388ff;
}
</style>
<!-- js -->
<script src="/roda/js/jquery/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="https://sgisapi.kostat.go.kr/OpenAPI3/auth/javascriptAuth?consumer_key=5546b294f09347dfbb58"></script>
<script type="text/javascript" src="https://code.jquery.com/jquery-latest.js"></script>
<script type="text/javascript" src="https://sgisapi.kostat.go.kr/OpenAPI3/auth/javascriptAuth?consumer_key=5546b294f09347dfbb58"></script>

<script type="text/javascript">
	//초기화
	var sgisMapObject = {};

	function sgisInitObject(id) {
		if (sgisMapObject[id] == undefined) {
			sgisMapObject[id] = {
				"map" : null,
				"maker" : null,
				"clusterMaker" : [],
				"polygon" : [],
				"polyline" : []
			}
		}
	}

	function sgisInitMap(id, config, x, y) {

		//필수 객체 초기화 

		sgisInitObject(id);

		//기본설정

		sgisConfig = {
			panControl : true // 지도 이동 컨트롤
			,
			scale : true // 축척 컨트롤 
			,
			measureControl : true
		// 측정 컨트롤 (면적, 길이)
		}

		if (undefined != config) {
			sgisConfig = Object.assign(sgisConfig, config);
		}
		if (sgisMapObject[id] != undefined && sgisMapObject[id]['map'] != null) {
			sgisMapObject[id]['map'].remove();
			sgisMapObject[id]['map'] = undefined;
		}
		sgisMapObject[id]['map'] = sop.map(id, sgisConfig);
		if (x == undefined || y == undefined) {
			sgisMapObject[id]['map'].setView(sop.utmk(989570,
					1817559.3181762695), 6);
		} else {
			sgisMapObject[id]['map'].setView(sop.utmk(x, y), 6);
		}

		return sgisMapObject[id];

	} 

	function restRequest(url, method, param, callbackSuccess) {
		$.ajax({
			type : method,
			async : false,
			url : url,
			dataType : "json",
			data : param,
			success : function(rtnData) {
				callbackSuccess(rtnData);
			}
		});
	}

	function polygonArea() {

		sgisInitObject("map");

		var today = new Date();
		var year = today.getFullYear() - 1;
		var dongNm = "";
		var dongIndex = "";

		url = "https://sgisapi.kostat.go.kr/OpenAPI3/auth/authentication.json";
		param = "&consumer_key=5546b294f09347dfbb58&consumer_secret=18b67c9c3338464a806b";
		geturl = "https://sgisapi.kostat.go.kr/OpenAPI3/boundary/hadmarea.geojson";
		restRequest(url, "get", param, function(data) {
			token = data.result.accessToken;
			// 동구
			getparam = "&accessToken=" + token + "&year=" + year
					+ "&adm_cd=25010";
			restRequest(geturl, "get", getparam, function(data01) {
				var polygons = [];
				for (i = 0; i < data01.features.length; i++) {
					p = data01.features[i].geometry.coordinates;
					polygons.push(p);
					dongIndex = data01.features[i].properties.adm_nm
							.lastIndexOf(" ") + 1;
					dongNm = data01.features[i].properties.adm_nm
							.substring(dongIndex);
					sgisAddPolygons('map', p, {
						"color" : "brown",
						"fillColor" : "brown"
					}, dongNm);
				}
			});

			// 중구

			getparam = "&accessToken=" + token + "&year=" + year
					+ "&adm_cd=25020";
			restRequest(geturl, "get", getparam, function(data02) {
				var polygons = [];
				for (i = 0; i < data02.features.length; i++) {
					p = data02.features[i].geometry.coordinates;
					polygons.push(p);
					dongIndex = data02.features[i].properties.adm_nm
							.lastIndexOf(" ") + 1;
					dongNm = data02.features[i].properties.adm_nm
							.substring(dongIndex);
					sgisAddPolygons('map', p, {
						"color" : "orange",
						"fillColor" : "orange"
					}, dongNm);
				}
			});
			// 서구
			getparam = "&accessToken=" + token + "&year=" + year
					+ "&adm_cd=25030";
			restRequest(geturl, "get", getparam, function(data03) {
				var polygons = [];
				for (i = 0; i < data03.features.length; i++) {
					p = data03.features[i].geometry.coordinates;
					polygons.push(p);
					dongIndex = data03.features[i].properties.adm_nm
							.lastIndexOf(" ") + 1;
					dongNm = data03.features[i].properties.adm_nm
							.substring(dongIndex);
					sgisAddPolygons('map', p, {
						"color" : "green",
						"fillColor" : "green"
					}, dongNm);
				}
			});
			// 유성구
			getparam = "&accessToken=" + token + "&year=" + year
					+ "&adm_cd=25040";
			restRequest(geturl, "get", getparam, function(data04) {
				var polygons = [];
				for (i = 0; i < data04.features.length; i++) {
					p = data04.features[i].geometry.coordinates;
					polygons.push(p);
					dongIndex = data04.features[i].properties.adm_nm
							.lastIndexOf(" ") + 1;
					dongNm = data04.features[i].properties.adm_nm
							.substring(dongIndex);
					sgisAddPolygons('map', p, {
						"color" : "purple",
						"fillColor" : "purple"
					}, dongNm);
				}
			});

			// 대덕구

			getparam = "&accessToken=" + token + "&year=" + year
					+ "&adm_cd=25050";
			restRequest(geturl, "get", getparam, function(data05) {
				var polygons = [];
				for (i = 0; i < data05.features.length; i++) {
					p = data05.features[i].geometry.coordinates;
					polygons.push(p);
					dongIndex = data05.features[i].properties.adm_nm
							.lastIndexOf(" ") + 1;
					dongNm = data05.features[i].properties.adm_nm
							.substring(dongIndex);
					sgisAddPolygons('map', p, {
						"color" : "black",
						"fillColor" : "black"
					}, dongNm);
				}
			});
		});
	}

	function sgisAddPolygons(id, param_polygon, config, context) {
		var sgisConfig = {
			stroke : true,
			color : "red",
			weight : 2,
			opacity : 0.4,
			fill : true,
			fillColor : "red",
			fillOpacity : 0.2
		}

		if (undefined != config) {
			sgisConfig = Object.assign(sgisConfig, config);
		}

		var polygon = sop.polygon(param_polygon, sgisConfig);
		if (context != undefined && context != "") {
			polygon.bindInfoWindow(context);
		}

		polygon.addTo(sgisMapObject[id]['map']);
		polygon.setCaption({
			title : context,
			color : "black"
		});

		sgisMapObject[id]['polygon'].push(polygon);
	}
	
	function addMarker(id){
        var marker = sop.marker([989134.913488497, 1811407.10612096]);
        marker.addTo(sgisMapObject[id]['map']);
   }
	
	function fnSearch() {
		
		// 조회 조건에 해당하는 폴리곤 값을 조회한다...
		// 그리고 마커 표시할 데이터와 폴리곤 값을 화면에 반영한다...
		//var frm = document.getElementById("frm");
		//frm.submit();
		
		
		// 마커를 표시할 데이터를 조회 한다....
		$.ajax({
	     url: "/searchInfo.do",
	     type: "POST",
	     data: $("#frm").serialize(),
	     dataType: "json", 
	     success: function(data) {
	    	// 조회한 데이터로 마커를 표시합니다...
	      	
	      	// 주소 값을 x, y로 바꾸자...
	      	
	      	var happnsWelfrCntrLstLength = data.happnsWelfrCntrLst.length;
	    	
	      	var amdTotalValueLstLength = data.amdTotalValueLst.length;

	      	sgisInitMap('map', {
				'measureControl' : true
			});
			var url = "https://sgisapi.kostat.go.kr/OpenAPI3/auth/authentication.json";
			var param = "&consumer_key=5546b294f09347dfbb58&consumer_secret=18b67c9c3338464a806b";
			
			
			restRequest(url, "get", param, function(data1) {
				token = data1.result.accessToken;
				
				
				
				var today = new Date();
				var year = today.getFullYear() - 1;
				var dongNm = "";
				var dongIndex = "";

				url = "https://sgisapi.kostat.go.kr/OpenAPI3/auth/authentication.json";
				param = "&consumer_key=5546b294f09347dfbb58&consumer_secret=18b67c9c3338464a806b";
				geturl = "https://sgisapi.kostat.go.kr/OpenAPI3/boundary/hadmarea.geojson";
					// 동구
					getparam = "&accessToken=" + token + "&year=" + year
							+ "&adm_cd=25010";
					restRequest(geturl, "get", getparam, function(data01) {
						var polygons = [];
						for (i = 0; i < data01.features.length; i++) {
							p = data01.features[i].geometry.coordinates;
							polygons.push(p);
							dongIndex = data01.features[i].properties.adm_nm
									.lastIndexOf(" ") + 1;
							dongNm = data01.features[i].properties.adm_nm
									.substring(dongIndex);
							
							for( var j = 0; j < amdTotalValueLstLength; j++ ) {
								if( data01.features[i].properties.adm_cd == data.amdTotalValueLst[j].administzone_code ) {
									if( data.amdTotalValueLst[j].total > 200000 ) {
										sgisAddPolygons('map', p, {
											"color" : "red",
											"fillColor" : "red"
										}, dongNm);
									}else if( data.amdTotalValueLst[j].total > 100000 ) {
										sgisAddPolygons('map', p, {
											"color" : "green",
											"fillColor" : "green"
										}, dongNm);
									} else {
										sgisAddPolygons('map', p, {
											"color" : "blue",
											"fillColor" : "blue"
										}, dongNm);
									}
								}
							}
							
							
						}
					});	
						
						// 중구
						getparam = "&accessToken=" + token + "&year=" + year
								+ "&adm_cd=25020";
						restRequest(geturl, "get", getparam, function(data01) {
						var polygons = [];
						for (i = 0; i < data01.features.length; i++) {
							p = data01.features[i].geometry.coordinates;
							polygons.push(p);
							dongIndex = data01.features[i].properties.adm_nm
									.lastIndexOf(" ") + 1;
							dongNm = data01.features[i].properties.adm_nm
									.substring(dongIndex);
							
							for( var j = 0; j < amdTotalValueLstLength; j++ ) {
								if( data01.features[i].properties.adm_cd == data.amdTotalValueLst[j].administzone_code ) {
									if( data.amdTotalValueLst[j].total > 200000 ) {
										sgisAddPolygons('map', p, {
											"color" : "red",
											"fillColor" : "red"
										}, dongNm);
									}else if( data.amdTotalValueLst[j].total > 100000 ) {
										sgisAddPolygons('map', p, {
											"color" : "green",
											"fillColor" : "green"
										}, dongNm);
									} else {
										sgisAddPolygons('map', p, {
											"color" : "red",
											"fillColor" : "red"
										}, dongNm);
									}
								}
							}
						}
						
					});
					
						
						// 서구
						getparam = "&accessToken=" + token + "&year=" + year
								+ "&adm_cd=25030";
						restRequest(geturl, "get", getparam, function(data01) {
						var polygons = [];
						for (i = 0; i < data01.features.length; i++) {
							p = data01.features[i].geometry.coordinates;
							polygons.push(p);
							dongIndex = data01.features[i].properties.adm_nm
									.lastIndexOf(" ") + 1;
							dongNm = data01.features[i].properties.adm_nm
									.substring(dongIndex);
							
							for( var j = 0; j < amdTotalValueLstLength; j++ ) {
								if( data01.features[i].properties.adm_cd == data.amdTotalValueLst[j].administzone_code ) {
									if( data.amdTotalValueLst[j].total > 200000 ) {
										sgisAddPolygons('map', p, {
											"color" : "red",
											"fillColor" : "red"
										}, dongNm);
									} else if( data.amdTotalValueLst[j].total > 100000 ) {
										sgisAddPolygons('map', p, {
											"color" : "green",
											"fillColor" : "green"
										}, dongNm);
									}else {
										sgisAddPolygons('map', p, {
											"color" : "blue",
											"fillColor" : "blue"
										}, dongNm);
									}
								}
							}
						}
						
					});
						
						
						// 유성구
						getparam = "&accessToken=" + token + "&year=" + year
								+ "&adm_cd=25040";
						restRequest(geturl, "get", getparam, function(data01) {
						var polygons = [];
						for (i = 0; i < data01.features.length; i++) {
							p = data01.features[i].geometry.coordinates;
							polygons.push(p);
							dongIndex = data01.features[i].properties.adm_nm
									.lastIndexOf(" ") + 1;
							dongNm = data01.features[i].properties.adm_nm
									.substring(dongIndex);
							
							for( var j = 0; j < amdTotalValueLstLength; j++ ) {
								if( data01.features[i].properties.adm_cd == data.amdTotalValueLst[j].administzone_code ) {
									if( data.amdTotalValueLst[j].total >200000 ) {
										sgisAddPolygons('map', p, {
											"color" : "red",
											"fillColor" : "red"
										}, dongNm);
									}else if( data.amdTotalValueLst[j].total > 100000 ) {
										sgisAddPolygons('map', p, {
											"color" : "green",
											"fillColor" : "green"
										}, dongNm);
									} else {
										sgisAddPolygons('map', p, {
											"color" : "blue",
											"fillColor" : "blue"
										}, dongNm);
									}
								}
							}
						}
						
					});
						
						// 대덕구
						getparam = "&accessToken=" + token + "&year=" + year
								+ "&adm_cd=25050";
						restRequest(geturl, "get", getparam, function(data01) {
						var polygons = [];
						for (i = 0; i < data01.features.length; i++) {
							p = data01.features[i].geometry.coordinates;
							polygons.push(p);
							dongIndex = data01.features[i].properties.adm_nm
									.lastIndexOf(" ") + 1;
							dongNm = data01.features[i].properties.adm_nm
									.substring(dongIndex);
							
							for( var j = 0; j < amdTotalValueLstLength; j++ ) {
								if( data01.features[i].properties.adm_cd == data.amdTotalValueLst[j].administzone_code ) {
									if( data.amdTotalValueLst[j].total > 200000 ) {
										sgisAddPolygons('map', p, {
											"color" : "red",
											"fillColor" : "red"
										}, dongNm);
									} else if( data.amdTotalValueLst[j].total > 100000 ) {
										sgisAddPolygons('map', p, {
											"color" : "green",
											"fillColor" : "green"
										}, dongNm);
									}else {
										sgisAddPolygons('map', p, {
											"color" : "blue",
											"fillColor" : "blue"
										}, dongNm);
									}
								}
							}
						}
						
					});
					for( var i = 0; i < happnsWelfrCntrLstLength; i++ ) {
						var getparam = "&accessToken=" + token + "&address=" + data.happnsWelfrCntrLst[i].addr;
						var geturl = "https://sgisapi.kostat.go.kr/OpenAPI3/addr/geocode.json";
						
						restRequest(geturl, "get", getparam, function(data02) {
							
							var marker = sop.marker([data02.result.resultdata[0].x, data02.result.resultdata[0].y]);
					        marker.addTo(sgisMapObject['map']['map']);
					        marker.bindInfoWindow(data.happnsWelfrCntrLst[i].orgn_nm+'<br>Tel) '+data.happnsWelfrCntrLst[i].tel_number);
					        
						});
					}
			});
	        
	     },
	     error: function(request, status, error) {
	      alert('실패');
	     }
	    });

		
	}
	
	
	
</script>
</head>
<body>
	<main>
	<div class="main_flex">
		<!-- 지도표시할 div -->
		<div id="map"></div>	
		<script type="text/javascript">
			sgisInitMap('map', {
				'measureControl' : true
			});
			polygonArea();				
			addMarker('map');
			
		</script>
		<form id="frm" name="frm" method="post">
		<div class="right_icon_box">
			<div class="search_box">
				<div class="search_wrap">
					<ul class="search_select">
						<li>
							<ul class="condition_btn">
							</ul>
						</li>
						<li class="search_line01">
						<select id="startTimeSel" name="startTimeSel">
							<c:forEach var="time" items="${timeznLst}" varStatus="status">
								<option value="${time.timezn_cd}">${time.timezn_nm}</option>
							</c:forEach>
						</select> - 
						<select id="endTimeSel" name="endTimeSel">
								<c:forEach var="time" items="${timeznLst}" varStatus="status">
									<option value="${time.timezn_cd}">${time.timezn_nm}</option>
								</c:forEach>
						</select>
							<div class="input_flex">
								<a id="btnSearch" name="btnSearch" class="search_btn" onclick="fnSearch();"></a>
							</div></li>
					</ul>
					<div class="search_img_box active" id="search_img_box" title="검색">
						<span class="search_img"></span>
					</div>
				</div>
			</div>
		</div>
		</form>
	</div>
	</main>
</body>
</html>