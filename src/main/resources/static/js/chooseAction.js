var action = null;
var isAction = document.getElementById("isAction");
var actionBlock = document.getElementById("actionBlock");
var paramBlock = document.getElementById("paramBlock");
var templateAction = document.getElementById("templateAction");
var inputURL = document.getElementById("urlValue");
var inputAction = document.getElementById("idAction");
function chooseActions() {
    if(isAction.checked) {
        actionBlock.style.display = "";
    } else {
        actionBlock.style.display = "none";
        templateAction.value = "";
    }
}
function showAction() {
    paramBlock.innerHTML = "";
    if(templateAction.value !== "") {
        action = actions[templateAction.value];
        for(let i = 0; i < action.urlparams.length; i++) {
            let nameParam = document.createElement('span');
            nameParam.textContent = action.urlparams[i].name + ': ';
            paramBlock.appendChild(nameParam);

            let inputParam = document.createElement('input');
            inputParam.setAttribute('type', 'text');
            inputParam.setAttribute('id', 'input-' + action.urlparams[i].name);
            paramBlock.appendChild(inputParam);
        }
    } else {
        action = null;
    }
}
function getUrl() {
    let url = action.url;
    if(action.urlparams.length > 0) {
        url += '?';
    }
    for(let i = 0; i < action.urlparams.length; i++) {
        let nameParam = action.urlparams[i].name;
        url += nameParam + '=' + document.getElementById('input-' + nameParam).value;
        if(i + 1 < action.urlparams.length) {
            url += '&';
        }
    }
    return url;
}
