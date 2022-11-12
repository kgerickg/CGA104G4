function onSaveClick(admId) {
    const admAcc = document.querySelector(`#admAcc${admId}`).textContent;
    const admPwd = document.querySelector(`#admPwd${admId}`).textContent;
    const admName = document.querySelector(`#admName${admId}`).textContent;
    const admAccStat = !!+document.querySelector(`#admAccStat${admId}`).value;

    fetch('save', {
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

