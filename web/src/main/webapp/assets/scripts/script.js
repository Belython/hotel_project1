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

function setLocale() {
    // var xhttp = new XMLHttpRequest();
    // var language = $("#language").val();
    // // var url = "controller?command=setLocale&language=" + language;
    // // xhttp.open("GET", url, true);
    // // xhttp.send();
    // var url = "controller";
    // xhttp.open("POST", url, false);
    // xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    // xhttp.send("command=setLocale&language=" + language);

    if ($("#amountRooms").text() == "") {
        $("#langbtn").click();
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

$(document).ready(setLocale());

