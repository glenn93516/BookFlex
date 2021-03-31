from flask import Flask, request, make_response, jsonify
from flask_cors import CORS
from flask_restx import Resource, Api
from flask_sqlalchemy import SQLAlchemy
from apis.recommendation import recommendation


app = Flask(__name__)

# DB 설정
app.config["SQLALCHEMY_DATABASE_URI"] = "mysql+pymysql://ssafy:ssafyssafy@field-ensemble.cqwzhdgaabxu.ap-northeast-2.rds.amazonaws.com:3306/fieldproject"
app.config["SQLALCHEMY_TRACK_MODIFICATIONS"] = False
app.config['SQLALCHEMY_POOL_RECYCLE'] = 3600
db = SQLAlchemy(app, session_options={'autocommit': True})

# CORS 설정
cors = CORS(app, resources={r"/*": {"origins": "*"}})

# EndPoint 추가
app.register_blueprint(recommendation, url_prefix="/ml/api/recommend")


if __name__ == "__main__":
    app.run(debug=True, host="0.0.0.0")
    print(db)
    print(app.config)
