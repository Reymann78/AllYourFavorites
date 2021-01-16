
export default function formatDate(date) {
    let d = new Date(date);
    const weekday = new Array(7);
    weekday[0] = 'Sonntag';
    weekday[1] = 'Montag';
    weekday[2] = 'Dienstag';
    weekday[3] = 'Mittwoch';
    weekday[4] = 'Donnerstag';
    weekday[5] = 'Freitag';
    weekday[6] = 'Samstag';

    let formattedWeekday = weekday[d.getDay()] + ' ',
        day = d.getDate() + '.',
        month = d.getMonth() + 1 + '.',
        year = d.getFullYear() + ' ',
        hour = d.getHours() + ':',
        min = d.getMinutes() + ':',
        sec = '00';

    if (month.length < 3) month = '0' + month;
    if (day.length < 3) day = '0' + day;
    if (min.length < 3) min = '0' + min;

    return [formattedWeekday, day, month, year, hour, min, sec];
}