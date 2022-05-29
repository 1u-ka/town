import psycopg2

class DB:
  def __init__(self):
    self.conn = psycopg2.connect(('%s://%s:%s@%s:%d/%s' % ('postgresql',
                                                        'council',
                                                        'abcd',
                                                        'localhost',
                                                        5434,
                                                        'urban')))

  def do(self, stmt: str) -> list:
    curs = self.conn.cursor()
    curs.execute(stmt)
    return curs.fetchall()

db = DB()
