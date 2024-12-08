from .route import *
router_inversionista = Blueprint('router_inversionista', __name__)

@router_inversionista.route('/investor/list')
def list_inversionista(msg=''):
    r = requests.get('http://localhost:8077/sgpe/investor/list')
    print(r.json())
    inversionistas = r.json()["data"]
    i = 1
    for inversionista in inversionistas:
        inversionista['numero'] = i
        i+=1
    return render_template('fragmento/inversionista/lista.html', inversionistas = inversionistas)


@router_inversionista.route('/investor/register')
def view_register_inversionista():
    r = requests.get("http://localhost:8077/sgpe/investor/listType")
    print(r.json())
    data = r.json()["data"]
    return render_template('fragmento/inversionista/registro.html', list = data)


@router_inversionista.route('/investor/save', methods=['POST'])
def save_inversionista():
    headers = {'Content-Type': 'application/json'}
    form = request.form

    dataF = {
        "nombre": form['nom'],
        "apellido": form['ape'],
        "dni": form['dni'],
        "direccion": form['dir'],
        "capitalInvertido": form['capInv'],
        "tipo": form['tipoI'],
    }

    r = requests.post("http://localhost:8077/sgpe/investor/save", data=json.dumps(dataF), headers=headers)
    print(r.json())
    dat = r.json()
    if r.status_code == 200:
        flash("¡Se ha guardado correctamente!", category='info')
        return redirect("/investor/list")
    else:
        flash(str(dat["data"]), category='error')
        return redirect("/investor/list")
    

@router_inversionista.route('/investor/edit/<id>')
def view_edit_inversionista(id):
    r = requests.get("http://localhost:8077/sgpe/investor/listType")
    print(r.json())
    data = r.json()["data"]
    r1 = requests.get("http://localhost:8077/sgpe/investor/get/"+id)
    print(r1.json())
    data1 = r1.json()["data"]
    if(r1.status_code == 200):
        return render_template('fragmento/inversionista/editar.html', list = data, inversionista=data1)
    else:
        flash(data1, category='error')
        return redirect("/investor/list")


@router_inversionista.route('/investor/update', methods=['POST'])
def update_inversionista():
    headers = {'Content-Type': 'application/json'}
    form = request.form

    dataF = {
        "id": form['idInv'],
        "nombre": form['nom'],
        "apellido": form['ape'],
        "dni": form['dni'],
        "direccion": form['dir'],
        "capitalInvertido": form['capInv'],
        "tipo": form['tipoI'],
    }

    r = requests.post("http://localhost:8077/sgpe/investor/update", data=json.dumps(dataF), headers=headers)
    print(r.json())
    dat = r.json()
    if r.status_code == 200:
        flash("¡Se ha actualizado correctamente!", category='info')
        return redirect("/investor/list")
    else:
        flash(str(dat["data"]), category='error')
        return redirect("/investor/list")
    

@router_inversionista.route('/investor/delete/<id>')
def view_delete_inversionista(id):
    return render_template('fragmento/inversionista/eliminar.html', id=id)


@router_inversionista.route('/investor/remove', methods=['POST'])
def delete_inversionista():
    form = request.form
    r = requests.post("http://localhost:8077/sgpe/investor/delete/"+form['idInv'])
    print(r.json())
    if r.status_code == 200:
        flash("¡Se ha eliminado correctamente!", category='info')
        return redirect("/investor/list")
    else:
        flash("¡No se ha podido eliminar!", category='error')
        return redirect("/investor/list")
    

@router_inversionista.route('/investor/sort/<atributo>/<orden>/<metodoOrden>')
def order_inversionista(atributo, orden, metodoOrden):
    r = requests.get('http://localhost:8077/sgpe/investor/sort/'+ atributo + '/' + orden + '/' + metodoOrden)
    print(r.json())
    inversionistas = r.json()["data"]
    i = 1
    for inversionista in inversionistas:
        inversionista['numero'] = i
        i+=1
    return render_template('fragmento/inversionista/lista.html', inversionistas = inversionistas)


@router_inversionista.route('/investor/search/<atributo>/<valor>')
def search_inversionista(atributo, valor):
    r = requests.get('http://localhost:8077/sgpe/investor/search/'+ atributo + '/' + valor)
    print(r.json())
    inversionistas = r.json()["data"]
    i = 1
    for inversionista in inversionistas:
        inversionista['numero'] = i
        i+=1
    return render_template('fragmento/inversionista/lista.html', inversionistas = inversionistas)