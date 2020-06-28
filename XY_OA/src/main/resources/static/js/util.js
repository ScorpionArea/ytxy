$.fn.serializeObject = function () {
    var o = {};
    var a = this.serializeArray();
    $.each(a, function () {
        if (o[this.name]) {
            if (!o[this.name].push) {
                o[this.name] = [o[this.name]];
            }
            o[this.name].push(this.value || '');
        } else {
            o[this.name] = this.value || '';
        }
    });
    return o;
};

/**
 *
 *输入的时间格式为yyyy-MM-dd
 */
function convertDateFromString(dateString) {
    if (dateString) {
        var date = new Date(dateString.replace(/-/, "/"))
        return date;
    }
}

/**
 *
 *输入的时间格式为yyyy-MM-dd hh:mm:ss
 */
function convertDatetimeFromString(dateString) {
    if (dateString) {
        var arr1 = dateString.split(" ");
        var sdate = arr1[0].split('-');
        var date = new Date(sdate[0], sdate[1] - 1, sdate[2]);
        return date;
    }
}

/**
 * 几天后的时间（负数为几天前）;
 */
function getOtherDate(curDate, number) {
    var otherDate = new Date(curDate.getTime() + number * 24 * 60 * 60 * 1000);
    return otherDate;
};

