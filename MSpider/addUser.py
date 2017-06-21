__author__ = 'yangyudong'

import random
import string
import DBHelper
if __name__=='__main__':
    ul=[]
    dbh = DBHelper.DBHelper()
    for _ in range(80):
        user=dict()
        user['nickName']=''.join(random.choice(string.ascii_letters) for x in range(3))
        user['passwd']='123'
        user['email']=''.join(random.choice(string.digits) for x in range(9))+'@qq.com'
        user['telPhone']=''.join(random.choice(string.digits) for x in range(11))
        user['userType']=1
        ul.append(user)
    dbh.insertUsers(ul)







