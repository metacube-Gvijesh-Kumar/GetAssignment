 
 const sideBarToggle=document.querySelector("#sideBarToggle");
 const sideNavCollapse=document.querySelector("#sideNavCollapse");
 
 sideBarToggle.addEventListener('click',(e)=>{
    console.log('clicked',e)
    
    const tg =e.target;

    if(tg.getAttribute('aria-expanded')=="true"){
        tg.setAttribute('aria-expanded','false');
        sideNavCollapse.setAttribute('data-expanded',"false");
    }
    else{
        tg.setAttribute('aria-expanded','true');
        sideNavCollapse.setAttribute('data-expanded',"true");
    }

 })