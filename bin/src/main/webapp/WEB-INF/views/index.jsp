<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

	<%@ include file='/WEB-INF/views/include/header.jsp'%>

    
    <div class="select_place" style="background-color: #e3f2fd; padding-top: 30px; padding-bottom: 25px; ">
     <div class="container">
    <form id="searchAptName" action="${root }/main">
    <div class="input-group rounded">
    <input type="hidden" name="action" value="searchAptName">
  <input type="search" id="aptPattern" class="form-control rounded" placeholder="Search" aria-label="Search" aria-describedby="search-addon" name="searchName"/>
  <span class="input-group-text border-0" id="search-addon">
   <i class="bi bi-search" id="search-aptName"></i>
  </span>
    </form>
</div>    
    </div>
   <div style="height: 20px;"> </div>
      <div class="container-fluid">
        <span class="align-middle">
          <div class="row col-md-12 justify-content-center mb-2">
          	<div class="form-group col-md-2">
              <select class="form-select bg-secondary text-light" id="favorite" name='favorite'>
                <option value="">관심지역</option>
              </select>
            </div>
            <div class="form-group col-md-2">
              <select class="form-select bg-secondary text-light" id="sido" name='sido'>
                <option value="">도/광역시</option>
              </select>
            </div>
            <div class="form-group col-md-2">
              <select class="form-select bg-secondary text-light" id="gugun" name='gugun'>
                <option value="">시/구/군</option>
              </select>
            </div>
            <div class="form-group col-md-1">
              <select class="form-select bg-secondary text-light" id="dong" name='dong'>
                <option value="">동</option>
              </select>
            </div>
            <div class="form-group col-md-1">
              <select class="form-select bg-dark text-light" id="year" name='year'></select>
            </div>
            <div class="form-group col-md-1">
              <select class="form-select bg-dark text-light" id="month" name='month'>
                <option value="">매매월선택</option>
              </select>
            </div>
            <div class="form-group col-md-2" style="margin: auto 0; display:contents">
              <button type="submit" id="list-btn" class="btn btn-outline-primary">
                아파트매매정보
              </button>
              </div>
              <div class="col-md-2" style="margin: auto 0; display:contents">
              <button type="button" id="favorite-btn" class="btn btn-outline-primary">
                관심지역 추가
              </button>
            </div>
          </div>
        </span>
        
        <div id='table_parent'>
          <div class="row">
            <div class="col">
              <table class="table table-hover text-center" id="t" style="display: none">
                <tr>
                  <th>아파트이름</th>
                  <th>층</th>
                  <th>면적</th>
                  <th>법정동</th>
                  <th>거래금액</th>
                </tr>
                <tbody id="aptlist"></tbody>
                <c:if test="${homeList ne null}">
				</c:if>
              </table>
            </div>
            <div class="col">
              <div id="map" style="width: 100%; height: 500px"></div>
            </div>
          </div>
          <div id='' style='display: inline-block;'>
          	<input type='text' id='pageId' style='display: inline-block; width : 30px;'>
          	<div id='pageLen' style='display: inline-block;'>/</div>
          	<button type="input" id='pagebnt'>이동</button>
          </div>
        </div>
      </div>
    </div>
  </nav>    

<!-- makeList("${homeList}"); -->
  <footer id="footer" class="clearfix " style="margin-top: 60px;">

    <!-- .footer start -->
    <!-- ================ -->
    <div class="footer">
      <div class="container">
        <div class="footer-inner">
          <div class="row">
           <div class="col-md-1">
              <div class="footer-content">
                <img alt="" src="./img/logo.png"  width="100">
              </div>
            </div>
            <div class="col-md-8">
              <div class="footer-content">
                <h2 class="title">Find Us</h2>
                <div class="separator-2"></div>
                <ul class="list-icons">
                  <li><i class="bi-geo-alt-fill" style="margin-right: 5px;"></i> (SSAFY) 서울시 강남구  테헤란로 멀티스퀘어</li>
                  <li><i class="bi-telephone-forward-fill" style="margin-right: 5px;"></i> 1544-9001</li>
                  <li><i class="bi-envelope-fill" style="margin-right: 10px;"></i><a href="#">admin@ssafy.com</a></li>
                </ul>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    
    <!-- .footer end -->

    <!-- .subfooter start -->
    <!-- ================ -->
    <div class="subfooter">
      <div class="container">
        <div class="subfooter-inner">
          <div class="row">
            <div class="col-md-12">
              <p class="text-center">Copyright by SSAFY. All rights reserved.</p>
            </div>
          </div>
        </div>
      </div>
    </div>
    <!-- .subfooter end -->

  </footer>
