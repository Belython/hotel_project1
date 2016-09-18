function main() {
    $(".submitBtn").click(function (event) {
        alterEntity(event)
    });

    function alterEntity(event) {
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

    $(".addRowBtn").click(function () {
        addRow()
    });

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

    $(".alterEntityBtn").click(function (event) {
        alterEntity(event)
    });

    $(".addEntityBtn").click(function (event) {
        alterEntity(event)
    });

    $(".removeRowBtn").click(function (event) {
        removeRow(event)
    });

    function removeRow(event) {
        var button = event.target;
        var tableRow = $(button).parents().eq(1);
        tableRow.remove();
    }
}
$("document").ready(main);
