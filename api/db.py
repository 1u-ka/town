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

  def do(self, stmt: str) -> list:
    curs = self.conn.cursor()
    curs.execute(stmt)
    return curs.fetchall()

db = DB(os.environ.get('DATABASE_URI'))