<!-- footer end -->
</div>
<script src="./js/main.js"></script>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=	57dd21f3fc158412d95faecd7bab8cf2&libraries=services"></script>
<script>
  ///////////////////////// 아파트 매매 정보 /////////////////////////

  <c:if test="${!empty loginUser}">
  	sendRequest("favorite",false);
  </c:if>
  
  document.querySelector("#search-aptName").addEventListener("click", ()=>{
	  //document.querySelector("#searchAptName").submit();
	  
	  let search = document.querySelector("#aptPattern").value;
	  //창 띄우기
	  window.open("${root}/main?action=searchAptName&searchName="+search,"popup-test","width = 500, height = 500, top = 100, left = 200, location = no");

  });
  
  document.querySelector("#favorite-btn").addEventListener("click", ()=>{
	  let sidoSel= document.querySelector("#sido");
	  let sido = sidoSel[sidoSel.selectedIndex].text;
	  
	  let gugunSel = document.querySelector("#gugun");
	  let gugun = gugunSel[gugunSel.selectedIndex].text;
	  
	  let dongSel = document.querySelector("#dong");
	  let dong = dongSel[dongSel.selectedIndex].text;
	  
	  //console.log(url);
	  /* config = {
			  
			  method : "post",
			  
			  body : JSonirg,gr();
	  } */
	  
	  //fetch(url).then(response => sendRequest("favorite",false));
	  
	  (
		async ()=>{
	  		const url = "${root}/main/addFavorite?sido="+sido+"&gugun="+gugun+"&dong="+dong;
			const response = await  fetch(url);
			if(response.ok){
				let info = await response.json();
				const favorite =document.querySelector("#favorite"); 
				favorite.innerHTML = "<option value=''>관심지역</option>";
				info.forEach(item =>{
					console.log(item.sidoName)
					favorite.innerHTML+=`<option>\${item.sidoName} \${item.gugunName} \${item.dongName}</option>`		
					
					
				})
				
			}else{
				let message = await response.text();
				alert(message);
			}
		}	  
	  )();
	  
	  

  	});
  document.querySelector("#list-btn").addEventListener("click", function (e) {
	  console.log('test');
	  e.preventDefault();
	  
	  let url = getContextPath()+"/main?action=search";
	  
	  let yearSel = document.querySelector("#year");
	  let year = yearSel[yearSel.selectedIndex].value;
	  
	  let monthSel = document.querySelector("#month");
	  let month = monthSel[monthSel.selectedIndex].value;
	  
	  let dongSel = document.querySelector("#dong");
	  let dong = dongSel[dongSel.selectedIndex].text;
	
	  url += "&dong="+dong+"&year="+year+"&month="+month;
			  
		/*
		          <div id='' style='display: inline-block;'>
          	<input type='text' id='pageId' style='display: inline-block; width : 30px;'>
          	<div id='pageLen' style='display: inline-block;'>/5</div>
          	<button type="button" id='pagebnt'>이동</button>
          </div>
		
		*/
	  fetch(url)
	  .then(response => response.json())
	  .then(data => {
		  console.log(data);
		  makeList(data.results);
		  console.log(data.resultLen);
		  document.querySelector('#pageLen').innerText = '/'+Math.ceil(data.resultLen/10);
		  document.querySelector('#pagebnt').addEventListener('click',(e)=>{
				e.preventDefault();
			  let makeUrl = "&pageNo="+document.querySelector('#pageId').value;
			  console.log(url+makeUrl);
			  fetch(url+makeUrl)
			  .then(response => response.json())
			  .then(data =>{
				  makeList(data.results);
			  })
		  })
	  })
  });

  // <거래금액>   170,000</거래금액><거래유형> </거래유형><건축년도>2011</건축년도><년>2021</년><도로명>소공로</도로명><도로명건물본번호코드>00035</도로명건물본번호코드><도로명건물부번호코드>00000</도로명건물부번호코드><도로명시군구코드>11140</도로명시군구코드><도로명일련번호코드>04</도로명일련번호코드><도로명지상지하코드>0</도로명지상지하코드><도로명코드>3101004</도로명코드><법정동> 회현동1가</법정동><법정동본번코드>0208</법정동본번코드><법정동부번코드>0000</법정동부번코드><법정동시군구코드>11140</법정동시군구코드><법정동읍면동코드>12100</법정동읍면동코드><법정동지번코드>1</법정동지번코드><아파트>남산롯데캐슬아이리스</아파트><월>1</월><일>8</일><일련번호>11140-1142</일련번호><전용면적>133.98</전용면적><중개사소재지> </중개사소재지><지번>208</지번><지역코드>11140</지역코드><층>26</층><해제사유발생일> </해제사유발생일><해제여부> </해제여부>
  // 주소를 넘기거나 함수를 호출하거나  
  function makeList(datas) {
    document.querySelector("#t").setAttribute("style", "display: ;");
    let tbody = document.querySelector("#aptlist");
   
    initTable();
	    
    /*
    aptName: "광화문풍림스페이스본(101동~105동)"
	area: 131
	dealAmount: "   119,000"
	dealMonth: 0
	dealYear: 0
	dongName: "사직동"
	floor: 13
	gugunName: null
	lat: "37.5743822"
	lng: "126.9688505"
	sidoName: null
    */
  	
    /*
    1. data가 10개 이상인지 확인 -> 왜냐? 10개 이하면 굳이 페이지 버튼?을 만들 필요가 없어
    2. 10개 이상이면 10개씩 찍히는걸 저장할 변수를 하나 만들어서 10개 이상인 경우에 페이지 버튼을 
    하나씩 추가하는 식으로 하고 테이블에는 더이상 데이터를 추가하지 않는 방식이어야 돼
    
    */
    let cnt = 0;
    datas.forEach((apt) => {
    	if(cnt >=10){
    		return;
    		//create button -> map에다가 저장했던 길이 배열을 가져오면 총 버튼을
    		// 몇개 만들 수 있는지 알 수 있다. 62/10 = 7개 1,2,3,4,5,6,7
    		// 버튼 몇 개 만들고 outer forEach break;
    		//여기서 만든 버튼에 eventListener-> 2번버튼 서버에 pagenum=2 fetch()->query
    		//->result -> initTable(); -> queryResult.forEach(data) ->;
    		//-> LIMIT? 라는걸 써야할거야->
    		
    		//[3]/8 [이동]
    	}
    	
      let tr = document.createElement("tr");
	  
      let nameTd = document.createElement("td");
      nameTd.appendChild(document.createTextNode(apt.aptName));
      tr.appendChild(nameTd);

      let floorTd = document.createElement("td");
      floorTd.appendChild(document.createTextNode(apt.floor));
      tr.appendChild(floorTd);

      let areaTd = document.createElement("td");
      areaTd.appendChild(document.createTextNode(apt.area));
      tr.appendChild(areaTd);

      let dongTd = document.createElement("td");
      dongTd.appendChild(document.createTextNode(apt.dongName));
      tr.appendChild(dongTd);

      let priceTd = document.createElement("td");
      priceTd.appendChild(
        document.createTextNode(apt.dealAmount.trim() + "만원"),
      );
      priceTd.classList.add("text-end");

      tr.appendChild(priceTd);

      tbody.appendChild(tr);
      
      makeMakerPre(apt);
      
      cnt+=1;
  });
  }

  function initTable() {
    let tbody = document.querySelector("#aptlist");
    let len = tbody.rows.length;
    for (let i = len - 1; i >= 0; i--) {
      tbody.deleteRow(i);
    }
  }
  
  var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
      mapOption = {
          center: new kakao.maps.LatLng(37.5012743, 127.039585), // 지도의 중심좌표
          level: 3 // 지도의 확대 레벨
      };  

  // 지도를 생성합니다    
  var map = new kakao.maps.Map(mapContainer, mapOption); 

  // 주소-좌표 변환 객체를 생성합니다
  var geocoder = new kakao.maps.services.Geocoder();

  // 주소로 좌표를 검색합니다

  function makeMakerPre(apt) {

     var coords = new kakao.maps.LatLng(apt.lat, apt.lng);
     console.log(coords);

     // 결과값으로 받은 위치를 마커로 표시합니다
     var marker = new kakao.maps.Marker({
         map: map,
         position: coords
     });

	   // 인포윈도우로 장소에 대한 설명을 표시합니다
	   var infowindow = new kakao.maps.InfoWindow({
	       content: `<div style="width:150px;text-align:center;padding:6px 0;">`+apt.aptName+`</div>`
	   });
	   infowindow.open(map, marker);

     // 마커에 click 이벤트를 등록합니다
     kakao.maps.event.addListener(
       marker,
       "click",
       makeClickListener(map, marker, infowindow, apt),
     );

     // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
     map.setCenter(coords);
  
  } 

  // 인포윈도우를 닫는 클로저를 만드는 함수입니다
  function makeOutListener(map, infowindow, marker, apt, pos) {
    console.log("jkj");
    
    let str = "이름: " + apt.aptName + `<br>` +

              "면적: " + apt.area + `<br>` +

              "거래금액: " + apt.dealAmount.trim() + "만원" + `<br>` +
    
    		  `<button type="button" id="bestPath" class="btn btn-primary">최적 경로</button>`;
              
    infowindow = new kakao.maps.InfoWindow({
      content : `<div style="width:250px;text-align:center;padding:6px 0;">`+str+`</div>`,
      removable : true
    });
    infowindow.open(map, marker);

    document.querySelector("#bestPath").addEventListener("click", ()=>{
	  //document.querySelector("#searchAptName").submit();
	  let dongName= document.querySelector("#dong").value;
	  let year= document.querySelector("#year").value;
	  let month= document.querySelector("#month").value;
	  
	  console.log(pos.La);
	  console.log(pos.Ma);
	  //창 띄우기
	  window.open("${root}/main?action=bestPath&dong="+dongName+"&La="+pos.La+"&Ma="+pos.Ma+"&dealYear="+year+"&dealMonth="+month+"&aptName="+apt.aptName,"popup-test","width = 500, height = 500, top = 100, left = 200, location = no");
	//alert("click!!");
  });
  }
  
  
  function makeClickListener(map, marker, infowindow, apt) {
    return function () {
      var pos = marker.getPosition();
      console.log(pos);
      map.setLevel(5);
      map.panTo(pos);

      // let table = document.querySelector("#t");
      // table.style.display = "none";
      makeOutListener(map, infowindow, marker, apt, pos);

    };
  }
  
  function makeMarker(data) {
    let parser = new DOMParser();
    const xml = parser.parseFromString(data, "application/xml");
    // console.log(xml);
    let apts = xml.querySelectorAll("item");

    let positions = [];
    let items = xml.getElementsByTagName("item");
    for (let item of items) {
      positions.push({
        title: `\${item.getElementsByTagName("yadmNm")[0].textContent}`,
        content: `<div class="info">\${
          item.getElementsByTagName("yadmNm")[0].textContent
        }</div>`,
        latlng: new kakao.maps.LatLng(
          `\${item.getElementsByTagName("YPosWgs84")[0].textContent}`,
          `\${item.getElementsByTagName("XPosWgs84")[0].textContent}`,
        ),
      });
    }
    console.log(positions);

    // 마커 이미지의 이미지 주소입니다
    var imageSrc = "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png";
    for (var i = 0; i < positions.length; i++) {
      // 마커 이미지의 이미지 크기 입니다
      var imageSize = new kakao.maps.Size(24, 35);

      // 마커 이미지를 생성합니다
      var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);

      // 마커를 생성합니다
      var marker = new kakao.maps.Marker({
        map: map, // 마커를 표시할 지도
        position: positions[i].latlng, // 마커를 표시할 위치
        title: positions[i].title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
        image: markerImage, // 마커 이미지
      });

      // 마커에 표시할 인포윈도우를 생성합니다
      var infowindow = new kakao.maps.InfoWindow({
        content: positions[i].content, // 인포윈도우에 표시할 내용
      });

      // 마커에 mouseover 이벤트와 mouseout 이벤트를 등록합니다
      // 이벤트 리스너로는 클로저를 만들어 등록합니다
      // for문에서 클로저를 만들어 주지 않으면 마지막 마커에만 이벤트가 등록됩니다
      kakao.maps.event.addListener(
        marker,
        "mouseover",
        makeOverListener(map, marker, infowindow),
      );
      kakao.maps.event.addListener(marker, "mouseout", makeOutListener(infowindow));

      // 마커에 click 이벤트를 등록합니다
      kakao.maps.event.addListener(
        marker,
        "click",
        // makeClickListener(map, marker, infowindow),
      );
    }

    //  지도의 중심을 첫번째 요양병원으로 이동.
    // 이동할 위도 경도 위치를 생성합니다
    var moveLatLon = new kakao.maps.LatLng(positions[0].latlng.Ma, positions[0].latlng.La);

    // 지도의 레벨 변경.
    map.setLevel(6);
    // 지도 중심을 이동 시킵니다
    map.setCenter(moveLatLon);
  }
</script>
</body>
</html>