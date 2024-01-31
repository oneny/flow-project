const $fixedCheckboxList = document.querySelector('.fixed-checkbox-list');
const fixedExtension = ['bat', 'cmd', 'com', 'cpl', 'exe', 'scr', 'js'];

fixedExtension.forEach((extension) => {
    const liElement = document.createElement('li');
    const inputElement = document.createElement('input')
    const spanElement = document.createElement('span');

    inputElement.type = 'checkbox';
    spanElement.textContent = extension;

    liElement.classList.add('fixed-checkbox-item')
    liElement.append(inputElement, spanElement);
    $fixedCheckboxList.appendChild(liElement);
});