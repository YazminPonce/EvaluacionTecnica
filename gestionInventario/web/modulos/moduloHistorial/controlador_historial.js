let movimientos = [];

export function inicializar() {
    servicioTabla();
}

export function cargarTabla(datos) {
    let cuerpo = "";
    movimientos = datos;
    movimientos.forEach(function (movimiento) {
     
            let rol = movimiento.usuario.rol.idRol === 1 ? 'Administrador' : movimiento.usuario.rol.idRol === 2 ? 'Almacenista' : 'Desconocido';
            let registro =
                '<tr>' +
                '<td>' + movimiento.tipoMovimiento + '</td>' +
                '<td>' + movimiento.fechaHora + '</td>' +
                '<td>' + movimiento.usuario.nombre + '</td>' +
                '<td>' + rol + '</td></tr>';
            cuerpo += registro;
        
    });
    document.getElementById("tblMovimientos").innerHTML = cuerpo;
}

export function filtrarTabla() {
    let filtro = document.getElementById("filterMovimiento").value;
    let cuerpo = "";
    movimientos.forEach(function (movimiento) {
        let tipoMovimiento = movimiento.tipoMovimiento.toLowerCase();
        if ((filtro === "todos") ||
            (filtro === "entrada" && tipoMovimiento === "entrada") ||
            (filtro === "salida" && tipoMovimiento === "salida")) {
            let rol = movimiento.usuario.rol.idRol === 1 ? 'Administrador' : movimiento.usuario.rol.idRol === 2 ? 'Almacenista' : 'Desconocido';
            let registro =
                 '<tr>' +
                '<td>' + movimiento.tipoMovimiento + '</td>' +
                '<td>' + movimiento.fechaHora + '</td>' +
                '<td>' + movimiento.usuario.nombre + '</td>' +
                '<td>' + rol + '</td></tr>';
            cuerpo += registro;
        }
    });
    document.getElementById("tblMovimientos").innerHTML = cuerpo;
}





export function servicioTabla() {

    let url = "api/historial/getAll";
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