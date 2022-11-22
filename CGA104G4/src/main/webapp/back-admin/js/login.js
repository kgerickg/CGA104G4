(() => {
    const admAcc = document.querySelector('#admAcc');
    const admPwd = document.querySelector('#admPwd');
    const errMsg = document.querySelector('#errMsg');
    const path = window.location.pathname;
    const webCtx = path.substring(0, path.indexOf('/', 1));
    const url = webCtx + "/admin/login";
    document.getElementById('btn1').addEventListener('click', () => {
        fetch(url, {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify({
                admAcc: admAcc.value,
                admPwd: admPwd.value
            }),
        })
            .then(resp => resp.json())
            .then(body => {
                errMsg.textContent = '';
                const {successful, message} = body;
                if (successful) {
                    const {admId, admName} = body;
                    sessionStorage.setItem('admId', admId);
                    sessionStorage.setItem('admName', admName);
                    location = '../back-admin/index.html';
                } else {
                    errMsg.textContent = message;
                }
            });
    });
})();