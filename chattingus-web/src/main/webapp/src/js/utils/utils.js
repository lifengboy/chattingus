/**
 * Created by huzhengnan on 16/8/28.
 */
/**
 * 数字方式获取当前时间
 * @returns {number}
 */
function curentTime() {
    var now = new Date();

    var year = now.getFullYear();       //年
    var month = now.getMonth() + 1;     //月
    var day = now.getDate();            //日

    var hh = now.getHours();            //时
    var mm = now.getMinutes();          //分
    var ss = now.getSeconds();

    var clock = year;

    if (month < 10)
        month += "0";

    clock += "" + month;

    if (day < 10)
        day += "0";

    clock += "" + day;

    if (hh < 10)
        hh += "0";

    clock += "" + hh;
    if (mm < 10) mm += '0';
    clock += "" + mm;

    if (ss < 10) ss += '0';
    clock += "" + ss;
    return (clock);
}

/**
 * js 格式化显示
 * @param json
 * @param options
 * @returns {string}
 */
// Example usage: http://jsfiddle.net/q2gnX/
function jsonFormat(json, options) {
    var reg = null,
        formatted = '',
        pad = 0,
        PADDING = '    '; // one can also use '\t' or a different number of spaces

    // optional settings
    options = options || {};
    // remove newline where '{' or '[' follows ':'
    options.newlineAfterColonIfBeforeBraceOrBracket = (options.newlineAfterColonIfBeforeBraceOrBracket === true) ? true : false;
    // use a space after a colon
    options.spaceAfterColon = (options.spaceAfterColon === false) ? false : true;

    // begin formatting...
    if (typeof json !== 'string') {
        // make sure we start with the JSON as a string
        json = JSON.stringify(json);
    } else {
        // is already a string, so parse and re-stringify in order to remove extra whitespace
        json = JSON.parse(json);
        json = JSON.stringify(json);
    }

    // add newline before and after curly braces
    reg = /([\{\}])/g;
    json = json.replace(reg, '\r\n$1\r\n');

    // add newline before and after square brackets
    reg = /([\[\]])/g;
    json = json.replace(reg, '\r\n$1\r\n');

    // add newline after comma
    reg = /(\,)/g;
    json = json.replace(reg, '$1\r\n');

    // remove multiple newlines
    reg = /(\r\n\r\n)/g;
    json = json.replace(reg, '\r\n');

    // remove newlines before commas
    reg = /\r\n\,/g;
    json = json.replace(reg, ',');

    // optional formatting...
    if (!options.newlineAfterColonIfBeforeBraceOrBracket) {
        reg = /\:\r\n\{/g;
        json = json.replace(reg, ':{');
        reg = /\:\r\n\[/g;
        json = json.replace(reg, ':[');
    }
    if (options.spaceAfterColon) {
        reg = /\:/g;
        json = json.replace(reg, ': ');
    }

    $.each(json.split('\r\n'), function (index, node) {
        var i = 0,
            indent = 0,
            padding = '';

        if (node.match(/\{$/) || node.match(/\[$/)) {
            indent = 1;
        } else if (node.match(/\}/) || node.match(/\]/)) {
            if (pad !== 0) {
                pad -= 1;
            }
        } else {
            indent = 0;
        }

        for (i = 0; i < pad; i++) {
            padding += PADDING;
        }

        formatted += padding + node + '\r\n';
        pad += indent;
    });

    return formatted;
};

/**
 * 判断是否为正整数
 * @param s
 * @returns {boolean}
 */
function isPositiveNum(s) {//是否为正整数
    var re = /^[0-9]*[1-9][0-9]*$/;
    return re.test(s)
}

function parseJsonResult(json, successMsg, errorMsg, noResponseMsg, parseDataFunction) {
    console.log(json);
    if (noResponseMsg == undefined) {
        noResponseMsg = "无返回数据<br/>"
    }
    json = JSON.parse(json);
    var content = "";
    if (json != undefined && json.success === true) {
        if (successMsg != undefined) {
            content += successMsg + "<br/>";
        }
        for (var i in json.data) {
            content += i + ":" + json.data[i];
        }
        if (parseDataFunction != undefined) {
            content += parseDataFunction(json.data);
        }
    } else if (json != undefined) {
        if (errorMsg != undefined) {
            content += errorMsg + "<br/>";
        }
        if (json.code != undefined) {
            content += json.code + "<br/>";
        }
        if (json.msg != undefined) {
            content += json.msg + "<br/>";
        }
        if (json.subCode != undefined) {
            content += json.subCode + "<br/>";
        }
        if (json.subMsg != undefined) {
            content += json.subMsg + "<br/>";
        }
    } else {
        content += noResponseMsg;
    }
    return content;
}
var fetchData = function (url, method, params, successCallback, errorCallback) {
    $.ajax({
        url: url,
        dataType: "json",
        type: method,
        cache: false,
        headers: {
            'X-HTTP-Method-Override': method,
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        data: params,
        success: function (json) {
            successCallback(json);
        },
        error: function () {
            errorCallback();
        }
    });
};