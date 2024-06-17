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
       if (producto.estatus === 1) {
        let estado = producto.estatus === 1 ? 'Activo' : producto.estatus === 0 ? 'Inactivo' : 'Desconocido';
        let registro =
                '<tr onclick="moduloSalidaInventario.InventarioSelecionado(' + productos.indexOf(producto) + ');">' +
                '<td>' + producto.nombre + '</td>' +
                '<td>' + producto.cantidad + '</td>' +
                '<td>' + estado + '</td></tr>';
        cuerpo += registro;
       }
    });
    document.getElementById("tblProductos").innerHTML = cuerpo;
}


export function InventarioSelecionado(index) {
    document.getElementById("txtIdProducto").value = productos[index].idProductos;
    document.getElementById("txtCantidadActual").value = productos[index].cantidad;
    document.getElementById("txtCantidad").value = productos[index].cantidad;

    document.getElementById("txtCantidad").removeAttribute("readonly");

    indexProductoSeleccionado = index;
    servicioTabla();
}



export function actualizarCantidad() {

    let datos = null;
    
    let producto = new Object();

    let id = document.getElementById("txtIdProducto").value
    let cantidad = document.getElementById("txtCantidad").value
    let cantidadActual = document.getElementById("txtCantidadActual").value

    if(parseInt(cantidad) < parseInt(cantidadActual)){
       producto.idProductos =id;
       producto.nombre = "";
       producto.cantidad = cantidadActual-cantidad;
       producto.estatus = use.idUsuario;

       datos = JSON.stringify(producto);


       fetch("api/producto/CambiarCantidadSalidad",
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
        Swal.fire('', 'la cantidad excede a la cantidad actual del producto', 'warning');
    }
   
}




export function limpiar() {
    document.getElementById("txtIdProducto").value ="";
    document.getElementById("txtCantidadActual").value = "";
    document.getElementById("txtCantidad").value ="";
    document.getElementById("txtCantidad").setAttribute("readonly", true);

    
    indexProductoSeleccionado = 0;
    
}




