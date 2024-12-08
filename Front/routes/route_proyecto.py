from .route import *
router_proyecto = Blueprint('router_proyecto', __name__)

@router_proyecto.route('/proyect/list')
def list_proyecto():
    r = requests.get('http://localhost:8077/sgpe/proyect/list')
    print(r.json())
    proyectos = r.json()["data"]
    i = 1
    for proyecto in proyectos:
        proyecto['numero'] = i
        i+=1
    return render_template('fragmento/proyectos/lista.html', proyectos = proyectos)


@router_proyecto.route('/proyect/register')
def view_register_proyecto():
    r = requests.get("http://localhost:8077/sgpe/proyect/provincias")
    print(r.json())
    data = r.json()["data"]
    return render_template('fragmento/proyectos/registro.html', list = data)


@router_proyecto.route('/proyect/save', methods=['POST'])
def save_proyecto():
    headers = {'Content-Type': 'application/json'}
    form = request.form

    dataF = {
        "nombre": form['nom'],
        "fechaInicio": form['fechaI'],
        "fechaFin": form['fechaF'],
        "costo": form['cost'],
        "cantElectricidad": form['cantE'],
        "montoTotalInversion": form['montoT'],
        "provincia": form['prov'],
    }

    r = requests.post("http://localhost:8077/sgpe/proyect/save", data=json.dumps(dataF), headers=headers)
    print(r.json())
    dat = r.json()
    if r.status_code == 200:
        flash("¡Se ha guardado correctamente!", category='info')
        return redirect("/proyect/list")
    else:
        flash(str(dat["data"]), category='error')
        return redirect("/proyect/list")
    

@router_proyecto.route('/proyect/edit/<id>')
def view_edit_proyecto(id):
    r = requests.get("http://localhost:8077/sgpe/proyect/provincias")
    print(r.json())
    data = r.json()["data"]
    r1 = requests.get("http://localhost:8077/sgpe/proyect/get/"+id)
    print(r1.json())
    data1 = r1.json()["data"]
    if(r1.status_code == 200):
        return render_template('fragmento/proyectos/editar.html', list =data, proyecto = data1)
    else:
        flash(data1, category='error')
        return redirect("/proyect/list")
    

@router_proyecto.route('/proyect/update', methods=['POST'])
def update_proyecto():
    headers = {'Content-Type': 'application/json'}
    form = request.form

    dataF = {
        "id" : form['idPr'],
        "nombre": form['nom'],
        "fechaInicio": form['fechaI'],
        "fechaFin": form['fechaF'],
        "costo": form['cost'],
        "cantElectricidad": form['cantE'],
        "montoTotalInversion": form['montoT'],
        "provincia": form['prov'],
    }
    
    r = requests.post("http://localhost:8077/sgpe/proyect/update", data=json.dumps(dataF), headers=headers)
    print(r.json())
    if r.status_code == 200:
        flash("¡Se ha actualizado correctamente!", category='info')
        return redirect("/proyect/list")
    else:
        flash("¡No se ha podido actualizar!", category='error')
        return redirect("/proyect/list")
    

@router_proyecto.route('/proyect/delete/<id>')
def view_delete_proyecto(id):
    return render_template('fragmento/proyectos/eliminar.html', id=id)


@router_proyecto.route('/proyect/remove', methods=['POST'])
def delete_proyecto():
    form = request.form
    r = requests.post("http://localhost:8077/sgpe/proyect/delete/"+form['idPr'])
    print(r.json())
    if r.status_code == 200:
        flash("¡Se ha eliminado correctamente!", category='info')
        return redirect("/proyect/list")
    else:
        flash("¡No se ha podido eliminar!", category='error')
        return redirect("/proyect/list")
    

@router_proyecto.route('/proyect/sort/<atributo>/<orden>/<metodoOrden>')
def order_proyecto(atributo, orden, metodoOrden):
    r = requests.get('http://localhost:8077/sgpe/proyect/sort/'+ atributo + '/' + orden + '/' + metodoOrden)
    print(r.json())
    proyectos = r.json()["data"]
    i = 1
    for proyecto in proyectos:
        proyecto['numero'] = i
        i+=1
    return render_template('fragmento/proyectos/lista.html', proyectos = proyectos)


@router_proyecto.route('/proyect/search/<atributo>/<valor>')
def search_proyecto(atributo, valor):
    r = requests.get('http://localhost:8077/sgpe/proyect/search/'+ atributo + '/' + valor)
    print(r.json())
    proyectos = r.json()["data"]
    i = 1
    for proyecto in proyectos:
        proyecto['numero'] = i
        i+=1
    return render_template('fragmento/proyectos/lista.html', proyectos = proyectos)