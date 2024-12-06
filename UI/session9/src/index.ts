
enum Genders{
    male = 'male',
    female = "female",
    other = "others"
};

class employee{

    private readonly name: string;
    public getName(): string {
        return this.name;
    }

    private readonly gender:Genders;
    public getGenders(): string {
        return this.gender;
    }

    private readonly email:string;
    public getEmail():string{
        return this.email;
    }

    private readonly mobile:string;
    public getMobile():string{
        return this.mobile;
    }

    private readonly password:string;
    public getPassword():string{
        return this.password;
    }


    public constructor(name: string,gender:Genders,email:string,mobile:string,password:string){
        this.name = name;
        this.gender=gender;
        this.email=email;
        this.mobile=mobile;
        this.password=password;
    }


    public print():void {
        console.log(this.name,this.gender,this.email,this.mobile,this.password);
        return;
    }
}

let nextEmpId:string="1456";
let empId:string;

const empForm:HTMLFormElement | null  = document.querySelector("#AddEmployee .form");
const empToggle:HTMLInputElement | null = document.querySelector("#employeeToggle");
const empLabels:NodeListOf<HTMLLabelElement> | null= document.querySelectorAll("#AddEmployee .form > .form__label");

const empIdTag:HTMLParagraphElement | null= document.querySelector('#empId');

const empCancel:HTMLButtonElement|null= document.querySelector("#AddEmployee .cancel");
const empNext:HTMLButtonElement|null  = document.querySelector("#AddEmployee .next");
const empSubmit:HTMLInputElement|null = document.querySelector("#AddEmployee .form__submit")

let currentEmpLabel:number|undefined;

const password:HTMLInputElement|null = document.querySelector("#password");
const confirmPassword:HTMLInputElement|null = document.querySelector("#confirmPassword");


function show(ele:HTMLElement|null):void
{
    if(ele==null)
        return;

    if(ele.classList.contains('hide')){
        ele.classList.remove('hide');
    }
    ele.classList.add('show');
}

function hide(ele:HTMLElement|null):void{

    
    if(ele==null)
        return;

    if(ele.classList.contains('show')){
        ele.classList.remove('show');
    }
    ele.classList.add('hide');
}

function removeStrengthClass(input:HTMLInputElement){

    if(input.classList.contains("error__input"))
        input.classList.remove("error__input");

    
    if(input.classList.contains("strong__pass"))
        input.classList.remove("strong__pass");

    
    if(input.classList.contains("normal__pass"))
        input.classList.remove("normal__pass");
}



