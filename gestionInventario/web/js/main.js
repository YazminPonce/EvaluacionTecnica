let moduloInventario;
let moduloSalidaInventario;
let moduloHistorial;
 var use= new Object(); 
 use=JSON.parse(localStorage.getItem('currentUser'));

function cargarModuloInventario(){
    
     if(use !== null){
    
    fetch("modulos/moduloInventario/vista_inventario.html")
            .then(   
                function(response){
                    return response.text();
                }
            )
            .then(
                function(html){
                    document.getElementById("contenedorPrincipal").innerHTML = html;
                    import ("../modulos/moduloInventario/controlador_Inventario.js").then(
                            function(controller){
                               moduloInventario = controller;
                             moduloInventario.inicializar();
                            }
                            );
                }
            );
    
    }else{
       window.location.href="http://localhost:8080/Biblioteca/inicio.html"; 
    }
}



function cargarModuloGestionSalidaInventario(){
   

   
    if(use !== null){
    if (use.rol.idRol === 2){
         fetch("modulos/moduloSalidaProductos/vista_SalidaProducto.html")
            .then(
                function(response){
                    return response.text();
                }
            )
            .then(
                function(html){
                    document.getElementById("contenedorPrincipal").innerHTML = html;
                    import ("../modulos/moduloSalidaProductos/controlador_SalidaProducto.js").then(
                            function(controller){
                                moduloSalidaInventario = controller;
                              moduloSalidaInventario.inicializar();
                            }
                            );
                }
            );
    }else{
       alert("No tiene permiso para ver esta opcion.");
    }
    }else{
       window.location.href="http://localhost:8080/Biblioteca/inicio.html"; 
    }
}


function cargarModuloGestionHistorial(){
   
    
   
    if(use !== null){
    if (use.rol.idRol === 1){
         fetch("modulos/moduloHistorial/vista_historial.html")
            .then(
                function(response){
                    return response.text();
                }
            )
            .then(
                function(html){
                    document.getElementById("contenedorPrincipal").innerHTML = html;
                    import ("../modulos/moduloHistorial/controlador_historial.js").then(
                            function(controller){
                                moduloHistorial = controller;
                              moduloHistorial.inicializar();
                            }
                            );
                }
            );
    }else{
       alert("No tiene permiso para ver esta opcion.");
    }
    }else{
       window.location.href="http://localhost:8080/Biblioteca/inicio.html"; 
    }
}
       


function acceso(){
    
    
    let usuario = document.getElementById("username").value;
    let contra=document.getElementById("password").value;
    let objusuario= new Object();
    let objRol= new Object();
    
    objRol.idRol=0;
    objRol.nombre="";
    objusuario.idUsuario=0;
    objusuario.correo="";
    objusuario.nombre=usuario;
    objusuario.contrasenia=contra;
    objusuario.rol=objRol;
    objusuario.estatus=0;
    
    


    
  datos = JSON.stringify(objusuario); //conviertes un objeto java Script a una cadena JSON
  fetch('api/usuario/in',
    {
        method: 'POST',
                headers: { 'Accept': 'application/json',
                           'Content-Type': 'application/json'
                       },
                body: datos
    }).then(response => response.json())
      .then (data=>{
          if (data.exception != null)
        {
             alert("error del servidor");
            //Swal.fire('',"Error interno del servidor. Intente nuevamente más tarde",'error');
            return;
        }
        if (data.error != null)
        {
            alert(data.error);
           // Swal.fire('',data.error, 'warning');
            return;
        }
        
        localStorage.setItem('currentUser',JSON.stringify(data));
       
     
        
          alert("Bienvenid@");
       // Swal.fire('','bienvenidos','success');
      
         window.location.href="http://localhost:8080/gestionInventario/inicio.html";
       
      });
   
   
}


function cerrarSecion(){
    
    
     localStorage.removeItem('currentUser');
     window.location.href="http://localhost:8080/gestionInventario/index.html";
    alert('secion cerrada con exito');
    }
    
    
    
   