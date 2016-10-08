function main() {

    $(".submitBtn").click(function (event) {
        alterEntity(event)
    });

    $(".addRowBtn").click(function () {
        addRow()
    });

    $(".alterEntityBtn").click(function (event) {
        alterEntity(event)
    });

    $(".addEntityBtn").click(function (event) {
        alterEntity(event)
    });

    $(".removeRowBtn").click(function (event) {
        removeRow(event)
    });
    
    $(".alterEntityForm select").change(function (event) {
        constrainRow(event)
    });

    function getTableRow(event) {
        var eventTarget = event.target;
        var tableRow = $(eventTarget).parents().eq(1);
        return tableRow;
    }

    function constrainRow(event) {
        var tableRow = getTableRow(event);
        var rowInputNodes = tableRow.find("input, select");
        var rowParameters = rowInputNodes.serialize();
        var command = "command=constrainRow";
        var url = "controller?" + command + "&" + rowParameters;
        $.get(url, function (data, status) {
            tableRow.after(data);
            tableRow.remove();
        });
    }

    function alterEntity(event) {
        var tableRow = getTableRow(event);
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

    function removeRow(event) {
        var button = event.target;
        var tableRow = $(button).parents().eq(1);
        tableRow.remove();
    }

    var extractor = function (node) {
        var child = $(node).children().first();
        var childName = child.prop("tagName");
        switch (childName) {
            case "INPUT": 
                // alert( child.attr("value") + " input");
                return child.attr("value");
            case "SELECT": 
                // alert(child.children().filter("[selected]").eq(0).attr("value")+ " select");
                return child.children().filter("[selected]").eq(0).attr("value");
        }
    };
    
    $("#mt").tablesorter({textExtraction: extractor});
}
$("document").ready(main);
