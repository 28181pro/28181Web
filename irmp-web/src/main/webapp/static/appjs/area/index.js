function refresh() {
    treetable.query();
    $("#divdetail").html("<p>点击左侧区域查看明细</p>");

}
function search() {
    treetable.search($("#txtName").val());
}
function show(id){
    $("#divdetail").load("/area/detail/" + id, null, function (response, status, xhr) {
        if (status == "success" || status == "notmodified") {
        } else {
        }
    });
}