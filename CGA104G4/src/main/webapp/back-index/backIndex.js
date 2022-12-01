window.onload = function () {
    const refill = document.querySelector('#refill');
    const member = document.querySelector('#member');
    const emp = document.querySelector('#emp');
    const admRoleMem = sessionStorage.getItem('admRoleMem');
    const admRoleEmp = sessionStorage.getItem('admRoleEmp');
    const admRoleRef = sessionStorage.getItem('admRoleRef');
    admRoleRef == 1 ? (refill.classList.remove('hide')) : refill.classList.add('hide');
    admRoleEmp == 1 ? (emp.classList.remove('hide')) : emp.classList.add('hide');
    admRoleMem == 1 ? (member.classList.remove('hide')) : member.classList.add('hide');
};

const path = window.location.pathname;
const webCtx = path.substring(0, path.indexOf('/', 1));
const logoutUrl = webCtx + "/admin/logout"

const admName = sessionStorage.getItem('admName');
const admRoleMem = sessionStorage.getItem('admRoleMem');
const admRoleEmp = sessionStorage.getItem('admRoleEmp');
const admRoleRef = sessionStorage.getItem('admRoleRef');
const forLogout = document.querySelector('#forLogout');
const forLogin = document.querySelector('#forLogin');
forLogin.addEventListener('click', ()=>{
    forLogout.classList.remove('hide');
    forLogin.classList.add('hide');
});
forLogout.addEventListener('click', () => {
    sessionStorage.removeItem('admName');
    sessionStorage.removeItem('admId');
    sessionStorage.removeItem('admRoleMem');
    sessionStorage.removeItem('admRoleEmp');
    sessionStorage.removeItem('admRoleRef');
    forLogin.classList.remove('hide');
    forLogout.classList.add('hide');
    fetch(logoutUrl);
    document.location.reload();
});