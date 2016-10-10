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
    
    $(".entityForm select").change(function (event) {
        constrainRow(event)
    });

    function getTableRow(event) {
        var eventTarget = event.target;
        var tableRow = $(eventTarget).parents().eq(1);
        return tableRow;
    }

    function refresh() {
        $(".addEntityBtn").bind("click", function (event) {
            alterEntity(event)
        });
        $(".removeRowBtn").bind("click", function (event) {
            removeRow(event)
        });
        $(".alterEntityBtn").bind("click", function (event) {
            alterEntity(event)
        });
        $(".entityForm select").bind("change", function (event) {
            constrainRow(event)
        });
    }
    
    function constrainRow(event) {
        var tableRow = getTableRow(event);
        var some = $("button");
        var form = tableRow.parents("form").first();
        var formName = form.attr("name");
        var formNameParameter = "formName=" + formName;
        var rowInputNodes = tableRow.find("input, select");
        var rowParameters = rowInputNodes.serialize();
        var commandParameter = "command=constrainRow";
        var url = "controller?" + commandParameter + "&" + formNameParameter + "&" + rowParameters;
        $.get(url, function (data, status) {
            tableRow.after(data);
            tableRow.remove();
        });
        setTimeout(refresh, 1000);
    }

    function alterEntity(event) {
        var tableRow = getTableRow(event);
        var rowInputNodes = tableRow.find("input, select");
        var form = $(".entityForm").has(tableRow);
        var commandParameter = form.children("[name='command']").serialize();
        var subCommandParameter = form.children("[name='subCommand']").serialize();
        var rowParameters = rowInputNodes.serialize();
        var isAjaxRequestParameter = "isAjaxRequest=true";
        var url = "controller?" + commandParameter + "&" + subCommandParameter + "&" + isAjaxRequestParameter + "&" + rowParameters;
        $.get(url, function (data, status) {
            $("#operationMessage").text(data);
        });
    }

    function addRow() {
        var tableRow = $(".newEntity tr").last().clone();
        $(".newEntity tbody").append(tableRow);
        setTimeout(refresh, 1000);
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
