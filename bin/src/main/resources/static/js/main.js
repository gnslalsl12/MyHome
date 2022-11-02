let date = new Date();

  window.onload = function () {
    console.log(getContextPath());
    let yearEl = document.querySelector("#year");
    let yearOpt = `<option value="">매매년도선택</option>`;
    let year = date.getFullYear();
    for (let i = year; i > year - 20; i--) {
      yearOpt += `<option value="${i}">${i}년</option>`;
    }
    yearEl.innerHTML = yearOpt;

    // 브라우저가 열리면 시도정보 얻기.
    sendRequest("sido", true, "*00000000");
    
    
    document.querySelector('#favorite').addEventListener('change', ()=>{
        
        console.log(document.querySelector('#favorite').value);
        let words = document.querySelector('#favorite').value.split(" ")
        console.log(words[0], words[1], words[2]);
        optionSelected(words[0], words[1], words[2]);
        
    })
    
  };

  document.querySelector("#year").addEventListener("change", function () {
    let month = date.getMonth() + 1;
    let monthEl = document.querySelector("#month");
    let monthOpt = `<option value="">매매월선택</option>`;
    let yearSel = document.querySelector("#year");
    let m = yearSel[yearSel.selectedIndex].value == date.getFullYear() ? month : 13;
    for (let i = 1; i < m; i++) {
      monthOpt += `<option value="${i < 10 ? "0" + i : i}">${i}월</option>`;
    }
    monthEl.innerHTML = monthOpt;
  });

  // https://juso.dev/docs/reg-code-api/
  // let url = "https://grpc-proxy-server-mkvo6j4wsq-du.a.run.app/v1/regcodes";
  // let regcode = "*00000000";
  // 전국 특별/광역시, 도
  // https://grpc-proxy-server-mkvo6j4wsq-du.a.run.app/v1/regcodes?regcode_pattern=*00000000

  // 시도가 바뀌면 구군정보 얻기.
  document.querySelector("#sido").addEventListener("change", function () {
    if (this[this.selectedIndex].value) {
      let regcode = this[this.selectedIndex].value.substr(0, 2) + "*00000";
      sendRequest("gugun", true, regcode);
    } else {
      initOption("gugun");
      initOption("dong");
    }
  });

  // 구군이 바뀌면 동정보 얻기.
  document.querySelector("#gugun").addEventListener("change", function () {
    if (this[this.selectedIndex].value) {
      let regcode = this[this.selectedIndex].value.substr(0, 5) + "*";
      sendRequest("dong", true, regcode);
    } else {
      initOption("dong");
    }
  });
  
  
  function getContextPath(){
      var hostIndex = location.href.indexOf(location.host) + location.host.length;//root 시작 index
      var contextPath = location.href.substring(hostIndex,location.href.indexOf('/',hostIndex+1)); //root
      
      return "http://"+location.host+contextPath;
  }
  
  
  function sendRequest(selid, flag, regcode) {
    let url = "https://grpc-proxy-server-mkvo6j4wsq-du.a.run.app/v1/regcodes";
    let params = "regcode_pattern=" + regcode + "&is_ignore_zero=true"; 
    
    if(!flag){
        url= getContextPath()+"/main";
        params = "action=favorite";
    }
    
    console.log(`${url}?${params}`);
    fetch(`${url}?${params}`)
    .then((response) => response.json())
    .then((data) => addOption(selid, data));
  }

  function addOption(selid, data) {
    console.log(data);
    let opt = ``;
    initOption(selid);
    switch (selid) {
      case "favorite":
          opt += `<option value="">관심지역</option>`;
          data.regcodes.forEach(function (regcode) {
            opt += `
            <option value="${regcode.sidoName} ${regcode.gugunName} ${regcode.dongName}">${regcode.sidoName} ${regcode.gugunName} ${regcode.dongName}</option>
            `;
          });
        break;
      case "sido":
        opt += `<option value="">시도선택</option>`;
        data.regcodes.forEach(function (regcode) {
          opt += `
          <option value="${regcode.code}">${regcode.name}</option>
          `;
        });
        break;
      case "gugun":
        opt += `<option value="">구군선택</option>`;
        for (let i = 0; i < data.regcodes.length; i++) {
          if (i != data.regcodes.length - 1) {
            if (
              data.regcodes[i].name.split(" ")[1] == data.regcodes[i + 1].name.split(" ")[1] &&
              data.regcodes[i].name.split(" ").length !=
                data.regcodes[i + 1].name.split(" ").length
            ) {
              data.regcodes.splice(i, 1);
              i--;
            }
          }
        }
        let name = "";
        data.regcodes.forEach(function (regcode) {
          if (regcode.name.split(" ").length == 2) name = regcode.name.split(" ")[1];
          else name = regcode.name.split(" ")[1] + " " + regcode.name.split(" ")[2];
          opt += `
          <option value="${regcode.code}">${name}</option>
          `;
        });
        break;
      case "dong":
        opt += `<option value="">동선택</option>`;
        let idx = 2;
        data.regcodes.forEach(function (regcode) {
          if (regcode.name.split(" ").length != 3) idx = 3;
          opt += `
          <option value="${regcode.name.split(" ")[idx]}">${regcode.name.split(" ")[idx]}</option>
          `;
        });

    }
    document.querySelector(`#${selid}`).innerHTML = opt;
  }
  
  function optionSelected(sido, gugun, dong){
//      console.log(document.querySelectorAll("#sido"));
//      console.log(document.querySelectorAll("#sido").options);
      let selects = document.querySelector("#sido");
//      let index = 0;
      
      console.log(sido, gugun, dong);
      
      selects[0].innerText=sido;
      
      let guguns = document.querySelector("#gugun");

      console.log(guguns.length);
      guguns[0].innerText=gugun;

      
      let dongs = document.querySelector('#dong');
      
      dongs[0].innerText=dong;
      
  }
  
  
  function initOption(selid) {
    let options = document.querySelector(`#${selid}`);
    options.length = 0;

  }

  document.querySelector("#b-login").addEventListener("click", ()=>{
    handleLogin(true);
  });

  document.querySelector("#login2").addEventListener("click", ()=>{
    handleLogin(false);
  });

  function handleLogin(isLogin){
    // pre-login 요소들
    let preLogins = document.querySelector("#prelogin1");
    let preLogins2 = document.querySelector("#prelogin2");
    let Logins = document.querySelector("#login1");
    let Logins2 = document.querySelector("#login1");
    
    // isLogin에 따라서 보이고 숨기고를 설정
    
    preLogins.style.display = isLogin ? "none":"inline-block";
    preLogins2.style.display = isLogin ? "none":"inline-block";

    Logins.style.display != isLogin ? "none":"inline-block";
    Logins2.style.display != isLogin ? "none":"inline-block";

  }