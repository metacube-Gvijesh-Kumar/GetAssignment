
let nextEmpId=1456;

let empId;
let currentEmp;

const empForm  = document.querySelector("#AddEmployee .form");
const empToggle= document.querySelector("#employeeToggle");

const empLabels= document.querySelectorAll("#AddEmployee .form > .form__label");

const empCancel= document.querySelector("#AddEmployee .cancel");
const empNext  = document.querySelector("#AddEmployee .next");
const empSubmit = document.querySelector("#AddEmployee .form__submit")

const empIdTag = document.querySelector('#empId');

let currentEmpLabel;


function show(ele){
    if(ele.classList.contains('hide')){
        ele.classList.remove('hide');
    }
    ele.classList.add('show');
}

function hide(ele){
    if(ele.classList.contains('show')){
        ele.classList.remove('show');
    }
    ele.classList.add('hide');
}


function currentEmpInputValid(label){
    
    const inputId = label.getAttribute('for');
    const input = document.querySelector('#'+inputId);
    
    if(input.id == "gender__radio"){

        if(document.querySelector("#male").checked ||  document.querySelector("#female").checked || document.querySelector("#otherGender").checked){
            return true;
        }

        input.classList.add("error__input");
        return false;
        
    }
    else if (!input.checkValidity()){
        input.reportValidity();    
        return false;
    }
    //currently here we are returning true but later we will do verification here a
    // just query select the input corresponding to the label and then we can check the value of the input
    return true;
}


function resetEmployeeForm(fullReset){
        

        show(empCancel);
          show(empNext);
        hide(empSubmit);

        currentEmpLabel=undefined;
        
        if(!fullReset)
            return;

        const empInputs= document.querySelectorAll("#AddEmployee .form  .form__input");
        const empRadio=document.querySelector("#AddEmployee .form .form__radio");

        hide(empRadio);

        for(const label of empLabels){
            hide(label)
        }

        for(const empInput of empInputs){
            
            if(empInput.type=="radio"){
                empInput.checked=false;
            }
            else{
                hide(empInput);
                empInput.value="";
            }
        }
}

function takeNextEmpInput(){
    // console.log('here');

    if(currentEmpLabel==undefined)
        currentEmpLabel=0;
    else if(currentEmpInputValid(empLabels[currentEmpLabel])){

        const label=empLabels[currentEmpLabel];
        const inputId = label.getAttribute('for');
        const input = document.querySelector('#'+inputId);
        hide(input);
        hide(label);
        currentEmpLabel++;
    }
    else
        return;

    if(currentEmpLabel>=empLabels.length-1){
        hide(empNext);
        show(empSubmit);
    }
    const label=empLabels[currentEmpLabel];
    const inputId = label.getAttribute('for');
    const input = document.querySelector('#'+inputId);

    show(input);
    show(label);
}




empCancel.addEventListener('click',(e)=>{
    resetEmployeeForm(true);
    empToggle.checked=true;
})

empNext.addEventListener('click',(e)=>{
    takeNextEmpInput();
})

employeeToggle.addEventListener("input",(e)=>{

    let checked = e.target.checked;
    resetEmployeeForm(false);

    if(empId!=undefined){
        alert("your employeeId is : "+ empId);
        empIdTag.innerHTML="your employeeId is : "+ empId;

        document.querySelector("#AddEmployee .sideContainer").classList.add("hide");
        document.querySelector("#AddEmployee .formContainer").classList.add("hide");
    }
    else if(!checked){
        takeNextEmpInput();
    }
});

empForm.addEventListener('submit',(e)=>{

    e.preventDefault();

    const data = new FormData(empForm);
    for (const [name,value] of data) {
      console.log(name, ":", value)
    }        
    currentEmp=data;
    empId=nextEmpId;
    nextEmpId++;

    alert("your employeeId is : "+ empId);
    resetEmployeeForm(true);

    empIdTag.innerHTML="your employeeId is : "+ empId;

    document.querySelector("#AddEmployee .sideContainer").classList.add("hide");
    document.querySelector("#AddEmployee .formContainer").classList.add("hide");

    console.log("submitted");
})


let vehType; // stores vehicle type
const vehForm  = document.querySelector("#AddVehicle .form");
const vehToggle= document.querySelector("#vehicleToggle");

