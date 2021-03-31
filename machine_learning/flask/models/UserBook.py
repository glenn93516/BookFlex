from flask_sqlalchemy import SQLAlchemy


db = SQLAlchemy()


class UserBook(db.Model):
    ''' user_book table
    - user_book_id : pk
    - user_id : user id
    - book_isbn : book id
    '''

    __tablename__ = "user_book"

    user_book_id = db.Column(db.Integer, nullable=False,
                             autoincrement=True, primary_key=True)
    user_id = db.Column(db.Integer)
    book_isbn = db.Column(db.Integer)

    def __repr__(self):
        return f"[UserBook] ({self.user_book_id}, {self.user_id}, {self.book_isbn})"
