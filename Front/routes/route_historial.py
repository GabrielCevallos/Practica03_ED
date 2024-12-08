from .route import *
router_historial = Blueprint('router_historial', __name__)

@router_historial.route('/history/list')
def list_historial(msg=''):
    r = requests.get('http://localhost:8077/sgpe/history/list')
    print(r.json())
    historiales = r.json()["data"]
    i = 1
    for historial in historiales:
        historial['numero'] = i
        i+=1
    return render_template('fragmento/historial/lista.html', historiales = historiales)


@router_historial.route('/history/sort/<atributo>/<orden>/<metodoOrden>')
def order_historial(atributo, orden, metodoOrden):
    r = requests.get('http://localhost:8077/sgpe/history/sort/'+ atributo + '/' + orden + '/' + metodoOrden)
    print(r.json())
    historiales = r.json()["data"]
    i = 1
    for historial in historiales:
        historial['numero'] = i
        i+=1
    return render_template('fragmento/historial/lista.html', historiales = historiales)