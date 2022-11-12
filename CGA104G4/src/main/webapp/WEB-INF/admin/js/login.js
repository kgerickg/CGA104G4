(() => {
  const admAcc = document.querySelector('#admAcc');
  const admPwd = document.querySelector('#admPwd');
  const errMsg = document.querySelector('#errMsg');
  document.getElementById('btn1').addEventListener('click', () => {
    fetch('login', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({
        admAcc: admAcc.value,
        admPwd: admPwd.value
      }),
    })
      .then(resp => resp.json())
      .then(body => {
        errMsg.textContent = '';
        const { successful, message } = body;
        if (successful) {
          const { admId, admName } = body;
          sessionStorage.setItem('admId', admId);
          sessionStorage.setItem('admName', admName);
          location = '../index.html';
        } else {
          errMsg.textContent = message;
        }
      });
  });
})();