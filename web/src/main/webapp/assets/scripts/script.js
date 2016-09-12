function calcul() {
    var xhttp = new XMLHttpRequest();
    var num1 = $("#num1").val();
    var num2 = $("#num2").val();
    var url = "controller?command=calcul&num1=" + num1 + "&num2=" + num2;
    xhttp.onreadystatechange = function () {
        if (xhttp.readyState == 4 && xhttp.status == 200) {
            var rez = xhttp.responseText.valueOf();
            var test = rez.length;
            var rez2 = xhttp.responseType;
            $("#rez").text(rez);
            $("#tsst").text(rez2);
        }
    };
    xhttp.open("GET", url, true);
    xhttp.send();
}

// function setLocale() {
//     // var xhttp = new XMLHttpRequest();
//     // var language = $("#language").val();
//     // // var url = "controller?command=setLocale&language=" + language;
//     // // xhttp.open("GET", url, true);
//     // // xhttp.send();
//     // var url = "controller";
//     // xhttp.open("POST", url, false);
//     // xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
//     // xhttp.send("command=setLocale&language=" + language);
//
//     if ($("#amountRooms").text() == "") {
//         $("#langbtn").click();
//     }
// }

function cookieTest() {
    var x = getCookie("cock");
    alert(x);

}

function setCookie(cname, cvalue, exdays) {
    var d = new Date();
    d.setTime(d.getTime() + (exdays*24*60*60*1000));
    var expires = "expires="+d.toUTCString();
    document.cookie = cname + "=" + cvalue + "; " + expires;
}

function getCookie(cname) {
    var name = cname + "=";
    var ca = document.cookie.split(';');
    for(var i = 0; i < ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0) == ' ') {
            c = c.substring(1);
        }
        if (c.indexOf(name) == 0) {
            return c.substring(name.length, c.length);
        }
    }
    return "";
}

function checkCookie() {
    var user = getCookie("username");
    if (user != "") {
        alert("Welcome again " + user);
    } else {
        user = prompt("Please enter your name:", "");
        if (user != "" && user != null) {
            setCookie("username", user, 365);
        }
    }
}

// function addOptions() {
//     for (var i = 1; i <= 20; i++) {
//         var opt = "<option value=" + i + ">" + i + "</option>";
//         $("#no_persons").append(opt);
//     }
//
//     alert("done");
// }



