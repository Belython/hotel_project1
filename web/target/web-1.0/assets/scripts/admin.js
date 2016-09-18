function main() {
    // $("#addBtn").click(function () {
    //     var tableRow = "\n" +
    //         "<tr> " +
    //         "<input type=\"hidden\" name=\"hotelId\" value=\"-1\"> " +
    //         "<td><input type=\"text\" name=\"hotelCountry\" placeholder=\"hotelCountry\"></td> " +
    //         "<td><input type=\"text\" name=\"hotelCity\" placeholder=\"hotelCity\"></td> " +
    //         "<td><input type=\"text\" name=\"hotelName\" placeholder=\"hotelName\"></td> " +
    //         "<td><input type=\"text\" name=\"hotelDiscount\" placeholder=\"hotelDiscount\"></td> " +
    //         "<td><input type=\"text\" name=\"hotelStatus\" placeholder=\"hotelStatus\"></td> " +
    //         "<td><button class=\"submitBtn\" type=\"button\">Засслать</button></td> " +
    //         "</tr>";
    //     $("tr").last().after(tableRow);
    //     $(".submitBtn").bind("click", function (event) {
    //         dot(event)
    //     });
    // });

    $(".submitBtn").click(function (event) {alterEntity(event)});

    function alterEntity (event) {
        var button = event.target;
        var tableRow = $(button).parents().eq(1);
        var rowInputNodes = tableRow.find("input, select");
        // var commonInputNodes = $(".redactorForm").has(tableRow).children("input");
        var redactorForm = $(".redactorForm").has(tableRow);
        var command = redactorForm.children("[name='command']").serialize();
        var subCommand = redactorForm.children("[name='subCommand']").serialize();
        var rowParameters = rowInputNodes.serialize();
        // var commonParameters = commonInputNodes.serialize();
        var isAjaxRequest = "isAjaxRequest=true";
        var url = "controller?" + command + "&" + subCommand + "&" + isAjaxRequest + "&" + rowParameters;
        $.get(url, function (data, status) {
            $("#operationMessage").text(data);
        });
    }

    $(".addRowBtn").click(function () {addRow()});
    
    function addRow() {
        var tableRow = $(".newEntity tr").last().clone();
        $(".newEntity tbody").append(tableRow);
        $(".addEntityBtn").bind("click", function (event) {
            alterEntity(event)
        });
        $(".removeRowBtn").bind("click", function (event) {
            removeRow(event)
        });
    }

    $(".alterEntityBtn").click(function (event) {alterEntity(event)});

    $(".addEntityBtn").click(function (event) {alterEntity(event)});

    $(".removeRowBtn").click(function () {removeRow()});

    function removeRow (event) {
        var button = event.target;
        var tableRow = $(button).parents().eq(1);
        tableRow.remove();

        // $(".newEntity tr").last().remove();
    }
    
    
}
$("document").ready(main);
