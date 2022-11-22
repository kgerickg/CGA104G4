function onSaveClick(admId) {
    const admAcc = document.querySelector(`#admAcc${admId}`).textContent;
    const admPwd = document.querySelector(`#admPwd${admId}`).textContent;
    const admName = document.querySelector(`#admName${admId}`).textContent;
    const admAccStat = +document.querySelector(`#admAccStat${admId}`).value;
    const path = window.location.pathname;
    const webCtx = path.substring(0, path.indexOf('/', 1));
    const url = webCtx + "/admin/save";

    fetch(url, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({
            admId,
            admAcc,
            admPwd,
            admName,
            admAccStat
        })
    })
        .then(resp => resp.json())
        .then(body => {
            const { successful, message } = body;
            if (successful) {
                alert('存檔成功!');
                location.reload();
            } else {
                alert(message ?? '存檔失敗');
            }
        });
}

