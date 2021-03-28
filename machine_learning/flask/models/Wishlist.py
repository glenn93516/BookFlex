from flask_sqlalchemy import SQLAlchemy


db = SQLAlchemy()


class Wishlist(db.Model):
    ''' wishlist table
    - wishlist_id : pk
    - wishlist_date : 위시리스트 등록 날짜
    - user_id : user id
    - book_isbn : book isbn
    '''

    __tablename__ = "wishlist"

    wishlist_id = db.Column(db.Integer, nullable=False,
                            autoincrement=True, primary_key=True)
    wishlist_date = db.Column(db.DateTime)
    user_id = db.Column(db.Integer)
    book_isbn = db.Column(db.Integer)

    def __repr__(self):
        return f"[Wishlist] ({self.wishlist_id}, {self.wishlist_date}, {self.user_id}, {self.book_isbn})"
