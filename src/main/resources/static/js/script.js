
// //스크롤 1000이상 넘어가면 메뉴바 폰트색깔 black으로 변경
// $(window).scroll(function () {
//     var scrollValue = $(document).scrollTop();
//     if(scrollValue >= 1000)  $('a').css("color","black");
//     else $('a').css("color","white");
// });


//TOP 버튼
// function hasScrollBehavior() {
//     return 'scrollBehavior' in document.documentElement.style;
//   }
  
//   function smoothScroll() {
//     var currentY = window.scrollY;
//     var int = setInterval(function () {
//       window.scrollTo(0, currentY);
  
//       if (currentY > 500) {
//         currentY -= 70;
//       } else if (currentY > 100) {
//         currentY -= 50;
//       } else {
//         currentY -= 10;
//       }
  
//       if (currentY <= 0) clearInterval(int);
//     }, 1000 / 60); 
//   }
  
//   function scrollToTop() {
//     if (hasScrollBehavior()) {
//       window.scrollTo({ top: 0, behavior: 'smooth' });
//     } else {
//       smoothScroll();
//     }
//   }
  
//   function toggleScrollUpButton() {
//     var y = window.scrollY;
//     var e = document.getElementById('scroll-to-top');
//     if (y >= 350) {
//       e.style.transform = 'translateY(-30%)'
//       e.style.opacity = 1;
//     } else {
//       e.style.opacity = 0;
//       e.style.transform = 'translateY(30%)'
//     }
//   }
  
//   document.addEventListener("DOMContentLoaded", function () {
//     document.removeEventListener("DOMContentLoaded", arguments.callee, false);
  
//     window.addEventListener("scroll", toggleScrollUpButton);
  
//     var e = document.getElementById('scroll-to-top');
//     e.addEventListener('click', scrollToTop, false);
//   }, false);


//   $(document).ready(function(){
//     // Add smooth scrolling to all links in navbar + footer link
//     $(".navbar a, footer a[href='#myPage']").on('click', function(event) {
  
//     // Make sure this.hash has a value before overriding default behavior
//     if (this.hash !== "") {
  
//       // Prevent default anchor click behavior
//       event.preventDefault();
  
//       // Store hash
//       var hash = this.hash;
  
//       // Using jQuery's animate() method to add smooth page scroll
//       // The optional number (900) specifies the number of milliseconds it takes to scroll to the specified area
//       $('html, body').animate({
//         scrollTop: $(hash).offset().top
//       }, 900, function(){
  
//         // Add hash (#) to URL when done scrolling (default click behavior)
//         window.location.hash = hash;
//         });
//       } // End if
//     })
//   })
$(document).ready(function(){
  // 검색화면 토글창
  // $(".search-icon").click(function(){
  //   $(".search-txt").toggle();
  //   console.log("haha");
  // })
  
  //클릭시 여행지 선택하는거
  const placeSelect = document.getElementById("place-select");

  $("#place-btn").click(function (){
    if(placeSelect.style.display === 'none'){
      placeSelect.style.display = "block";
    }else{
      placeSelect.style.display ="none";
    }
  });

  $(".place-select-li").click(function (){
    placeSelect.style.display ="none";
  });

  // 클릭시 여행지 선택하는거
    $(".place-select-li").click(function (){
      const placeValue = $(this).attr('value');
      document.getElementById("place-name").innerText = placeValue;
      const ha = document.getElementById("place-name").value = placeValue;
    });

    // 로그인 화면 모달창
  const modal = document.getElementById("modal-login")

  function modalOn() {
    modal.style.display = "flex"
  }
  function isModalOn(){
    return modal.style.display === "flex"
  }
  function modalOff(){
    modal.style.display = 'none'
  }

  //x버튼 눌러서 창닫기
  $('.modal-close').click(function(){
    modalOff();
  });

  //esc로 창닫기
  $(document).keyup(function (e){
    if(e.key == "Escape") { //esc키
      console.log("esc");
      modalOff();
    }
  });


  $('#login').click(function(){
    modalOn();
  });

  $("#comment-content-login").click(function (){
    modalOn();
  });

  const search = document.getElementsByClassName("search-icon");

  $(".search-icon").click(function (){
    if($(".search-txt").val() == ""){
      return false;
    }else{
      return true;
    }
  });

   // 댓글 글자수 감지
  $(".comment-content").keyup(function (e){
    var content = $(this).val();

    //글자수 세기
    if(content.length == 0 || content == ""){
        $(".comment_length").text('0');
    }else{
        $(".comment_length").text(content.length);
    }

    if (content.length > 500) {
    // 500자 부터는 타이핑 되지 않도록
    $(this).val($(this).val().substring(0, 500));
    // 500자 넘으면 알림창 뜨도록
  };

  });


});