const vehLabels= document.querySelectorAll("#AddVehicle .form > .form__label");

const vehCancel= document.querySelector("#AddVehicle .cancel");
const vehNext  = document.querySelector("#AddVehicle .next");
const vehSubmit = document.querySelector("#AddVehicle .form__submit")

const vehIdTag = document.querySelector('#vehId');

let currentVehLabel;


function show(ele){
    if(ele.classList.contains('hide')){
        ele.classList.remove('hide');
    }
    ele.classList.add('show');
}

function hide(ele){
    if(ele.classList.contains('show')){
        ele.classList.remove('show');
    }
    ele.classList.add('hide');
}


function currentVehInputValid(label){

    const inputId = label.getAttribute('for');
    const input = document.querySelector('#'+inputId);
    
    if(input.id == "vehicleRadio"){

        if(document.querySelector("#carType").checked ||  document.querySelector("#bikeType").checked || document.querySelector("#otherType").checked){
            return true;
        }

        input.classList.add("error__input");
        return false;
        
    }
    else if (!input.checkValidity()){
        input.reportValidity();    
        return false;
    }
    //currently here we are returning true but later we will do verification here a
    // just query select the input corresponding to the label and then we can check the value of the input
    return true;
}


function resetVehicleForm(fullReset){
        

        show(vehCancel);
          show(vehNext);
        hide(vehSubmit);

        currentVehLabel=undefined;
        
        if(!fullReset)
            return;

        const vehInputs= document.querySelectorAll("#AddVehicle .form .form__input");

        const vehRadio=document.querySelector("#AddVehicle .form .form__radio");

        hide(vehRadio);

        for(const label of vehLabels){
            hide(label)
        }

        for(const vehInput of vehInputs){

            if(vehInput.type=="radio"){
                vehInput.checked=false;
            }
            else{
                hide(vehInput);
                vehInput.value="";
            }
        }
}

function takeNextVehInput(){
    // console.log('here');

    if(currentVehLabel==undefined)
        currentVehLabel=0;

    else if(currentVehInputValid(vehLabels[currentVehLabel])){

        const label=vehLabels[currentVehLabel];
        const inputId = label.getAttribute('for');
        const input = document.querySelector('#'+inputId);
        hide(input);
        hide(label);
        currentVehLabel++;
    }
    else
        return;

    if(currentVehLabel>=vehLabels.length-1){
        hide(vehNext);
        show(vehSubmit);
    }
    const label=vehLabels[currentVehLabel];
    const inputId = label.getAttribute('for');
    const input = document.querySelector('#'+inputId);

    show(input);
    show(label);
}




vehCancel.addEventListener('click',(e)=>{
    resetVehicleForm(true);
    vehToggle.checked=true;
})

vehNext.addEventListener('click',(e)=>{
    takeNextVehInput();
})

vehToggle.addEventListener("input",(e)=>{

    let checked = e.target.checked;
    resetVehicleForm(false);

    if(vehType!=undefined){
        alert("your pricing has been updated as per the vehicle type" + vehType);
        vehIdTag.innerHTML="your pricing has been updated as per the vehicle type " + vehType;

        document.querySelector("#AddVehicle .sideContainer").classList.add("hide");
        document.querySelector("#AddVehicle .formContainer").classList.add("hide");
    }
    else if(!checked){
        takeNextVehInput();
    }
});

vehForm.addEventListener('submit',(e)=>{

    e.preventDefault();

    const data = new FormData(vehForm);

    for (const [name,value] of data) {
      console.log(name, ":", value)
      if(name=="type")
        vehType=value;
    }

    document.querySelector("#"+vehType+"Card").classList.add("show");
    document.querySelector("#pricingId").classList.add("hide");

    // update the pricing section as per the value of the veh type entered by user

    alert("your pricing has been updated as per the vehicle type" + vehType);
    vehIdTag.innerHTML="your pricing has been updated as per the vehicle type " + vehType;
    resetVehicleForm(true);

    
    document.querySelector("#AddVehicle .sideContainer").classList.add("hide");
    document.querySelector("#AddVehicle .formContainer").classList.add("hide");

    console.log("submitted");
})


