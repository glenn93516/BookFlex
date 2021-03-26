from flask import Flask, request
from flask_cors import CORS
from flask_restx import Resource, Api
from recommendation import recommendation


app = Flask(__name__)
cors = CORS(app, resources={r"/*": {"origins": "*"}})
app.register_blueprint(recommendation, url_prefix="/ml/api/recommend")


if __name__ == "__main__":
    app.run(debug=True)
