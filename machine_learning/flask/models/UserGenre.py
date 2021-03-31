from flask_sqlalchemy import SQLAlchemy


db = SQLAlchemy()


class UserGenre(db.Model):
    ''' user_genre table
    - user_genre_id : pk
    - user_id : user id
    - genre_id : genre id
    '''

    __tablename__ = "user_genre"

    user_genre_id = db.Column(db.Integer, nullable=False,
                              autoincrement=True, primary_key=True)
    user_id = db.Column(db.Integer)
    genre_id = db.Column(db.Integer)

    def __repr__(self):
        return f"[UserGenre] ({self.user_genre_id}, {self.user_id}, {self.genre_id})"
