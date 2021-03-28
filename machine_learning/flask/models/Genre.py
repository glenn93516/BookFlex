from flask_sqlalchemy import SQLAlchemy


db = SQLAlchemy()


class Genre(db.Model):
    ''' genre table
    - genre_id : 장르 id
    - genre_name : 장르 이름
    '''

    __tablename__ = "genre"

    genre_id = db.Column(db.Integer, nullable=False,
                         autoincrement=True, primary_key=True)
    genre_name = db.Column(db.String(255))

    def __repr__(self):
        return f"[Genre] ({self.genre_id}, {self.genre_name})"
