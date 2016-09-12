function main() {
    $("#addBtn").click(function () {
        var tableRow = "\n" +
            "<tr> " +
            "<input type=\"hidden\" name=\"hotelId\" value=\"-1\"> " +
            "<td><input type=\"text\" name=\"hotelCountry\" placeholder=\"hotelCountry\"></td> " +
            "<td><input type=\"text\" name=\"hotelCity\" placeholder=\"hotelCity\"></td> " +
            "<td><input type=\"text\" name=\"hotelName\" placeholder=\"hotelName\"></td> " +
            "<td><input type=\"text\" name=\"hotelDiscount\" placeholder=\"hotelDiscount\"></td> " +
            "<td><input type=\"text\" name=\"hotelStatus\" placeholder=\"hotelStatus\"></td> " +
            "<td><button class=\"submitBtn\" type=\"button\">Засслать</button></td> " +
            "</tr>";
        $("tr").last().after(tableRow);
        $(".submitBtn").bind("click", function (event) {
            dot(event)
        });
    });

    $(".submitBtn").click(function (event) {dot(event)});

    function dot (event) {
        var button = event.target;
        alert(button.nodeName);
        var parentNode = $(button.parentNode).parent();
        var inputNodes = parentNode.find("input");
        var parameters = inputNodes.serialize();
        var isAjaxRequest = "isAjaxRequest=true";
        var url = "controller?command=alterHotels&" + parameters + "&" + isAjaxRequest;
        $.get(url, function (data, status) {
            alert(data);
        });
    }

   

}
$("document").ready(main);
