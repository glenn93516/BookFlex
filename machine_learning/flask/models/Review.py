from flask_sqlalchemy import SQLAlchemy


db = SQLAlchemy()


class Review(db.Model):
    ''' review table
    - review_id         : 리뷰 아이디(PK)
    - book_isbn         : 책 ISBN(FK)
    - review_content    : 리뷰 내용
    - review_rating     : 리뷰 점수
    '''

    __tablename__ = "review"

    review_id = db.Column(db.Integer, nullable=False,
                          autoincrement=True, primary_key=True)
    book_isbn = db.Column(db.Integer, nullable=False)
    review_content = db.Column(db.Text(), nullable=False)
    review_rating = db.Column(db.Integer)

    def __repr__(self):
        return f"[Review] ({self.review_id}, {self.book_isbn}, {self.review_content}, {self.review_rating})"

    def as_dict(self):
        return {x.name: getattr(self, x.name) for x in self.__table__.columns}
