let productos = [];
let  indexProductoSeleccionado;
 var use= new Object(); 
 use=JSON.parse(localStorage.getItem('currentUser'));
export function inicializar() {
    servicioTabla();
}

export function servicioTabla() {

    let url = "api/producto/getAll";
    fetch(url)
            .then(response => {
                return response.json();
            })
            .then(function (data) {
                if (data.exception != null) {
                    Swal.fire('', 'error interno del servidor.Intete nuevamente mas tarde.', 'error');
                    return;
                }
                if (data.error != null) {
                    Swal.fire('', data.error, 'Warning');
                    return;
                }

                cargarTabla(data);
            });
}

export function cargarTabla(datos) {
    let cuerpo = "";
    productos = datos;
    productos.forEach(function (producto) {
        let estado = producto.estatus === 1 ? 'Activo' : producto.estatus === 0 ? 'Inactivo' : 'Desconocido';
        let registro =
                '<tr onclick="moduloInventario.InventarioSelecionado(' + productos.indexOf(producto) + ');">' +
                '<td>' + producto.nombre + '</td>' +
                '<td>' + producto.cantidad + '</td>' +
                '<td>' + estado + '</td></tr>';
        cuerpo += registro;
    });
    document.getElementById("tblProductos").innerHTML = cuerpo;
}


export function InventarioSelecionado(index) {
    document.getElementById("txtIdProducto").value = productos[index].idProductos;
    document.getElementById("txtNombre").value = productos[index].nombre;
    document.getElementById("txtCantidad").value = productos[index].cantidad;
    
    let estatus = productos[index].estatus === 1 ? "Activo" : "Inactivo";
    document.getElementById("txtestatus").value = estatus;
    document.getElementById("txtCantidad").removeAttribute("readonly");

    indexProductoSeleccionado = index;
    servicioTabla();
}


export function guardar() {
 if (use.rol.idRol === 1){

    let datos = null;
    
    let producto = new Object();

   let nombre= document.getElementById("txtNombre").value;

    producto.idProductos =0;
    producto.nombre = nombre;
    producto.cantidad = 0;
    producto.estatus = use.idUsuario;

    datos = JSON.stringify(producto);


    fetch("api/producto/guardar",
            {
                method: "POST",
                headers:  { 'Accept': 'application/json',
                           'Content-Type': 'application/json'
                       },
                body: datos
            })
            .then(response => {
                return response.json();
            })
            .then(function (data)
            {
                if (data.exception != null) {
                    Swal.fire('', "Error interno del servidor. Intente nuevamente más tarde" + data.exception + data, 'error');
                    return;
                }
                if (data.error != null) {
                    Swal.fire('', data.error, 'warning')
                    return;
                }
                if (data.errorperm != null)
                {
                    Swal.fire('', "No tiene permiso para realizar esta operación.", 'warning');
                }
                document.getElementById("txtIdProducto").value = data.idProductos;
                Swal.fire('', 'Producto guardado correctamente', 'success');

            });
    servicioTabla();
    limpiar();
}else{
     Swal.fire('', 'no tienes permiso para realizar esta accion', 'warning');
}
}


export function actualizarCantidad() {
 if (use.rol.idRol === 1){

    let datos = null;
    
    let producto = new Object();

    let id = document.getElementById("txtIdProducto").value
    let cantidad = document.getElementById("txtCantidad").value
    if(cantidad > productos[indexProductoSeleccionado].cantidad){
       producto.idProductos =id;
       producto.nombre = "";
       producto.cantidad = cantidad;
       producto.estatus = 1;

       datos = JSON.stringify(producto);


       fetch("api/producto/CambiarCantidad",
               {
                   method: "POST",
                   headers:  { 'Accept': 'application/json',
                              'Content-Type': 'application/json'
                          },
                   body: datos
               })
               .then(response => {
                   return response.json();
               })
               .then(function (data)
               {
                   if (data.exception != null) {
                       Swal.fire('', "Error interno del servidor. Intente nuevamente más tarde" + data.exception + data, 'error');
                       return;
                   }
                   if (data.error != null) {
                       Swal.fire('', data.error, 'warning')
                       return;
                   }
                   
                   document.getElementById("txtIdProducto").value = data.idProductos;
                   Swal.fire('', 'cantidad actualizada correctamente', 'success');

               });
       servicioTabla();
       limpiar();
        
    }else{
        Swal.fire('', 'no puedes disminuir la cantidad ', 'warning');
    }
   }else{
     Swal.fire('', 'no tienes permiso para realizar esta accion', 'warning');
}
}




export function limpiar() {
    document.getElementById("txtIdProducto").value ="";
    document.getElementById("txtNombre").value = "";
    document.getElementById("txtCantidad").value ="";
    document.getElementById("txtestatus").value ="";
    document.getElementById("txtCantidad").setAttribute("readonly", true);

    
    indexProductoSeleccionado = 0;
    
}


export function eliminar() {
     if (use.rol.idRol === 1){


    let datos = null;
    
    let producto = new Object();

    let id = document.getElementById("txtIdProducto").value

       producto.idProductos =id;
       producto.nombre = "";
       producto.cantidad = 0;
       producto.estatus = 0;

       datos = JSON.stringify(producto);


       fetch("api/producto/CambiarEstatus",
               {
                   method: "POST",
                   headers:  { 'Accept': 'application/json',
                              'Content-Type': 'application/json'
                          },
                   body: datos
               })
               .then(response => {
                   return response.json();
               })
               .then(function (data)
               {
                   if (data.exception != null) {
                       Swal.fire('', "Error interno del servidor. Intente nuevamente más tarde" + data.exception + data, 'error');
                       return;
                   }
                   if (data.error != null) {
                       Swal.fire('', data.error, 'warning')
                       return;
                   }
                  Swal.fire('', 'Producto eliminado', 'success');

               });
       servicioTabla();
       limpiar();
        
   }else{
     Swal.fire('', 'no tienes permiso para realizar esta accion', 'warning');
}
   
}

export function activar() {
 if (use.rol.idRol === 1){

    let datos = null;
    
    let producto = new Object();

    let id = document.getElementById("txtIdProducto").value

       producto.idProductos =id;
       producto.nombre = "";
       producto.cantidad = 0;
       producto.estatus = 1;

       datos = JSON.stringify(producto);


       fetch("api/producto/CambiarEstatus",
               {
                   method: "POST",
                   headers:  { 'Accept': 'application/json',
                              'Content-Type': 'application/json'
                          },
                   body: datos
               })
               .then(response => {
                   return response.json();
               })
               .then(function (data)
               {
                   if (data.exception != null) {
                       Swal.fire('', "Error interno del servidor. Intente nuevamente más tarde" + data.exception + data, 'error');
                       return;
                   }
                   if (data.error != null) {
                       Swal.fire('', data.error, 'warning')
                       return;
                   }
                 
                   Swal.fire('', 'Producto activado', 'success');

               });
       servicioTabla();
       limpiar();
        }else{
     Swal.fire('', 'no tienes permiso para realizar esta accion', 'warning');
}
   


   
}