 
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

    // var sideBarWidth=sideNavCollapse.offsetWidth;
    // console.log(sideBarWidth);
    // const r = document.querySelector(':root');
    // r.style.setProperty('--sideBar-width',sideBarWidth);

 })


 window.onload = function () {
    var chart = new CanvasJS.Chart("chartContainer",
        {

            data: [
                {
                    type: "doughnut",
                    innerRadius: 90,  // Makes the doughnut thinner
                    dataPoints: [
                        { y: 64, color: "#0acf97" },
                        { y: 10, color: "#fa5c7c" },
                        { y: 26, color: "#727cf5" }
                    ]
                }
            ]
        });

    chart.render();
}


