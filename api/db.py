from toolz import pipe

import os
import psycopg2

class DB:
  def __init__(self, uri: str = None):
    if uri is None:
      uri = ('%s://%s:%s@%s:%d/%s' % ('postgresql',
                                      'council',
                                      'abcd',
                                      'localhost',
                                      5434,
                                      'urban'))

    self.conn = psycopg2.connect(uri)

  def do(self, stmt: str, *args) -> list:
    curs = self.conn.cursor()
    curs.execute(stmt, args)
    return curs.fetchall()

  def select(self, props: list, stmt: str, *args) -> dict:
    return pipe(stmt.format(', '.join(props)),
                lambda t: self.do(t, args),
                lambda t: map(lambda r: zip(props, r), t),
                lambda t: list(t))

db = DB(os.environ.get('DATABASE_URL'))
