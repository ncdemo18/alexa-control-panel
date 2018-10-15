function parseURL(url) {
    let objParams = {};

    let indexParam = url.indexOf('?');
    if(indexParam !== -1 && indexParam !== url.length - 1) {
        let strParams = url.slice(url.indexOf('?') + 1);
        let params = strParams.split('&');

        params.forEach(
            function (val, key) {
                let parts = val.split('=', 2);
                objParams[parts[0]] = parts[1];
            }
        );
    }

    return objParams;
}

function fillParamBlock(index) {
    let paramsBlock = document.getElementById("paramsBlock-" + index);
    let urlParams = parseURL(answer.actions[index].url);
    for(let key in urlParams) {
        let elementLabel = document.createElement('p');
        elementLabel.textContent = key;
        paramsBlock.appendChild(elementLabel);

        let elementInput = document.createElement('input');
        elementInput.setAttribute('name', key);
        elementInput.setAttribute('value', urlParams[key]);
        paramsBlock.appendChild(elementInput);
    }
}

if(answer.actions !== undefined) {
    for(let i = 0; i < answer.actions.length; i++) {
        fillParamBlock(i);
    }
}

function generateURL() {
    if(answer.actions !== undefined) {
        for (let i = 0; i < answer.actions.length; i++) {
            let urlAction = answer.actions[i].templateAction.url;
            let paramsInput = document.getElementById("paramsBlock-" + i).getElementsByTagName('input');
            if(paramsInput.length > 0) {
                urlAction += '?';
                for (let j = 0; j < paramsInput.length; j++) {
                    urlAction += paramsInput[j].name + '=' + paramsInput[j].value;
                    if(j < paramsInput.length - 1) {
                        urlAction += '&';
                    }
                }
            }

            let urlInputBlock = document.getElementById("url-" + i);
            urlInputBlock.setAttribute('value', urlAction);
        }
    }
}