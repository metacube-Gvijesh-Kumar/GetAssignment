 
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

 function addPieChart(){

  const ctx = document.getElementById('pieChart');
   
  const data = {
    labels: [
      'Red',
      'Blue',
      'Yellow'
    ],
    datasets: [{
      label: 'My First Dataset',
      data: [300, 50, 100],
      backgroundColor: [
        'rgb(255, 99, 132)',
        'rgb(54, 162, 235)',
        'rgb(255, 205, 86)'
      ],
      hoverOffset: 4
    }]
  };

  const config = {
    type: 'doughnut',
    data: data,
  };
  
  new Chart(ctx,config);
}

addPieChart();