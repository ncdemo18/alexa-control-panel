function createSimpleElement(tagName, properties, textContent) {
    let element = document.createElement(tagName);
    for(let i = 0; i < properties.length; i++) {
        for(let key in properties[i]) {
            setProperty(element, key, properties[i][key]);
        }
        let value = properties[i];
        setProperty(element, value.key, value.value);
    }
    if(textContent !== undefined) {
        element.textContent = textContent;
    }
    return element;
}

function setProperty(element, attrName, value) {
    if(attrName !== undefined && value !== undefined) {
        element.setAttribute(attrName, value);
    }
}

function createActionElement() {
    let actionElement = {};
    actionElement["url"] = getUrl();
    let templateAction = document.getElementById("templateAction");
    actionElement["templateAction"] = actions[templateAction.value];
    return actionElement;
}

function addHtmlElement(action, index) {
    let actionBlock = document.getElementById("actionsBlock");
    let actionItem = createSimpleElement('div', [{'class' : 'list-group-item'}, {'id' : 'actionItem-' + index}]);
    actionBlock.appendChild(actionItem);

    actionItem.appendChild(createActionItem('idTemplate-' + index, 'actions[' + index + '].templateAction.id', 'hidden', action.templateAction.id));
    actionItem.appendChild(createActionItem('url-' + index, 'actions[' + index + '].url', 'hidden', action.id));
    actionItem.appendChild(createSimpleElement('p', [], action.templateAction.description));
    actionItem.appendChild(document.createElement('br'));
    actionItem.appendChild(createSimpleElement('div', [{'id' : 'paramsBlock-' + index}]));

    if(action.templateAction.urlparams.length > 0) {
        fillParamBlock(index);
    }

}

function createActionItem(id, name, type, value) {
    return createSimpleElement('input', [{'id' : id}, {'name' : name}, {'type' : type}, {'value' : value}]);
}

function addAction() {
    let count;
    if(answer.actions.length === undefined) {
        count = 0;
    } else {
        count = answer.actions.length;
    }
    answer.actions[count] = createActionElement();
    addHtmlElement(answer.actions[count], count);
}