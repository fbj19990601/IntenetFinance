
function selectControl(currentObj)
{

if(currentObj.checked){ 
   obj=document.getElementsByName('id');
   for(var i=0;i<obj.length;i++) 
   { 
    //document.form1.range[i].checked=true; 
    obj.item(i).checked=true;
     
   } 
 }
 else{
 obj=document.getElementsByName('id');
   for(var i=0;i<obj.length;i++) 
   { 
        if(obj.item(i).checked) 
    { 
        obj.item(i).checked=false; 
    } 
else
    { 
        obj.item(i).checked=true; 
    } 
   } 
 }

}
