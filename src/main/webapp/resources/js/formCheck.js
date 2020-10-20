
	function checkFormjquery() {
		var errcnt = 0;
		$(".required").each(function() {
			fObj = $(this).attr("name");
			fVal = $(this).val();
			fTyp = toUpperCase($(this).attr("type"));
			fMsg = $(this).attr("msg");
			fMal = $(this).attr("chkmail");
			if (fMsg != null) {
				if (fVal == "" && (fTyp == "SELECT-ONE" || fTyp == "SELECT-MULTIPLE")) {
					alert(fMsg + " 선택해 주세요");
					$(this).focus();
					errcnt++;
					return false;
				}
				if(fVal == "" && (fTyp == "TEXT" || fTyp == "HIDDEN" || fTyp == "TEXTAREA" || fTyp == "PASSWORD" || fTyp == "NUMBER")) {
					alert(fMsg + " 입력해 주세요");
					$(this).focus();
					errcnt++;
					return false;
				}
				if (fVal != null && (fTyp == "RADIO" || fTyp == "CHECKBOX") && checkChecked(fObj) == false) {
					alert(fMsg + " 선택해 주세요");
					$(this).focus();
					errcnt++;
					return false;
				}
				if(fMal != null && fVal != "" && checkEmail(fVal) == false) {
					alert("이메일 주소가 올바르지 않습니다");
					$(this).focus();
					errcnt++;
					return false;
				}
			}
		});

		if (errcnt > 0)
			return false;
		else
			return true;
	}

	// 이메일 유효성 체크
	function checkEmail(str){
	    var reg = /^((\w|[\-\.])+)@((\w|[\-\.])+)\.([A-Za-z]+)$/;
	    if (str.search(reg) != -1) {
			return true;
		}
		return false;
	}

	// 배열 요소일 경우 checked 된것이 있는지 확인
	function checkChecked(obj) {
		var objnm = $("input[name="+ obj +"]");
		var ret = false;

		$("input[name="+ obj +"]").each(function(){
			if ($(this).attr("checked")) {
				ret = true;
			}
		});

		return ret;
	}

	// 대문자 변환 ex) toUpperCase(문자)
	function toUpperCase(str) {
		var ret;
		str != null ? ret = str.toUpperCase() : ret = "";
		return ret;
	}
