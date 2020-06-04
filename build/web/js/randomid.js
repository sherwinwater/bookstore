function randomID() {
    let year = new Date().getFullYear().toString();
    let month = (new Date().getMonth() + 1).toString();
    let date = new Date().getDate().toString();
    let hour = new Date().getHours().toString();
    let min = new Date().getMinutes().toString();
    let second = new Date().getSeconds().toString();
    month = month.length == 1 ? "0".toString() + month : month;
    date = date.length == 1 ? "0".toString() + date : date;
    hour = hour.length == 1 ? "0".toString() + hour : hour;
    min = min.length == 1 ? "0".toString() + min : min;
    second = second.length == 1 ? "0".toString() + second : second;
//    if(month.length == 1){
//        month = "0".toString()+month;
//    }
//    if(date.length == 1){
//        date = "0".toString()+date;
//    }
//    if(hour.length == 1){
//        hour = "0".toString()+hour;
//    }
//    if(min.length == 1){
//        min = "0".toString()+min;
//    }
//    if(second.length == 1){
//        second = "0".toString()+second;
//    }

//    let randomnum = Math.floor(Math.random() * 100000).toString();
    return year + month + date + hour + min + second;
    alert(year + month + date + hour + min + second);
}
export {randomID};