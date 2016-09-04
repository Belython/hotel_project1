function newHotel() {
    var tableRow ="\n" +
        "<tr> " +
        "<td><input type=\"text\" name=\"hotelId\" placeholder=\"ID можно не писать\"></td> " +
        "<td><input type=\"text\" name=\"hotelCountry\" placeholder=\"hotelCountry\"></td> " +
        "<td><input type=\"text\" name=\"hotelCity\" placeholder=\"hotelCity\"></td> " +
        "<td><input type=\"text\" name=\"hotelName\" placeholder=\"hotelName\"></td> " +
        "<td><input type=\"text\" name=\"hotelDiscount\" placeholder=\"hotelDiscount\"></td> " +
        "<td><input type=\"text\" name=\"hotelStatus\" placeholder=\"hotelStatus\"></td> " +
        "<td><input type=\"submit\" value=\"apply\"></td> " +
        "</tr>";
    $("table").append(tableRow);
}
