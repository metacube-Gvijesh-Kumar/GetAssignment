
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
    return true;
}


function resetEmployeeForm(fullReset){
        
        show(empCancel);
        show(empNext);
        hide(empSubmit);

        currentEmpLabel=undefined;
        
        if(!fullReset)
            return;

        const empInputs= document.querySelectorAll("#AddEmployee .form .form__input");

        for(const label of empLabels){
            hide(label)
        }

        for(const empInput of empInputs){

            hide(empInput);
        
            if(empInput.type=="radio"){
                empInput.checked=false;
            }
            else{
                empInput.value="";
            }
        }
}

function takeNextEmpInput(){
    console.log('here');

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

empToggle.addEventListener("input",(e)=>{

    let checked = e.target.checked;
    resetEmployeeForm(false);

    if(empId!=undefined){
        alert("your employeeId is : "+ empId);
        empIdTag.innerHTML="your employeeId is : "+ empId;
        document.querySelector("#AddEmployee .container").classList.add("hide");
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

    empToggle.checked=true;
    empIdTag.innerHTML="your employeeId is : "+ empId;

    document.querySelector("#AddEmployee .container").classList.add("hide")


    console.log("submitted");
})