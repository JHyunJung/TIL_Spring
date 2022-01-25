var main = {
    init : function () {
        var _this = this;
        $("#btn-save").on("click", function () {
            _this.save();
        });
        $("#btn-duplicate-check").on("click", function () {
            _this.duplicateCheck();
        });
    },
    save : function () {
        var data = {
            userId : $("#userId").val(),
            userPassword : $("#userPassword").val(),
            userName : $("#userName").val()
        };

        $.ajax({
            type : 'POST',
            url : '/api/v1/user',
            dataType : 'json',
            contentType : 'application/json; charset=utf-8',
            data : JSON.stringify(data)
        }).done(function () {
            alert('회원가입이 완료되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        })
    },
    duplicateCheck : function (){
        var data = {
            userId : $("#userId").val()
        };

        $.ajax({
            type : 'POST',
            url: '/api/vi/user/duplicate',
            dataType : 'json',
            contentType: 'application/json; charset=utf-8',
            dataset : JSON.stringify(data)
        }).done(function (){
            if(data == 0){
                console.log("아이디 없음");
                alert("사용하실 수 있는 아이디입니다.");
            }else{
                console.log("아이디 있음");
                alert("중복된 아이디가 존재합니다.");
            }
        }).fail(function (error){
            alert(JSON.stringify(error));
        })
    }

};

main.init();