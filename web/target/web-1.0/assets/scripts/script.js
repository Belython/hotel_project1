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

$(".payBillBtn").click(function (event) {
    payBill(event)
});

function payBill(event) {
    var button = event.target;
    var tableRow = $(button).parents().eq(1);
    var rowInputNodes = tableRow.find("input, select");
    var redactorForm = $(".redactorForm").has(tableRow);
    var command = redactorForm.children("[name='command']").serialize();
    var subCommand = redactorForm.children("[name='subCommand']").serialize();
    var rowParameters = rowInputNodes.serialize();
    var isAjaxRequest = "isAjaxRequest=true";
    var url = "controller?" + command + "&" + subCommand + "&" + isAjaxRequest + "&" + rowParameters;
    $.get(url, function (data, status) {
        $("#operationMessage").text(data);
    });
}
// function addOptions() {
//     for (var i = 1; i <= 20; i++) {
//         var opt = "<option value=" + i + ">" + i + "</option>";
//         $("#no_persons").append(opt);
//     }
//
//     alert("done");
// }



