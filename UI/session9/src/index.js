var nextEmpId = "1456";
var empId;
var currentEmp;
var empForm = document.querySelector("#AddEmployee .form");
var empToggle = document.querySelector("#employeeToggle");
var empLabels = document.querySelectorAll("#AddEmployee .form > .form__label");
var empIdTag = document.querySelector('#empId');
var empCancel = document.querySelector("#AddEmployee .cancel");
var empNext = document.querySelector("#AddEmployee .next");
var empSubmit = document.querySelector("#AddEmployee .form__submit");
var currentEmpLabel;
var password = document.querySelector("#password");
var confirmPassword = document.querySelector("#confirmPassword");
function show(ele) {
    if (ele == null)
        return;
    if (ele.classList.contains('hide')) {
        ele.classList.remove('hide');
    }
    ele.classList.add('show');
}
function hide(ele) {
    if (ele == null)
        return;
    if (ele.classList.contains('show')) {
        ele.classList.remove('show');
    }
    ele.classList.add('hide');
}
function removeStrengthClass(input) {
    if (input.classList.contains("error__input"))
        input.classList.remove("error__input");
    if (input.classList.contains("strong__pass"))
        input.classList.remove("strong__pass");
    if (input.classList.contains("normal__pass"))
        input.classList.remove("normal__pass");
}
password === null || password === void 0 ? void 0 : password.addEventListener('input', function (e) {
    removeStrengthClass(e.target);
    var strength = 0;
    var val = e.target.value;
    console.log(val);
    var smallReg = /[a-z]/;
    var capitalReg = /[A-Z]/;
    var numReg = /[0-9]/;
    var specialReg = /[!@#$%^&*()_,.?]/;
    if (val.length >= 8)
        strength++;
    if (smallReg.test(val))
        strength++;
    if (capitalReg.test(val))
        strength++;
    if (numReg.test(val))
        strength++;
    if (specialReg.test(val))
        strength++;
    if (strength < 5) {
        e.target.classList.add('error__input');
    }
    else {
        if (val.length >= 12)
            strength++;
        if (strength == 5) {
            e.target.classList.add('normal__pass');
        }
        else {
            e.target.classList.add('strong__pass');
        }
    }
});
confirmPassword === null || confirmPassword === void 0 ? void 0 : confirmPassword.addEventListener('input', function (e) {
    removeStrengthClass(e.target);
    var val = e.target.value;
    if ((password === null || password === void 0 ? void 0 : password.value) == val)
        confirmPassword.classList.add('strong__pass');
    else
        confirmPassword.classList.add('error__input');
});
function currentEmpInputValid(label) {
    var inputId = label.getAttribute('for');
    var input = document.querySelector('#' + inputId);
    if ((input === null || input === void 0 ? void 0 : input.id) == "password") {
        if (!input.checkValidity()) {
            input.reportValidity();
            input === null || input === void 0 ? void 0 : input.setCustomValidity('password must have at least one capital,one small alphabet,one numeric,one special character');
            return false;
        }
    }
    else if ((input === null || input === void 0 ? void 0 : input.id) == "confirmPassword") {
        if (!input.checkValidity()) {
            input.reportValidity();
            return false;
        }
        if ((password === null || password === void 0 ? void 0 : password.value) != input.value) {
            return false;
        }
        else {
            return true;
        }
    }
    else if ((input === null || input === void 0 ? void 0 : input.id) == "gender__radio") {
        var male = document.querySelector("#male");
        var female = document.querySelector("#female");
        var others = document.querySelector('#otherGender');
        if ((male === null || male === void 0 ? void 0 : male.checked) || (female === null || female === void 0 ? void 0 : female.checked) || (others === null || others === void 0 ? void 0 : others.checked)) {
            return true;
        }
        input.classList.add("error__input");
        return false;
    }
    else if (!(input === null || input === void 0 ? void 0 : input.checkValidity())) {
        input === null || input === void 0 ? void 0 : input.reportValidity();
        return false;
    }
    //currently here we are returning true but later we will do verification here a
    // just query select the input corresponding to the label and then we can check the value of the input
    return true;
}
function resetEmployeeForm(fullReset) {
    show(empCancel);
    show(empNext);
    hide(empSubmit);
    currentEmpLabel = undefined;
    if (!fullReset)
        return;
    var empInputs = document.querySelectorAll("#AddEmployee .form  .form__input");
    var empRadio = document.querySelector("#AddEmployee .form .form__radio");
    hide(empRadio);
    if (empLabels == null)
        return;
    for (var i = 0; i < empLabels.length; i++) {
        var label = empLabels[i];
        hide(label);
    }
    for (var i = 0; i < empInputs.length; i++) {
        var empInput = empInputs[i];
        if (empInput.type == "radio") {
            empInput.checked = false;
        }
        else {
            hide(empInput);
            empInput.value = "";
        }
    }
}
function takeNextEmpInput() {
    // console.log('here');
    if (empLabels == null)
        return;
    if (currentEmpLabel == undefined)
        currentEmpLabel = 0;
    else if (currentEmpInputValid(empLabels[currentEmpLabel])) {
        var label_1 = empLabels[currentEmpLabel];
        var inputId_1 = label_1.getAttribute('for');
        var input_1 = document.querySelector('#' + inputId_1);
        hide(input_1);
        hide(label_1);
        currentEmpLabel++;
    }
    if (currentEmpLabel >= empLabels.length - 1) {
        hide(empNext);
        show(empSubmit);
    }
    var label = empLabels[currentEmpLabel];
    var inputId = label.getAttribute('for');
    var input = document.querySelector('#' + inputId);
    show(input);
    show(label);
}
empCancel === null || empCancel === void 0 ? void 0 : empCancel.addEventListener('click', function (e) {
    resetEmployeeForm(true);
    if (empToggle == null)
        return;
    empToggle.checked = true;
});
empNext === null || empNext === void 0 ? void 0 : empNext.addEventListener('click', function (e) {
    takeNextEmpInput();
});
empToggle === null || empToggle === void 0 ? void 0 : empToggle.addEventListener('input', function (e) {
    var _a;
    if (e == null)
        return;
    var checked = (_a = e.target) === null || _a === void 0 ? void 0 : _a.checked;
    resetEmployeeForm(false);
    if (empId != undefined) {
        alert("your employeeId is : " + empId);
        if (empIdTag == null)
            return;
        empIdTag.innerHTML = "your employeeId is : " + empId;
        var sideContainer = document.querySelector("#AddEmployee .sideContainer");
        var formContainer = document.querySelector("#AddEmployee .formContainer");
        sideContainer === null || sideContainer === void 0 ? void 0 : sideContainer.classList.add("hide");
        formContainer === null || formContainer === void 0 ? void 0 : formContainer.classList.add("hide");
    }
    else if (!checked) {
        takeNextEmpInput();
    }
});
empForm === null || empForm === void 0 ? void 0 : empForm.addEventListener('submit', function (e) {
    e.preventDefault();
    var data = new FormData(empForm);
    // for (const [name,value] of data) {
    //   console.log(name, ":", value)
    // }
    currentEmp = data;
    empId = nextEmpId;
    nextEmpId = "something";
    alert("your employeeId is : " + empId);
    resetEmployeeForm(true);
    if (empIdTag == null)
        return;
    empIdTag.innerHTML = "your employeeId is : " + empId;
    var sideContainer = document.querySelector("#AddEmployee .sideContainer");
    var formContainer = document.querySelector("#AddEmployee .formContainer");
    sideContainer === null || sideContainer === void 0 ? void 0 : sideContainer.classList.add("hide");
    formContainer === null || formContainer === void 0 ? void 0 : formContainer.classList.add("hide");
    console.log("submitted");
});
var vehType;
var vehForm = document.querySelector("#AddVehicle .form");
var vehToggle = document.querySelector("#vehicleToggle");
var vehLabels = document.querySelectorAll("#AddVehicle .form > .form__label");
var vehCancel = document.querySelector("#AddVehicle .cancel");
var vehNext = document.querySelector("#AddVehicle .next");
var vehSubmit = document.querySelector("#AddVehicle .form__submit");
var vehIdTag = document.querySelector('#vehId');
var currentVehLabel;
function currentVehInputValid(label) {
    if (label == null)
        return;
    var inputId = label.getAttribute('for');
    var input = document.querySelector('#' + inputId);
    if ((input === null || input === void 0 ? void 0 : input.id) == "vehicleRadio") {
        var carType = document.querySelector("#carType");
        var bikeType = document.querySelector("#bikeType");
        var otherType = document.querySelector("#otherType");
        if ((carType === null || carType === void 0 ? void 0 : carType.checked) || (bikeType === null || bikeType === void 0 ? void 0 : bikeType.checked) || (otherType === null || otherType === void 0 ? void 0 : otherType.checked)) {
            return true;
        }
        input.classList.add("error__input");
        return false;
    }
    else if (!(input === null || input === void 0 ? void 0 : input.checkValidity())) {
        input === null || input === void 0 ? void 0 : input.reportValidity();
        return false;
    }
    //currently here we are returning true but later we will do verification here a
    // just query select the input corresponding to the label and then we can check the value of the input
    return true;
}
function resetVehicleForm(fullReset) {
    show(vehCancel);
    show(vehNext);
    hide(vehSubmit);
    currentVehLabel = undefined;
    if (!fullReset)
        return;
    var vehInputs = document.querySelectorAll("#AddVehicle .form .form__input");
    var vehRadio = document.querySelector("#AddVehicle .form .form__radio");
    hide(vehRadio);
    if (vehLabels == null)
        return;
    for (var i = 0; i < vehLabels.length; i++) {
        var label = vehLabels[i];
        hide(label);
    }
    for (var i = 0; i < vehInputs.length; i++) {
        var vehInput = vehInputs[i];
        if (vehInput.type == "radio") {
            vehInput.checked = false;
        }
        else {
            hide(vehInput);
            vehInput.value = "";
        }
    }
}
function takeNextVehInput() {
    // console.log('here');
    if (vehLabels == null)
        return;
    if (currentVehLabel == undefined)
        currentVehLabel = 0;
    else if (currentVehInputValid(vehLabels[currentVehLabel])) {
        var label_2 = vehLabels[currentVehLabel];
        var inputId_2 = label_2.getAttribute('for');
        var input_2 = document.querySelector('#' + inputId_2);
        hide(input_2);
        hide(label_2);
        currentVehLabel++;
    }
    else
        return;
    if (currentVehLabel >= vehLabels.length - 1) {
        hide(vehNext);
        show(vehSubmit);
    }
    var label = vehLabels[currentVehLabel];
    var inputId = label.getAttribute('for');
    var input = document.querySelector('#' + inputId);
    show(input);
    show(label);
}
vehCancel === null || vehCancel === void 0 ? void 0 : vehCancel.addEventListener('click', function (e) {
    resetVehicleForm(true);
    if (vehToggle == null)
        return;
    vehToggle.checked = true;
});
vehNext === null || vehNext === void 0 ? void 0 : vehNext.addEventListener('click', function (e) {
    takeNextVehInput();
});
vehToggle === null || vehToggle === void 0 ? void 0 : vehToggle.addEventListener('input', function (e) {
    if ((e === null || e === void 0 ? void 0 : e.target) == null)
        return;
    var checked = e.target.checked;
    resetVehicleForm(false);
    if (vehType != undefined) {
        if (vehIdTag == null)
            return;
        // alert("your pricing has been updated as per the vehicle type" + vehType);
        vehIdTag.innerHTML = "your pricing has been updated as per the vehicle type " + vehType;
        var sideContainer = document.querySelector("#AddVehicle .sideContainer");
        var formContainer = document.querySelector("#AddVehicle .formContainer");
        sideContainer === null || sideContainer === void 0 ? void 0 : sideContainer.classList.add("hide");
        formContainer === null || formContainer === void 0 ? void 0 : formContainer.classList.add("hide");
    }
    else if (!checked) {
        takeNextVehInput();
    }
});
vehForm === null || vehForm === void 0 ? void 0 : vehForm.addEventListener('submit', function (e) {
    e.preventDefault();
    if (vehIdTag == null || vehToggle == null)
        return;
    var data = new FormData(vehForm);
    var enteredEmpId = "";
    data.forEach(function (value, name) {
        console.log(name, ":", value);
        if (name == "type")
            vehType = value;
        if (name == "employeeId")
            enteredEmpId = value;
    });
    // for (const [name,value] of data) {
    //   console.log(name, ":", value)
    //   if(name=="type")
    //     vehType=value as string;
    //   if(name=="employeeId")
    //     enteredEmpId=value as string;
    // }
    var msg;
    if (enteredEmpId == "" || enteredEmpId != empId) {
        msg = 'your entered empId doesnot correspond to employee id in our system get the emp Id through the employee reg form';
        vehType = undefined;
        alert(msg);
        resetVehicleForm(true);
        vehToggle.checked = true;
        return;
    }
    else {
        msg = "your pricing has been updated as per the vehicle type " + vehType;
        var pricingId = document.querySelector("#pricingId");
        var vehTypeCard = document.querySelector("#" + vehType + "Card");
        var pricingToggle = document.querySelector("#pricingToggle");
        pricingId === null || pricingId === void 0 ? void 0 : pricingId.classList.add("hide");
        vehTypeCard === null || vehTypeCard === void 0 ? void 0 : vehTypeCard.classList.add("show");
        if (pricingToggle == null)
            return;
        pricingToggle.checked = false;
        vehTypeCard === null || vehTypeCard === void 0 ? void 0 : vehTypeCard.scrollIntoView();
    }
    alert(msg);
    vehIdTag.innerHTML = msg;
    resetVehicleForm(true);
    var sideContainer = document.querySelector("#AddVehicle .sideContainer");
    var formContainer = document.querySelector("#AddVehicle .formContainer");
    sideContainer === null || sideContainer === void 0 ? void 0 : sideContainer.classList.add("hide");
    formContainer === null || formContainer === void 0 ? void 0 : formContainer.classList.add("hide");
    console.log("submitted");
});
function currencyConverter(val, requiredCurrency, currCurrency) {
    if (val == null || requiredCurrency == null || currCurrency == null)
        return "";
    // console.log('converstion')
    // console.log(val,requiredCurrency,currCurrency);
    var rupee = val;
    if (currCurrency == 'euro') {
        rupee = val * 50;
    }
    else if (currCurrency == 'usd') {
        rupee = val * 100;
    }
    else
        rupee = val;
    var result;
    if (requiredCurrency == 'usd')
        result = '$' + rupee / 100;
    else if (requiredCurrency == 'euro')
        result = '€' + rupee / 50;
    else
        result = '₹' + rupee;
    // console.log(result);
    return result;
}
var lastCurr = 'rupee';
function changeCurrency(val, previousVal) {
    var cardCircles = document.querySelectorAll(".card__logo h2");
    for (var i = 0; i < cardCircles.length; i++) {
        var cardCircle = cardCircles[i];
        var matchArr = cardCircle.innerHTML.match(/(\d+)/);
        if (matchArr == null || matchArr.length == 0)
            return;
        var currVal = matchArr[0];
        cardCircle.innerHTML = currencyConverter(currVal, val, previousVal);
    }
    var card__list__items = document.querySelectorAll(".card__list__item");
    for (var i = 0; i < card__list__items.length; i++) {
        var card__list__item = card__list__items[i];
        var matchArr = card__list__item.innerHTML.match(/(\d+)/);
        ;
        if (matchArr == null || matchArr.length == 0)
            return;
        var currVal = matchArr[0];
        card__list__item.innerHTML = currencyConverter(currVal, val, previousVal);
    }
}
var currencies = document.querySelector("#currencies");
currencies === null || currencies === void 0 ? void 0 : currencies.addEventListener('change', function (e) {
    if (e == null || e.target == null)
        return;
    var tg = e.target;
    changeCurrency(tg.value, lastCurr);
    lastCurr = tg.value;
});