__author__ = 'yangyudong'
import pynlpir
from DBHelper import *
pynlpir.open()
dbh=DBHelper()
list=dbh.selectJobs()
for e in list:
    kw=pynlpir.get_key_words(e[1])
    print('{0}:{1}'.format(e[1],kw))


# segments = pynlpir.segment(s)
# for segment in segments:
#     print(segment[0], '\t', segment[1])
