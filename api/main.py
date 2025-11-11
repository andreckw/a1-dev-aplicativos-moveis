from flask import Flask
from flask_sqlalchemy import SQLAlchemy
import os

app = Flask(__name__)

app.config['SECRET_KEY'] = "aaaaaaaaaaa"
app.config['SQLALCHEMY_DATABASE_URI'] = 'sqlite:///calculadora.db'

db = SQLAlchemy()
db.init_app(app)

class Basico(db.Model):
    __tablename__ = "basicos"
    id = db.Column(db.Integer, primary_key=True)
    formula = db.Column(db.String)
    resultado = db.Column(db.Float)

    def __init__(self, formula, resultado):
        self.formula = formula
        self.resultado = resultado

if not os.path.isfile("instance/calculadora.db"):
    with app.app_context():
        db.create_all()



@app.route("/")
def index():
    return {"api": "API da calculadora"}

@app.route("/basicos")
def buscar():
    historicos = Basico.query.all()
    if (historicos == []):
        db.session.add(Basico(formula="1+1", resultado=2))
        db.session.add(Basico(formula="100*1", resultado=100))
        db.session.add(Basico(formula="130/10", resultado=13))
        db.session.add(Basico(formula="100-150", resultado=-50))
        db.session.commit()
        historicos = Basico.query.all()
    
    listaDict = []
    for h in historicos:
        print(h.formula)
        listaDict.append({
            "id": h.id,
            "formula": h.formula,
            "resultado": h.resultado,
        })

    return listaDict
    


if __name__ == "__main__":
    debug = True
    ip = "127.0.0.1"
    port = "5000"
    app.run(ip, port, debug=debug)