password?.addEventListener('input',(e:any)=>{

    removeStrengthClass(e.target);

    let strength=0;
    let val = e.target.value;
    console.log(val);
    const smallReg=/[a-z]/;
    const capitalReg=/[A-Z]/;
    const numReg=/[0-9]/;
    const specialReg=/[!@#$%^&*()_,.?]/;

    if(val.length>=8)
        strength++;
    if(smallReg.test(val))
        strength++;
    if(capitalReg.test(val))
        strength++;
    if(numReg.test(val))
        strength++;
    if(specialReg.test(val))
        strength++; 

    if(strength<5){
        console.log('seted');
        password.setCustomValidity('password must have at least one capital,one small alphabet,one numeric,one special character');
        password.reportValidity();
        e.target.classList.add('error__input');
    }
    else{

        password.setCustomValidity("");

        if(val.length>=12)
            strength++;
    
        if(strength==5){
            e.target.classList.add('normal__pass');
        }
        else{
            e.target.classList.add('strong__pass');
        }

    }

})

confirmPassword?.addEventListener('input',(e:any)=>{
    removeStrengthClass(e.target);
    let val = e.target.value;
    if(password?.value==val){
        confirmPassword.setCustomValidity("");
        confirmPassword.classList.add('strong__pass');
    }
    else{
        confirmPassword.setCustomValidity("confirm password should match password");
        confirmPassword.reportValidity()
        confirmPassword.classList.add('error__input');
    }
})

function currentEmpInputValid(label:HTMLLabelElement){
    
    const inputId = label.getAttribute('for');
    const input:HTMLInputElement|null = document.querySelector('#'+inputId);
    
    if(input?.id=="password"){
        
        if (!input.checkValidity()){
            input.classList.add("error__input");
            input.reportValidity(); 
            return false;
        }

    }
    else if(input?.id=="confirmPassword"){
        
        if (!input.checkValidity()){
            input.reportValidity(); 
            input.classList.add("error__input");
            return false;
        }

        if(password?.value!=input.value){   
            input.classList.add("error__input");
            return false;
        }
        else{
            return true;
        }
    }

    else if(input?.id == "gender__radio"){

        const male:HTMLInputElement|null = document.querySelector("#male");
        const female:HTMLInputElement|null=document.querySelector("#female");
        const others:HTMLInputElement|null=document.querySelector('#otherGender');

        if(male?.checked ||  female?.checked || others?.checked){
            return true;
        }

        input.classList.add("error__input");
        return false;
        
    }
    else if (!input?.checkValidity()){
        input?.reportValidity();    
        input?.classList.add("error__input");
        return false;
    }

    return true;
}


function resetEmployeeForm(fullReset:Boolean){
        
     
            show(empCancel);
            show(empNext);
            hide(empSubmit);

        currentEmpLabel=undefined;
        
        if(!fullReset)
            return;

        const empInputs: NodeListOf<HTMLInputElement>= document.querySelectorAll("#AddEmployee .form  .form__input");
        const empRadio:HTMLInputElement|null=document.querySelector("#AddEmployee .form .form__radio");

     
            hide(empRadio);

        if(empLabels==null)
            return;

        for(let i=0;i<empLabels.length;i++){
            const label = empLabels[i];
            hide(label);
        }

        for(let i=0;i<empInputs.length;i++){
            
            const empInput=empInputs[i];

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

    
    if(empLabels==null)
        return;

    if(currentEmpLabel==undefined)
        currentEmpLabel=0;

    else if(currentEmpInputValid(empLabels[currentEmpLabel])){

        const label=empLabels[currentEmpLabel];
        const inputId = label.getAttribute('for');
        const input:HTMLInputElement|null = document.querySelector('#'+inputId);
        
        hide(input);
        hide(label);
        currentEmpLabel++;
    }
  
    if(currentEmpLabel>=empLabels.length-1){
        hide(empNext);
        show(empSubmit);
    }
    const label=empLabels[currentEmpLabel];
    const inputId = label.getAttribute('for');
    const input:HTMLInputElement|null= document.querySelector('#'+inputId);

    show(input);
    input?.focus();
    show(label);
}




empCancel?.addEventListener('click',(e)=>{
    resetEmployeeForm(true);
    if(empToggle==null)
        return;
    empToggle.checked=true;
})

empNext?.addEventListener('click',(e)=>{
    takeNextEmpInput();
})

empToggle?.addEventListener('input',(e:Event|null)=>{

    if(e==null)
        return;


    let checked = (e.target as HTMLInputElement)?.checked;
    resetEmployeeForm(false);

    if(empId!=undefined){
        alert("your employeeId is : "+ empId);

        
            if(empIdTag==null)
                return;


        empIdTag.innerHTML="your employeeId is : "+ empId;

        const sideContainer:HTMLElement|null = document.querySelector("#AddEmployee .sideContainer");
        const formContainer:HTMLElement|null = document.querySelector("#AddEmployee .formContainer");
        sideContainer?.classList.add("hide");
        formContainer?.classList.add("hide");
    }
    else if(!checked){
        takeNextEmpInput();
    }
});

empForm?.addEventListener("keypress",(e)=>{

    if(currentEmpLabel==undefined)
        return;

    if(e.code=='Enter' && currentEmpLabel<empLabels.length-1){
            e.preventDefault();
            takeNextEmpInput();    
    }
    
},true);

empForm?.addEventListener('submit',(e)=>{

    e.preventDefault();

    const data = new FormData(empForm);

    let empName:string|undefined;  
    let empGender:string|undefined;
    let empEmail:string|undefined;
    let empMobile:string|undefined;
    let empPass:string|undefined;

    data.forEach((value,name)=>{

        if(name=="fullName")
            empName=value as string ;
        else if(name=="password")
            empPass=value as string;
        else if(name=="email")
            empEmail=value as string;
        else if(name=="phone")
            empMobile=value as string;
        else if(name=="gender")
            empGender=value as string;

    })
    
    let createdEmp:employee|undefined;

    if(empName!=undefined && empGender!=undefined && empPass!=undefined && empMobile!=undefined && empEmail!=undefined){

        let gen:Genders ;

        if(empGender=='male')
            gen = Genders.male;
        else if(empGender=='female')
            gen= Genders.female;
        else 
            gen = Genders.other;

        createdEmp = new employee(empName,gen,empEmail,empMobile,empPass);
    }

    if(createdEmp!=undefined)
    createdEmp.print();


    empId=nextEmpId;

    nextEmpId="something";

    alert("your employeeId is : "+ empId);
    resetEmployeeForm(true);

    
    if(empIdTag==null)
        return;


    empIdTag.innerHTML="your employeeId is : "+ empId;

    const sideContainer:HTMLElement|null = document.querySelector("#AddEmployee .sideContainer");
    const formContainer:HTMLElement|null = document.querySelector("#AddEmployee .formContainer");
    sideContainer?.classList.add("hide");
    formContainer?.classList.add("hide");

    console.log("submitted");
})


let vehType:string|undefined;

const vehForm:HTMLFormElement | null  = document.querySelector("#AddVehicle .form");
const vehToggle:HTMLInputElement|null= document.querySelector("#vehicleToggle");

const vehLabels:NodeListOf<HTMLLabelElement>|null= document.querySelectorAll("#AddVehicle .form > .form__label");

const vehCancel:HTMLButtonElement|null= document.querySelector("#AddVehicle .cancel");
const vehNext:HTMLButtonElement|null  = document.querySelector("#AddVehicle .next");
const vehSubmit:HTMLInputElement|null = document.querySelector("#AddVehicle .form__submit")

const vehIdTag:HTMLParagraphElement|null = document.querySelector('#vehId');

let currentVehLabel:number|undefined;

enum VehicalType{
    car = 'car',
    bike = "bike",
    others = "others"
};

class Vehicle{

    private readonly company: string;
    public getCompany(): string {
        return this.company;
    }

    private readonly type:VehicalType;
    public getVehicleType(): string {
        return this.type;
    }

    private readonly model:string;
    public getModel():string{
        return this.model;
    }

    private readonly vehicleNo:string;
    public getVehicleNo():string{
        return this.vehicleNo;
    }

    private readonly identification:string;
    public getIdentification():string{
        return this.identification;
    }


    public constructor(company: string,type:VehicalType,model:string,vehicleNo:string,identification:string){
        this.company = company;
        this.type=type;
        this.model=model;
        this.vehicleNo=vehicleNo;
        this.identification=identification;
    }


    public print():void {
        console.log(this.company,this.model,this.type,this.vehicleNo,this.identification);
        return;
    }
}


function currentVehInputValid(label:HTMLLabelElement|null){

    if(label==null)
        return;

    const inputId = label.getAttribute('for');

    const input:HTMLInputElement|null = document.querySelector('#'+inputId);

    
    if(input?.id == "vehicleRadio"){

        const carType:HTMLInputElement|null = document.querySelector("#carType");
        const bikeType:HTMLInputElement|null = document.querySelector("#bikeType");
        const otherType:HTMLInputElement|null = document.querySelector("#otherType");

        if( carType?.checked ||  bikeType?.checked || otherType?.checked){
            return true;
        }

        input.classList.add("error__input");
        return false;
        
    }
    else if (!input?.checkValidity()){
        input?.classList.add("error__input");
        input?.reportValidity();    
        return false;
    }
    return true;
}


function resetVehicleForm(fullReset:Boolean){
        

        show(vehCancel);
          show(vehNext);
        hide(vehSubmit);

        currentVehLabel=undefined;
        
        if(!fullReset)
            return;

        const vehInputs:NodeListOf<HTMLInputElement>|null= document.querySelectorAll("#AddVehicle .form .form__input");

        const vehRadio:HTMLInputElement|null=document.querySelector("#AddVehicle .form .form__radio");

        hide(vehRadio);

        if(vehLabels==null)
            return;

        for(let i=0;i<vehLabels.length;i++){
            const label = vehLabels[i];
            hide(label)
        }

        for(let i=0;i<vehInputs.length;i++){

            const vehInput = vehInputs[i];

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

    if(vehLabels==null)
        return;

    if(currentVehLabel==undefined)
        currentVehLabel=0;

    else if(currentVehInputValid(vehLabels[currentVehLabel])){

        const label=vehLabels[currentVehLabel];
        const inputId = label.getAttribute('for');
        const input:HTMLInputElement|null = document.querySelector('#'+inputId);
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
    const input:HTMLInputElement|null = document.querySelector('#'+inputId);

    show(input);
    show(label);
    input?.focus();
}




vehCancel?.addEventListener('click',(e)=>{
    resetVehicleForm(true);

    if(vehToggle==null)
        return;

    vehToggle.checked=true;
})

vehNext?.addEventListener('click',(e)=>{
    takeNextVehInput();
})

vehToggle?.addEventListener('input',(e:Event|null)=>{

    if(e?.target==null)
        return;

    let checked = (e.target as HTMLInputElement).checked;
    resetVehicleForm(false);

    if(vehType!=undefined){

        if(vehIdTag==null)
            return;

        // alert("your pricing has been updated as per the vehicle type" + vehType);
        vehIdTag.innerHTML="your pricing has been updated as per the vehicle type " + vehType;

        const sideContainer:HTMLElement|null = document.querySelector("#AddVehicle .sideContainer");
        const formContainer:HTMLElement|null = document.querySelector("#AddVehicle .formContainer");

        sideContainer?.classList.add("hide");
        formContainer?.classList.add("hide");

    }
    else if(!checked){
        takeNextVehInput();
    }
});

vehForm?.addEventListener("keypress",(e)=>{

    if(currentVehLabel==undefined)
        return;

    if(e.code=='Enter' && currentVehLabel<vehLabels.length-1){
            e.preventDefault();
            takeNextVehInput();    
    }
    
},true);

vehForm?.addEventListener('submit',(e)=>{

    e.preventDefault();

    if(vehIdTag==null || vehToggle==null)
        return;

    const data = new FormData(vehForm);

    let enteredEmpId:string="";

    data.forEach((value,name)=>{
        console.log(name, ":", value)
      if(name=="type")
        vehType=value as string;
      if(name=="employeeId")
        enteredEmpId=value as string;
    })


    let vehCompany:string|undefined;  
    let typeVeh:VehicalType|undefined;
    let vehModel:string|undefined;
    let vehNo:string|undefined;
    let vehIdent:string|undefined;

    data.forEach((value,name)=>{

        if(name=="company")
            vehCompany=value as string ;
        else if(name=="Identification")
            vehIdent=value as string;
        else if(name=="model")
            vehModel=value as string;
        else if(name=="vehicleNumber")
            vehNo=value as string;
        else if(name=="type"){
            if(value=='car')
                typeVeh = VehicalType.car;
            else if(value='bike')
                typeVeh=VehicalType.bike;
            else if(value='others')
                typeVeh=VehicalType.others;
        }

    })
    
    let createdveh:Vehicle|undefined;

    if(vehCompany!=undefined && typeVeh!=undefined && vehIdent!=undefined && vehNo!=undefined && vehModel!=undefined){
        createdveh = new Vehicle(vehCompany,typeVeh,vehModel,vehNo,vehIdent);
    }

    if(createdveh!=undefined)
    createdveh.print();

    let msg;

    if(enteredEmpId=="" || enteredEmpId!=empId){
        msg='your entered empId doesnot correspond to employee id in our system get the emp Id through the employee reg form';
        vehType=undefined;
        alert(msg);
        resetVehicleForm(true);
        vehToggle.checked=true;
        return;
    }
    else{
        msg="your pricing has been updated as per the vehicle type " + vehType;

        const pricingId:HTMLParagraphElement|null = document.querySelector("#pricingId");

        const vehTypeCard:HTMLDivElement|null =  document.querySelector("#"+vehType+"Card");

        const pricingToggle:HTMLInputElement|null = document.querySelector("#pricingToggle");


        pricingId?.classList.add("hide");
        vehTypeCard?.classList.add("show");

        if(pricingToggle==null)
            return;

        pricingToggle.checked=false;  
        vehTypeCard?.scrollIntoView();
    }

    alert(msg);
    vehIdTag.innerHTML=msg;

    resetVehicleForm(true);

    const sideContainer:HTMLElement|null = document.querySelector("#AddVehicle .sideContainer");
    const formContainer:HTMLElement|null = document.querySelector("#AddVehicle .formContainer");

    sideContainer?.classList.add("hide");
    formContainer?.classList.add("hide");

    console.log("submitted");
})


function currencyConverter(val:number|null,requiredCurrency:string|null,currCurrency:string|null):string{

    if(val==null || requiredCurrency==null || currCurrency==null)
        return "";

    // console.log('converstion')
    // console.log(val,requiredCurrency,currCurrency);

    let rupee=val; 

    if(currCurrency=='euro'){
        rupee=val*50;
    }
    else if(currCurrency=='usd'){
        rupee=val*100;
    }
    else
        rupee=val;
    
    let result;    

    if(requiredCurrency=='usd')
        result= '$'+ rupee/100;
    else if(requiredCurrency=='euro')
        result= '€'+rupee/50;
    else
        result= '₹'+rupee;    

    // console.log(result);
    
    return result;
}

let lastCurr:string='rupee';

function changeCurrency(val:string,previousVal:string){

        let cardCircles:NodeListOf<HTMLHeadingElement>|null = document.querySelectorAll(".card__logo h2");

        for(let i=0;i<cardCircles.length;i++){

            let cardCircle = cardCircles[i];

            let matchArr:any|null = cardCircle.innerHTML.match(/(\d+)/);

            if(matchArr==null || matchArr.length==0)
                return;

            let currVal : number= (matchArr[0] as number);

            cardCircle.innerHTML=currencyConverter(currVal,val,previousVal);
        }

        let card__list__items = document.querySelectorAll(".card__list__item");

        for(let i=0;i<card__list__items.length;i++){

            let card__list__item = card__list__items[i];

            let matchArr:any|null = card__list__item.innerHTML.match(/(\d+)/);;

            if(matchArr==null || matchArr.length==0)
                return;

            let currVal : number= (matchArr[0] as number);


            card__list__item.innerHTML=currencyConverter(currVal,val,previousVal);
        }

}

const currencies:HTMLSelectElement|null = document.querySelector("#currencies");

currencies?.addEventListener('change',(e:Event|null)=>{
    
    if(e==null || e.target==null)
        return;

    const tg = e.target as HTMLSelectElement;

    changeCurrency(tg.value,lastCurr);

    lastCurr=tg.value;
})