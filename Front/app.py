from flask import Flask 

def create_app():
    app = Flask(__name__, instance_relative_config=False)
    
    with app.app_context():
        from routes.route import router
        from routes.route_proyecto import router_proyecto
        from routes.route_inversionista import router_inversionista
        from routes.route_historial import router_historial
        app.register_blueprint(router)
        app.register_blueprint(router_proyecto)
        app.register_blueprint(router_inversionista)
        app.register_blueprint(router_historial)
    return app