function checkInput() {
	var colId = [ "code", "pname", "cost", "pnum", "jnum", "sale", "gcode" ];
	var col = [ "제품코드", "제품이름", "제품원가", "목표수량", "재고수량", "출고가", "그룹코드" ];
	
	for(var i = 0; i < colId.length - 1; i++) {
		var elem = document.getElementsByName(colId[i])[0];
		
		if(elem.value == "") {
			elem.focus();
			alert(col[i] + "를 입력해주세요");
			return false;
		}
	}
	
	return true;
}

function submitForm(form, location) {
	if(checkInput()) {
		form.action = location;
		form.submit();
	}
}

function searchForm(form, location) {
	if(form.code.value == "") {
		alert("제품코드를 입력하세요");
	} else {
		form.action = location;
		form.submit();
	}
}

function disabled() {
	var colId = [ "code", "pname", "cost", "pnum", "jnum", "sale", "gcode" ];
	
	for(var i = 1; i < colId.length; i++) {
		var elem = document.getElementsByName(colId[i])[0];
		
		elem.disabled = true;
	}
	
	var button = document.getElementsByClassName("submitBtn");
	
	for(var i = 0; i < button.length; i++) {
		button[i].disabled = true;
	}
}

function activateBtn() {
	var btn = document.getElementsByClassName('submitBtn');
	for(var i = 0; i < btn.length; i++) {
		if(btn[i].id == "update") {
			btn[i].setAttribute("onclick", "submitForm(document.productForm, 'update.do');");
		} else if(btn[i].id == "delete") {
			btn[i].setAttribute("onclick", "submitForm(document.productForm, 'delete.do');");
		}
	}
}

function notify(elem) {
	elem.classList.add("show");
	
	setTimeout(() => {
		elem.classList.remove("show");
	}, 2500)
}