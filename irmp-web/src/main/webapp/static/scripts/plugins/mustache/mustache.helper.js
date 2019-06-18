function renderTemplate(id, item, elem) {
    var template = $('#' + id).html();
    Mustache.parse(template);
    var rendered = Mustache.render(template, item);
    var obj = $(rendered)
    elem.append(obj);
    return obj;
}
